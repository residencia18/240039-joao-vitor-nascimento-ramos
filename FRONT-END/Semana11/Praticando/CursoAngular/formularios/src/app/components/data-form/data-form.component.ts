import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-data-form',
  templateUrl: './data-form.component.html',
  styleUrl: './data-form.component.css'
})
export class DataFormComponent  implements OnInit{

  formulario: FormGroup = new FormGroup({});

  constructor(private formBuilder: FormBuilder,
              private http:HttpClient){

  }

  ngOnInit(): void {

    this.formulario = this.formBuilder.group({
      nome: [null, [Validators.required , Validators.minLength(3)]],
      email: [null ,[Validators.required, Validators.email]]

    })
    
  }

  verificaValidTouched(campo:any) {
    return !this.formulario.get(campo)?.valid && this.formulario.get(campo)?.touched;
  }

  aplicaCssErro(campo:any) {
    return {
      'has-error': this.verificaValidTouched(campo),
      'has-feedback': this.verificaValidTouched(campo)
    };
  }


  verificaEmailInvalido(){
    let campoEmail = this.formulario.get('email');
    if(campoEmail?.errors ){
      return campoEmail.errors['email'];
    }
  }

  onSubmit(){
    this.http.post('https://httpbin.org/post' , JSON.stringify(this.formulario.value)).subscribe(dados => {
      console.log(dados)
      this.resetar();
    },
    (error:any) => alert("erro")
    )
  }

  resetar(){
    this.formulario.reset();
  }

}
