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
