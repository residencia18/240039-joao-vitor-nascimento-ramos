#include<iostream>

using namespace std;

int main(){

    double x,y,z;

    printf("Entre com o valor de x :");
    scanf("%lf",&x);

    printf("Entre com o valor de y :");
    scanf("%lf",&y);

    if(x>y){
        z=x;
        printf("%d é o maior entre os 2 numeros digitados\n",z);
    }else if(x<y){
        z=y;
        printf("%d é o maior entre os 2 numeros digitados\n",z);
    }else{
        printf("Numeros iguais");
    }

    return 0;
}