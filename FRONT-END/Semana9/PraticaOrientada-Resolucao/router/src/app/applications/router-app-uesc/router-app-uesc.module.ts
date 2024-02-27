import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterAppUescComponent } from './router-app-uesc.component';
import { DestaquesComponent } from './components/destaques/destaques.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { MenuComponent } from './components/menu/menu.component';
import { NoticiasComponent } from './components/noticias/noticias.component';
import { ResultadosComponent } from './components/resultados/resultados.component';
import { ServicosComponent } from './components/servicos/servicos.component';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [RouterAppUescComponent,
        DestaquesComponent,
    FooterComponent,
HeaderComponent,
MenuComponent,
NoticiasComponent,
ResultadosComponent,
ServicosComponent],
    imports: [ CommonModule,
    FormsModule ],
    exports: [],
    providers: [],
})
export class AppUescModule {}