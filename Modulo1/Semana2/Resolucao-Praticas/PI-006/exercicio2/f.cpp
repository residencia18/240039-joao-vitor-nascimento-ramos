#include<iostream>

using namespace std;

int main(){

    int a,b,c;

    cout << "Entre com o valor de a :";
    cin >> a;

    cout << "Entre com o valor de b :";
    cin >> b;

    c = 4*(a + b)/(3 - 5);

    // c = = 4*(a + b)/(3 - 5); e  c = 4*a + b/3 - 5; são diferentes pela ordem de preferência
    // outra variação 4*(a+b)/3 -5 é outra variação

    return 0;
}