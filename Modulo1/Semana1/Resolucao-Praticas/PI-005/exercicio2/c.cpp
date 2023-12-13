#include<iostream>
#include<cctype>

using namespace std;

int main(){

    char ch1,ch2,ch3;

    cout << "Digite um caractere : ";
    cin >> ch1;

    cout << (((int)(ch1) >= 65 && int(ch1) <=90 ) ? "É uma letra maiúscula" :
             ((int)(ch1) >= 97 && int(ch1) <=122 ) ? "É uma letra minúscula" :
             ((int)(ch1) >= 48 && int(ch1) <=57 ) ? "É um digito" : "É um caractere especial") << endl;

    return 0;
}