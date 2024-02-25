import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServicoService {

  constructor() { }


  mostra(msg:string){
    console.log(msg);
  }
}
