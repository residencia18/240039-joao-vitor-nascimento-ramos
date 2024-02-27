import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServiceService } from './service.service';
import { ActivatedRoute , Router} from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent  implements OnInit, OnDestroy{

  cursos:any;
  pagina:number = 0;
  inscricao:Subscription | undefined;

  constructor(private cursosService:ServiceService , private route:ActivatedRoute , private router:Router){

  }

  ngOnInit(): void {
    this.cursos = this.cursosService.getCursos();
    this.inscricao = this.route.queryParams.subscribe(
      (queryParams:any)=>{
        this.pagina = queryParams['pagina']
      }
    )
  }

  ngOnDestroy(){
    this.inscricao?.unsubscribe();
  }
  
  proximaPagina(){
    this.pagina++;
    this.router.navigate(['/cursos'],{queryParams: {'pagina':this.pagina}});
  }

}
