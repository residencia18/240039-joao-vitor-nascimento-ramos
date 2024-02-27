import { NgModule } from '@angular/core';
import { CursosComponent } from './cursos.component';
import { CursoDetalheComponent } from './curso-detalhe/curso-detalhe.component';
import { CursoNaoEncontradoComponent } from './curso-nao-encontrado/curso-nao-encontrado.component';
import { ServiceService } from './service.service';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { RouterModule } from '@angular/router';


@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        BrowserModule,
        HttpClientModule
        
    ],
    declarations: [
        CursosComponent,
        CursoDetalheComponent,
        CursoNaoEncontradoComponent                
    ],

    exports: [],
    providers: [ServiceService],
    bootstrap: [AppComponent]
})
export class CursoModule {}
