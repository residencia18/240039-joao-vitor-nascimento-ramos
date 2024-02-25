import { Component ,Output , EventEmitter} from '@angular/core';

@Component({
  selector: 'app-primeira-categoria',
  templateUrl: './primeira-categoria.component.html',
  styleUrl: './primeira-categoria.component.css'
})
export class PrimeiraCategoriaComponent {
  @Output() categoriaSelecionada = new EventEmitter<number>();
  @Output() tipoSelecionado = new EventEmitter<string>();
  select(selected: number, type: string){
    this.categoriaSelecionada.emit(selected);
    this.tipoSelecionado.emit(type)
  }
}
