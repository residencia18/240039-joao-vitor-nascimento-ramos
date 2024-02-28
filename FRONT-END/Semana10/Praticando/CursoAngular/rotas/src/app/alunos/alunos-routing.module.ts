import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanDeactivate } from '@angular/router';
import { AlunosComponent } from './alunos.component';
import { AlunoDetalheComponent } from './aluno-detalhe/aluno-detalhe.component';
import { AlunosFormComponent } from './alunos-form/alunos-form.component';
import { AlunosGuard } from '../guards/alunos.guard';
import { AlunosDeactivateGuard } from '../guards/aluno-deactivate.guard';
import { AlunoDetalheResolveGuard } from '../guards/aluno-detalhe.resolver';



const routes: Routes = [
    {path:'', component:AlunosComponent ,
      canActivateChild: [AlunosGuard],
    children: [
        {path:'novo',component:AlunosFormComponent},
        {path:':id',component:AlunoDetalheComponent , resolve:{aluno : AlunoDetalheResolveGuard}},
        {path:':id/edit',component:AlunosFormComponent , canDeactivate: [AlunosDeactivateGuard]}
    ]}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AlunosRoutingModule { }
