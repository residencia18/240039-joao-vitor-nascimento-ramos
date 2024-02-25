import { Component, OnInit } from '@angular/core';
import { CursosService } from '../cursos/cursos.service';

@Component({
  selector: 'app-receber-curso',
  templateUrl: './receber-curso.component.html',
  styleUrl: './receber-curso.component.css'
})
export class ReceberCursoComponent implements OnInit{

  curso:string = "";

  constructor(private service:CursosService){
    
  }


  ngOnInit(): void {
    this.service.emitirCursoCriado.subscribe(cursoNovo => {
      this.curso = cursoNovo;
    })
  }

}
