import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SuinosService } from '../../services/suinos.service';
import { Suino } from '../../model/suino';

@Component({
  selector: 'app-cadastro-suino',
  templateUrl: './cadastro-suino.component.html',
  styleUrl: './cadastro-suino.component.css',
})
export class CadastroSuinoComponent {
  cadastro_suino: FormGroup;
  lista_status: string[] = ['Ativo', 'Vendido', 'Morto'];

  constructor(private suinosService: SuinosService) {
    this.cadastro_suino = new FormGroup({
      brinco: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]+$'),
      ]),
      brinco_pai: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]+$'),
      ]),
      brinco_mae: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]+$'),
      ]),
      dt_nasc: new FormControl('', [
        Validators.required,
        this.dataNascimentoValidator,
      ]),
      dt_saida: new FormControl('', [
        Validators.required,
        this.dataSaidaValidator,
      ]),
      status: new FormControl('', [Validators.required]),
      sexo: new FormControl('', [Validators.required]),
    });
  }

  // Data de nascimento Validator

  dataNascimentoValidator = () => {
    // Verifica se o formulário está inicializado
    if (!this.cadastro_suino) {
      return null;
    }

    const dataNascimento = this.cadastro_suino.get('dt_nasc')?.value;

    // Verifica se a data de nascimento foi preenchida
    if (!dataNascimento) {
      return null;
    }

    const hoje = new Date();
    const nascimento = new Date(dataNascimento);

    return hoje <= nascimento ? { invalido: true } : null;
  };

  dataSaidaValidator = () => {
    // Verifica se o formulário está inicializado
    if (!this.cadastro_suino) {
      return null;
    }

    const dataSaida = this.cadastro_suino.get('dt_saida')?.value;
    const dataNascimento = this.cadastro_suino.get('dt_nasc')?.value;

    // Verifica se a data de nascimento foi preenchida
    if (!dataSaida || !dataNascimento) {
      return null;
    }

    const nascimento = new Date(dataNascimento);
    const saida = new Date(dataSaida);

    return nascimento > saida ? { invalido: true } : null;
  };
  onSubmit() {
    if(this.cadastro_suino.invalid)
      return;

    let suino: Suino;

  }
}
