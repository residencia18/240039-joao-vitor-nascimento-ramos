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
      email: [null ,[Validators.required, Validators.email]],

      endereco: this.formBuilder.group({
        cep: [null,Validators.required],
        numero: [null,Validators.required],
        complemento: [null],
        rua: [null,Validators.required],
        bairro: [null,Validators.required],
        cidade: [null,Validators.required],
        estado: [null,Validators.required]
      })



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

    if(this.formulario){
      this.http.post('https://httpbin.org/post' , JSON.stringify(this.formulario.value)).subscribe(dados => {
        console.log(dados)
        this.resetar();
      },
      (error:any) => alert("erro")
      )
    } else {
      // Object.keys(this.formulario).forEach((campo: string) => {
      //   console.log(campo);
      // });
      Object.keys(this.formulario.controls).forEach(campo => {
        console.log("Nome do campo:", campo);
        console.log("Valor do campo:", this.formulario.get(campo)?.value);
      });    }

  }

  resetar(){
    this.formulario.reset();
  }

  consultaCEP(){

    let cep = this.formulario.get('endereco.cep')?.value;
    cep = cep.replace(/\D/g,'');

    if(cep!=""){
      let validacep = /^[0-9]{8}$/;

      if (validacep.test(cep)) {
        this.resetaDadosForm()
        this.http.get(`//viacep.com.br/ws/${cep}/json`).subscribe(dados => this.populaDadosForm(dados));
        };
      }

  }

  resetaDadosForm() {
    this.formulario.patchValue({
      endereco: {
        rua: null,
        complemento: null,
        bairro: null,
        cidade: null,
        estado: null
      }
    });
  }

  populaDadosForm(dados:any) {

    this.formulario.patchValue({
      endereco: {
        rua: dados.logradouro,
        // cep: dados.cep,
        complemento: dados.complemento,
        bairro: dados.bairro,
        cidade: dados.localidade,
        estado: dados.uf
      }
    });

    // console.log(form);
  }


}
