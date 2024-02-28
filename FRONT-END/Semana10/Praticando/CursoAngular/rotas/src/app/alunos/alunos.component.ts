import { Component, OnInit } from '@angular/core';
import { AlunosService } from './alunos.service';

@Component({
  selector: 'app-alunos',
  templateUrl: './alunos.component.html',
  styleUrl: './alunos.component.css'
})
export class AlunosComponent implements OnInit {

  alunos:any[] = [];

  constructor( private service:AlunosService){

  }

  ngOnInit(): void {
    this.alunos = this.service.getAll();
  }

}
