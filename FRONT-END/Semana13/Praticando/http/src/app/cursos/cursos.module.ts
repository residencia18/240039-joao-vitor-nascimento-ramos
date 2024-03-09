import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CursosRoutingModule } from './cursos-routing.module';
import { CursosListaComponent } from './cursos-lista/cursos-lista.component';
import {  HttpClientModule } from '@angular/common/http';
import { CursosListaService } from './cursos-lista/Service/cursos-lista.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { CursosFormComponent } from './cursos-form/cursos-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CursoResolverGuard } from './guards/guards.guard';


@NgModule({
  declarations: [
    CursosListaComponent,
    CursosFormComponent
  ],
  imports: [
    CommonModule,
    CursosRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
    ],
  providers:[
    CursosListaService,
    BsModalService
    ]
})
export class CursosModule { }
