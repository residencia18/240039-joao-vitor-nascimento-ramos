import { Component } from '@angular/core';
import { WikiService } from './Wiki/Services/service-wiki.service';

@Component({
  selector: 'app-router-wiki',
  templateUrl: './router-wiki.component.html',
  styleUrl: './router-wiki.component.css'
})
export class RouterWikiComponent {

  title = 'wiki';

  busca = "";
  resultados: any[] = [];

  constructor(private servico: WikiService) {}

  onPesquisa(busca: string){
    this.busca = busca;
    this.servico.search(busca).subscribe(data => {
      this.resultados = data.query.search;
    });
  }
}
