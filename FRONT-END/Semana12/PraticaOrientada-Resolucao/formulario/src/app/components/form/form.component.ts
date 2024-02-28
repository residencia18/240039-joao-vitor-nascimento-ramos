import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GenerosService } from '../../services/generos.service';
import { ProfissoesService } from '../../services/profissoes.service';
import { FormService } from '../../Service/form.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent implements OnInit {

  formulario: FormGroup = new FormGroup({});

  generos:string[] = [];
  profissoes:string[] = [];

  constructor(private formBuilder: FormBuilder,
    private http:HttpClient,
    private generoService:GenerosService,
    private profissaoService:ProfissoesService,
    private formularioService: FormService){}

  ngOnInit(): void {

    this.generos = this.generoService.getGeneros();
    this.profissoes = this.profissaoService.getProfissoes();

    this.formulario = this.formBuilder.group({
      nomeUsuario: [null, [Validators.required, Validators.minLength(3), Validators.pattern(/^\S{1,12}$/)]],
      senha: [null, [
        Validators.required,
        Validators.minLength(4),
        Validators.pattern(/^(?=.*[A-Z])(?=.*\W)/) // Pelo menos uma letra maiúscula e um símbolo
      ]],
      email: [null, [Validators.required, Validators.email]],
      nomeCompleto: [null, [Validators.required, this.nomeCompletoValidator]],
      telefone: [null, [Validators.required, Validators.pattern(/^\([1-9]{2}\)\s9?[0-9]{4}-[0-9]{4}$/)]],
      endereco: [null, Validators.required],
      dataNascimento: [null, [Validators.required, this.dataNascimentoValidator]],
      genero: [null, Validators.required],
      profissao: [null, Validators.required]
    });

    this.formularioService.setFormData(this.formulario);
    this.formularioService.getFormData().subscribe((data) => {
      console.log(data.status);
      console.log(data);
    });

  }

  verificaValidTouched(campo: string) {
    const campoControl = this.formulario.get(campo);
    return campoControl?.touched && (campoControl?.value === null || campoControl?.value === '');
  }
  

  aplicaCssErro(campo:any) {
  return {
  'has-error': this.verificaValidTouched(campo),
  'has-feedback': this.verificaValidTouched(campo)
  };
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


  //--------------VERIFICAÇÕES

  // NOME USUARIO

  verificaNomeUsuarioInvalido() {
    const campoNomeUsuario = this.formulario.get('nomeUsuario');
    
    if (campoNomeUsuario?.errors && campoNomeUsuario.touched) {
      if (campoNomeUsuario.errors['required']) {
        return { tipo: 'required', mensagem: 'O nome de usuario é obrigatório.' };
      }
      if (campoNomeUsuario.errors['minlength']) {
        return { tipo: 'minlength', mensagem: 'O nome de usuário deve ter pelo menos 3 caracteres.' };
      }
      if (campoNomeUsuario.errors['pattern']) {
        return { tipo: 'pattern', mensagem: 'O nome de usuário deve ter no máximo 12 caracteres e não pode conter espaços em branco.' };
      }
    }
  
    return null;
  }

  // SENHA

  verificaSenhaInvalida() {
    const campoSenha = this.formulario.get('senha');
    
    if (campoSenha?.errors && campoSenha.touched) {
      if (campoSenha.errors['required']) {
        return { tipo: 'required', mensagem: 'A senha é obrigatória.' };
      }
      if (campoSenha.errors['minlength']) {
        return { tipo: 'minlength', mensagem: 'A senha deve ter pelo menos 4 caracteres.' };
      }
      if (campoSenha.errors['pattern']) {
        return { tipo: 'pattern', mensagem: 'A senha deve conter pelo menos uma letra maiúscula e um símbolo.' };
      }
    }
  
    return null;
  }



  // nome completo

  nomeCompletoValidator = () => {
    const nomeCompleto = this.formulario.get('nomeCompleto');
    if (!nomeCompleto?.value || nomeCompleto?.value.trim().split(' ').length < 2) {
      return { 'nomeCompleto': true };
    }
    return null;
  }
  
  nomeCompletoInvalido() {
    const campoNomeCompleto = this.formulario.get('nomeCompleto');
    
    if (campoNomeCompleto?.errors && campoNomeCompleto.touched) {
      if (campoNomeCompleto.errors['required']) {
        return { tipo: 'required', mensagem: 'O nome completo é obrigatório.' };
      }
      if (campoNomeCompleto.errors['nomeCompleto']) {
        return { tipo: 'nomeCompleto', mensagem: 'O nome completo deve conter um nome e um sobrenome.' };
      }
    }
  
    return null;
  }



// EMAIL
verificaEmailInvalido() {
  const campoEmail = this.formulario.get('email');
  
  if (campoEmail?.errors && campoEmail.touched) {
    if (campoEmail.errors['required']) {
      return { tipo: 'required', mensagem: 'O email é obrigatório.' };
    }
    if (campoEmail.errors['email']) {
      return { tipo: 'email', mensagem: 'O email deve ter um formato válido.' };
    }
  }

  return null;
}

// TELEFONE
verificaTelefoneInvalido() {
  const campoTelefone = this.formulario.get('telefone');
  
  if (campoTelefone?.errors && campoTelefone.touched) {
    if (campoTelefone.errors['required']) {
      return { tipo: 'required', mensagem: 'O telefone é obrigatório.' };
    }
    if (campoTelefone.errors['pattern']) {
      return { tipo: 'pattern', mensagem: 'O telefone deve ter um formato válido (DD) 9XXXX-XXXX.' };
    }
  }

  return null;
}

// Data de nascimento Validator

dataNascimentoValidator = () => {
  // Verifica se o formulário está inicializado
  if (!this.formulario) {
    return null;
  }

  const dataNascimento = this.formulario.get('dataNascimento')?.value;

  // Verifica se a data de nascimento foi preenchida
  if (!dataNascimento) {
    return null;
  }

  const hoje = new Date();
  const nascimento = new Date(dataNascimento);
  let idade = hoje.getFullYear() - nascimento.getFullYear();
  const mes = hoje.getMonth() - nascimento.getMonth();

  if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate())) {
    idade--;
  }

  return idade < 18 ? { 'menorDeIdade': true } : null;
}


// DATA DE NASCIMENTO
verificaDataNascimentoInvalida() {
  const campoDataNascimento = this.formulario.get('dataNascimento');
  
  if (campoDataNascimento?.errors && campoDataNascimento.touched) {
    if (campoDataNascimento.errors['required']) {
      return { tipo: 'required', mensagem: 'A data de nascimento é obrigatória.' };
    }
    if (campoDataNascimento.errors['menorDeIdade']) {
      return { tipo: 'menorDeIdade', mensagem: 'O usuário deve ter pelo menos 18 anos.' };
    }
  }

  return null;
}


// ENDEREÇO
verificaEnderecoInvalido() {
  const campoEndereco = this.formulario.get('endereco');
  
  if (campoEndereco?.errors && campoEndereco.touched) {
    if (campoEndereco.errors['required']) {
      return { tipo: 'required', mensagem: 'O endereço é obrigatório.' };
    }
  }

  return null;
}

// GÊNERO
verificaGeneroInvalido() {
  const campoGenero = this.formulario.get('genero');
  
  if (campoGenero?.errors && campoGenero.touched) {
    if (campoGenero.errors['required']) {
      return { tipo: 'required', mensagem: 'O gênero é obrigatório.' };
    }
  }

  return null;
}

// PROFISSÃO
verificaProfissaoInvalida() {
  const campoProfissao = this.formulario.get('profissao');
  
  if (campoProfissao?.errors && campoProfissao.touched) {
    if (campoProfissao.errors['required']) {
      return { tipo: 'required', mensagem: 'A profissão é obrigatória.' };
    }
  }

  return null;
}

  

}
