const p_array = document.getElementById('array')
const btnReduzir = document.getElementById('btnReduzir');
const resultado = document.getElementById('resultado');

const elementosArray = [10,17,15,5,4,6,7,8,11];
p_array.innerHTML="["+elementosArray+"]";
let aux=[];
let atu=[];

btnReduzir.addEventListener('click' ,(el) =>{
    resultado.innerHTML = elementosArray.reduce( (anterior,atual,pos) =>{
        aux.push(anterior);
        atu.push(atual)
        return atual+anterior;
    })
    resultado.innerHTML += "<br/>"+aux+"<br/>"+atu;
})