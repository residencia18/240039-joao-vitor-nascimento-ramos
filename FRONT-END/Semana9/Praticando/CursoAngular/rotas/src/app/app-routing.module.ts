import { CursosRoutingModule } from './cursos/cursos-routing.module';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AlunosRoutingModule } from './alunos/alunos-routing.module';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes) , CursosRoutingModule , AlunosRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
