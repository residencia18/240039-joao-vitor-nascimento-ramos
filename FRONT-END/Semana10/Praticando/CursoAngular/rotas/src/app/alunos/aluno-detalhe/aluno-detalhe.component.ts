import { Aluno } from './../../aluno';
import { AlunosService } from './../alunos.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router, } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-aluno-detalhe',
  templateUrl: './aluno-detalhe.component.html',
  styleUrl: './aluno-detalhe.component.css'
})
export class AlunoDetalheComponent implements OnInit , OnDestroy{

  aluno:Aluno | undefined;
  inscricao:Subscription | undefined;


  constructor(private route:ActivatedRoute,
              private service:AlunosService,
              private router:Router){

              }

  ngOnInit(): void {
    // this.inscricao = this.route.params.subscribe(
    //   (queryParams:any)=>{
    //     let id = queryParams['id'];
    //     this.aluno = this.service.getAluno(id);
    //   }
    // );

    this.inscricao = this.route.data.subscribe(
      (info) =>{
        this.aluno = info['aluno'];
      }
    );
  }

  ngOnDestroy(): void {
    this.inscricao?.unsubscribe();
  }

  editarContato(){
    this.router?.navigate(['/alunos', this.aluno?.id,'edit']);
  }

}
