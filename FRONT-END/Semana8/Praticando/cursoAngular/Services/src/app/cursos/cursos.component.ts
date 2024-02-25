import { Component, OnInit } from '@angular/core';

import { CursosService } from './cursos.service';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent implements OnInit {

  cursos:string[] = [];

  constructor(private service:CursosService){
  }

  ngOnInit(): void {
    this.cursos = this.service.getCursos();

    this.service.emitirCursoCriado.subscribe(
      (curso:string)=>{
        console.log(curso)
      }
    );
  }

}
