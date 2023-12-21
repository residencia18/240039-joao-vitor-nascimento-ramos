var apiResultados = new Promise(function (resolve, reject) {
    fetch("http://servicodados.ibge.gov.br/api/v3/noticias/?qtd=3")
        .then(function (response) {
        if (response.ok) {
            return response.json();
        }
        else {
            throw new Error(response.statusText);
        }
    })
        .then(function (data) { return resolve(data); })
        .catch(function (error) { return reject(error); });
});
apiResultados
    .then(function (data) {
    try {
        var json = JSON.stringify(data);
        var objetoJS = JSON.parse(json);
        var resultadosDOM_1 = document.querySelector("#resultados");
        if (resultadosDOM_1 != null) {
            objetoJS.items.map(function (item, id) {
                var novoLink = document.createElement("a");
                resultadosDOM_1.children[id * 2].innerHTML = item.titulo;
                resultadosDOM_1.children[id * 2 + 1].innerHTML = item.introducao;
                novoLink.href = item.link;
                novoLink.textContent = "Saiba mais...";
                resultadosDOM_1.children[id * 2 + 1].appendChild(novoLink);
            });
        }
    }
    catch (error) {
        console.log(error);
    }
})
    .catch(function (error) { return console.log(error); });
var apiMeteorologia = new Promise(function (resolve, reject) {
    fetch("https://api.openweathermap.org/data/2.5/weather?lat=-14.795999193380537&lon=-39.17346823068079&lang=pt_br&appid=6b0044ed510c0cabb54f601d3daa70de")
        .then(function (response) {
        if (response.ok) {
            return response.json();
        }
        else {
            throw new Error(response.statusText);
        }
    })
        .then(function (data) { return resolve(data); })
        .catch(function (error) { return reject(error); });
});
apiMeteorologia
    .then(function (data) {
    try {
        var json = JSON.stringify(data);
        var objetoJS = JSON.parse(json);
        var servicosDOM_1 = document.querySelector("#servicos");
        if (servicosDOM_1 != null) {
            var dadosTempo = [
                "Previsão do Tempo no Campus",
                (objetoJS.main.temp - 273.15).toFixed(1) + "°C",
                "Umidade do ar: " + objetoJS.main.humidity + "%",
                objetoJS.weather[0].description.toString(),
            ];
            dadosTempo.map(function (x) {
                var novoElemento = document.createElement("div");
                novoElemento.setAttribute("class", "item");
                novoElemento.textContent = x;
                servicosDOM_1.appendChild(novoElemento);
            });
            var url = "http://openweathermap.org/img/w/" +
                objetoJS.weather[0].icon +
                ".png";
            var icon = document.createElement("img");
            icon.src = url;
            servicosDOM_1.appendChild(icon);
        }
    }
    catch (error) {
        console.log(error);
    }
})
    .catch(function (error) { return console.log(error); });
var apiNoticias = new Promise(function (resolve, reject) {
    fetch("https://api.worldnewsapi.com/search-news?api-key=a1866c1a071e4ef49aef93db0f2beab5&language=pt&entities=LOC:Brasil&source-countries=br&number=3&earliest-publish-date=2023-10-01")
        .then(function (response) {
        if (response.ok) {
            return response.json();
        }
        else {
            throw new Error(response.statusText);
        }
    })
        .then(function (data) { return resolve(data); })
        .catch(function (error) { return reject(error); });
});
apiNoticias
    .then(function (data) {
    try {
        var json = JSON.stringify(data);
        var objetoJS = JSON.parse(json);
        var noticiasDOM_1 = document.querySelector("#noticias");
        if (noticiasDOM_1 != null) {
            objetoJS.news.map(function (item, id) {
                var novoLink = document.createElement("a");
                novoLink.innerHTML = item.title;
                novoLink.href = item.url;
                noticiasDOM_1.appendChild(novoLink);
            });
        }
    }
    catch (error) {
        console.log(error);
    }
})
    .catch(function (error) { return console.log(error); });
