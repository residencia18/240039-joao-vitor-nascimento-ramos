import { EventEmitter, Injectable } from "@angular/core"
import { ServicoService } from "../search/servico.service";

@Injectable()
export class CursosService{

    emitirCursoCriado = new EventEmitter<string>();

    cursos:string[] = ['Angular',"Java","CSS"];

    constructor(private service:ServicoService){

    }

    getCursos(){
        return this.cursos;
    }


    addCurso(novoCurso:string){
        this.service.mostra(`novo curso : ${novoCurso}`);
        this.cursos.push(novoCurso);
        this.emitirCursoCriado.emit(novoCurso);
    }

}