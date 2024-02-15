function nome(){
    console.log("CFB Cursos")
}

const botao = document.querySelector('button')

botao.addEventListener('click',mudarTexto)

function mudarTexto(){
    let d1 = document.getElementById('1');
    let d2 = document.getElementById('2');
    let d3 = document.getElementById('3');
    d1.innerHTML="Vasco";
    d2.innerHTML="Flamengo";
    d3.innerHTML="São Paulo";
}

nome()

for(let i = 0 ; i < 10 ; i++){
    nome()
}


function parOuIrmpar(valor){
    return valor%2==0 ? "Par" : "Impar"
}
console.log(parOuIrmpar(5)
)

function soma(...valores){
    let somatotal = 0;
    for( n of valores){
        somatotal +=n;
    }
    return somatotal
}

console.log(soma(1,5,7,5,3,8,9))
