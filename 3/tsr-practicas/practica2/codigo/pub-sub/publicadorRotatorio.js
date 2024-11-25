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
