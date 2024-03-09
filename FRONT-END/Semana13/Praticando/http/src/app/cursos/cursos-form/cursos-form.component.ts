import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CursosListaService } from '../cursos-lista/Service/cursos-lista.service';
import { AlertModalService } from '../../alert-model/alert-model.service';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-cursos-form',
  templateUrl: './cursos-form.component.html',
  styleUrls: ['./cursos-form.component.scss']
})
export class CursosFormComponent implements OnInit  {

  form: FormGroup = new FormGroup({}); // Inicialización de la propiedad form
  submitted = false;



  constructor(private fb: FormBuilder,
    private service:CursosListaService,
    private alertService:AlertModalService,
    private location:Location,
    private route:ActivatedRoute) { }

    ngOnInit() {
      const curso = this.route.snapshot.data['curso'];
    
      if (curso) { // Verifica se 'curso' não é null
        this.form = this.fb.group({
          id: [curso.id],
          nome: [curso.nome, [Validators.required, Validators.minLength(1), Validators.maxLength(250)]]
        });
      } else {
        // Se 'curso' for null, você pode inicializar o formulário de uma maneira padrão ou vazio
        this.form = this.fb.group({
          nome: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(250)]]
        });
      }
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
      console.log('submit');

      let msgSuccess = 'Curso criado com sucesso!';
      let msgError = 'Erro ao criar curso, tente novamente!';
      if (this.form.value.id) {
        msgSuccess = 'Curso atualizado com sucesso!';
        msgError = 'Erro ao atualizar curso, tente novamente!';
      }

      this.service.save(this.form.value).subscribe(
        success => {
          this.alertService.showAlertSuccess(msgSuccess);
            this.location.back();
        },
        error => this.alertService.showAlertDanger(msgError)
      );
  }
}

  onCancel() {
    this.submitted = false;
    this.form.reset();
    // console.log('onCancel');
  }
}
