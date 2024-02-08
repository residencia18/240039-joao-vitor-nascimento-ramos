function tocaSom(seletorAudio){
    const elemento =  document.querySelector(seletorAudio);

    if(elemento && elemento.localName === 'audio') elemento.play(); 
    else console.log("elemento não encontrado ou seletor inválido");    

}

const listaDeTeclas = document.querySelectorAll('.tecla');

for(const tecla of listaDeTeclas){
    const instrumento = tecla.classList[1];
    const atributo = `#som_${instrumento}`;
    
    tecla.onclick = function (){
        tocaSom(atributo);
    }

    tecla.onkeydown = function (evento) {
        if(evento.code === "Enter" || evento.code === "Space") {
        tecla.classList.add('ativa');
        }else if (evento.code === "Tab"){
          tecla.classList.remove('ativa');
        }else if(evento.code === "Backspace"){
          removeLastCharacterPlaceHolder();
        }
      }

    tecla.onkeyup = function(){
        tecla.classList.remove('ativa');
    }
}
