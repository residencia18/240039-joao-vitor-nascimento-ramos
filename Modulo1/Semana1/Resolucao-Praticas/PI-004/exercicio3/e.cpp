#include<iostream>

using namespace std;

int main(){

    long int maiorLongComSinal = 0x7FFFFFFFFFFFFFFF;
    unsigned int inteiroSemSinal;
    inteiroSemSinal =  maiorLongComSinal;
    maiorLongComSinal = inteiroSemSinal;

    cout << "variavel ui : " << inteiroSemSinal << endl;
    cout << "variavel li : " << inteiroSemSinal << endl;

    /*A variavel armazenou o maior valor possivel em sua memoria e depois retornou esse valor para a variavel li */

    return 0;
}