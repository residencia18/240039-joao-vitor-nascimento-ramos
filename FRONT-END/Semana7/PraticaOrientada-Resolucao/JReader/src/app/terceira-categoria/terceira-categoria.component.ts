import { Component , Input , Output , EventEmitter } from '@angular/core';

@Component({
  selector: 'app-terceira-categoria',
  templateUrl: './terceira-categoria.component.html',
  styleUrl: './terceira-categoria.component.css'
})
export class TerceiraCategoriaComponent {

  @Input() showVeiculo: string | undefined = '';
  @Input() type: string = '';
  @Output() dadoSelecionado = new EventEmitter();

  select(tipo: number): void {
    this.dadoSelecionado.emit(tipo);
  }

  atributos = [
    'Name',
    'Model',
    'Engine',
    'NumberOfPassengers',
    'Autonomia',
    'Alcance',
    'Teto',
  ];

}
