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
