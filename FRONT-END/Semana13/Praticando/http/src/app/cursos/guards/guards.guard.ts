import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { Curso } from '../../model/curso';
import { CursosListaService } from '../cursos-lista/Service/cursos-lista.service';

@Injectable({
  providedIn: 'root'
})
export class CursoResolverGuard implements Resolve<Curso | null> {
  constructor(private service: CursosListaService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Curso | null> {
    const id = route.paramMap.get('id');

    if (id) {
      return this.service.loadByID(id);
    } else {
      return of({
        id: null,
        nome: null
      });
    }
  }
}
