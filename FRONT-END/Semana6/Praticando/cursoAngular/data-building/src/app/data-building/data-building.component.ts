import { Component } from '@angular/core';

@Component({
  selector: 'app-data-building',
  templateUrl: './data-building.component.html',
  styleUrl: './data-building.component.css'
})
export class DataBuildingComponent {

  url:string = "http://loiane.com"
  cursoAngular = true;
  urlImagem1:string = "http://lorempixel.com.br/500/400/?1";
  urlImagem2:string = "http://lorempixel.com.br/500/400/?2";
  urlImagem3:string = "http://lorempixel.com.br/500/400/?3";

  constructor(){}

  getValor(){
    return 25;
  }

  getCurtirCurso(){
    return true;
  }

}
