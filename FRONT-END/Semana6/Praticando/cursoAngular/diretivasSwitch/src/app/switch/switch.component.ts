import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrl: './switch.component.css'
})
export class SwitchComponent  implements OnInit{

  aba:string = 'Lista';

  constructor(){

  }

  setAba(aba: string) {
    this.aba = aba;
  }

  ngOnInit(){

  }

}
