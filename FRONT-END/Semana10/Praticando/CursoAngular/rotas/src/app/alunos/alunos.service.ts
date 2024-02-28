import { Injectable } from '@angular/core';
import { Aluno } from '../aluno';

@Injectable({
  providedIn: 'root'
})
export class AlunosService {

  private alunos: Aluno[] = [
    {id: 1 , nome: 'João' , email: 'jvitorsb98@gmail.com'},
    {id: 2 , nome: 'Paulo', email: 'reboto@gmail.com'},
    {id: 3 , nome: 'Caio' , email: 'times98@gmail.com'}
  ]

  getAll(){
    return this.alunos;
  }

  getAluno(id:number){
    let aluno;

    for(let i = 0 ; i < this.alunos.length ; i++){
      let aluno = this.alunos[i];
      if(aluno.id == id){
        return aluno
      }
    }

    return null
  }

  constructor() { }
}
