import { Injectable } from '@angular/core';
import { CanDeactivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AlunosFormComponent } from '../alunos/alunos-form/alunos-form.component';
import { IFormCanDeactive } from './iform-candactive';

@Injectable({
    providedIn: 'root'
})
export class AlunosDeactivateGuard implements CanDeactivate<IFormCanDeactive> {
    canDeactivate(
        component: IFormCanDeactive,
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<boolean> | Promise<boolean> | boolean {

        return component.podeDesativar();
    }
}