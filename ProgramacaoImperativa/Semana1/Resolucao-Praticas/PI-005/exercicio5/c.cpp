#include<iostream>
#include<cmath>

using namespace std;

int main(){

    unsigned char gene;
    int count = 0 ;
    int geneEspecifico;

    cout << "Entre com o caractere do gene : ";
    cin >> gene;

    gene = 16;
     
    count += (gene & 0x01); 
    count += (gene & 0x02) >> 1;
    count += (gene & 0x04) >> 2; 
    count += (gene & 0x08) >> 3; 
    count += (gene & 0x10) >> 4; 
    count += (gene & 0x20) >> 5; 
    count += (gene & 0x40) >> 6; 
    count += (gene & 0x80) >> 7; 

    cout << "Qual gene especifico busca essa informação ?  ";
    cin >> geneEspecifico;
    

    cout << ((gene & (int)round(pow(2, (geneEspecifico-1)))) >> (geneEspecifico - 1) ?
    "O gene se encontra nessa planta" : "O Gene não se encontra na planta") << endl;


    return 0;
}