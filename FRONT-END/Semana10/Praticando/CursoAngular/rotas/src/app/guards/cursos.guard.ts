import { ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "../login/auth.service";
import { Injectable } from "@angular/core";

@Injectable()
export class CursosGuard implements CanActivateChild{

    constructor(private authService:AuthService ,
                private router:Router){

                }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        console.log("GuARDA DE RODAS CURSOS")
        return true;
    }
}