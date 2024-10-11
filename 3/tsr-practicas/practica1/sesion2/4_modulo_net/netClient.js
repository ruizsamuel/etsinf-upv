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
