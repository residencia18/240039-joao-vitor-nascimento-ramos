const caixaCursos=document.querySelector("#caixaCursos")
const btn_c=[...document.querySelectorAll(".curso")]
const c1_2=document.querySelector("#c1_2")
const cursos=["HTML","CSS","Javascript","PHP","React","MySQL","ReactNative"]
const btnCursoSelecionado=document.getElementById("btnCursoSelecionado")
const btnRemoverCurso = document.getElementById("caixaRemoverCurso")


cursos.map((el,chave)=>{
    const novoElemento=document.createElement("div")
    novoElemento.setAttribute("id","c"+chave)
    novoElemento.setAttribute("class","curso c1")
    novoElemento.innerHTML=el

    const comandos=document.createElement("div")
    comandos.setAttribute("class","comandos")
    
    const rb=document.createElement("input")
    rb.setAttribute("type","radio")
    rb.setAttribute("name","rb_curso")

    comandos.appendChild(rb)

    novoElemento.appendChild(comandos)

    caixaCursos.appendChild(novoElemento)
    
})

const cursoSelecionado=()=>{
    const todosRadios = [...document.querySelectorAll('input[type=radio')];
    const radioSelecionados = todosRadios.filter((el)=>{
        return el.checked;
    })
    return radioSelecionados[0];
}


btnCursoSelecionado.addEventListener('click',(evt)=>{
    const rs = cursoSelecionado();
    if (rs!=undefined) {
        const curso = rs.parentNode.previousSibling.textContent;
        alert("Curso selecionado : " +  curso);
    }else{
        alert("Selecione um curso")
    }
})

btnRemoverCurso.addEventListener("click",(evt)=>{
    const rs = cursoSelecionado();
    if(rs!=undefined){
        const curso = rs.parentNode.parentNode;
        curso.remove()
    }else{
        alert("Selecione um curso")
    }
})

