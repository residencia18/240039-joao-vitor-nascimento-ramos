import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceWikiService {

  private urlWiki = 'https://pt.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=';

  constructor(private http: HttpClient) { }

  search(atributo: string): Observable<any> {
    const fullUrl = this.urlWiki + atributo + '&origin=*';
    return this.http.get(fullUrl);
  }
}
