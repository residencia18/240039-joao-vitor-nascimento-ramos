#include<iostream>

using namespace std;

int main(){

    unsigned long int maiorLongSemSinal = 0xFFFFFFFFFFFFFFFF;
    long int li;
    li =  maiorLongSemSinal;
    
    cout << "valor da variavel li : " << li << endl;

    maiorLongSemSinal = li;

    cout << "vaor da variavel uli : " << maiorLongSemSinal << endl;

    /* A variavel li não consegue armazenar o valor por ser maior do que seu espaço de memoria, 
    */
    return 0;
}