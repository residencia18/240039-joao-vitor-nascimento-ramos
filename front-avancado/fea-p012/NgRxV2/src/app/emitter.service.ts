import { EventEmitter, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmitterService {
  alterarTarefaEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor() { }
}
