const html = document.querySelector('html');

const botaoFoco = document.querySelector('.app__card-button--foco');
const botaoDescansoCurto = document.querySelector('.app__card-button--curto');
const botaoDescansoLongo = document.querySelector('.app__card-button--longo');
const banner = document.querySelector('.app__image')
const titulo = document.querySelector('.app__title')
const botoes = document.querySelectorAll('.app__card-button');
const musicFocoInput = document.querySelector('#alternar-musica')
const musica = new Audio('./sons/luna-rise-part-one.mp3')
musica.loop = true;
const beep = new Audio('./sons/beep.mp3')
const pause = new Audio('./sons/pause.mp3')
const play = new Audio('./sons/play.wav')

const iniciarOuPausarBt = document.querySelector('#start-pause span')
const imagemPause = document.querySelector('.app__card-primary-butto-icon');
const startPauseBt = document.querySelector('#start-pause')

const enderecoPause = './imagens/pause.png';
const enderecoPlay = './imagens/play_arrow.png'

const tempoNaTela = document.querySelector("#timer")

let tempoDecorridoEmSegundos = 1500;
let intervaloID = null;


musicFocoInput.addEventListener('change', () => {
    if(musica.paused){
        musica.play();
    }else{
        musica.pause();
    }
})

botaoFoco.addEventListener('click', () => {
    alterarContexto('foco')
})

botaoDescansoCurto.addEventListener('click', () => {
    tempoDecorridoEmSegundos
    alterarContexto('descanso-curto')
})

botaoDescansoLongo.addEventListener('click', () => {
    tempoDecorridoEmSegundos = 
    alterarContexto('descanso-longo')
})

function alterarContexto(novoContexto){
    html.setAttribute('data-contexto', novoContexto)
    banner.setAttribute('src',`./imagens/${novoContexto}.png`)

    botoes.forEach(function (novoContexto) {
        novoContexto.classList.remove('active');
    });
    


    switch (novoContexto) {
        case "foco":
            titulo.innerHTML =  `Otimize sua produtividade,<br><strong class="app__title-strong">mergulhe no que importa.</strong>`
            botaoFoco.classList.add('active')
            tempoDecorridoEmSegundos = 1500
            break;
        case "descanso-curto":
            titulo.innerHTML =`Quem tal uma respirada ? ,<br><strong class="app__title-strong">faça uma pausa curta.</strong>`
            botaoDescansoCurto.classList.add('active')
            tempoDecorridoEmSegundos = 300
            break
        case "descanso-longo":
            titulo.innerHTML =`Hora de voltar à superficie ,<br><strong class="app__title-strong">faça uma pausa longa.</strong>`
            botaoDescansoLongo.classList.add('active')
            tempoDecorridoEmSegundos = 900
            break    
        default:
            break;
    }

    mostrarTempo()
}


const contagemRegressiva = () => {
    if(tempoDecorridoEmSegundos<=0){
        zerar()
        beep.play();
        alert('tempo finalizado')
        return;
    }
    tempoDecorridoEmSegundos -=1;
    mostrarTempo()
}

startPauseBt.addEventListener('click',iniciarOuPausar);

function iniciarOuPausar() {
    if(intervaloID){
        zerar()
        pause.play();
        return;
    }
    play.play();
    intervaloID = setInterval(contagemRegressiva,1000)
    iniciarOuPausarBt.innerHTML="Pausar";
    imagemPause.setAttribute('src',enderecoPause);
}

function zerar(){
    clearInterval(intervaloID)
    intervaloID = null;
    iniciarOuPausarBt.textContent="Começar";
    imagemPause.setAttribute('src',enderecoPlay);

}


function mostrarTempo(){
    const tempo = new Date(tempoDecorridoEmSegundos*1000);
    const tempoFormatado = tempo.toLocaleTimeString('pt-Br',{minute: '2-digit', second: '2-digit'})
    tempoNaTela.innerHTML = `${tempoFormatado}`
}

mostrarTempo();