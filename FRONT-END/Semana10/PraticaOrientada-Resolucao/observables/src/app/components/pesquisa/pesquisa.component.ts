import { Component } from '@angular/core';
import { VeiculosService } from '../../services/veiculos.service';

@Component({
  selector: 'app-pesquisa',
  templateUrl: './pesquisa.component.html',
  styleUrl: './pesquisa.component.css'
})
export class PesquisaComponent {

  constructor( private service:VeiculosService) {}

  name: string = '';

  click() {
    this.service.onAbrirArquivo(this.name);
  }

}
