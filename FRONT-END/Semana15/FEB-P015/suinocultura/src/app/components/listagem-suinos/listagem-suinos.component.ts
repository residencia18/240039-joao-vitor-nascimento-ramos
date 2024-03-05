import { Component, PipeTransform } from '@angular/core';
import { Suino } from '../../model/suino';

import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { DecimalPipe } from '@angular/common';
import { CampoSuino } from '../../model/Enums/CampoSuino';

const SUINOS: Suino[] = [
  {
    brinco: 'ABC123',
    brinco_pai: 'DEF456',
    brinco_mae: 'GHI789',
    dt_nasc: '05-02-2020',
    dt_saida: '20-05-2021',
    status: 'Vendido',
    sexo: 'Fêmea',
  },
  {
    brinco: 'DEF456',
    brinco_pai: 'XYZ789',
    brinco_mae: 'ABC123',
    dt_nasc: '10-04-2019',
    dt_saida: ' ',
    status: 'Ativo',
    sexo: 'Macho',
  },
  {
    brinco: 'ABC456',
    brinco_pai: 'DEF456',
    brinco_mae: 'GHI789',
    dt_nasc: '05-02-2020',
    dt_saida: '20-05-2021',
    status: 'Vendido',
    sexo: 'Fêmea',
  },
  {
    brinco: 'DEF789',
    brinco_pai: 'XYZ789',
    brinco_mae: 'ABC123',
    dt_nasc: '10-04-2019',
    dt_saida: ' ',
    status: 'Ativo',
    sexo: 'Macho',
  },
  {
    brinco: 'ABC789',
    brinco_pai: 'DEF456',
    brinco_mae: 'GHI789',
    dt_nasc: '05-02-2020',
    dt_saida: '20-05-2021',
    status: 'Vendido',
    sexo: 'Fêmea',
  },
  {
    brinco: 'DEF231',
    brinco_pai: 'XYZ789',
    brinco_mae: 'ABC123',
    dt_nasc: '10-04-2019',
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

  constructor(private pipe: DecimalPipe) {
    this.suinosFiltrados$ = this.filter.valueChanges.pipe(
      startWith(''), // Emite um valor inicial vazio
      map(valor => this.filtrarSuinos(valor))
    );
  }

  filtrarSuinos(valor: string | null): Suino[] {
    if (!valor || !this.campoSelecionado) {
      return SUINOS;
    }

    const termo = valor.toLowerCase();
    return SUINOS.filter(suino => {
      const valorCampo = suino[this.campoSelecionado].toLowerCase();
      return valorCampo.includes(termo);
    });
  }

}