const idades = [15,21,30,17,18,44,12,50]

const maiorQue18 = idades.filter((el)=>{
    return el > 18;
});

console.log(maiorQue18)