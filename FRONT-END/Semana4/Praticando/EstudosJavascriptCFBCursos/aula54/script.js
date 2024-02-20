// const caixa = document.querySelector("#caixa")

let mapa = new Map();

mapa.set(1,"naruto")
mapa.set(2,"DBZ")
mapa.set(3,"One Piece")

caixa.innerHTML = mapa.get(2);
if(mapa.has(3)){
    caixa.innerHTML = mapa.get(3);
}