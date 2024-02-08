class Computador{
    constructor(marca,preco,memoriaRam,cor){
        this.marca = marca;
        this.preco = preco;
        this.memoriaRam = memoriaRam;
        this.cor = cor;
    }

    display() {
        return "******Computador******\n" +
        "Marca : " + this.marca + "\n" +  
        "Preco : " + this.preco + "\n" +
        "Memoria Ram : " + this.memoriaRam + "\n" +
        "Cor : " + this.cor + "\n";
    }

    get marcaValue(){
        return this.marca;
    }

    get precoValue(){
        return this.preco;
    }

    get memoriaValue(){
        return this.memoriaRam;
    }

    get corValue(){
        return this.cor;
    }

}

function retornaMap(computador){
    const mp = new Map();

    mp.set('Marca',computador.marca);
    mp.set('Preco',computador.preco);
    mp.set('Memoria Ram',computador.memoriaRam);
    mp.set('Cor',computador.cor);
    return mp;
}

function adicionarComputador() {
    var inputMarca = document.getElementById("marca").value;
    var inputPreco = document.getElementById("preco").value;
    var inputMemoria = document.getElementById("memoria").value;
    var inputCor = document.getElementById("cor").value;

    var novoComputador = new Computador(inputMarca, inputPreco, inputMemoria, inputCor);
    adicionaComputadorDOM(novoComputador);
    adicionaComputadorNoStorage(novoComputador);
    
    document.getElementById("marca").value = "";
    document.getElementById("preco").value = "";
    document.getElementById("memoria").value = "";
    document.getElementById("cor").value = "";
}

function adicionaComputadorDOM(computador) {
    var ul = document.getElementById("listaComputadores");
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(computador.display()));
    ul.appendChild(li);
}

function adicionaComputadorNoStorage(computador) {
    var listaComputadores = JSON.parse(localStorage.getItem("computadores")) || [];
    listaComputadores.push(computador);
    localStorage.setItem("computadores", JSON.stringify(listaComputadores));
}

document.addEventListener("DOMContentLoaded", function () {
    var computadoresSalvos = JSON.parse(localStorage.getItem("computadores")) || [];

    computadoresSalvos.forEach(function (computador) {
        adicionaComputadorDOM(computador);
    });
});
