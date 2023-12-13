#include<iostream>

using namespace std;


void multiplicacao(){
    float x,y;
    cout << "Entre com o valor de do primeiro numero: " ;
    cin >> x;
    
    cout << "Entre com o valor de do segundo numero: " ;
    cin >> y;

    cout << "Multiplicação entre "<< x << " e " << y << " : " << x*y;
}

void divisao(){
    float x,y;
    cout << "Entre com o valor de do primeiro numero: " ;
    cin >> x;
    
    cout << "Entre com o valor de do segundo numero: " ;
    cin >> y;

    cout << "Divisão entre "<< x << " e " << y << " : " << x/y;
}

void soma(){
    float x,y;
    cout << "Entre com o valor de do primeiro numero: " ;
    cin >> x;
    
    cout << "Entre com o valor de do segundo numero: " ;
    cin >> y;

    cout << "Soma entre "<< x << " e " << y << " : " << x+y;
}

void subtração(){
    float x,y;
    cout << "Entre com o valor de do primeiro numero: " ;
    cin >> x;
    
    cout << "Entre com o valor de do segundo numero: " ;
    cin >> y;

    cout << "Subtração entre "<< x << " e " << y << " : " << x-y;
}

int main(){


    return 0;
}