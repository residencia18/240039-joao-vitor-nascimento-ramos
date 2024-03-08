import { Component, PipeTransform } from '@angular/core';
import { Suino } from '../../model/suino';

import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { DecimalPipe } from '@angular/common';
import { CampoSuino } from '../../model/Enums/CampoSuino';
import { EditarSuinoComponent } from '../editar-suino/editar-suino.component';
import { MatDialog } from '@angular/material/dialog';
import { DatabaseService } from '../../services/database.service';

const SUINOS: Suino[] = [
  {
    brinco: 'ABC123',
    brinco_pai: 'DEF456',
    brinco_mae: 'GHI789',
    dt_nasc: '2020-02-05',
    dt_saida: '2021-05-20',
    status: 'Vendido',
    sexo: 'Fêmea',
  },
  {
    brinco: 'DEF456',
    brinco_pai: 'XYZ789',
    brinco_mae: 'ABC123',
    dt_nasc: '2019-04-10',
    dt_saida: ' ',
    status: 'Ativo',
    sexo: 'Macho',
  },
  {
    brinco: 'ABC456',
    brinco_pai: 'DEF456',
    brinco_mae: 'GHI789',
    dt_nasc: '2020-02-05',
    dt_saida: '2021-05-20',
    status: 'Vendido',
    sexo: 'Fêmea',
  },
  {
    brinco: 'DEF789',
    brinco_pai: 'XYZ789',
    brinco_mae: 'ABC123',
    dt_nasc: '2019-04-10',
    dt_saida: ' ',
    status: 'Ativo',
    sexo: 'Macho',
  },
  {
    brinco: 'ABC789',
    brinco_pai: 'DEF456',
    brinco_mae: 'GHI789',
    dt_nasc: '2020-02-05',
    dt_saida: '2021-05-20',
    status: 'Vendido',
    sexo: 'Fêmea',
  },
  {
    brinco: 'DEF231',
    brinco_pai: 'XYZ789',
    brinco_mae: 'ABC123',
    dt_nasc: '2019-04-10',
    dt_saida: ' ',
    status: 'Ativo',
    sexo: 'Macho',
  },
];

@Component({
  selector: 'app-listagem-suinos',
  templateUrl: './listagem-suinos.component.html',
  styleUrls: ['./listagem-suinos.component.css'],
  providers: [DecimalPipe],
})
export class ListagemSuinosComponent {
  suinosFiltrados$: Observable<Suino[]>;
  filter = new FormControl('');
  camposSuino = Object.values(CampoSuino);
  campoSelecionado: CampoSuino = CampoSuino.brinco;

  constructor(private pipe: DecimalPipe, public dialog: MatDialog , private dados:DatabaseService) {
    this.suinosFiltrados$ = this.filter.valueChanges.pipe(
      startWith(''), // Emite um valor inicial vazio
      map((valor) => this.filtrarSuinos(valor))
    );
    console.log(dados.getSuinos())

  }

  filtrarSuinos(valor: string | null): Suino[] {
    if (!valor || !this.campoSelecionado) {
      return SUINOS;
    }

    const termo = valor.toLowerCase();
    return SUINOS.filter((suino) => {
      const valorCampo = suino[this.campoSelecionado].toLowerCase();
      return valorCampo.includes(termo);
    });
  }

  onExcluir(suino: Suino): void {
    const index = SUINOS.indexOf(suino);
    if(index !== -1 && confirm("Deseja remover este suíno?")){
      SUINOS.splice(index, 1);
      this.filter.setValue('');
    }
  }

  onEditar(suino: Suino): void {
    const dialogRef = this.dialog.open(EditarSuinoComponent,{
      width: '500px',
      data: suino
    });
  }
}
