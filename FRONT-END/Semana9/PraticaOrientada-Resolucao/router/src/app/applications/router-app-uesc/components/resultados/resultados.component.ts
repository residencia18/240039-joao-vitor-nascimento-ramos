import { Component } from '@angular/core';

const apiResultados: Promise<void> = new Promise<void>((resolve, reject) => {
  fetch('http://servicodados.ibge.gov.br/api/v3/noticias/?qtd=3')
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error(response.statusText);
      }
    })
    .then((data) => resolve(data))
    .catch((error) => reject(error));
});

apiResultados
  .then((data) => {
    try {
      const json = JSON.stringify(data);
      const objetoJS = JSON.parse(json);
      const resultadosDOM = document.querySelector('#resultados');
      if (resultadosDOM != null) {
        objetoJS.items.map((item: any, id: number) => {
          const novoLink = document.createElement('a');
          resultadosDOM.children[id * 2].innerHTML = item.titulo;
          resultadosDOM.children[id * 2 + 1].innerHTML = item.introducao;
          novoLink.href = item.link;
          novoLink.textContent = 'Saiba mais...';
          resultadosDOM.children[id * 2 + 1].appendChild(novoLink);
        });
      }
    } catch (error) {
      console.log(error);
    }
  })
  .catch((error) => console.log(error));

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrl: './resultados.component.css'
})
export class ResultadosComponent {

}
