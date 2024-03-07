import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CursosListaService } from '../cursos-lista/Service/cursos-lista.service';
import { AlertModalService } from '../../alert-model/alert-model.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Subscribable } from 'rxjs';
import { exhaustMap, map, switchMap } from 'rxjs/operators';


@Component({
  selector: 'app-cursos-form',
  templateUrl: './cursos-form.component.html',
  styleUrls: ['./cursos-form.component.scss']
})
export class CursosFormComponent implements OnInit {

  form: FormGroup = new FormGroup({}); // Inicialización de la propiedad form
  submitted = false;



  constructor(private fb: FormBuilder,
    private service:CursosListaService,
    private alertService:AlertModalService,
    private location:Location,
    private route:ActivatedRoute) { }

  ngOnInit() {

    this.route.params.pipe(
      map((params: any) => params['id']),
      switchMap(id => this.service.loadByID(id))
      ).subscribe((curso) => {
      this.updateForm(curso);
    });
  
    this.form = this.fb.group({
      nome: [null, [Validators.required, Validators.minLength(3), Validators.maxLength(250)]]
    });
  }

  updateForm(curso:any){
    this.form.patchValue({
      id: curso.id,
      nome: curso.nome
    })
  }

  hasError(field: string) {

    if(field==null || field== undefined){
      return;
    }

    return this.form.get(field)!.errors;
  }

  onSubmit() {
    this.submitted = true;
    console.log(this.form.value);
    if (this.form.valid) {
      this.service.create(this.form.value).subscribe(sucess => {
        this.alertService.showAlertSuccess('Curso adicionado com sucesso')
        this.location.back()
      },
      error => this.alertService.showAlertDanger('Erro ao adicionar curso'),
      () => console.log("request completo"));
    }
  }

  onCancel() {
    this.submitted = false;
    this.form.reset();
    // console.log('onCancel');
  }
}
