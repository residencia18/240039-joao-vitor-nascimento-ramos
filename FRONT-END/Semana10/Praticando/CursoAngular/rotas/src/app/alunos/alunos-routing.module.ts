import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AlunosComponent } from './alunos.component';
import { AlunoDetalheComponent } from './aluno-detalhe/aluno-detalhe.component';
import { AlunosFormComponent } from './alunos-form/alunos-form.component';
import { AlunosGuard } from '../guards/alunos.guard';



const routes: Routes = [
    {path:'', component:AlunosComponent ,
      canActivateChild: [AlunosGuard],
    children: [
        {path:'novo',component:AlunosFormComponent},
        {path:':id',component:AlunoDetalheComponent},
        {path:':id/edit',component:AlunosFormComponent}
    ]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlunosRoutingModule { }
