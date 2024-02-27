import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterReaderComponent } from './router-reader/router-reader.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { ListagemComponent } from './listagem/listagem.component';
import { PesquisaComponent } from './pesquisa/pesquisa.component';
import { PrimeiraCategoriaComponent } from './primeira-categoria/primeira-categoria.component';
import { SegundaCategoriaComponent } from './segunda-categoria/segunda-categoria.component';
import { TerceiraCategoriaComponent } from './terceira-categoria/terceira-categoria.component';
import { UltimaCategoriaComponent } from './ultima-categoria/ultima-categoria.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
    declarations: [RouterReaderComponent,
    CategoriasComponent,
    ListagemComponent,
    PesquisaComponent,
PrimeiraCategoriaComponent,
SegundaCategoriaComponent,
TerceiraCategoriaComponent,
UltimaCategoriaComponent],
    imports: [ CommonModule ,
    FormsModule,
    HttpClientModule],
    exports: [],
    providers: [],
})
export class JReaderModule {}