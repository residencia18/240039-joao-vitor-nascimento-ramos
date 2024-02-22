import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { meuPrimeiroComponent } from './meu-primeiro/meu-primeiro-component';
import { SegundoComponentComponent } from './segundo-component/segundo-component.component';
import { CursosModule } from './cursos/cursos.module';
import { CursosService } from './cursos/cursos.service';

@NgModule({
  declarations: [
    AppComponent,
    meuPrimeiroComponent,
    SegundoComponentComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CursosModule
  ],
  providers: [CursosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
