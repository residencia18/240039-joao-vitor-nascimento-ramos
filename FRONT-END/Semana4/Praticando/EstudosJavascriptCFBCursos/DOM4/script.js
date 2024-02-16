const cursoTodos = [...document.getElementsByClassName("curso")]
const cursoC1 = [...document.getElementsByClassName("c1")]
const cursoC2 = [...document.getElementsByClassName("c2")]
const cursoEspecifico = [...document.getElementsByClassName("curso")][1]

console.log(cursoTodos)
console.log(cursoC1)
console.log(cursoC2)
console.log(cursoEspecifico)

cursoEspecifico.classList.add('destaque')