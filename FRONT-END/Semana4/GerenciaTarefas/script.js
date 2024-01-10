class Tarefa {
    constructor(descricao, dataFinal) {
        this.descricao = descricao;
        this.dataFinal = dataFinal;
    }

    display() {
        return this.descricao + ", Data de Hoje: " + this.dataFinal;
    }
}

function adicionaTarefaDOM(tarefa) {
    var ul = document.getElementById("listaTarefas");
    var li = document.createElement("li");
    li.appendChild(document.createTextNode(tarefa.display()));
    ul.appendChild(li);
}

function adicionaTarefaNoStorage(tarefa) {
    var listaDeTarefas = JSON.parse(localStorage.getItem("tarefas")) || [];
    listaDeTarefas.push(tarefa);
    localStorage.setItem("tarefas", JSON.stringify(listaDeTarefas));
}

function adicionarTarefa() {
    var inputTarefa = document.getElementById("taref");
    var descricaoTarefa = inputTarefa.value;

    var novaTarefa = new Tarefa(descricaoTarefa, new Date().toLocaleDateString());
    adicionaTarefaDOM(novaTarefa);
    adicionaTarefaNoStorage(novaTarefa);

    inputTarefa.value = "";
}

document.addEventListener("DOMContentLoaded", function () {
    var tarefasSalvas = JSON.parse(localStorage.getItem("tarefas")) || [];

    tarefasSalvas.forEach(function (tarefa) {
        adicionaTarefaDOM(new Tarefa(tarefa.descricao, tarefa.dataFinal));
    });
});