import { AuthGuard } from './guards/auth-guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CursosGuard } from './guards/cursos.guard';
import { AlunosGuard } from './guards/alunos.guard';

const routes: Routes = [
  { path: 'cursos',
   loadChildren: () => import('./cursos/cursos.module').then(m => m.CursoModule),
    canActivate: [AuthGuard] ,
    canActivateChild: [CursosGuard]
  },
  { path: 'alunos',
   loadChildren: () => import('./alunos/alunos.module').then(m => m.AlunosModule),
   canActivate: [AuthGuard]
  },
  { path: 'login', component: LoginComponent },
  { path: '', component: HomeComponent ,
  canActivate: [AuthGuard] 
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
