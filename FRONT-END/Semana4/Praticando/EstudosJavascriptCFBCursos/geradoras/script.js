function* cores(){
    yield  'red'
    yield 'green'
    yield 'blue'
}

const itc =cores();

while( saida = itc.next().value){
    console.log(saida)
}

function* perguntas(){
    const nome = yield 'Qual seu nome ? '
    const esporte = yield 'qual seu esporte ? '
    return `Seu nome é ${nome} e seu esporte favorito é ${esporte}`;
}

const itp = perguntas();

console.log(itp.next().value)
console.log(itp.next('Bruno').value)
console.log(itp.next('Natação').value)

