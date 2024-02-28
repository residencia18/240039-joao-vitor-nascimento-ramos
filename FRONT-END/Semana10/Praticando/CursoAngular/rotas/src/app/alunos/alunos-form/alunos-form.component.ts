import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AlunosService } from './../alunos.service';

@Component({
  selector: 'app-alunos-form',
  templateUrl: './alunos-form.component.html',
  styleUrl: './alunos-form.component.css'
})
export class AlunosFormComponent {

  aluno:any;
  inscricao:Subscription | undefined;


  constructor(private route:ActivatedRoute,
              private service:AlunosService){

              }

  ngOnInit(): void {
    this.inscricao = this.route.params.subscribe(
      (queryParams:any)=>{
        let id = queryParams['id'];
        this.aluno = this.service.getAluno(id);

        if(this.aluno==null){
          this.aluno = {}
        }

      }
    );
  }

  ngOnDestroy(): void {
    this.inscricao?.unsubscribe();
  }


}
