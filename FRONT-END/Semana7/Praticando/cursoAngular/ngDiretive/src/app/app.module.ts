import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NovaDiretivaComponent } from './nova-diretiva/nova-diretiva.component';
import { NovinhaDirective } from './novinha.directive';
import { HightightMouseDirective } from './hightight-mouse.directive';
import { HightlightDirective } from './hightlight.directive';

@NgModule({
  declarations: [
    AppComponent,
    NovaDiretivaComponent,
    NovinhaDirective,
    HightightMouseDirective,
    HightlightDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
