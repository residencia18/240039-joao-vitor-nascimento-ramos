let cursos =['HTML','CSS','Javascript']
const caixa = document.getElementById('caixa');
cursos.push("Python")
cursos.unshift("C++")  

let dados = ['18',14,"ola",0.1417]
cursos.push(dados)
cursos.map((el)=>{
    let p = document.createElement("p");
    p.innerHTML=el;
    caixa.appendChild(p)
})