import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators'; // Importe map desta forma


@Component({
  selector: 'app-template-form',
  templateUrl: './template-form.component.html',
  styleUrl: './template-form.component.css'
})
export class TemplateFormComponent implements OnInit{

  usuario:any = {
    nome:  null,
    email: null,
    cep: null
  }

  constructor(private http:HttpClient){


  }

  ngOnInit(): void {
    
  }

  onSubmit(form:any){
    this.http.post('https://httpbin.org/post' , JSON.stringify(form.value)).subscribe(dados => console.log(dados))
  }

  verificaValidTouched(campo:any) {
    return !campo.valid && campo.touched;
  }

  aplicaCssErro(campo:any) {
    return {
      'has-error': this.verificaValidTouched(campo),
      'has-feedback': this.verificaValidTouched(campo)
    };
  }

  consultaCEP(cep:string , form:any){
    cep = cep.replace(/\D/g,'');

    if(cep!=""){
      let validacep = /^[0-9]{8}$/;

      if (validacep.test(cep)) {
        this.resetaDadosForm(form)
        this.http.get(`//viacep.com.br/ws/${cep}/json`).subscribe(dados => this.populaDadosForm(dados, form));
        };
      }

  }

  

  populaDadosForm(dados:any, formulario:any) {
    /*formulario.setValue({
      nome: formulario.value.nome,
      email: formulario.value.email,
      endereco: {
        rua: dados.logradouro,
        cep: dados.cep,
        numero: '',
        complemento: dados.complemento,
        bairro: dados.bairro,
        cidade: dados.localidade,
        estado: dados.uf
      }
    });*/

    formulario.form.patchValue({
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


  resetaDadosForm(formulario:any) {
    formulario.form.patchValue({
      endereco: {
        rua: null,
        complemento: null,
        bairro: null,
        cidade: null,
        estado: null
      }
    });
  }

}
