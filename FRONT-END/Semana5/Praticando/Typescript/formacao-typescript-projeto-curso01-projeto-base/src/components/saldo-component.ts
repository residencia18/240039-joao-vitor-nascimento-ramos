import { formatarMoeda } from "../utils/formatters";
import { formatarData } from "../utils/formatters";
import { FormatoData } from "../types/FormatoData";

let saldo:number = 3000;

const elementoSaldo = document.querySelector(".saldo-valor .valor") as HTMLElement;


const elementoDataAcesso = document.querySelector(".block-saldo time") as HTMLElement

if(elementoDataAcesso){
    const dataAcesso: Date = new Date();
    elementoDataAcesso.textContent = formatarData(dataAcesso,FormatoData.DIA_SEMANA_DIA_MES_ANO)
}

export function getSaldo() : number{
    return saldo;
}

atualizaSaldo(saldo);
export function atualizaSaldo(novoSaldo:number):void{
    saldo = novoSaldo;
    if(elementoSaldo != null){
        elementoSaldo.textContent = formatarMoeda(saldo);
    }
}