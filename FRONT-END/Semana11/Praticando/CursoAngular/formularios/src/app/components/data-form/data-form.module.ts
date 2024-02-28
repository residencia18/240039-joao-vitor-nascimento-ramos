import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DataFormComponent } from './data-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CompartilhadoModule } from '../share/compartilhado/compartilhado.module';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [DataFormComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CompartilhadoModule,
    HttpClientModule
  ]
})
export class DataFormModule { }
