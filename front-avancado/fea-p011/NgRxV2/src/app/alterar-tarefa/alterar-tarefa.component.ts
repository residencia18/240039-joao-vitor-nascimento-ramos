import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Tarefa } from '../tarefa.model';
import { EmitterService } from '../emitter.service';
import { alterarTarefa } from '../store/tarefa.actions';
import { Store } from '@ngrx/store';
import { TarefaState } from '../store/tarefa.reducer';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-alterar-tarefa',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './alterar-tarefa.component.html',
  styleUrls: ['./alterar-tarefa.component.css']
})
export class AlterarTarefaComponent implements OnInit, OnDestroy {
  tarefaModal!: Tarefa;
  descricao: string = '';
  open = false;
  private subscription: Subscription = new Subscription();

  constructor(
    private eventEmitter: EmitterService,
    private store: Store<{ tarefas: TarefaState }>
  ) {}

  ngOnInit() {
    this.subscription.add(
      this.eventEmitter.alterarTarefaEvent.subscribe((tarefa: Tarefa) => {
        this.tarefaModal = tarefa;
        this.descricao = this.tarefaModal.descricao;
        this.open = true;
      })
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  closeForm() {
    this.open = false;
  }

  alterarTarefa() {
    const task: Tarefa = {
      id: this.tarefaModal.id,
      descricao: this.descricao,
    };
    this.store.dispatch(alterarTarefa({ tarefa: task }));
    this.closeForm();
  }
}
