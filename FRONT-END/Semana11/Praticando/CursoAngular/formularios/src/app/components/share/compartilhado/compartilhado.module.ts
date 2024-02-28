import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormComponent } from './debug/form/form.component';
import { ErrorComponent } from './error/error.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';



@NgModule({
  declarations: [FormComponent,
    ErrorComponent],
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule
  ],
  exports: [
    FormComponent,
    ErrorComponent
  ]
})
export class CompartilhadoModule { }
