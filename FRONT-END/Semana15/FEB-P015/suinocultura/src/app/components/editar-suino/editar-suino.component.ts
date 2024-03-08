import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { SuinosService } from '../../services/suinos.service';

@Component({
  selector: 'app-editar-suino',
  templateUrl: './editar-suino.component.html',
  styleUrl: './editar-suino.component.css',
})
export class EditarSuinoComponent {
  cadastro_suino: FormGroup;
  lista_status: string[] = ['Ativo', 'Vendido', 'Morto'];
  suino: any;
  sexo: string = '';

  constructor(public dialogRef: MatDialogRef<EditarSuinoComponent>,@Inject(MAT_DIALOG_DATA) public data: any, private suinoService: SuinosService) {
    this.suino = {...data};
    this.sexo = this.suino.sexo;
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

  salvarAlteracoes(): void {
    console.log('Alterações salvas:', this.suino);
    this.dialogRef.close();
  };

  cancelar(): void {
    if(this.cadastro_suino.valueChanges)
      if(confirm("Deseja sair sem salvar as alterações?")){
        this.dialogRef.close();
      }
  };

}
