import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormArray, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-form-reativo',
  templateUrl: './form-reativo.component.html',
  styleUrls: ['./form-reativo.component.css']
})
export class FormReativoComponent implements OnInit{
  exemploForm: FormGroup;
  labelLogs = ['Data:', 'Origem:', 'Destino:']; 
  y: number = 0;

  constructor() { 
    this.exemploForm = new FormGroup({
      'id': new FormControl(null, Validators.required),
      'modelo': new FormControl(null,  [Validators.required, 
                                        Validators.minLength(3),
                                        Validators.maxLength(10)]),
      'capacidade': new FormControl(null),
      'empresa': new FormControl('FAB',[this.empresaValidator.bind(this)]),
      'logVoos': new FormArray([])
    });
  }
  onSubmit(){
    console.log(this.exemploForm);
    console.log(this.exemploForm.value);
  }

  addLogVoo(){
   const data =  new FormControl(null);
   const origem = new FormControl(null);
   const destino = new FormControl(null);
    (<FormArray>this.exemploForm.get('logVoos')).push(data);
    (<FormArray>this.exemploForm.get('logVoos')).push(origem);
    (<FormArray>this.exemploForm.get('logVoos')).push(destino);
  }

  getControls(){
    return (<FormArray>this.exemploForm.get('logVoos')).controls;
  }

  incY(){
    this.y++;
    if(this.y > 2){
      this.y = 0;
    }
  }  

  empresaValidator(control: AbstractControl): 
  { [key: string]: boolean } | null {
    
    const value = control.value;
    if (value !== 'FAB' && value !== 'AZUL') {
      return { 'invalidEmpresa': true };
    }
    return null;
  }


  ngOnInit(){
  
  }


}
