#include<iostream>

using namespace std;

int main(){

    char ch1,ch2,ch3;

    cout << "Digite um caractere :";
    cin >> ch1;

    cout << "Digite um caractere :";
    cin >> ch2;

    (((int)ch1-1) <= 255 && ((int)ch1-1) >=0) ? (ch3 = (int)ch1-1) : (ch3='_');

    cout << dec << (int)ch3 << " - " <<  oct << (int)ch3 << " - " <<  hex << (int)ch3 << " - " << ch3 << endl;  

    return 0;
}