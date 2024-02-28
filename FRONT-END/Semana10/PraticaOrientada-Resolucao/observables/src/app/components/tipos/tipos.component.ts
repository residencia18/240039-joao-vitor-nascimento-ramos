import { Component } from '@angular/core';

import { VeiculosService } from '../../services/veiculos.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-tipos',
  templateUrl: './tipos.component.html',
  styleUrl: './tipos.component.css'
})
export class TiposComponent {

  show:boolean = false;
  private sub:Subscription |undefined;

  constructor(private service:VeiculosService){
    this.sub = this.service.showTipos$.subscribe(
      (showTipos$)=>{
        this.show = showTipos$;
      } 
    )
  }

  onClick(selecionado:string){
    this.service.selecionarTipoVeiculo(selecionado);
  }



}
