import { Component , Output  , EventEmitter} from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {

  @Output() pesquisa: EventEmitter<any> = new EventEmitter();
  atributo:string = "";

  busca(){
    this.pesquisa.emit(this.atributo);
  }

}
