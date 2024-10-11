const ev = require('events')
const emitter = new ev.EventEmitter()

const e1='e1', e2='e2'
let inc=0, t
function rand() {
    return Math.floor(2000 + Math.random() * 3000)
}

function handler (e,n) { // e es el evento, n el valor asociado
    return (inc) => {
        n = n + inc
        console.log(e + '-->' + n)
    }
}

emitter.on(e1, handler(e1,0))
emitter.on(e2, handler(e2,''))

function etapa() {
    emitter.emit(e1, inc)
    emitter.emit(e2, inc)
    console.log('Etapa ' + inc + ' iniciada después de ' + t + ' ms')
    inc++
    setTimeout(etapa,t=rand())
}

setTimeout(etapa,t=rand())
