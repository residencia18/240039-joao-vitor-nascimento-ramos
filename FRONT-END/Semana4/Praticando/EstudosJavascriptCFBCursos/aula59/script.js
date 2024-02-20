class Pessoa{
    constructor(pNome,pIdade){
        this.nome = pNome;
        this.idade = pIdade;
    }

    getNome(){
        return this.nome;
    }

    getIdade(){
        return this.idade;
    }

    setIdade(nIdade){
        this.idade = nIdade;
    }

    setNome(nNome){
        this.nome = nNome;
    }

    info(){
        console.log(`Nome : ${this.nome}`);
        console.log(`Idade: ${this.idade}`)
    }

    toString(){
        return `Nome: ${this.getNome()}, Idade: ${this.getIdade()}`
    }

}


const btnAdicionar = document.getElementById('btn_add');
const nomePessoa = document.querySelector('input[name=f_nome]')
const idadePessoa = document.querySelector('input[name=f_idade]')
const caixa = document.getElementsByClassName('res');


btnAdicionar.addEventListener('click', (el)=>{
    if(nomePessoa.value !== "" && idadePessoa.value !== ""){
        let pessoa = new Pessoa(nomePessoa.value, idadePessoa.value);
        let novoElemento = document.createElement('p');
        novoElemento.textContent = pessoa.toString();
        caixa[0].appendChild(novoElemento);
        nomePessoa.value = "";
        idadePessoa.value = "";
    }else{
        alert("Preencha todos os campos");
    }
});
