function *contador(){
    let i = 0;

    while(true){
        yield i++;
        if(i>33){
            break;
        }
    }


}

const itc=contador();

for(let c of itc){
    console.log(c)
}