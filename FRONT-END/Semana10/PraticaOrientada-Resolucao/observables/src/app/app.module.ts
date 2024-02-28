import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PesquisaComponent } from './components/pesquisa/pesquisa.component';
import { VeiculosService } from './services/veiculos.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { TiposComponent } from './components/tipos/tipos.component';
import { VeiculosComponent } from './components/veiculos/veiculos.component';
import { PropriedadesComponent } from './components/propriedades/propriedades.component';
import { ValorComponent } from './components/propriedades/valor/valor.component';
import { ListagemComponent } from './components/listagem/listagem.component';

@NgModule({
  declarations: [
    AppComponent,
    PesquisaComponent,
    TiposComponent,
    VeiculosComponent,
    PropriedadesComponent,
    ValorComponent,
    ListagemComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [VeiculosService],
  bootstrap: [AppComponent]
})
export class AppModule { }
