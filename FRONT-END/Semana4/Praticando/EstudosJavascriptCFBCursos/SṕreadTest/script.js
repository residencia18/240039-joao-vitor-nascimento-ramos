const jogador1 = {nome:"Bruno" , energia:100 , vidas:3,Magia:8}
const jogador2 = {nome:"João" , energia:100 , vidas:5 , velocidade:77}
const jogador3 = {...jogador1,...jogador2}
console.log(jogador3)


const soma = (v1,v2,v3) => {
    return v1+v2+v3;
}
let valores = [1,4,2]
console.log(soma(...valores))


const objs = document.getElementsByTagName('div')
const objs2 = [...document.getElementsByTagName('div')]

console.log(objs)
console.log(objs2)

objs2.forEach(element => {
    console.log(element)
})
