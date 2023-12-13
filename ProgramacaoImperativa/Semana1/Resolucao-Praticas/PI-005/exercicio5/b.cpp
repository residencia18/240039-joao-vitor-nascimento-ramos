#include<iostream>

using namespace std;

int main(){

    unsigned char gene;

    cout << "Entre com o caractere do gene : ";
    cin >> gene;
    gene = 10;
    int count = 0 ;
     
    count += (gene & 0x01); 
    count += (gene & 0x02) >> 1;
    count += (gene & 0x04) >> 2; 
    count += (gene & 0x08) >> 3; 
    count += (gene & 0x10) >> 4; 
    count += (gene & 0x20) >> 5; 
    count += (gene & 0x40) >> 6; 
    count += (gene & 0x80) >> 7; 

    cout << "Quantidade de genes estudados : " << count << endl;



    return 0;
}