#include<iostream>

using namespace std;

int main(){

    int n;
    short int maiorShortInt= 0x7FFF;

    cout << "Entre com um valor inteiro : ";
    cin >> n;

    cout << ((n<=maiorShortInt) ? to_string(n) + " esta no range do short int " : to_string(n) + " não esta no range do short int " ) << endl;


    return 0;
}