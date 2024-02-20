class Npc{

    static alerta= false;

    constructor(energia){
        this.energia = energia;
    }

    info=function(){
        console.log(`Energia ${this.energia}`);
        console.log(`Alerta ${Npc.alerta? "Sim" : "Não"}`)
    }

    static alertar = function(){
        Npc.alerta=true;
    }

}

const npc1 = new Npc(100);

const npc2 = new Npc(80);

const npc3 = new Npc(85);

npc1.info();
Npc.alerta=true;
npc2.info();
npc3.info();
