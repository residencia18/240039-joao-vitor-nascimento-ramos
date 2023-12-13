#include<iostream>

using namespace std;

int main(){

    int a,b,c;

    cout << "Entre com o valor da variavel a : " ;
    cin >> a;

    cout << "Entre com o valor da variavel b : " ;
    cin >> b;

    c = a-b;

    cout << "O valor da diferença entre " << a << " e " << b << " é : " << ((c>0) ? (c) : (-c)) << endl;

    return 0;
}