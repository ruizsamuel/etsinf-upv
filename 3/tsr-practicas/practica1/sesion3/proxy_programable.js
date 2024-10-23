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
