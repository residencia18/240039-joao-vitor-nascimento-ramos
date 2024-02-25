import { ServiceWikiService } from './Services/service-wiki.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'wiki';

  busca = "";
  resultados: any[] = [];

  constructor(private servico: ServiceWikiService) {}

  onPesquisa(busca: string){
    this.busca = busca;
    this.servico.search(busca).subscribe(data => {
      this.resultados = data.query.search;
    });
  }
}
