const p_array = document.getElementById('array')
const txt_pesquisar = document.getElementById('txt_pesquisar');
const btnPesquisar = document.getElementById('btnPesquisar');
const resultado = document.getElementById('resultado');

const elementosArray = [10,20,30,5,4,6,7,8,11];
p_array.innerHTML="["+elementosArray+"]";

btnPesquisar.addEventListener('click', (evt) =>{
    const encontrou = elementosArray.find( (e) =>{
        if(e == txt_pesquisar.value){
            return e;
        }
        
    })
    
    if(encontrou!=undefined){
        resultado.innerHTML="Encontrado";
    }else{
        resultado.innerHTML="Não encontrado"
    }
})
