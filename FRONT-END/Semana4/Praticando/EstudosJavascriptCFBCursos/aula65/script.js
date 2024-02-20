const pessoa={
    nome:"Bruno",
    canal:"CFB Cursos",
    curso:"Javascript",
    aulas:{
        aula01:"Introdução",
        aula02:"Variaveis",
        aula03:"Condicional"
    }

}

const s_josn = JSON.stringify(pessoa);
const pessao2 = JSON.parse(s_josn)
console.log(pessao2)