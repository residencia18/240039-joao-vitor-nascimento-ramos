import { Component, OnInit } from '@angular/core';
import { CursosListaService } from './Service/cursos-lista.service';
import { Curso } from '../../model/curso';
import { Observable, Subject, catchError, empty } from 'rxjs';
import { BsModalRef, BsModalService, ModalContainerComponent } from 'ngx-bootstrap/modal';
import { AlertModelComponent } from '../../alert-model/alert-model.component';
import { AlertModalService } from '../../alert-model/alert-model.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-cursos-lista',
  templateUrl: './cursos-lista.component.html',
  styleUrls: ['./cursos-lista.component.css'] // Alterado para styleUrls
})
export class CursosListaComponent implements OnInit {

  bsModalRef: BsModalRef | undefined;

  cursos$: Observable<Curso[]> | undefined;
  error$ = new Subject<boolean>();

  constructor(
    private service: CursosListaService,
    private alertService:AlertModalService,
    private router:Router,
    private route:ActivatedRoute) { }

  ngOnInit() {
    this.onRefresh();
  }

  onRefresh(){
    this.cursos$ = this.service.list().pipe(catchError(error => {
      this.handdleError()
      return empty();
    }));
  }

  handdleError(){
    this.alertService.showAlertDanger("Erro ao carregar cursos")
  }

  onEdit(id:number){
    this.router.navigate(['editar',id], {relativeTo:this.route});
  }

  
}
