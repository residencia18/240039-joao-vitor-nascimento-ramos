#include<iostream>
#include<cctype>

using namespace std;

int main(){

    char ch1,ch2,ch3;

    cout << "Digite um caractere : ";
    cin >> ch1;

    cout << (isupper(ch1) ? "É uma letra maiúscula" :
             (islower(ch1) ? "É uma letra minúscula" :
             (isdigit(ch1) ? "É um digito" : "É um caractere especial"))) << endl;

    return 0;
}