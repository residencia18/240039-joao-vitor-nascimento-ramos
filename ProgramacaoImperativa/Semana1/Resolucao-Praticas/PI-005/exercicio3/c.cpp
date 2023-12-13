#include<iostream>

using namespace std;

int main(){

    int a,b,c;

    cout << "Entre com o valor da variavel a : " ;
    cin >> a;

    cout << "Entre com o valor da variavel b : " ;
    cin >> b;

    c = a+b;

    cout << "A soma de " << a  << " + " << b << " é : " << hex << c << endl; 

    return 0;
}