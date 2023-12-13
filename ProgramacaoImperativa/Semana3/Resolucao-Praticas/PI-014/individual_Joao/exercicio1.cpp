#include<iostream>

typedef long long int ll ;

ll fatorial(int);

int main(){

    int n1 = 5 , n2=6 , n3=0;

    std::cout <<"Fatorial de " << n1 << " : " <<fatorial(n1) << std::endl;
    std::cout <<"Fatorial de " << n2 << " : " << fatorial(n2) << std::endl;
    std::cout <<"Fatorial de " << n3 << " : " << fatorial(n3) << std::endl;

    return 0;
}

ll fatorial(int valor){
    return (valor==0) ? 1 : (valor==1) ? valor : valor*(fatorial(valor-1));
}


