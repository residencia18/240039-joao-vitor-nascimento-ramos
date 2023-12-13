#include<iostream>

using namespace std;


int main(){

    char c;

    cout << "Entre com um caracter :";
    cin >> c;

    cout << "'" << c << "' - " << (int)c << " - "  << oct << (int)c <<  " - "  << hex << (int)c << endl;


    return 0;
}