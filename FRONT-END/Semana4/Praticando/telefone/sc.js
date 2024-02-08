function insertIntoPlaceHolder(valorAInserir){
    tel.value += valorAInserir;
}

function removeLastCharacterPlaceHolder() {
  tel.value = tel.value.substr(0, tel.value.length - 1);
}

const listaDeTeclas = document.querySelectorAll('input[type="button"]');

const tel = document.querySelector('input[type="tel"]');

for(const tecla of listaDeTeclas){
  
    const valor =  tecla.value;
  
  tecla.onclick = function (){
    insertIntoPlaceHolder(valor);
  }


  tecla.onkeydown = function (evento) {
    if(evento.code === "Enter" || evento.code === "Space") {
    tecla.classList.add('ativa');
    }else if (evento.code == "Tab"){
      tecla.classList.remove('ativa');
    }else if(evento.code== "Backspace"){
      removeLastCharacterPlaceHolder();
    }
  }

  tecla.onkeyup = function () {
    tecla.classList.remove('ativa');
  }

  
  
}



