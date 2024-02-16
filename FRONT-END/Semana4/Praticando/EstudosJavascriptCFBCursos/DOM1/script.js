document.querySelector('h1').textContent= 'Diga ae DOM'

let novoParagrafo = document.createElement('p');

novoParagrafo.textContent = 'Esse paragrafo foi criado com javascript'

document.body.appendChild(novoParagrafo)