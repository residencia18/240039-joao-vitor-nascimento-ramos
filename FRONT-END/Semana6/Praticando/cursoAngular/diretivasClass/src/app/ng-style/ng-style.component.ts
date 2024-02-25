import { Component } from '@angular/core';

@Component({
  selector: 'app-ng-style',
  templateUrl: './ng-style.component.html',
  styleUrl: './ng-style.component.css'
})
export class NgStyleComponent {

  ativo:boolean = false;
  tamanhoFonte: number = 10;


  mudarAtivo(){
    this.ativo = !this.ativo;
  }
}
