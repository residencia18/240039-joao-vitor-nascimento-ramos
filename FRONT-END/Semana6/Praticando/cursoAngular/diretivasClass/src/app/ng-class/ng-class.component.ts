import { Component } from '@angular/core';

@Component({
  selector: 'app-ng-class',
  templateUrl: './ng-class.component.html',
  styleUrl: './ng-class.component.css'
})
export class NgClassComponent {

  meuFatorito:boolean=true;
  

  onClick(){
    this.meuFatorito = !this.meuFatorito;
  }

}
