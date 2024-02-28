import { AlunosService } from './../alunos/alunos.service';
import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AlunoDetalheComponent } from '../alunos/aluno-detalhe/aluno-detalhe.component';
import { Aluno } from '../aluno';

@Injectable({
    providedIn: 'root'
})
export class AlunoDetalheResolveGuard implements Resolve<Aluno> {

    constructor(private service:AlunosService){

    }

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<any> | Promise<any> | any {

        let id = route.params['id'];

        return this.service.getAluno(id);
    }
}
