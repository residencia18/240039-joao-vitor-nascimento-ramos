import { TipoTransacao } from "./TipoTransacao";

export type Transacao = {
    tipoTransacao: TipoTransacao;
    valor: number;
    data: Date;
}