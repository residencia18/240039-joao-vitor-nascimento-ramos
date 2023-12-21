const apiResultados: Promise<void> = new Promise<void>((resolve, reject) => {
  fetch("http://servicodados.ibge.gov.br/api/v3/noticias/?qtd=3")
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
      const resultadosDOM = document.querySelector("#resultados");
      if (resultadosDOM != null) {
        objetoJS.items.map((item: any, id: number) => {
          const novoLink = document.createElement("a");
          resultadosDOM.children[id * 2].innerHTML = item.titulo;
          resultadosDOM.children[id * 2 + 1].innerHTML = item.introducao;
          novoLink.href = item.link;
          novoLink.textContent = "Saiba mais...";
          resultadosDOM.children[id * 2 + 1].appendChild(novoLink);
        });
      }
    } catch (error) {
      console.log(error);
    }
  })
  .catch((error) => console.log(error));

const apiMeteorologia: Promise<void> = new Promise<void>((resolve, reject) => {
  fetch(
    "https://api.openweathermap.org/data/2.5/weather?lat=-14.795999193380537&lon=-39.17346823068079&lang=pt_br&appid=6b0044ed510c0cabb54f601d3daa70de"
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
      const servicosDOM = document.querySelector("#servicos");
      if (servicosDOM != null) {
        const dadosTempo = [
          "Previsão do Tempo no Campus",
          (objetoJS.main.temp - 273.15).toFixed(1) + "°C",
          "Umidade do ar: " + objetoJS.main.humidity + "%",
          objetoJS.weather[0].description.toString(),
        ];
        dadosTempo.map((x) => {
          const novoElemento = document.createElement("div");
          novoElemento.setAttribute("class", "item");
          novoElemento.textContent = x;
          servicosDOM.appendChild(novoElemento);
        });

        const url =
          "http://openweathermap.org/img/w/" +
          objetoJS.weather[0].icon +
          ".png";
        const icon = document.createElement("img");
        icon.src = url;
        servicosDOM.appendChild(icon);
      }
    } catch (error) {
      console.log(error);
    }
  })
  .catch((error) => console.log(error));

const apiNoticias: Promise<void> = new Promise<void>((resolve, reject) => {
  fetch(
    "https://api.worldnewsapi.com/search-news?api-key=a1866c1a071e4ef49aef93db0f2beab5&language=pt&entities=LOC:Brasil&source-countries=br&number=3&earliest-publish-date=2023-10-01"
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
      const noticiasDOM = document.querySelector("#noticias");
      if (noticiasDOM != null) {
        objetoJS.news.map((item: any, id: number) => {
          const novoLink = document.createElement("a");
          novoLink.innerHTML = item.title;
          novoLink.href = item.url;
          noticiasDOM.appendChild(novoLink);
        });
      }
    } catch (error) {
      console.log(error);
    }
  })
  .catch((error) => console.log(error));
