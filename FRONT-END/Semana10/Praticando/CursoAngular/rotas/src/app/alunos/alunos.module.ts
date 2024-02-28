import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlunosComponent } from './alunos.component';
import { AlunosFormComponent } from './alunos-form/alunos-form.component';
import { AlunoDetalheComponent } from './aluno-detalhe/aluno-detalhe.component';
import { AlunosRoutingModule } from './alunos-routing.module';
import { AlunosService } from './alunos.service';
import { FormsModule } from '@angular/forms';
import { AlunosGuard } from '../guards/alunos.guard';
import { AlunosDeactivateGuard } from '../guards/aluno-deactivate.guard';
import { AlunoDetalheResolveGuard } from '../guards/aluno-detalhe.resolver';

@NgModule({
    declarations: [AlunosComponent, AlunosFormComponent, AlunoDetalheComponent ],
    imports: [ CommonModule , AlunosRoutingModule , FormsModule, RouterModule  ],
    exports: [],
    providers: [AlunosService,AlunosGuard,AlunosDeactivateGuard , AlunoDetalheResolveGuard],
})
export class AlunosModule {}