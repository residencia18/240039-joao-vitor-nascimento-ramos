const p_array = document.getElementById('array')
const btnVerificar = document.getElementById('btnVerificar');
const resultado = document.getElementById('resultado');

const elementosArray = [10,17,15,5,4,6,7,8,11];
p_array.innerHTML="["+elementosArray+"]";

btnVerificar.addEventListener('click',(evt)=>{
    resultado.innerHTML="Array não conforme";
    const verificacao = elementosArray.some((e,i)=>{
        if(e<18){
            resultado.innerHTML="Array não conforme na posicao " + i;
        }

        return e>=18
    })

    if(verificacao){
        resultado.innerHTML="OK"
    }
})