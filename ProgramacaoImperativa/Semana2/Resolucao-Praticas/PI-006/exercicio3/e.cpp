#include<iostream>
#include<cctype>
using namespace std;

int main(){

    char ch1,ch2,ch3;

    cout << "Digite um caractere :";
    cin >> ch1;

    cout << "Digite um caractere :";
    cin >> ch2;

    (isupper(ch1)) ? (ch3='A') : (ch3 = ' ');

    printf("%c\n", ch3);

    return 0;
}