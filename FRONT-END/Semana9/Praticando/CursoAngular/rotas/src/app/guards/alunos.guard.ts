import { ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs";
import { AuthService } from "../login/auth.service";
import { Injectable } from "@angular/core";

@Injectable()
export class AlunosGuard implements CanActivateChild{

    constructor(private authService:AuthService ,
                private router:Router){

                }

    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        console.log("GuARDA DE RODAS alunos")
        return true;
    }
}