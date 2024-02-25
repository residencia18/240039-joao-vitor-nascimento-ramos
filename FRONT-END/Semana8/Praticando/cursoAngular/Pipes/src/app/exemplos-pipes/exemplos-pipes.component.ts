import { Component } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-exemplos-pipes',
  templateUrl: './exemplos-pipes.component.html',
  styleUrl: './exemplos-pipes.component.css'
})
export class ExemplosPipesComponent {

  filtro:string = "";

  Livro:any = {
    titulo: 'Learning Javascript Data Structures and Algorithms',
    rating: 4.5431,
    numeroPaginas: 314,
    preco : 44.99,
    dataLancamento: new Date(2016,5,26),
    url: 'http://a.co/glqjpRP'
  };


  lista: string[] = ["Java","C++","Python"];

  adicionarCurso(novoCurso:string){
    this.lista.push(novoCurso);
  }

  obterCursos(){
    if (!this.lista || this.lista.length === 0 || this.filtro=== undefined || this.filtro.trim()==="") {
      return this.lista;
    }

    return this.lista.filter(v => {
      if(v.toLowerCase().indexOf(this.filtro) !== -1){
        return true;
      }else{
        return false;
      }
    });
  }

  valorAsync = new Promise((resolve,reject) =>{
    setTimeout(() =>{
      resolve('Valor assincrono'),5000
    });
  })

  valorAsync2 = new Observable<string>(observer => {
    let count = 0;
    setInterval(() => {
      observer.next('Valor assíncrono 2');
    }, 2000);
  });
}
