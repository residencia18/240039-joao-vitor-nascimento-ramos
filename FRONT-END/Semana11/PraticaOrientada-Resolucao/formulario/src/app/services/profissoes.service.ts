import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfissoesService {

  profissoes = ['Engenheiro', 'Médico', 'Professor', 'Advogado'];

  getProfissoes(){
    return this.profissoes;
  }

  constructor() { }
}
