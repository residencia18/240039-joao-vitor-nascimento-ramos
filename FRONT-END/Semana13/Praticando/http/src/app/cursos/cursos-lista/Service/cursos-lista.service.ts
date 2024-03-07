import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Curso } from '../../../model/curso';
import { delay, take } from 'rxjs';
import { AlertModalService } from '../../../alert-model/alert-model.service';

@Injectable({
  providedIn: 'root'
})
export class CursosListaService {

  private readonly API = "http://localhost:3000/cursos";

  
  constructor(private http:HttpClient) { 

  }

  list(){
    return this.http.get<Curso[]>(this.API).pipe(delay(2000));
  }

  create(curso:Curso){
    return this.http.post(this.API,curso).pipe(take(1));
  }

  loadByID(id:string){
    return this.http.get(`${this.API}/${id}`).pipe(take(1));
  }
}
