#include<iostream>
#include<cctype>
using namespace std;

int main(){

    int a,b;

    cout << "Entre com valor de um numero inteiro : ";
    cin >> a;

    cout << "Entre com valor de outro numero inteiro : ";
    cin >> b;

    if(a > b ){
        printf("Maior numero é %d\n",a);
        if(a%2==0){
            printf("%d é par\n",a);
        }else{
            printf("%d é impar\n",a);
        }
    }else{
        printf("Maior numero é %d\n",b);
        if(b%2==0){
            printf("%d é par\n",b);
        }else{
            printf("%d é impar\n",b);
        }    
    }



    return 0;
}