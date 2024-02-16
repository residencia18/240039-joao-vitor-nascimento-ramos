const soma = (...valores) => {
    let valor=0
    for(n of valores){
        valor += n
    }
    return valor
}

const nome=n=>{
    return n;
}

const add=n=>n+10

console.log(add(8))

console.log(soma(2,4,6,4,9))