import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DataFormComponent } from './components/data-form/data-form.component';
import { TemplateFormModule } from './components/template-form/template-form.module';

@NgModule({
  declarations: [
    AppComponent,
    DataFormComponent,
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TemplateFormModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
