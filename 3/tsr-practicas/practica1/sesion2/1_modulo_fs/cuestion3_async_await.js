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