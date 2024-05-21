import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TarefaState } from '../store/tarefa.reducer';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { selectorSelecionaTarefa } from '../store/tarefa.seletors';
import { Tarefa } from '../tarefa.model';
import { removerTarefa } from '../store/tarefa.actions';
import { EmitterService } from '../emitter.service';

@Component({
  selector: 'app-show-tarefas',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './show-tarefas.component.html',
  styleUrls: ['./show-tarefas.component.css'],
})
export class ShowTarefasComponent {
  tarefas: Tarefa[] = [{ id: '1', descricao: 'Descrição 1' }];
  tasks$!: Observable<TarefaState>;

  constructor(
    private store: Store<{ tarefas: TarefaState }>,
    private eventEmiter: EmitterService
  ) {}

  ngOnInit() {
    this.tasks$ = this.store.select(selectorSelecionaTarefa);
    this.tasks$.subscribe((t) => {
      this.tarefas = t.tarefas;
    });
  }

  removeTarefa(id: string) {
    this.store.dispatch(removerTarefa({ id: id }));
  }

  alterarTarefa(tarefa: Tarefa) {
    this.eventEmiter.alterarTarefaEvent.emit(tarefa);
  }
}