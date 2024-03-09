import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CursosModule } from './cursos/cursos.module';
import { AlertModelComponent } from './alert-model/alert-model.component';
import { CursoResolverGuard } from './cursos/guards/guards.guard';

@NgModule({
  declarations: [
    AppComponent,
    AlertModelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CursosModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
