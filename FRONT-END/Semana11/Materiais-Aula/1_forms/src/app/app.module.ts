import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AnimalFormComponent } from './animal-form/animal-form.component';
import {MatSelectModule} from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { FormularioComumComponent } from './formulario-comum/formulario-comum.component';
import { HttpClientModule } from '@angular/common/http';
import { FormReativoComponent } from './form-reativo/form-reativo.component';

@NgModule({
  declarations: [
    AppComponent,
    AnimalFormComponent,
    FormularioComumComponent,
    FormReativoComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatInputModule,
    FormsModule,
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
