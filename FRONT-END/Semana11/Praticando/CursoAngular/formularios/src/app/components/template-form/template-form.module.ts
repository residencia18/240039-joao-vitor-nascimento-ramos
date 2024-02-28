import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { TemplateFormComponent } from './template-form.component';
import { HttpClientModule } from '@angular/common/http';
import { CompartilhadoModule } from '../share/compartilhado/compartilhado.module';


@NgModule({
  declarations: [   TemplateFormComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    HttpClientModule,
    CompartilhadoModule,
    ]
})
export class TemplateFormModule { }
