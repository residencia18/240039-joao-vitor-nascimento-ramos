import { EventEmitter, Injectable } from '@angular/core';
import { Usuario } from './usuario';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado:boolean= false;

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router:Router) { }


  fazerLogin(usuario:Usuario){
    if(usuario.nome === 'usuario' && usuario.senha==='1234'){
      this.usuarioAutenticado=true;

      this.mostrarMenuEmitter.emit(this.usuarioAutenticado);

      this.router.navigate(['/'])
    }else{
      this.mostrarMenuEmitter.emit(this.usuarioAutenticado);
    }
  }


  autenticado(){
    return this.usuarioAutenticado;
  }
}
