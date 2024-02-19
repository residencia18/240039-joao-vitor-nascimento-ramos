const caixa = document.querySelector('#caixa1');

const cursos = ['HTML','CSS','Javascript','Typescript','C++','Java','C#']

cursos.map((el,chave) =>{
    const novoElemento = document.createElement("div");
    novoElemento.classList.add("curso",`c${chave}`)
    novoElemento.setAttribute('id',`c${chave}`)
    novoElemento.innerHTML = el
    novoElemento.addEventListener('click', (el) =>{
        novoElemento.parentElement.removeChild(novoElemento)
    })
    caixa.appendChild(novoElemento)
})


  