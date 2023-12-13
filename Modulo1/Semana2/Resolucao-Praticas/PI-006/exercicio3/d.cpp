#include<iostream>

using namespace std;

int main(){

    char ch1,ch2,ch3;

    cout << "Digite um caractere :";
    cin >> ch1;

    cout << "Digite um caractere :";
    cin >> ch2;

    (((int)ch2-1) <= 255 && ((int)ch2-1) >=0) ? (ch3 = (int)ch2-1) : (ch3='_');


    printf("%d - %o - %x - %c\n", ch3, ch3 , ch3, ch3);

    return 0;
}