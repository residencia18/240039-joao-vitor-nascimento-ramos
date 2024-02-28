import { Component } from '@angular/core';
import { VeiculosService } from '../../../services/veiculos.service';

@Component({
  selector: 'app-valor',
  templateUrl: './valor.component.html',
  styleUrl: './valor.component.css'
})
export class ValorComponent {


  constructor(private service: VeiculosService){}
  show:boolean = false;
  name: string  = '';
  infoPropriedade: string|undefined = '';

  ngOnInit(){
    this.service.veiculoSelecionado$.subscribe(veiculo => {
      this.name = veiculo.Name;
    })
    this.service.propriedadeSelecionada$.subscribe(propriedade => {
      this.infoPropriedade = propriedade;
    })
    this.service.showValorComponent$.subscribe(
      (show)=>{
        this.show = show;
      }
    )
  }

  onClick(){
    let temp;
    this.service.veiculoSelecionado$.subscribe(veiculo => {
      temp = veiculo;
    })
    this.service.adicionarVeiculo(temp!);
  }

}
