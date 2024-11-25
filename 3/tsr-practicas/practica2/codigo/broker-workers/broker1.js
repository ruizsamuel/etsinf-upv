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
