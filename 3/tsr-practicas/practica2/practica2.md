# Práctica 2. 0MQ
## 1. Introducción
### 1.1 Objetivos
- Afianzar conceptos teóricos (tema 3).
- Experimentar con patrones de diseño y sockets.
- Profundizar en el patrón broker.

### 1.2 Organización del Tiempo
- **Sesión 1**: Prueba de patrones básicos y chat.
- **Sesión 2**: Publicador rotatorio, broker y estadísticas.
- **Sesión 3**: Broker para clientes + workers.
- **Sesión 4**: Broker tolerante a fallos.

### 1.3 Método de Trabajo
- Ejecutar componentes en una misma máquina (localhost) o distribuidos.
- No modificar el código ni la estructura de directorios.
- Utilizar `refZMQ.pdf` como referencia.

## 2. Tareas

### 2.1 Prueba de Patrones Básicos y Chat
#### 2.1.1 Biblioteca `tsr.js`
Define funciones para facilitar la prueba de patrones.
- `error(msg)` Muestra el mensaje de error y finaliza la ejecución del programa
- `lineaOrdenes(params)` Comprueba que la línea de órdenes contiene los parámetros indicados, y crea las variables correspondientes
- `traza(f,nombres,valores)` Muestra el valor de los argumentos cuando se invoca la función f
- `adios(sockets,despedida)` Cierra los sockets indicados. muestra el mensaje de despedida, y finaliza el programa
- `creaPuntoConexion(socket,port)` Aplica la operación bind sobre el port indicado, y verifica el resultado
- `conecta(socket,ip,port)` Aplica la operación connect sobre `tcp://ip:port`

#### 2.1.2 Patrón Cliente/Servidor (`req-rep`)

1. **Un cliente y un servidor**

Ejecución:

```bash
# Terminal 1
$ node req-rep/servidor.js A 9990 2 Hola

# Terminal 2
$ node req-rep/cliente1.js localhost 9990 Pepe
```
Preguntas:
- **¿Qué pasa con argumentos incorrectos o fuera de orden?**

El programa arroja un error. Si el número de argumentos es incorrecto, se indica por línea de órdenes cuál es el uso correcto del programa. Si están fuera de orden, arrojará un error de argumento(s) inválido(s).

- **¿Afecta el orden de arranque?**

No, no afecta, debido a la existencia de colas de entrada y salida (persistencia débil, en RAM).

- **¿Cómo construye y accede a mensajes multi-segmento?**

En este caso el cliente construye los mensajes multi-segmento usando un array, con `[mensaje, i]`, donde `mensaje` es texto y `i` es el número de segmento.

- **¿Cuándo termina el servidor?**

En este caso, el servidor permanece activo escuchando peticiones hasta que el usuario lo cierra manualmente (`CTRL + C`).

2. **Un cliente y dos servidores**

```bash
# Terminal 1
$ node req-rep/servidor.js A 9990 2 Hola

# Terminal 2
$ node req-rep/servidor.js B 9991 2 Hello

# Terminal 3
$ node req-rep/cliente2.js localhost 9990 localhost 9991 Pepe
```

Preguntas:
- **¿Afecta el orden de ejecución al resultado?**

El orden de arranque no afecta al resultado debido a la persistencia de mensajes (colas de entrada y salida) y a que los mensajes se envían en un orden concreto (cada mensaje se envía primero al Servidor `A` y luego al `B`) y debido al patrón de comunicación sincrónico, las respuestas se procesan en orden de envío.

- **¿Qué ocurre si dos servidores reciben el mismo número de puerto?**

Si dos servidores (que se lanzan en la misma máquina) reciben el mismo número de puerto, se arrojará un error de `EADDRINUSE` (Address already in use), ya que el puerto ya está en uso.

- **¿Qué ocurre si los dos servidores reciben un valor de segundos distinto (ej 1 y 3 respectivamente)?. ¿Afecta al orden en que se responde al cliente?**

Si los dos servidores reciben un valor de segundos distinto, el servidor que recibe el valor más bajo tardará menos en responder al cliente, y el servidor que recibe el valor más alto, tardará más. No obstante, el orden de respuesta al cliente no se verá afectado, ya que el cliente espera a recibir las respuestas en el mismo orden en que envió los mensajes, debido al patrón de comunicación síncrono.

- **La práctica totalidad del tiempo lo consumen los servidores ¿Conseguimos reducir a la mitad el tiempo de ejecución del cliente al utilizar 2 servidores?**

No, no se consigue reducir a la mitad el tiempo de ejecución del cliente al utilizar 2 servidores, ya que el cliente envía los mensajes y recibe sus respuestas de forma secuencial. El tiempo de ejecución del cliente depende únicamente del tiempo de respuesta del/los servidores, y no de la cantidad de estos que se utilicen.

- **Si queremos usar 3 servidores, ¿hay que modificar el código del cliente?**

Únicamente habría que modificar la cantidad de argumentos que recibe el cliente, para que se le pueda indicar la dirección y puerto del tercer servidor. El envío de peticiones funcionaría igualmente, repartiendo las peticiones siguiendo el criterio *Round Robin* (debido a que hay un `req` conectado a múltiples sockets `rep`).

- **Con un número de peticiones par, ¿podemos garantizar que cada servidor atiende la misma cantidad de peticiones?**

En el supuesto de que el número de servidores a los que se conecta el socket `req` sea dos, puede garantizarse que para un número de mensajes par, cada servidor atenderá el mismo número de peticiones, debido al criterio de *Round Robin* que sigue el socket `req` para enviar mensajes a los sockets `rep` conectados.

#### 2.1.3 Patrón Pipeline (`push-pull`)

1. Origen 1 -> Destino (`A->B`)

Ejecución:
```bash
# Terminal 1
$ node push-pull/origen1.js A localhost 9000

# Terminal 2
$ node push-pull/destino.js B 9000
```
Preguntas:
- **¿Afecta el orden de arranque?**

No, no afecta, debido a la existencia de colas de entrada y salida (persistencia débil, en RAM).

- **¿Por qué `origen.js` no usa `socket.on(‘message’...)`?**

Porque en el patrón `push-pull`, el emisor del mensaje no espera respuesta, por lo que no necesita un callback para recibir mensajes.

2. Origen 1 -> Filtro -> Destino (`A->B->C`)

Ejecución:
```bash
# Terminal 1
$ node push-pull/origen1.js A localhost 9000

# Terminal 2
$ node push-pull/filtro.js B 9000 localhost 9001 2

# Terminal 3
$ node push-pull/destino.js C 9001
```
Preguntas:

- **Compueba si el orden de arranque afecta al resultado.**

El orden de arranque no afecta al resultado, debido a la persistencia de mensajes (colas de entrada y salida) y a que los mensajes se envían en un orden concreto (cada mensaje se envía primero al Filtro y luego al Destino) y debido al patrón de comunicación síncrono.

- **Indica la razón por la que `origen1.js` y `destino.js` definen un único socket cada uno, pero `filtro.js` define dos.**

`origen1.js` y `destino.js` definen un único socket cada uno porque son los extremos de la cadena de comunicación, mientras que `filtro.js` define dos sockets porque es el intermediario entre `origen1.js` y `destino.js`, y necesita un socket para recibir mensajes de `origen1.js` y otro para enviar mensajes a `destino.js`.

- **Si `origen1.js` genera 4 mensajes y filtro retrasa dos segundos, ¿cuánto tardará el último mensaje de origen en llegar al destino?**

Si `origen1.js` genera 4 mensajes y el filtro retrasa dos segundos, los mensajes llegarán al filtro de manera simultánea. Este escucha las peticiones de forma asíncrona, por lo que comenzará a procesar los mensajes en el momento que estos lleguen. Como tarda dos segundos en procesar estas peticiones y reenviarlas al destino, el cuarto mensaje tardará poco más de dos segundos en llegar al destino.

3. Origen 2 -> (Filtro, Filtro) -> Destino (`A->(B,C)->D`)

Ejecución:
```bash
# Terminal 1
$ node push-pull/origen2.js A localhost 9000 localhost 9001

# Terminal 2
$ node push-pull/filtro.js B 9000 localhost 9002 2

# Terminal 3
$ node push-pull/filtro.js C 9001 localhost 9002 3

# Terminal 4
$ node push-pull/destino.js D 9002
```

Preguntas:
- **Comprueba si el orden de arranque afecta al resultado.**

En este caso, el orden de arranque puede llegar a alterar del orden de llegada al destino, en función de cuándo y en qué orden se arranquen los filtros una vez arrancado el origen (mensajes enviados).

- **¿Cómo se reparte la entrega de mensajes a los filtros B y C?**

Al igual que en el caso anterior (patrón `req-res`), los mensajes se reparten de forma equitativa entre los filtros B y C, siguiendo el criterio *Round Robin*.

- **¿Pueden trabajar B y C en paralelo (ej. si se ejecutasen en máquinas distintas)?**

Sí, B y C pueden trabajar en paralelo, ya que son dos procesos independientes que reciben mensajes de manera asíncrona y los procesan de forma independiente.

- **¿En qué orden llegan los mensajes a destino? ¿Cómo afectaría al comportamiento modificar los segundos del filtro C?**

Si se ejecutan los filtros y el destino previamente al envío de los mensajes, debido a los retrasos establecidos, los mensajes llegan en orden `[1,3,2,4]`, debido a que el filtro C tiene 3 segundos de retraso, frente a los 2 segundos del filtro B. Debido a que los filtros procesan las respuestas de forma asíncrona, y que las cuatro llegan, de manera aproxiamda, de forma simultánea, el filtro C tardará más en procesar los mensajes, y por tanto, el mensaje 3 llegará antes que el mensaje 2.


Si se modificaran los segundos de retraso del filtro C, puede afectar al orden de las siguientes maneras:

> - Si el filtro C tardara menos de 2 segundos en procesar los mensajes, el mensaje 2 llegaría antes que el mensaje 3, siendo el orden`[2,4,1,3]`.
> - Si el filtro C tardara más de 2 segundos en procesar los mensajes, el mensaje 3 llegaría después que el mensaje 2, siendo el orden`[1,3,2,4]`.
> - Si el filtro C tardara exactamente 2 segundos en procesar los mensajes, el orden de llegada estaría indeterminado y dependería de cómo los sistemas operativos de los filtros procesaran los mensajes.

#### 2.1.4 Patrón Difusión (`pub-sub`)
Ejecución:
```bash
# Terminal 1
$ node pub-sub/publicador.js 9990 Economia Deportes Cultura

# Terminal 2
$ node pub-sub/suscriptor.js A localhost 9990 Economia

# Terminal 3
$ node pub-sub/suscriptor.js B localhost 9990 Deportes

# Terminal 4
$ node pub-sub/suscriptor.js C localhost 9990 Economia
```
Preguntas:
- **¿Qué pasa con el orden de arranque?**

En este caso, el orden de arranque sí afecta. Los suscriptores deben estar activos (y suscritos) antes de que el publicador envíe mensajes, ya que si no, no recibirán los mensajes.

- **¿Qué ocurre con los mensajes de `Cultura`?**

Ningún suscriptor recibe mensajes de `Cultura`, debido a que no hay ningún proceso escuchando mensajes de ese tema. No obstante, los mensajes siguen siendo publicados.

- **¿Pueden recibir el mismo mensaje múltiples suscriptores?**

Sí, todos los suscriptores que estén correctamente conectados y suscritos a un publicador, reciben los mismos mensajes del tema correspondiente.

- **¿Cómo se puede cambiar la cantidad de mensajes que genera el publicador?**

Habría que cambiar la línea
```javascript
if (i==10) adios([pub],"No me queda nada que publicar. Adios")()
```
y modificar la condición `i==10` por la cantidad de mensajes a enviar.

- **Los suscriptores no terminan. Piensa en una modificación para que terminen tras un tiempo determinado sin recibir mensajes**

Se podría añadir un temporizador en los suscriptores que, tras un tiempo determinado sin recibir mensajes, cierre la conexión con el publicador y termine el proceso.

El código podría ser algo así:
```javascript
const { zmq, lineaOrdenes, traza, error, adios, conecta } = require('../tsr');
lineaOrdenes("identidad ipPublicador portPublicador tema");

let sub = zmq.socket('sub');
sub.subscribe(tema);
conecta(sub, ipPublicador, portPublicador);

const TIMEOUT_INACTIVIDAD = 5000;
let timeoutInactividad;

function iniciaTimeout() {
    // Si ya hay un temporizador activo, lo limpiamos antes de iniciar uno nuevo
    if (timeoutInactividad) {
        clearTimeout(timeoutInactividad);
    }

    // Creamos un nuevo temporizador de inactividad
    timeoutInactividad = setTimeout(() => {
        console.log("No se ha recibido un mensaje en los últimos 5 segundos.");
        // Aquí puedes agregar cualquier otra acción que desees ejecutar al finalizar el tiempo de espera
    }, TIMEOUT_INACTIVIDAD);
}

function recibeMensaje(tema, numero, ronda) {
    traza('recibeMensaje', 'tema numero ronda', [tema, numero, ronda]);
    // Reinicia el temporizador cada vez que llega un nuevo mensaje
    iniciaTimeout();
}

// Iniciar el primer timeout al arrancar
iniciaTimeout();

sub.on('message', recibeMensaje);
sub.on('error', (msg) => { error(`${msg}`); });
process.on('SIGINT', adios([sub], "abortado con CTRL-C"));
```
- **Es posible que el publicador genere algún mensaje cuando todavía no ha procesado las conexiones de los suscriptores. ¿Qué pasa con esos mensajes?**

Si el publicador genera mensajes antes de que los suscriptores estén conectados, los mensajes se pierden, ya que los suscriptores no están escuchando en ese momento. No obstante, una vez los suscriptores se conectan, comienzan a recibir los mensajes que el publicador envía.

### 2.2 Prueba de Aplicación Chat (`chat`)
```bash
# Terminal 1
$ node chat/servidorChat.js 9000 9001

# Terminal 2
$ node chat/clienteChat.js Pepe localhost 9000 9001

# Terminal 3
$ node chat/clienteChat.js Ana localhost 9000 9001
```

Preguntas:
- **¿Afecta el orden de arranque?**

En este caso, el orden de arranque no afecta. No obstante para que los mensajes se envíen y reciban correctamente, el servidor debe estar activo y los clientes conectados.

- **¿Por qué se crean ambos puntos de conexión en el servidor?**

Debido a que muchos clientes pueden conectarse al mismo servidor de chat, la conexión se realiza con el servidor para centralizar la comunicación. De esta forma los clientes no deben conocer sus direcciones previamente intercambiar mensajes, únicamente tendrán que conocer la del servidor.

- **¿Por qué el servidor no mantiene una lista de clientes conectados?**

Tal y como está estructurado el chat no es necesario que el servidor mantenga una lista de clientes. Simplemente se limita a escuchar mensajes que recibe (con `pull`) y a reenviarlos a todos los clientes conectados, `pub`. De manera análoga, los clientes envían con `push` y reciben con `sub`.

### 2.3 Publicador Rotatorio (`pub-sub`)
Desarrollar `publicadorRotatorio.js` que envíe mensajes por temas de forma rotatoria

que se invoque como:
```bash
$ node pub-sub/publicador.js port numMensajes tema1 tema2 tema3 ...
```
donde:

> - port = el port al que deben conectarse los suscriptores (el host es localhost)
> - numMensajes = número de mensajes a emitir, tras lo cual el publicador terminan
> - tema1 tema2 tema3 ... = número variable de temas (a priori no sabemos cuántos)

Debe generar un mensaje por segundo con la estructura [tema, numMensaje, numRonda]


Código `publicadorRotatorio.js`:
```javascript
const {zmq, error, traza, adios, creaPuntoConexion} = require('../tsr')

// En vez de usar lineaOrdenes, usamos args para que pueda haber un número variable de temas
if (process.argv.length < 5) {
  console.error('Uso: node publicadorRotatorio.js puerto numMensajes tema1 ...')
}

let port = process.argv[2]
let numMensajes = process.argv[3]

// Número variable de temas
let temas = []
for (i = 4; i < process.argv.length; i++) {
  temas.push(process.argv[i])
}

var pub = zmq.socket('pub')
creaPuntoConexion(pub, port)

function envia(tema, numMensaje, ronda) {
  traza('envia','tema numMensaje ronda',[tema, numMensaje, ronda])
  pub.send([tema, numMensaje, ronda])
}

function publica(i) {
  return () => {
    t = temas[0]
    envia(t, i + 1, Math.trunc(i/temas.length) + 1)

    // Rota los temas
    temas.shift(); temas.push(t);

    if (i==numMensajes) adios([pub],"No me queda nada que publicar. Adios")()
      else setTimeout(publica(i+1),1000)
  }
}

setTimeout(publica(0), 1000)

pub.on('error', (msg) => {error(`${msg}`)})
process.on('SIGINT', adios([pub],"abortado con CTRL-C"))
```

### 2.4 Tareas sobre el Patrón Broker/Workers (`broker-workers`)

#### 2.4.1 Prueba del Patrón Broker/Workers

Se pretende analizar el código de `broker-workers/brokerRouterRouter.js`, `broker-workers/workerReq.js` y `broker-workers/cliente.js` y responder a las preguntas.

Ejecución:
```bash
# Terminal 1
$ node broker-workers/brokerRouterRouter.js 9000 9001

# Terminal 2
$ node broker-workers/workerReq.js w1 localhost 9001

# Terminal 3
$ node broker-workers/workerReq.js w2 localhost 9001

# Terminal 4
$ node broker-workers/cliente.js A localhost 9000

# Terminal 5
$ node broker-workers/cliente.js B localhost 9000
```

Preguntas:

- **¿Cómo afecta al resultado cambiar el orden de arranque de los workers (terminales 2 y 3?. ¿Y de los clientes (terminales 4,5)?**

El orden de arranque de los workers afecta al reparto de las peticiones. Puesto que el broker envía los mensajes con el criterio Round Robin, el primer worker en conectarse (arrancar), será el que atienda la primera petición. Por lo que dependiendo del order de arranque de los workers, el mensaje de cada cliente llegará a un worker distinto.

De la misma manera ocurre con los clientes. El mensaje del primero en ejecutarse, será atendido por el primer worker que se haya conectado al broker, independientemente de su identificador (nombre, ej: `w1` o `w2`).

- **¿Qué pasa si arrancamos el broker al final (el 1) pasa al 5))**

Si el broker se ejecuta en último lugar, quedará indeterminado el orden de conexión, por lo que no se podrá predecir qué worker recibirá qué mensaje. Al igual que en patrones de comunicación similares, debido a la persistencia en colas de entrada/salida, aunque el broker se ejecute en último lugar, los mensajes se siguen enviando y recibiendo correctamente.

#### 2.4.2 Estadísticas Broker
Modificar `brokerRouterRouter.js` para llevar un registro de:
- Total de peticiones atendidas.
- Número de peticiones atendidas por cada worker (mostrar cada 5 segundos).

El ejercicio se ha resuelto en `broker-workers/brokerEstadisticas.js`.  
Cuyo contenido queda de la siguiente manera:
```javascript
const {zmq, lineaOrdenes, traza, error, adios, creaPuntoConexion} = require('../tsr')
lineaOrdenes("frontendPort backendPort")

let workers  =[] // workers disponibles
let pendiente=[] // peticiones no enviadas a ningun worker

let stats = {}

let frontend = zmq.socket('router')
let backend  = zmq.socket('router')

creaPuntoConexion(frontend, frontendPort)
creaPuntoConexion(backend,  backendPort)

function procesaPeticion(cliente,sep,msg) { // llega peticion desde cliente
  traza('procesaPeticion','cliente sep msg',[cliente,sep,msg])
  if (workers.length) backend.send([workers.shift(),'',cliente,'',msg])
  else pendiente.push([cliente,msg])
}

function procesaMsgWorker(worker,sep1,cliente,sep2,resp) {
  traza('procesaMsgWorker','worker sep1 cliente sep2 resp',[worker,sep1,cliente,sep2,resp])

  if (stats[worker] != null) stats[worker]++
  else stats[worker] = 0

  if (pendiente.length) { // hay trabajos pendientes. Le pasamos el mas antiguo al worker
    let [c,m] = pendiente.shift()
    backend.send([worker,'',c,'',m])
  }
  else workers.push(worker) // añadimos al worker como disponible
  if (cliente) frontend.send([cliente,'',resp]) // habia un cliente esperando esa respuesta
}

function info() {
  console.log("********* INFO STATS *********")

  let totalReq = Object.keys(stats).reduce((acc, k) => {
    console.log(k + ": " + stats[k])
    return acc + stats[k]
  }, 0)

  console.log("TOTAL PETICIONES ATENDIDAS: " + totalReq)

  console.log("******************************")
  setTimeout(info, 5000)
}

info()

frontend.on('message', procesaPeticion)
frontend.on('error'  , (msg) => {error(`${msg}`)})
backend.on('message', procesaMsgWorker)
backend.on('error'  , (msg) => {error(`${msg}`)})
process.on('SIGINT' , adios([frontend, backend],"abortado con CTRL-C"))
```


Preguntas:
- **Indica una estrategia para mantener en el broker estadísticas separadas para cada worker**

En este caso se ha optado por un objeto en el que cada clave representa a un worker, y su valor es el número de peticiones atendidas por este worker, a modo de diccionario.

- **Indica cómo conseguir que se ejecute una acción de forma repetida (periódica)**

Con un `setTimeout`. Cuando la función acaba, vuelve a lanzar el `Timeout` con ella misma como argumento.

- **Si llega una petición y se la pasamos al worker w, ¿debemos incrementar el número de peticiones atendidas por w (y el total) en ese momento, o cuando llegue la respuesta desde w?**

Lo que más sentido tiene es aumentar los contadores al recibir la respuesta por parte del worker, pues es en ese momento cuando la petición se considera atendida.

Debido a que al establecer la conexión, el worker (socket `req`) envía un mensaje al broker, la primera recepción inicializa el contador a `0`, registrando la entrada del worker en el diccionario y preparandolo para empezar a sumar por cada petición atendida.

#### 2.4.3 Broker para Clientes + Broker para Workers
Crear dos brokers(`broker1.js` y `broker2.js`) interconectados, que mantengan las mismas características externas (o sea, frente a clientes y frente a workers) que el código original:  

- `Broker 1` maneja a los clientes y mantiene la cola de peticiones.
- `Broker 2` gestiona los workers y distribuye la carga
- Todo cliente envía las peticiones a `Broker 1`, que la guarda en la cola de peticiones pendientes o la pasa a `Broker 2`
- Cuando llega una petición a `Broker 2`, éste la envía al worker correspondiente
- La respuesta del worker llega a `Broker 2`, que la pasa a `Broker 1`, y éste al cliente
- El alta de un worker llega a `Broker 2` (y si se considera necesario se puede informar a `Broker 1`)

Una posible solución sería la siguiente:

**Estructura:**

- Petición: Cliente (Req) -> (Router) Broker 1 (Dealer) -> (Rep) Broker 2 (Router) -> \[RR\] (Req) Worker
- Respuesta: Worker (Req) -> (Router) Broker 2 (Rep) -> (Dealer) Broker 1 (Router) -> (Req) Cliente

**Código:**
```javascript

// broker1.js

const {zmq, lineaOrdenes, traza, error, adios, creaPuntoConexion} = require('../tsr')
lineaOrdenes("frontendPort outPort")

let frontend = zmq.socket('router')
let broker2  = zmq.socket('dealer')

creaPuntoConexion(frontend, frontendPort)
creaPuntoConexion(broker2, outPort)

function procesaPeticion(cliente,sep,msg) { // llega peticion desde cliente
  traza('procesaPeticion','cliente sep msg',[cliente,sep,msg])
  broker2.send(['',cliente,'',msg])
}

function procesaMsgBroker(sep1,cliente,sep2,resp) {
  traza('procesaMsgWorker','sep1 cliente sep2 resp',[sep1,cliente,sep2,resp])
  if (cliente) frontend.send([cliente,'',resp]) // habia un cliente esperando esa respuesta
}

frontend.on('message', procesaPeticion)
frontend.on('error'  , (msg) => {error(`${msg}`)})
broker2.on('message', procesaMsgBroker)
broker2.on('error'  , (msg) => {error(`${msg}`)})
process.on('SIGINT' , adios([frontend, broker2],"abortado con CTRL-C"))


// broker2.js

const {zmq, lineaOrdenes, traza, error, adios, creaPuntoConexion, conecta} = require('../tsr')
lineaOrdenes("backendPort broker1Host broker1Port")

let workers  =[] // workers disponibles
let pendiente=[] // peticiones no enviadas a ningun worker

let backend  = zmq.socket('router')
let broker1 = zmq.socket('rep')

creaPuntoConexion(backend, backendPort)
conecta(broker1, broker1Host, broker1Port)

function procesaPeticion(cliente,sep,msg) { // llega peticion desde cliente
  traza('procesaPeticion','cliente sep msg',[cliente,sep,msg])
  if (workers.length) backend.send([workers.shift(),'',cliente,'',msg])
  else pendiente.push([cliente,msg])
}

function procesaMsgWorker(worker,sep1,cliente,sep2,resp) {
  traza('procesaMsgWorker','worker sep1 cliente sep2 resp',[worker,sep1,cliente,sep2,resp])
  if (pendiente.length) { // hay trabajos pendientes. Le pasamos el mas antiguo al worker
    let [c,m] = pendiente.shift()
    backend.send([worker,'',c,'',m])
  }
  else workers.push(worker) // añadimos al worker como disponible
  if (cliente != '') {
    traza('prueba', 'cliente resp', [cliente,resp])
    broker1.send([cliente,'',resp]) // habia un cliente esperando esa respuesta
  }
}

broker1.on('message', procesaPeticion)
broker1.on('error'  , (msg) => {error(`${msg}`)})
backend.on('message', procesaMsgWorker)
backend.on('error'  , (msg) => {error(`${msg}`)})
process.on('SIGINT' , adios([broker1, backend],"abortado con CTRL-C"))
```

#### 2.4.4 Broker Tolerante a Fallos de Workers (`brokerToleranteFallos`)
Desarrollar un broker que sea tolerante a fallos de workers. Si un worker falla, el broker debe seguir funcionando y reenviar las peticiones a los workers que sigan activos.

Para ello, se prueba primero a generar una situación de fallo:
```bash
# Terminal 1
$ node broker-workers/brokerRouterRouter.js 9000 9001

# Terminal 2
$ node broker-workers/workerReq.js w1 localhost 9001

# Terminal 3
$ node broker-workers/workerReq.js w2 localhost 9001

# Terminal 4
$ node broker-workers/workerReq.js w3 localhost 9001

# Terminal 2
$ CTRL + C

# Terminal 5
$ node broker-workers/cliente.js A localhost 9000 & node broker-workers/cliente.js B localhost 9000 & node broker-workers/cliente.js C localhost 9000
```

Preguntas:
- **¿Cuantas respuestas se obtienen? Indica qué trabajadores las han enviado**

A persar de que se envían tres peticiones, solo se reciben dos respuestas, de los workers `w2` y `w3`, que se corresponde con los dos workers que no han fallado.

- **¿Quedan clientes esperando?**

Sí, el primer cliente que hace la petición (y al que le es asignado `w1`) queda esperando una respuesta que no llega, ya que el worker `w1` ha fallado. El broker no es capaz de detectar que `w1` ha fallado.

Ahora se repite la prueba sustituyendo la orden del primer terminal por:
```bash
$ node brokerToleranteFallos/broker.js 9000 9001
```

Preguntas:
- **¿Quedan clientes esperando?**

En este caso, no queda ningún cliente esperando. El cliente al que le es asignado el broker que falla, queda esperando (`ans_time`, en este caso 2 segundos) hasta que el broker le asigna otro worker disponible para responder.

- **¿El cierre (caída) del worker, es transparente para el cliente?**

Sí, lo es. El cliente no es consciente de que el worker ha fallado, ya que el broker se encarga de reasignar la petición a otro worker disponible.

- **Únicamente se abordan posibles fallos de workers. Indica si se puede aplicar alguna estrategia ante un posible fallo del broker.**

Podría tenerse algún sistema que compruebe periódicamente si el broker está activo, y que sea capaz de reactivarlo o sustituirlo.
