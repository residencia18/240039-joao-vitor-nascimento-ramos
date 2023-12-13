#include<iostream>

using namespace std;

int main(){

    unsigned long int maiorLongSemSinal = 0xFFFFFFFFFFFFFFFF;
    
    cout << "Maior long int sem sinal : " << maiorLongSemSinal << endl;
    cout << "Menor long int sem sinal : " << (maiorLongSemSinal+1) << endl;


    return 0;
}