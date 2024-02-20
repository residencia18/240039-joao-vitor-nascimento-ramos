class Carro{
    constructor(pnome,pTipo){
        this.nome=pnome;
        if(pTipo==1){
            this.tipo = "Esportivo";
            this.velmax=300;
        }else if(pTipo==2){
            this.tipo = "Utilitario";
            this.velmax=100;
        }else if(pTipo==3){
            this.tipo = "Passeio";
            this.velmax=160;
        }else{
            this.tipo="Militar"
            this.velmax=180;
        }
    }

    getNome(){
        return this.nome;
    }

    getTipo(){
        return this.tipo;
    }

    getVelMax(){
        return this.velmax;
    }

    setNome(pNome){
        this.nome = pNome;
    }

    setTipo(pTipo){
        this.tipo = pTipo;
    }

    setVelMax(NVel){
        this.velmax = NVel;
    }

    info(){
       console.log("Nome   : " + this.getNome());
       console.log("Tipo   : " + this.getTipo());
       console.log("VelMax : " + this.getVelMax()) 
       console.log("----------------------------")
    }

}


let c1 = new Carro("Ferrari",1);
let c2 = new Carro("Golf",2);
let c3 = new Carro("Fox",3);
let c4 = new Carro("Tanque",4);

c1.info();
c2.info();
c3.info();
c4.info();