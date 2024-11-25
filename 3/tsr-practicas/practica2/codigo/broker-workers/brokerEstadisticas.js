const {zmq, lineaOrdenes, traza, error, adios, creaPuntoConexion} = require('../tsr')
lineaOrdenes("frontendPort backendPort")

let workers  =[] // workers disponibles
let pendiente=[] // peticiones no enviadas a ningun worker

let stats = {}

let frontend = zmq.socket('router')
let backend  = zmq.socket('router')

creaPuntoConexion(frontend, frontendPort)
creaPuntoConexion(backend,  backendPort)

function procesaPeticion(cliente,sep,msg) { // llega peticion desde cliente
  traza('procesaPeticion','cliente sep msg',[cliente,sep,msg])
  if (workers.length) backend.send([workers.shift(),'',cliente,'',msg])
  else pendiente.push([cliente,msg])
}

function procesaMsgWorker(worker,sep1,cliente,sep2,resp) {
  traza('procesaMsgWorker','worker sep1 cliente sep2 resp',[worker,sep1,cliente,sep2,resp])

  if (stats[worker] != null) stats[worker]++
  else stats[worker] = 0

  if (pendiente.length) { // hay trabajos pendientes. Le pasamos el mas antiguo al worker
    let [c,m] = pendiente.shift()
    backend.send([worker,'',c,'',m])
  }
  else workers.push(worker) // añadimos al worker como disponible
  if (cliente) frontend.send([cliente,'',resp]) // habia un cliente esperando esa respuesta
}

function info() {
  console.log("********* INFO STATS *********")

  let totalReq = Object.keys(stats).reduce((acc, k) => {
    console.log(k + ": " + stats[k])
    return acc + stats[k]
  }, 0)

  console.log("TOTAL PETICIONES ATENDIDAS: " + totalReq)

  console.log("******************************")
  setTimeout(info, 5000)
}

info()

frontend.on('message', procesaPeticion)
frontend.on('error'  , (msg) => {error(`${msg}`)})
backend.on('message', procesaMsgWorker)
backend.on('error'  , (msg) => {error(`${msg}`)})
process.on('SIGINT' , adios([frontend, backend],"abortado con CTRL-C"))
