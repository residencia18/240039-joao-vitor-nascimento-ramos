import { Component } from '@angular/core';
import { ServicoDadosAtendimento } from '../../Services/appointment-data.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  form: FormGroup;
  editMode = false;
  appointmentId = '';

  constructor(private dataService: ServicoDadosAtendimento, private route: ActivatedRoute, private rotas: Router) { 
    this.form = new FormGroup({
      'appointment_day': new FormControl(null, [Validators.required]),
      'tutor_name': new FormControl(null, [Validators.required]),
      'pet_name': new FormControl(null, [Validators.required]),
      'pet_type': new FormControl(null, [Validators.required]),
      'pet_breed': new FormControl(null, [Validators.required]),
      'details': new FormControl(null),
    });
  }

  ngOnInit(): void {
    this.route.url.subscribe(params => {
      if (params[0].path === 'editar') {
        this.editMode = true;
        this.appointmentId = params[1].path;
      }
    });
  }

  onSubmit() {
    const dados = this.form.value;
    if (this.editMode) {
      dados.id = this.appointmentId;
      this.editMode = false;
      this.appointmentId = '';
      this.dataService.atualizarAtendimento(dados).subscribe(
        {
          next: (response) => {
            console.log(response);
            this.form.reset();
            this.rotas.navigate(['/lista']);
          },
          error: (error) => {
            console.log(error);
          }
        }
      );
    }else{
      this.dataService.adicionarAtendimento(dados);
      this.form.reset();
    }
  }

}
