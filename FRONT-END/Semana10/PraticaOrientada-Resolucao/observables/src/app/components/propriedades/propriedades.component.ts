import { Component, OnInit } from '@angular/core';
import { VeiculosService } from '../../services/veiculos.service';
import { Veiculo } from '../../modelos/Veiculo';

@Component({
  selector: 'app-propriedades',
  templateUrl: './propriedades.component.html',
  styleUrl: './propriedades.component.css'
})
export class PropriedadesComponent implements OnInit{

  propriedades = [
    'Name',
    'Model',
    'Engine',
    'NumberOfPassengers',
    'Autonomia',
    'Alcance',
    'Teto',
  ];

  constructor(private service:VeiculosService){

  }

  nomeVeiculo: string= '';
  veiculo: Veiculo | undefined;

  ngOnInit(): void {
    this.service.veiculoSelecionado$.subscribe(
      (veiculo) =>{
        this.veiculo = veiculo
        this.nomeVeiculo = veiculo.Name;
      }
    )
  }



  onClick(index: number){
    this.service.selecionarPropriedade(index);
  }

}
