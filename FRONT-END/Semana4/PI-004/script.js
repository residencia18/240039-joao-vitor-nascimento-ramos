const btnComprar = document.querySelectorAll('.roteiro-comprar'); 
const btnAdd = document.querySelector('#adicionar');
const container = document.querySelector('.container');

btnComprar.forEach((btn) => {
    btn.addEventListener('click', () => {
        const roteiro = btn.parentElement;
        const dados = [...roteiro.children];
        const conteudo = {
            "Local": dados[1].innerText,
            "Roteiro": dados[2].innerText.split('\n'),
            "Preço": dados[3].innerText,
            "Observação": dados[4].innerText.split('\n')
        };
        console.log(conteudo);
    });
});

btnAdd.addEventListener('click', () => {
    event.preventDefault();

    const dados = document.querySelectorAll('input');
    var form = [];
    dados.forEach((d, i) => {
        if (!d.isSameNode(btnAdd))
            form[i] = d.value;
    });

    const roteiro = document.createElement('div');
    const img = document.createElement('img');
    const h1 = document.createElement('h1');
    const ul = document.createElement('ul');
    const li = document.createElement('li');
    const h2 = document.createElement('h2');
    const p = document.createElement('p');
    const button = document.createElement('button');

    roteiro.classList.add('roteiros-viagens'); 
    img.src = form[4];
    h1.textContent = form[0];
    li.textContent = form[1];
    h2.textContent = form[2];
    p.textContent = form[3];
    button.textContent = "Comprar";
    button.classList.add('roteiro-comprar'); 
    button.id = 'btn-comprar';

    ul.appendChild(li);
    roteiro.appendChild(img);
    roteiro.appendChild(h1);
    roteiro.appendChild(ul);
    roteiro.appendChild(h2);
    roteiro.appendChild(p);
    roteiro.appendChild(button);

    container.appendChild(roteiro);
});
