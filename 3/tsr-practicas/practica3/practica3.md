# Práctica 3: Despliegue de Servicios. Docker

## 1. Introducción
Los servicios distribuidos enfrentan problemas como:
1. **Empaquetamiento**: Asegurar instancias repetibles y aisladas.
2. **Configuración**: Adaptar los componentes al entorno de despliegue.
3. **Interrelación**: Resolver enlaces entre componentes y asegurar escalado.

Para resolverlos:
- Usaremos Docker y Docker Compose.
- Realizaremos actividades incrementales, desde despliegues manuales hasta configuraciones automatizadas con nuevos componentes y pruebas de tolerancia a fallos.

---

## 2. Sesión 1: Primeros pasos con Docker

Servicio CBW:
- Cliente(s) -> Broker -> Worker(s)
- Worker(s) -> Broker -> Cliente(s)

### 2.1 Construyendo la imagen base (`tsr-zmq`)

**Dockerfile:**
```dockerfile
FROM ubuntu:22.04
WORKDIR /root
RUN apt-get update -y
RUN apt-get install curl ufw gcc g++ make gnupg -y
RUN curl -sL https://deb.nodesource.com/setup_20.x | bash -
RUN apt-get update -y
RUN apt-get install nodejs -y
RUN apt-get upgrade -y
RUN npm init -y
RUN npm install zeromq@5
```
**Comando para construir:**
```bash
docker build -t tsr-zmq .
```

### 2.2 Despliegue de imágenes individuales
Generar imágenes `imclient`, `imbroker` e `imworker`

El código necesita las siguientes adaptaciones:

1. El identificador de clientes y trabajadores se calcula automáticamente desde su IP en lugar de obtenerse como argumento (esta adaptación ya se ha aplicado).
2. El código del cliente debe emitir 5 solicitudes antes de finalizar, lo que nos proporciona un volumen mayor de mensajes. Puedes inspirarte en el código del cliente externo, que emite 10 solicitudes.  
```javascript
// cliente.js modificado
// ...
let count = 0;
for (let i = 0; i < 5; i++) {
  req.send("C_"+require('os').hostname())
}
// ...
function procesaRespuesta(msg) {
  traza('procesaRespuesta','msg',[msg])
  count++;
  if (count == 5) adios([req], `Adios`)()
}
// ...
```

Una vez adaptado el código para que se conecte entre sí, se construyen las imágenes:
```bash
$ docker build -t imbroker broker
$ docker build -t imclient client
$ docker build -t imworker worker
```

### 2.3 Despliegue del sistema CBW
Se ha creado un archivo `docker-compose.yml` para gestionar el despliegue.

```yaml
version: '2'
services:
  cli:
    image: imclient
    links:
      - bro
    environment:
      - BROKER_HOST=bro
      - BROKER_PORT=9998
  wor:
    image: imworker
    links:
      - bro
    environment:
      - BROKER_HOST=bro
      - BROKER_PORT=9999
  bro:
    image: imbroker
    expose:
      - "9998"
      - "9999"
```

Escalar a 2 clientes y 5 trabajadores con:
```bash
docker-compose up --scale cli=2 --scale wor=5
```
**Preguntas:**
1. **¿Cuál es la dirección IP de los 7 componentes desplegados?**  

> - **Broker**: `172.18.0.2`
> - **Worker 1**: `172.18.0.8`
> - **Worker 2**: `172.18.0.9`
> - **Worker 3**: `172.18.0.4`
> - **Worker 4**: `172.18.0.6`
> - **Worker 5**: `172.18.0.7`
> - **Cliente 1**: `172.18.0.3`
> - **Cliente 2**: `172.18.0.5`

2. **¿Qué información adicional puedes observar con el plugin Docker en Visual Studio Code?**  

Da información variada sobre los contenedores. Su nombre, estado, imagen, puertos expuestos, etc.

3. **Verifica:**
   - Cada cliente recibe respuesta únicamente a sus solicitudes. ✔️
   - No hay trabajadores libres si quedan peticiones pendientes. ✔️
   - La tolerancia a fallos ✔️: ¿qué ocurre si un trabajador falla durante una petición?  
      Si un trabajador falla durante una petición (se agota el tiempo máximo de espera `2000 ms`), el `broker` asigna ese trabajo a otro `worker` disponible.

## 3. Sesión 2: Almacenamiento persistente y acceso remoto

Se pretende desarrollar un sistema para registrar la actividad de los componenetes en un archivo de manera centralizada. Para ello se desarrollará un nuevo componenete `logger` que recibirá la información de los otros y la unificará y escribirá en un fichero persistente.
Se ha elegido broker para realizar la prueba. Para implementar en el resto de componentes, las modificaciones serían idénticas.

### 3.1 Logger: Almacenamiento centralizado
Implementar un componente **logger** para el componente `broker`:
- Usa sockets `PUSH` en el broker (u otros componentes que necesiten registrar actividad) y `PULL` en el logger.
- El logger guarda las anotaciones en `/tmp/cbwlog` mediante un volumen Docker.

```javascript
// logger.js

const {zmq, lineaOrdenes, traza, error, adios, creaPuntoConexion} = require('../tsr')
lineaOrdenes("loggerPort filename")
// logger in NodeJS
// First argument is port number for incoming messages
// Second argument is file path for appending log entries

const fs = require('fs');
let log = zmq.socket('pull')

creaPuntoConexion(log, loggerPort)
log.on('message', (text) => {fs.appendFileSync(filename, text+'\n')})
```

### 3.2 Despliegue del logger
- **Fragmentos de código en el `broker`:**
```javascript
// Obtiene dirección y puerto del logger por línea de comandos
lineaOrdenes("frontendPort backendPort loggerHost loggerPort")

// Crea y conecta el socket push para enviar información al logger
let slogger = zmq.socket('push');
conecta(slogger, loggerHost, loggerPort);

// Por cada llamada a traza, se deberá enviar la misma información al logger

// Mensaje en frontendPort
slogger.send(`frontend: cl=${client}, msg=${message}`)

// Mensaje en backend
slogger.send(`backend: wk=${worker}, cl=${client}, msg=${message}`)
```
- **Nuevo Dockerfile de broker para que la conexión con logger sea correcta**

```Dockerfile
FROM tsr-zmq
COPY ./tsr.js tsr.js
RUN mkdir broker
WORKDIR broker
COPY ./brokerl.js mybroker.js
EXPOSE 9998 9999
CMD node mybroker 9998 9999 $LOGGER_HOST $LOGGER_PORT
```

- **Configura `docker-compose.yml` para incluir el `logger` y conectar con `broker`:**
```yaml
bro:
  image: brokerl
  build: ./broker/
  expose:
    - "9998"
    - "9999"
  environment:
    - LOGGER_HOST=log
    - LOGGER_PORT=9995
log:
  image: logger
  build: ./logger/
  expose:
    - "9995"
  volumes:
    - /tmp/logger.log:/tmp/cbwlog
  environment:
    - LOGGER_DIR=/tmp/cbwlog
```

- **Dockerfile de `logger`**

```Dockerfile
FROM tsr-zmq
COPY ./tsr.js tsr.js
RUN mkdir logger
WORKDIR logger
COPY ./logger.js mylogger.js
VOLUME /tmp/cbwlog
EXPOSE 9995
CMD node mylogger 9995 $LOGGER_DIR/logs
```

### 3.3 Despliegue del nuevo servicio CBWL

Desplegamos el servicio con el comando

```bash
$ docker compose up -d --scale cli=4 --scale wor=2
```

Y comprobamos que se crea el fichero `/tmp/logger.log/logs` en el host y registra la actividad como se esperaba.

**Preguntas**  
1. **Detalla los pasos necesarios para cambiar la ubicación del fichero de log**

Únicamente habría que modificar la ruta del volumen en el achivo `docker-compose.yml`

```yaml
log:
  image: logger
  build: ./logger/
  expose:
    - "9995"
  volumes:
    - /NUEVA/RUTA:/tmp/cbwlog
  environment:
    - LOGGER_DIR=/tmp/cbwlog
```

2. **¿Puedes modificar la función traza de `tsr`, empleada por `broker`, para incorporar el envío de mensajes al logger?**

Sería necesario modificar la función `traza` de la biblioteca `tsr` de `broker` y añadir el socket del logger como argumento.

```javascript
function traza(f,names,value, slogger) { //muestra los argumentos al invocar la función f
  let mensaje_log = ''
  console.log(`funcion ${f}`)
  mensaje_log += `funcion ${f}\n`
  var args = names.split(" ")
  for (let a in args){
    console.log(`\t${args[a]}\t|${value[a]}|`)
    mensaje_log += `\t${args[a]}\t|${value[a]}|\n`
  }
  if (slogger) slogger.send(mensaje_log)
}
```

3. **Reflexiona, sin necesidad de ejecutar, qué ocurriría si intentáramos desplegarlo en los siguientes escenarios:**
- 2 clientes, 1 trabajador, 2 brokers, 1 logger

Debido a la existencia de la opcion `link` en el archivo `docker-compose.yml`, docker no balanceará la carga entre los broker y todos los clientes se conectarán al primer broker que arranque. Por lo tanto, el programa funcionaría de la misma manera que con un broker, solo que el segundo broker no recibirá ningún mensaje.

Si por el contrario se suprime la opción `link`, docker balanceará la carga y en este caso, cada cliente se conectará a un broker. Debido a que solo existe un worker, el cliente conectado al broker sin worker asociado permanecerá esperando la respuesta sin éxito.

- 2 clientes, 1 trabajador, 1 broker, 2 loggers

En este caso, puesto que solo existe un broker, independientemente de si se balancea la carga o no, solo uno de los loggers recibirá mensajes del broker.

---

## 4. Sesión 3: Despliegue de un servicio prefabricado
> [!CAUTION]
> Sesión fuera de la planificación de la asignatura.
