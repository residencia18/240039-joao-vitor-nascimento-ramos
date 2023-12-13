#include<iostream>
#include<cmath>

using namespace std;

int main(){

    double a,b,c;
    double delta;

    cout << "Digite o valor de a : ";
    cin >> a;

    cout << "Digite o valor de b : ";
    cin >> b;

    cout << "Digite o valor de c : ";
    cin >> c;

    delta = b*b - 4*a*c;

    cout << ( (delta == 0) ? "Uma Raiz real" : (delta < 0) ? "Nenhuma raiz real" : "Duas raizes reais") << endl;

    cout << ((delta == 0) ? to_string((-b + sqrt(delta)) / (2 * a)) + " é a raiz real" : to_string((-b + sqrt(delta)) / (2 * a)) + " e " + to_string((-b - sqrt(delta)) / (2 * a)) + " são as raízes reais") << endl;


    return 0;
}