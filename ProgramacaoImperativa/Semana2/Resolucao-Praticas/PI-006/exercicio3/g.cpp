#include<iostream>
#include<cctype>
using namespace std;

int main(){

    char ch1,ch2,ch3;

    cout << "Digite um caractere :";
    cin >> ch1;

    cout << "Digite um caractere :";
    cin >> ch2;

    (isdigit(ch2) || isdigit(ch1)) ? (ch3='1') : (ch3 = ' ');

    printf("%d - %o - %x \n", ch3, ch3 , ch3);

    return 0;
}