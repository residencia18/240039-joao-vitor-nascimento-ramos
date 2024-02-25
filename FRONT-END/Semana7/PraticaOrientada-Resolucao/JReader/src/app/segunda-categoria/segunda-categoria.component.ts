import { Component , Input , Output , EventEmitter} from '@angular/core';

@Component({
  selector: 'app-segunda-categoria',
  templateUrl: './segunda-categoria.component.html',
  styleUrl: './segunda-categoria.component.css'
})
export class SegundaCategoriaComponent {

  @Input() nomesParaSelecao: string[] = [];
  @Input() tipoSelecionado: string = "";
  @Output() nomeSelecionado = new EventEmitter();

  select(nome: number): void {
    this.nomeSelecionado.emit(nome);
  }

}
