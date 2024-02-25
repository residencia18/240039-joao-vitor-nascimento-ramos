import { Veiculo } from './../../Veiculo';
import { Component } from '@angular/core';
import { HttpClient  } from '@angular/common/http';
import { Categorias } from '../../Categorias';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrl: './categorias.component.css'
})
export class CategoriasComponent {

  constructor(private http: HttpClient){

  }
  names: string[] = [];
  json: Veiculo[][] = [];
  type:string = '';
  idType:number = -1;
  veiculoEscolhido: Veiculo | undefined = undefined;
  info: string | undefined  = undefined;
  listaVeiculos: Veiculo[] = [];

  caminhoArquivo: string = "";
  recebido: boolean = false;

  onOpenFile(caminho:string){
    this.caminhoArquivo = caminho;
    this.recuperarDadosJSON();
  }

  recuperarDadosJSON(){
    this.http.get<Categorias>("assets\\"+this.caminhoArquivo).subscribe(json => {
      this.json = [json.Avioes , json.Carros , json.Barcos]
      this.recebido = true;
    })
  }



  onCategoriaSelecionada(i: number) {
    this.names.splice(0, 3);
    this.idType = i;
    this.json[i].map(item=>{
      this.names.push(item.Name)
    })
  }

  onTipoSelecionado(s: string) {
    this.type = s;
    this.veiculoEscolhido = undefined;
  }

  onNomeSelecionado(index: number){
    let temp: Veiculo | undefined = this.json[this.idType][index];
    this.info = undefined;
    if (temp != undefined) {
      this.veiculoEscolhido = temp;
      console.log(this.veiculoEscolhido)
    }
  }

  onDadoSelecionado(index: number): void {
    switch (index) {
      case 0:
        this.info = this.veiculoEscolhido?.Name;
        break;
      case 1:
        this.info = this.veiculoEscolhido?.Model;
        break;
      case 2:
        this.info = this.veiculoEscolhido?.Engine;
        break;
      case 3:
        this.info = this.veiculoEscolhido?.NumberOfPassengers;
        break;
      case 4:
        this.info = this.veiculoEscolhido?.Autonomia;
        break;
      case 5:
        this.info = this.veiculoEscolhido?.Alcance;
        break;
      case 6:
        this.info = this.veiculoEscolhido?.Teto;
        break;
      default:
        break;
    }
  }


  onAdicionar(){
    if(this.veiculoEscolhido != undefined){
      if(!this.listaVeiculos.some(veiculo => veiculo.Name === this.veiculoEscolhido?.Name))
        this.listaVeiculos.push(this.veiculoEscolhido);
        console.log(this.listaVeiculos
          )
    }
  }

}
