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
