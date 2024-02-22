import { Component } from '@angular/core';
import { CursosService } from './cursos.service';


@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent {

  nomePortal: string;
  cursos: string[];

  constructor(private cursosService: CursosService){
    this.nomePortal = "http://loiane.training"
    this.cursos = this.cursosService.getCursos();

  }

}
