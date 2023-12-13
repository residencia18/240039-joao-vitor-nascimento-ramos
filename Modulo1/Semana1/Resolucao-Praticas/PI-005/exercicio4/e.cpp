#include<iostream>
#include<iomanip>

using namespace std;

int main(){

    double x,y,z;
    double curva;
    
    cout << "Digite o valor de x : ";
    cin >> x;

    cout << "Digite o valor de y : ";
    cin >> y;

    z = x*y;

    cout << "O produto entre as 2 variaveis é : " << scientific << z << endl;

    return 0;
}