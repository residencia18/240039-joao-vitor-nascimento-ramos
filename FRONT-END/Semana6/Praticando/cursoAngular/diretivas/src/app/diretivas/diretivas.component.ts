import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-diretivas',
  templateUrl: './diretivas.component.html',
  styleUrl: './diretivas.component.css'
})
export class DiretivasComponent implements OnInit {

  cursos: string[] = ["Angular2"];
  mostrarCursos: boolean = false;

  constructor(){

  }

  ngOnInit(){

  }

  onMostrarCursos(){
    if(this.mostrarCursos){
      this.mostrarCursos=false;
    }else{
      this.mostrarCursos=true;
    }
  }

}
