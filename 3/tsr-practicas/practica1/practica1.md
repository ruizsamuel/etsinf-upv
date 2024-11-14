# Práctica 1. Cuestiones

## Sesión 1. Ejecucuón de programas en JavaScript

### 1. Hola Mundo en JavaScript

**Observa el uso de la directiva `use strict` al comienzo del programa.**  
La directiva `"use strict"` en JavaScript activa el modo estricto, el cual previene ciertos errores comunes, como el uso de variables sin declarar. Esto mejora la seguridad del código y ayuda a prevenir errores silenciosos.

**Observa que no hay función `main`.**  
En JavaScript, no es necesario definir una función principal como `main`. El código se ejecuta secuencialmente de arriba hacia abajo sin necesidad de definir un punto de entrada explícito.

**Diferencias entre declarar y ejecutar funciones.**  
Declarar una función crea el bloque de código, pero no lo ejecuta. La ejecución ocurre cuando se invoca explícitamente la función con paréntesis.

### 2. Ámbito de las variables

**Cuestión 1a. ¿Podemos modificar otras variables globales?**  
Sí, en JavaScript podemos modificar variables globales declaradas con `let` o `var` desde cualquier función, siempre que no estén en un bloque con ámbito local.

**Cuestión 1b. Diferencia entre `let` y `var`.**  
- `let`: Tiene un ámbito de bloque, lo que significa que sólo existe dentro del bloque en el que se define.
- `var`: Tiene un ámbito de función, es decir, se puede acceder a la variable fuera de los bloques internos si está declarada en la misma función.

**Cuestión 2. ¿Podemos imprimir el valor de `let_i`?**  
No, ya que `let` tiene un ámbito de bloque y sólo es accesible dentro del bucle en el que se declaró. En cambio, `var` es accesible fuera del bucle.

**Cuestión 3. Directiva `use strict`.**  
Al añadir `"use strict"`, el código arrojará errores si hay variables no declaradas. Es útil para evitar errores de variables globales implícitas.

**Cuestión 4. Corrección con `use strict`.**  
Usar `use strict` nos obliga a declarar correctamente las variables (por ejemplo, corrigiendo `local_let` a `local1_let`). Esto ayuda a evitar errores de tipado y referencias incorrectas.

### 3. Tipos de variables

**Cuestión 1. Diferencia en el comportamiento de la resta y la suma.**  
La suma en JavaScript tiende a la concatenación cuando se trata de strings, mientras que la resta intenta convertir los operandos a números.

**Cuestión 2. TypeScript.**  
TypeScript añade tipado estático a JavaScript, lo que ayuda a evitar errores comunes al manejar tipos de datos dinámicos. Esto hace que los proyectos sean más mantenibles, especialmente en grandes equipos.

### 4. Argumentos de funciones

**Cuestión 1. Pseudo-vector `arguments`.**  
El objeto `arguments` permite acceder a todos los argumentos pasados a una función, independientemente de si la función los declara formalmente.

**Cuestión 2. ¿Qué significa `NaN`?**  
`NaN` significa "Not a Number". Aparece en este caso porque la función esperaba tres argumentos, pero sólo se proporcionó uno en `result3`.

**Cuestión 3. Tres puntos suspensivos.**  
El operador `...` es el operador *spread*, que permite descomponer arrays o colecciones en elementos individuales.

**Cuestión 4. Resultado `resultv2`.**  
El resultado es `1,2,3,4undefinedundefined` porque se pasa un array completo como un solo argumento, y los otros dos argumentos son `undefined`.

### 5. Clases en JavaScript

**Observciones**  
- El uso de `this` es similar a otros lenguajes como Java, donde `this` hace referencia a la instancia actual de la clase.
- Los atributos de las clases no son obligatorios, y se pueden añadir dinámicamente durante la ejecución.

### 6. Clausuras

**Cuestión 1. Por qué es interesante el uso de clausuras en software.**  
Las clausuras permiten encapsular el estado y evitar la contaminación del espacio de nombres global, lo que es crucial cuando se desarrollan bibliotecas o scripts que se ejecutarán en entornos compartidos como navegadores o Node.js.

**Cuestión 2. Clausura retornada en el programa `ej6-3`.**  
La clausura incluye la variable local `x`, el argumento `y` y la función `inner`. Estos valores son persistentes entre ejecuciones de la función.

**Cuestión 3. Clausura de las funciones `g0` y `g1`.**  
Ambas funciones contienen la variable `traza`, pero modifican su valor de forma diferente. Sólo hay una copia de `traza`, compartida por ambas funciones, debido al alcance léxico.

### 7. Programación asíncrona mediante callbacks

**Cuestión 1. ¿Por qué vemos `cinco` antes que `cuatro` en `ej7-1`?**  
El mensaje `cinco` se imprime antes porque los `setTimeout` se ejecutan de forma asíncrona, lo que significa que el código principal continúa mientras los temporizadores esperan.

**Cuestión 2. ¿Por qué `dos` antes que `tres` en `ej7-1`?**  
Esto puede variar dependiendo de la cantidad de trabajo en el bucle, ya que la ejecución asíncrona no garantiza el orden exacto, sólo asegura que se ejecutarán después del tiempo especificado.

**Cuestión 3. ¿Por qué imprime siempre `10` en `ej7-2`?**  
Esto se debe al uso de `var`, que tiene un alcance de función. Todos los `setTimeout` acceden al mismo valor final de `i`.

**Cuestión 4. ¿Ventaja de las clausuras en `ej7-3`?**  
Las clausuras permiten capturar el valor de `i` en cada iteración, evitando el comportamiento visto en `ej7-2` donde el valor de `i` es el mismo en todas las invocaciones.

**Cuestión 5. Modificación en `ej7-4` usando `var`.**  
Si usamos `var` en lugar de `let`, el valor de `k` no será capturado correctamente debido al ámbito de `var`, lo que causará que todas las funciones `setTimeout` usen el mismo valor final de `k`.

**Cuestión 6. Callback en `ej7-5`.**  
La función de callback proporcionada se ejecutará una vez que todas las clausuras hayan finalizado sus respectivas operaciones asíncronas, desde dentro de la última función que se ejecuta en el `setTimeout`.

## Sesión 2. Módulos de NodeJS

### 1. Módulo FS: Acceso a ficheros

**Cuestión 1. Razona cuántas iteraciones del bucle de eventos (turnos) suceden en cada uno de los programas analizados.**

a) Programa 1 (`readProgram.js`)

Ocurren **dos** iteraciones del bucle de eventos

- La primera iteración incluye la ejecución del código principal síncrono.
- La segunda iteración incluye la ejecución de la tarea asíncrona (ya sea el callback formato o cbError, dependiendo del resultado de la lectura del archivo).

b) Programa 2 (`writeProgram.js`)

Igualmente ocurren dos iteraciones

- La primera registra la operación de escritura (asíncrona) con `writeFile`
- Después de que el sistema realize la operación de escritura, el callback correspondiente (`cbEscritura` o `cbError`) se coloca en la event queue. Puesto que no existen más operaciones síncronas, este callback se coloca en la pila de llamadas y se imprime el mensaje correspondiente.

**Cuestión 2. En el apartado C e ha desarrollado un módulo de usuario. Detalla los pasos que se han realizado en el código para crear un módulo, a diferencia del código necesario para programar una aplicación.**

Un módulo en JavaScript es una unidad de código reutilizable que encapsula diferentes funciones, clases o variables que pueden ser utilizadas de forma concreta en un programa para resolver cierto problema. Mientras que el módulo no se ejecuta por si solo (ni tiene sentido que se haga), el programa está escrito para ejecutarse y utilizar recursos de los módulos que importa.

Para crear un módulo se requiere:

- Encapsular el código en funciones, clases y variables.
- Exportar lo elementos que serán accesibles desde fuera de él usando `module.exports` o `export`, en función del intérprete que se esté utilizando.

**Cuestión 3. Realiza dos versiones nuevas del apartado A. Una mediante promesas y otra mediante `async/await`**

```javascript
// Promesas
const fs = require("fs");

function readFilePromise(file) {
    return new Promise((resolve, reject) => {
        fs.readFile(file, "utf-8", (err, data) => {
            if (err) reject(err+'');
            else resolve(data+'');
        });
    });
}

// Consumo de la función
readFilePromise("archivo.txt")
    .then(console.log)
    .catch(console.error);
```

```javascript
// Async / Await

const fs = require("fs");

const readAsync = (file) => {
    return new Promise((resolve, reject) => {
        fs.readFile(file, "utf-8", (err, data) => {
            if (err) reject(err+'');
            else resolve(data+'');
        })
    });
};


// Consumo de la función
async function read() {
    try {
        console.log(await readAsync("archivo.txt"));
    } catch (err) {
        console.error(err+'');
    }
}

read();
```

### 2. Módulo Events

**Actividad. Completar el siguiente código (`emisor3.js`)**
```javascript
//...
const e1='e1', e2='e2'
let inc=0, t
function rand() { // debe devolver valores aleatorios en rango [2000,5000) (ms)
                  //...  Math.floor(x) devuelve la parte entera del valor x
                  //...  Math.random() devuelve un valor en el rango [0,1)
}
function handler (e,n) { // e es el evento, n el valor asociado
    return (inc) => {} // el oyente recibe un valor (inc)
}
emitter.on(e1, handler(e1,0))
emitter.on(e2, handler(e2,''))
function etapa() {
    //...
}
setTimeout(etapa,t=rand())
```
El código debe llamar una primera vez a la función “etapa”. Dentro de la función “etapa” se
debe reprogramar la llamada a “etapa” para que el programa ejecute una serie de “etapas”.  
Cada etapa debe iniciarse con un retraso de entre 2 y 5 segundos. Cada etapa debe hacer lo
siguiente:
- Emitir los eventos e1 y e2, pasando como valor asociado el de la variable “inc”.
- Aumentar la variable “inc” una unidad
- Mostrar por consola el retraso en la ejecución de la etapa.

**Razonar implicaciones de programar todas las etapas de golpe con un código similar al siguiente en la actividad `emisor3.js`**
```javascript
t = 0
for (let i=0; i<=10; i++) {
    t = t + rand();
    setTimeout(etapa, t);
}
```
El programa funcionaría de manera idéntica a excepción de que todas las etapas mostrarían el retraso de la última etapa (a partir del instante de lanzamiento), puesto que `t` es una variable global, y todas las etapas se lanzan después de que a `t` se le asigne el retraso de esta última.

### 3. Módulo HTTP

**Comprobar y analizar el funcionamiento del siguiente código de un servidor Web (`ejemploSenciollo.js`)**
```javascript
const http = require('http');
function dd(i) {return (i<10?"0":"")+i;}
const server = http.createServer(
        function (req,res) {
        res.writeHead(200,{'Content-Type':'text/html'});
        res.end('<marquee>Node y Http</marquee>');
        var d = new Date();
        console.log('alguien ha accedido a las '+
                d.getHours() +":"+
                dd(d.getMinutes()) +":"+
                dd(d.getSeconds()));
        }).listen(8000);
```

### 4. Módulo NET

Módulo que contiene la API para emplear sockets TCP/IP.

Se proporcionan dos programas, cliente y servidor. Analizar y comprobar su funcionamiento.


```javascript
// Cliente (netClient.js)

const net = require('net');

const client = net.connect({port:8000}, function() {
        console.log('client connected');
        client.write('world!\r\n');
});

client.on('data', function(data) {
        console.log(data.toString());
        client.end();
});

client.on('end', function() {
        console.log('client disconnected');
});
```

```javascript
// Servidor (netServer.js)

const net = require('net');

const server = net.createServer(
    function(c) {
        console.log('server: client connected');
        c.on('end', function() {
            console.log('server: client disconnected');
        });
        c.on('data', function(data) {
            c.write('Hello\r\n' + data.toString());
            c.end();
        });
});

server.listen(8000, function() {
    console.log('server bound');
});
```

Se pide modificar el código (`netClientLoad.js`, `netClientServer.js`) para que cumpla:

- El servidor netServerLoad no recibe argumentos en línea de órdenes y escuchará en cierto puerto (el puerto 8000 por ejemplo).
- El cliente netClientLoad debe recibir como argumentos en línea de órdenes la dirección IP del servidor y su IP local. Conectará con el servidor en la dirección IP del servidor y el puerto del servidor.
- Protocolo: Cuando el cliente envía una petición al servidor, incluye su propia IP, el servidorcalcula su carga y devuelve una respuesta al cliente en la que incluye la propia IP del servidor y el nivel de carga calculado con la función getLoad.
- Puede ser una buena idea que ambos programas intercambien los datos mediante JSON. Revisa la API JSON.stringify() y JSON.parse()
- Hay que asegurarse de que el cliente finaliza al obtener la carga (ej. con process.exit()) o cerrando su conexión.

Función `getLoad`
```javascript
function getLoad(){
    data=fs.readFileSync("/proc/loadavg"); //requiere fs
    var tokens = data.toString().split(' ');
    var min1 = parseFloat(tokens[0])+0.01;
    var min5 = parseFloat(tokens[1])+0.01;
    var min15 = parseFloat(tokens[2])+0.01;
    return min1*10+min5*2+min15;
};
```

Una vez modificados los archivos, quedarían:

```javascript
//Servidor (netServerLoad.js)

const net = require('net');
const fs = require('fs');

const getLoad = () => {
    data=fs.readFileSync("/proc/loadavg");
    var tokens = data.toString().split(' ');
    var min1 = parseFloat(tokens[0])+0.01;
    var min5 = parseFloat(tokens[1])+0.01;
    var min15 = parseFloat(tokens[2])+0.01;
    return min1*10+min5*2+min15;
};

const server = net.createServer(
    function(c) {
        console.log('server: client connected');
        c.on('end', function() {
            console.log('server: client disconnected');
        });
        c.on('data', (data) => {
            c.write(JSON.stringify({server_ip: JSON.parse(data).server_ip,load: getLoad()}));
            c.end();
        });
});

server.listen(8000, function() {
    console.log('server bound');
});
```

```javascript
//Cliente (netClientLoad.js)

const net = require('net');

if (process.argv.length < 4) {
    console.error("Al menos dos argumentos son necesarios. [ip_servidor] [ip_local]");
    process.exit();
}

const server_ip = process.argv[2];
const client_ip = process.argv[3];

const client = net.connect(8000, server_ip, function() {
        console.log('client connected');
        client.write(JSON.stringify({server_ip: server_ip, client_ip: client_ip}));
});

client.on('data', function(data) {
        console.log(JSON.parse(data));
        client.end();
});

client.on('error', function(ex) {
  console.error("ERROR: ");
  console.error(ex);
});

client.on('end', function() {
    console.log('client disconnected');
    process.exit();
});

```

## Sesión 3. Proxy Inverso

En esta sesión se trabajará con 3 versiones de un proxy http.

### 1. Proxy básico
Cuando el cliente HTTP contacta con el proxy en el puerto 8000, el proxy redirige la petición al servidor web correspondiente y posteriormente devuelve la respuesta del servidor al cliente.

Código (`proxy.js`)
```javascript
const net = require('net');
const LOCAL_PORT = 8000;
const LOCAL_IP = '127.0.0.1';
const REMOTE_PORT = 80;
const REMOTE_IP = '158.42.4.23'; // www.upv.es

// Crea el socket que escucha conexiones (proxy)
const server = net.createServer(function (socket) {

        /* Por cada conexión, abre una conexión con el servidor remoto (upv.es)
         *
         * De tal manera que
         * socket: conexión con el cliente
         * serviceSocket: conexión con el servidor remoto
         *
         */

        const serviceSocket = new net.Socket();
        serviceSocket.connect(parseInt(REMOTE_PORT),
                REMOTE_IP, function () {
                // Escucha las peticiones del cliente, y las reenvia al remoto
                socket.on('data', function (msg) {
                        serviceSocket.write(msg);
                        });
                // Escucha las respuestas del remoto y las redirige al cliente
                serviceSocket.on('data', function (data) {
                        socket.write(data);
                        });
                });
        }).listen(LOCAL_PORT, LOCAL_IP);

console.log("TCP server accepting connection on port: " + LOCAL_PORT);
```

### 2. Proxy Configurable

Ahora se podrá pasar como parámetro la dirección remota a la que redirigirá el proxy (por línea de comandos)

Código (`proxy_configurable.js`)
```javascript
const net = require('net');
const LOCAL_PORT = 8000;
const LOCAL_IP = '127.0.0.1';

if (process.argv.length < 3) {
    console.error("Al menos un argumento es necesario.");
    console.error("Uso del programa: proxy_configurable host_remoto [puerto_remoto] (por defecto: 80)");
    process.exit();
}

const REMOTE_IP = process.argv[2];
const REMOTE_PORT = (process.argv.length < 4) ? 80 : process.argv[3];

const server = net.createServer(function (socket) {
    const serviceSocket = new net.Socket();
    serviceSocket.connect(parseInt(REMOTE_PORT),
        REMOTE_IP, function () {
            socket.on('data', function (msg) {
                serviceSocket.write(msg);
            });
            serviceSocket.on('data', function (data) {
                socket.write(data);
            });
        });
}).listen(LOCAL_PORT, LOCAL_IP);

console.log("TCP server accepting connection on port: " + LOCAL_PORT);
```

### 3. Proxy Programable

Hemos de implementar el código del programador (`programador.js`) y hacer algunos cambios al código del nuevo proxy.

- El programador debe recibir en línea de órdenes la dirección IP del proxy, y los nuevos valores de IP y puerto correspondientes al servidor. Con la IP del proxy y el puerto por defecto del proxy (puerto 8001), contactará con el proxy para enviarle los datos del servidor remoto.
- El programador codificará los valores y los remitirá como mensaje al proxy, tras lo cual termina. Por su parte, el proxy recibirá este mensaje para actualizar la dirección del servidor al que contactará a partir de ese momento.
- El programador.js debería enviar mensajes con un contenido como el siguiente:
```javascript
var msg = JSON.stringify ({'remote_ip':"158.42.4.23", 'remote_port':80})
```

Código Proxy (`proxy_prgramable.js`)
```javascript
const net = require('net');
const LOCAL_PORT = 8000;
const CONFIG_PORT = 8001;
const LOCAL_IP = '127.0.0.1';

let remote_port = -1;
let remote_ip = '';

const server = net.createServer(function (socket) {
    if (!remote_ip || remote_port == -1) {
        socket.write("ERROR 502. Bad Gateway");
        socket.end();
    } else {
        const serviceSocket = new net.Socket();
        serviceSocket.connect(parseInt(remote_port),
        remote_ip, function () {
            socket.on('data', function (msg) {
                serviceSocket.write(msg);
            });
            serviceSocket.on('data', function (data) {
                socket.write(data);
            });
        });
    }
}).listen(LOCAL_PORT, LOCAL_IP);

const configServer = net.createServer(function (socket) {
    socket.on('data', function (msg) {
        data = JSON.parse(msg);
        if (data.remote_ip) remote_ip = data.remote_ip;
        if (data.remote_port) remote_port = data.remote_port;
        socket.end();
    });
}).listen(CONFIG_PORT, LOCAL_IP);

console.log("TCP server accepting connection on port: " + LOCAL_PORT);
```

Código Programador (`programador.js`)
```javascript
const net = require('net');

if (process.argv.length < 5) {
    console.error("Al menos dos argumentos son necesarios.");
    console.error("Uso: programador.js ip_proxy nueva_ip nuevo_puerto")
    process.exit();
}

const proxy_ip = process.argv[2];
const server_ip = process.argv[3];
const server_port = process.argv[4];

const client = net.connect(8001, proxy_ip, function() {
    client.write(JSON.stringify({remote_ip: server_ip, remote_port: server_port}));
});

client.on('end', function () {
    console.log("Configuración cambiada correctamente");
    process.exit();
});
```
