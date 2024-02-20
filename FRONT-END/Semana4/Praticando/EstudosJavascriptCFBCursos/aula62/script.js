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

const c1 = new Carro("Normal",4);

console.log(`Nome: ${c1.nome}`)
console.log(`Portas: ${c1.portas}`)
console.log(`Ligado: ${c1.ligado ? "Sim" : "Não"}`)
console.log(`Velocidade: ${c1.velocidade}`)
console.log(`Cor : ${c1.cor}`)

const c2 = new Militar("Milita",2,1200,15);

console.log(`Nome: ${c2.nome}`)
console.log(`Portas: ${c2.portas}`)
console.log(`Ligado: ${c2.ligado ? "Sim" : "Não"}`)
console.log(`Velocidade: ${c2.velocidade}`)
console.log(`Cor : ${c2.cor}`)
console.log(`Blindagem : ${c2.blindagem} `)
console.log(`Municao : ${c2.municao} `)

