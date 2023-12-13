#include<iostream>
#include <cstdlib> 

using namespace std;

int main(){

    int a,b,c;

    cout << "Entre com o valor da variavel a : " ;
    cin >> a;

    cout << "Entre com o valor da variavel b : " ;
    cin >> b;

    c = abs(a-b);

    cout << "O valor da diferença entre " << a << " e " << b << " é : " << c << endl;

    return 0;
}