import { Component, OnInit } from '@angular/core';
import { VeiculosService } from '../../services/veiculos.service';
import { Veiculo } from '../../modelos/Veiculo';

@Component({
  selector: 'app-veiculos',
  templateUrl: './veiculos.component.html',
  styleUrl: './veiculos.component.css'
})
export class VeiculosComponent implements OnInit{

  constructor(private servico:VeiculosService){

  }

  veiculos:Veiculo[] = [];
  tipo:string = '';

  ngOnInit(): void {
    this.servico.tipoVeiculoSelecionado$.subscribe(
      (tipo)=>{
        this.tipo = tipo;
      }
    )

    this.servico.veiculosSelecionados$.subscribe(
      (veiculos)=>{
        this.veiculos = veiculos;
      }
    )

  }

  onClick(index:number){
    this.servico.selecionarveiculo(index);
  }



}
