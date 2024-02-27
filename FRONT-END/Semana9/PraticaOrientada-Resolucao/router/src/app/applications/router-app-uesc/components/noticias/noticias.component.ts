import { Component } from '@angular/core';

const apiNoticias: Promise<void> = new Promise<void>((resolve, reject) => {
  fetch(
    'https://api.worldnewsapi.com/search-news?api-key=a1866c1a071e4ef49aef93db0f2beab5&language=pt&entities=LOC:Brasil&source-countries=br&number=3&earliest-publish-date=2023-10-01'
  )
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

apiNoticias
  .then((data) => {
    try {
      const json = JSON.stringify(data);
      const objetoJS = JSON.parse(json);
      const noticiasDOM = document.querySelector('#noticias');
      if (noticiasDOM != null) {
        const links = noticiasDOM.querySelectorAll("a");
        objetoJS.news.map((item: any, id: number) => {
          links[id].innerHTML = item.title;
          links[id].href = item.url;
        });
      }
    } catch (error) {
      console.log(error);
    }
  })
  .catch((error) => console.log(error));


@Component({
  selector: 'app-noticias',
  templateUrl: './noticias.component.html',
  styleUrl: './noticias.component.css'
})
export class NoticiasComponent {

}
