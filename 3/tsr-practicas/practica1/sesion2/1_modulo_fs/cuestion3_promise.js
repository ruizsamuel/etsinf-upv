// Promesas
const fs = require("fs");

const readFilePromise = (file) => {
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
