import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanLoad, Route, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../login/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate ,CanLoad {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {
    if (this.authService.autenticado()) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }


  canLoad(route: Route ): boolean | Observable<boolean > | Promise<boolean > {
    if (this.authService.autenticado()) {
      return true;
    }
    return false;
  }

}
