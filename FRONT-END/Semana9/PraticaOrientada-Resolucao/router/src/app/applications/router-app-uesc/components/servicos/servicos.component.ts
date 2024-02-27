import { Component } from '@angular/core';

const apiMeteorologia: Promise<void> = new Promise<void>((resolve, reject) => {
  fetch(
    'https://api.openweathermap.org/data/2.5/weather?lat=-14.795999193380537&lon=-39.17346823068079&lang=pt_br&appid=6b0044ed510c0cabb54f601d3daa70de'
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

apiMeteorologia
  .then((data) => {
    try {
      const json = JSON.stringify(data);
      const objetoJS = JSON.parse(json);
      const servicosDOM = document.querySelector('#servicos');
      if (servicosDOM != null) {
        const dadosTempo = [
          'Previsão do Tempo no Campus',
          (objetoJS.main.temp - 273.15).toFixed(1) + '°C',
          'Umidade do ar: ' + objetoJS.main.humidity + '%',
          objetoJS.weather[0].description.toString(),
        ];
        dadosTempo.map((x) => {
          const novoElemento = document.createElement('div');
          novoElemento.setAttribute('class', 'item');
          novoElemento.textContent = x;
          servicosDOM.appendChild(novoElemento);
        });

        const url =
          'http://openweathermap.org/img/w/' +
          objetoJS.weather[0].icon +
          '.png';
        const icon = document.createElement('img');
        icon.src = url;
        servicosDOM.appendChild(icon);
      }
    } catch (error) {
      console.log(error);
    }
  })
  .catch((error) => console.log(error));

@Component({
  selector: 'app-servicos',
  templateUrl: './servicos.component.html',
  styleUrl: './servicos.component.css'
})
export class ServicosComponent {

}
