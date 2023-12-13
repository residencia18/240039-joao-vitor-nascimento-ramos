#include<iostream>
#include<cmath>
using namespace std;

int main(){

    double raio;
    double area;


    cout << "Entre com o raio do circulo : ";
    cin >> raio;
    area = raio*raio*M_PI;
    cout << "Valor do diametro : " << area << endl;


    return 0;
}