import { Injectable } from '@angular/core';
import { Atendimento } from '../Model/Atendimento';

import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class ServicoDadosAtendimento {
  endpoint = 'https://feb-13-6e784-default-rtdb.firebaseio.com';
  constructor(private http: HttpClient) {}

  opcoesHttp = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }


  adicionarAtendimento(atendimento: Atendimento) {
    this.http
      .post(this.endpoint + '/atendimentos.json', atendimento)
      .subscribe((response) => {
        console.log(response);
      });
  }

  obterAtendimentos(): Observable<Atendimento[]>{
    return this.http
      .get<Atendimento[]>(this.endpoint + '/atendimentos.json')
      .pipe(retry(2), catchError(this.tratarErro));
  }

  obterAtendimento(id: string): Observable<Atendimento> {
    return this.http
      .get<Atendimento>(this.endpoint + `/atendimentos/${id}.json`)
      .pipe(retry(2), catchError(this.tratarErro));
  }

  atualizarAtendimento(atendimento: Atendimento) {
    return this.http
      .put<Atendimento>(this.endpoint + `/atendimentos/${atendimento.id}.json`, atendimento, this.opcoesHttp)
      .pipe(retry(1), catchError(this.tratarErro))
  }

  tratarErro(erro: HttpErrorResponse) {
    let mensagemErro = '';
    if (erro.error instanceof ErrorEvent) {
      mensagemErro = erro.error.message;
    } else {
      mensagemErro =
        `Código do erro: ${erro.status}, ` + `mensagem: ${erro.message}`;
    }

    return throwError(() => new Error('mensagemErro'));
  }
}
