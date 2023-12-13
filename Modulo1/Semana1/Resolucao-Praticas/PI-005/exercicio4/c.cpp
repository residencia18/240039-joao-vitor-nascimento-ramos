#include<iostream>

using namespace std;

int main(){

    double x,y,z;
    double curva;
    
    cout << "Digite o valor de x : ";
    cin >> x;

    cout << "Digite o valor de y : ";
    cin >> y;

    curva = (5*x + 2);

    cout << ((y > curva) ? "A esquerda da curva" : (y < curva) ? "A direita da curva" : "Na curva" ) << endl;



    return 0;
}