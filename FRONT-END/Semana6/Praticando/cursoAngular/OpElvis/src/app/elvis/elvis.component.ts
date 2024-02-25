import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-elvis',
  templateUrl: './elvis.component.html',
  styleUrl: './elvis.component.css'
})
export class ElvisComponent implements OnInit {

  tarefa: any = { 
    desc: "descrição da tarefa",
    responsavel: {
      usuario: {
        nome: "Joao"
      }
    }
  };


  constructor(){

  }

  ngOnInit(){

  }
  
}
