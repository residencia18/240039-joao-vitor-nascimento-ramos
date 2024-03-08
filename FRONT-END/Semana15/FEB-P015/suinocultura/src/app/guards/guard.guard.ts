import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const activateGuard: CanActivateFn = (route, state) => {
  const servicoAutenticacao = inject(AuthService);
  const router = inject(Router);

  if(servicoAutenticacao.isLogged()) {
    console.log('autenticado');
    return true;
  }else{
    console.log('Não autenticado');
    router.navigate(['/login']);
    return false;
  
  }
};