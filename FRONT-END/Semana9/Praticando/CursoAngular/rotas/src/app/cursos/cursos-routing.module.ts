import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CursosComponent } from './cursos.component';
import { CursoDetalheComponent } from './curso-detalhe/curso-detalhe.component';
import { CursoNaoEncontradoComponent } from './curso-nao-encontrado/curso-nao-encontrado.component';

const routes: Routes = [
  {path: 'cursos', component: CursosComponent},
  {path: 'naoEncontrado', component: CursoNaoEncontradoComponent},
  {path: 'curso/:id', component: CursoDetalheComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class CursosRoutingModule { }
