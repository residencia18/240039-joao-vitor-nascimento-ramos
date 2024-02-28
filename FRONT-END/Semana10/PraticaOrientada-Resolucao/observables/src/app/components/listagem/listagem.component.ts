import { Component, OnInit } from '@angular/core';
import { VeiculosService } from '../../services/veiculos.service';
import { Veiculo } from '../../modelos/Veiculo';

@Component({
  selector: 'app-listagem',
  templateUrl: './listagem.component.html',
  styleUrl: './listagem.component.css'
})
export class ListagemComponent implements OnInit{

  constructor(private service:VeiculosService){

  }

  veiculos: Veiculo[] = [];

  ngOnInit(): void {
    this.service.listaVeiculosAdicionados$.subscribe(
      (veiculos)=>{
        this.veiculos = veiculos;
      }
    )
  }

}
