import { Component , Input } from '@angular/core';
import { Veiculo } from '../Veiculo';
@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrl: './listagem.component.css'
})
export class ListagemComponent {
  @Input() listaVeiculos: Veiculo[] = [];
}
