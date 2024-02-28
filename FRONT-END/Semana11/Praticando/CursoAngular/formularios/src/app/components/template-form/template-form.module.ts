import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { TemplateFormComponent } from './template-form.component';
import { FormComponent } from './debug/form/form.component';
import { ErrorComponent } from './error/error.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [   TemplateFormComponent,
    FormComponent,
    ErrorComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    HttpClientModule
    ]
})
export class TemplateFormModule { }
