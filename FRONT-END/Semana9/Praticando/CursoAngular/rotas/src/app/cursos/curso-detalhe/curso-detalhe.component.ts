import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-curso-detalhe',
  templateUrl: './curso-detalhe.component.html',
  styleUrl: './curso-detalhe.component.css'
})
export class CursoDetalheComponent implements OnInit , OnDestroy{

  id:number;
  inscricao: Subscription | undefined;
  curso:any | undefined;

  constructor(private rounter:ActivatedRoute , private service:ServiceService , private rout:Router){
    this.id = this.rounter.snapshot.params['id']
  }

  ngOnInit(): void {
    this.inscricao = this.rounter.params.subscribe( (params:any)=> {
      this.id = params['id']
      this.curso = this.service.getCurso(this.id);
      console.log(this.curso)
      if(this.curso===null){
        this.rout?.navigate(['/naoEncontrado'])
      }
    
    }
    )
  }

  ngOnDestroy(){
    if (this.inscricao) {
      this.inscricao.unsubscribe();
    }
  }

}
