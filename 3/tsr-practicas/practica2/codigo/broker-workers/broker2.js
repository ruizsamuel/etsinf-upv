const {zmq, lineaOrdenes, traza, error, adios, creaPuntoConexion, conecta} = require('../tsr')
lineaOrdenes("backendPort broker1Host broker1Port")

let workers  =[] // workers disponibles
let pendiente=[] // peticiones no enviadas a ningun worker

let backend  = zmq.socket('router')
let broker1 = zmq.socket('rep')

creaPuntoConexion(backend, backendPort)
conecta(broker1, broker1Host, broker1Port)

function procesaPeticion(cliente,sep,msg) { // llega peticion desde cliente
  traza('procesaPeticion','cliente sep msg',[cliente,sep,msg])
  if (workers.length) backend.send([workers.shift(),'',cliente,'',msg])
  else pendiente.push([cliente,msg])
}

function procesaMsgWorker(worker,sep1,cliente,sep2,resp) {
  traza('procesaMsgWorker','worker sep1 cliente sep2 resp',[worker,sep1,cliente,sep2,resp])
  if (pendiente.length) { // hay trabajos pendientes. Le pasamos el mas antiguo al worker
    let [c,m] = pendiente.shift()
    backend.send([worker,'',c,'',m])
  }
  else workers.push(worker) // añadimos al worker como disponible
  if (cliente != '') {
    traza('prueba', 'cliente resp', [cliente,resp])
    broker1.send([cliente,'',resp]) // habia un cliente esperando esa respuesta
  }
}

broker1.on('message', procesaPeticion)
broker1.on('error'  , (msg) => {error(`${msg}`)})
backend.on('message', procesaMsgWorker)
backend.on('error'  , (msg) => {error(`${msg}`)})
process.on('SIGINT' , adios([broker1, backend],"abortado con CTRL-C"))
