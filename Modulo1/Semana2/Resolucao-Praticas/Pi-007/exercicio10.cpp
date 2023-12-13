#include<iostream>

int main(){

    int n1 = 1, n2 =1;
    int numero;

    printf("Entre com um número : ");
    scanf("%d",&numero);

    do{ 
        printf("%d\n",n2);
        n2 += n1;
        n1 = n2 -n1;

    }while(n2<=numero);


    return 0;
}