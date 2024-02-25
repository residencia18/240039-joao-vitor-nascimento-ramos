import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { PesquisaComponent } from './pesquisa/pesquisa.component';
import { PrimeiraCategoriaComponent } from './primeira-categoria/primeira-categoria.component';
import { SegundaCategoriaComponent } from './segunda-categoria/segunda-categoria.component';
import { TerceiraCategoriaComponent } from './terceira-categoria/terceira-categoria.component';
import { UltimaCategoriaComponent } from './ultima-categoria/ultima-categoria.component';
import { ListagemComponent } from './listagem/listagem.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoriasComponent,
    PesquisaComponent,
    PrimeiraCategoriaComponent,
    SegundaCategoriaComponent,
    TerceiraCategoriaComponent,
    UltimaCategoriaComponent,
    ListagemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule 
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
