import { Component  , OnInit} from '@angular/core';

import { CursosService } from '../cursos/cursos.service';

@Component({
  selector: 'app-criar-curso',
  templateUrl: './criar-curso.component.html',
  styleUrl: './criar-curso.component.css'
})
export class CriarCursoComponent implements OnInit{

  cursos: string[] = [];

  constructor(private service:CursosService){

  }

  ngOnInit(){
    this.cursos = this.service.getCursos();
  }

  onAddCurso(curso:string){
    this.service.addCurso(curso);
  }

}
