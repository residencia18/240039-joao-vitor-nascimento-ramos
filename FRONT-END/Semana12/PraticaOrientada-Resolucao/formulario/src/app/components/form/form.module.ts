import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormComponent } from './form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import {  HttpClientModule } from '@angular/common/http';
import { UtilsModule } from '../../utils/utils.module';
import { GenerosService } from '../../services/generos.service';
import { ProfissoesService } from '../../services/profissoes.service';
import { FormService } from '../../Service/form.service';



@NgModule({
  declarations: [FormComponent],
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    UtilsModule
  ]
  ,
  exports: [
    FormComponent
  ],
  providers: [GenerosService,ProfissoesService,FormService],

})
export class FormModule { }
