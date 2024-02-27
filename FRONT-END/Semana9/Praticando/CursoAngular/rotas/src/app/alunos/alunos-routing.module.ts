import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlunosComponent } from './alunos.component';
import { AlunoDetalheComponent } from './aluno-detalhe/aluno-detalhe.component';
import { AlunosFormComponent } from './alunos-form/alunos-form.component';



const routes: Routes = [
    {path:'alunos', component:AlunosComponent , children: [
        {path:'novo',component:AlunosFormComponent},
        {path:':id',component:AlunoDetalheComponent},
        {path:':id/editar',component:AlunosFormComponent}
    ]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AlunosRoutingModule { }
