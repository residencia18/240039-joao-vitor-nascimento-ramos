

function aluno(nome,nota){
    this.nome = nome;
    this.nota = nota;

    this.dados_anonimo=function(){
        setTimeout(function(){
        console.log(this.nome);
        console.log(this.nota);
        },5000)
    }

    this.dados_arrow=function(){
        setTimeout(() =>{
        console.log(this.nome);
        console.log(this.nota);
        },5000)
    }

}

const a1 = new aluno("Bruno",100)
a1.dados_arrow()
