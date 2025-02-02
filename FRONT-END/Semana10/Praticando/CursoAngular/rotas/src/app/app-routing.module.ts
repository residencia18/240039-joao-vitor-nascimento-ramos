import { AuthGuard } from './guards/auth-guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CursosGuard } from './guards/cursos.guard';
import { AlunosGuard } from './guards/alunos.guard';
import { PaginaNaoEncontradaComponent } from './pagina-nao-encontrada/pagina-nao-encontrada.component';

const routes: Routes = [
  { path: 'cursos',
   loadChildren: () => import('./cursos/cursos.module').then(m => m.CursoModule),
    canActivate: [AuthGuard] ,
    canActivateChild: [CursosGuard],
    canLoad: [AuthGuard]
  },
  { path: 'alunos',
   loadChildren: () => import('./alunos/alunos.module').then(m => m.AlunosModule),
   canActivate: [AuthGuard],
   canLoad: [AuthGuard]
  },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent ,
  canActivate: [AuthGuard] ,
  canLoad: [AuthGuard]
  },
  {path:'', redirectTo:'home' , pathMatch:'full'},
  {path:'**', component:PaginaNaoEncontradaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
