#include<iostream>

using namespace std;

int main(){

    double x,y;

    cout << "Entre com x : ";
    cin >> x;

    cout << "Entre com y : ";
    cin >> y;

    cout << (((x>0) && (y>0)) ? "Primeiro Quadrante" : ((x>0) && (y<0)) ?
    "Quarto Quadrante" : ((x<0) && (y>0)) ?
    "Segundo Quadrante" : ((x<0) && (y<0)) ?
    "Terceiro quadrante" : "Sobre os eixos") << endl; 



    return 0;
}