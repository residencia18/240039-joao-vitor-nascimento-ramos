import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GenerosService {

  generos = ['Masculino', 'Feminino', 'Outro'];

  getGeneros(){
    return this.generos;
  }

  constructor() { }
}
