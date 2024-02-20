const btnAdicionarTarefa = document.querySelector('.app__button--add-task');
const formAdicionarTarefa = document.querySelector('.app__form-add-task');
const textArea = document.querySelector('.app__form-textarea');

const tarefas = [];

function criarElementoTarefa(tarefa){
    const li = document.createElement('li');
    li.classList.add('add__section-task-list-item');

    const svg
}

btnAdicionarTarefa.addEventListener('click',(evt)=>{
    formAdicionarTarefa.classList.toggle('hidden');
})

formAdicionarTarefa.addEventListener('submit',(evt)=>{
    evt.preventDefault();
    const tarefa ={
        descricaoTarefa: textArea.value
    }
    tarefas.push(tarefa);
    localStorage.setItem('tarefas', JSON.stringify(tarefas));
})