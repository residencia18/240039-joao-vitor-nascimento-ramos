import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterarTarefaComponent } from './alterar-tarefa.component';

describe('AlterarTarefaComponent', () => {
  let component: AlterarTarefaComponent;
  let fixture: ComponentFixture<AlterarTarefaComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AlterarTarefaComponent]
    });
    fixture = TestBed.createComponent(AlterarTarefaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
