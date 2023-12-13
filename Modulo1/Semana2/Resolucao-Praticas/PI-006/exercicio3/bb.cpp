#include<iostream>
#include<cctype>
using namespace std;

int main(){

    int a,b;

    cout << "Entre com valor de um numero inteiro : ";
    cin >> a;

    cout << "Entre com valor de outro numero inteiro : ";
    cin >> b;

    cout << ((a>b) ? to_string(a) + " é maior" : (a<b) ? to_string(b) + " é maior" : "Numeros iguais")  << endl;  

    return 0;
}