//definimos um selector para o tarefa

import { TarefaState } from "./tarefa.reducer";

export const selectorSelecionaTarefa = (estado: {tarefas: TarefaState}) => estado.tarefas;




