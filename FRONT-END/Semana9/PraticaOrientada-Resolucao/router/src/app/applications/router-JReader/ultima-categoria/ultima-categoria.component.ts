import { Component , Input , Output ,EventEmitter} from '@angular/core';

@Component({
  selector: 'app-ultima-categoria',
  templateUrl: './ultima-categoria.component.html',
  styleUrl: './ultima-categoria.component.css'
})
export class UltimaCategoriaComponent {
  @Input() showVeiculo: string|undefined = undefined;
  @Input() showInfo: string|undefined = undefined;
  @Output() adicionar = new EventEmitter();

  add(){
    this.adicionar.emit();
  }
}
