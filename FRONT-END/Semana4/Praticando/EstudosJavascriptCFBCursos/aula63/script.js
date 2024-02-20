const f_tipoMilitar = document.querySelector('#f_tipoMilitar');
const f_tipoNormal = document.querySelector('#f_tipoNormal');
const f_blindagem = document.querySelector("#f_blindagem");
const f_municao = document.querySelector("#f_municao");
const carros = document.querySelector("#carros");


let a_carros=[];

f_tipoMilitar.addEventListener('click',(evt)=>{
    f_blindagem.removeAttribute("disabled");
    f_municao.removeAttribute("disabled");
})

f_tipoNormal.addEventListener('click',(evt)=>{
    f_blindagem.value=0;
    f_municao.value=0;
    f_blindagem.setAttribute('disabled',"disabled");
    f_municao.setAttribute('disabled',"disabled");
})

const gerenciarExibicaoCarros=()=>{
    carros.innerHTML="";
    carros
}

class Carro{
    constructor(nome,portas){
        this.nome = nome;
        this.portas = portas;
        this.ligado = false;
        this.velocidade = 0;
        this.cor = undefined;
    }

    ligar=function(){
        this.ligado=true;
    }

    desligar=function(){
        this.ligado=false;
    }

    setCor=function(cor){
        this.cor=cor;
    }
}


class Militar extends Carro{
    constructor(nome,portas,blindagem,municao){
        super(nome,portas);
        this.blindagem = blindagem;
        this.municao = municao;
    }
}


