import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Suino } from '../model/suino';
import { Observable, catchError, map, retry, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatabaseService {

  endpoint = "https://suinocultura-31ff3-default-rtdb.firebaseio.com";
  ;
  constructor(private http: HttpClient) {}

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  getSuinos(): Observable<Suino[]> {
    return this.http
      .get<{ [key: string]: Suino }>(`${this.endpoint}/Suinos/NbTbpgyXVjLd0zosXdfL`)
      .pipe(
        tap(response => {
          console.log('Objetos Suinos:', response);
        }),
        map(response => {
          return Object.keys(response).map(key => response[key]);
        }),
        retry(2),
        catchError(this.handleError)
      );
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage =
        `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }

    return throwError(() => new Error('errorMessage'));
  }

}
