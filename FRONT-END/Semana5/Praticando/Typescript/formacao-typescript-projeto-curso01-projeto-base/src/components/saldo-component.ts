import { formatarMoeda, formatarData } from "../utils/formatters";
import { FormatoData } from "../types/FormatoData";
import Conta from "../types/Conta";


const elementoSaldo = document.querySelector(".saldo-valor .valor") as HTMLElement;
const elementoDataAcesso = document.querySelector(".block-saldo time") as HTMLElement;

if(elementoDataAcesso != null){
    elementoDataAcesso.textContent = formatarData(Conta.getDataAcesso(),FormatoData.DIA_SEMANA_DIA_MES_ANO)
}

renderizarSaldo();
function renderizarSaldo():void{
    if(elementoSaldo != null){
        elementoSaldo.textContent = formatarMoeda(Conta.getSaldo());
    }
}

const saldoComponent = {
    atualizar(){
        renderizarSaldo();
    }
}

export default saldoComponent;
