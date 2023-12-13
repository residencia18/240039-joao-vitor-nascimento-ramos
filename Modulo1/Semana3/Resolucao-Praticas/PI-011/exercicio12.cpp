#include<iostream>
#include<cstring>

using namespace std;

string emComum(string, string);
void insereSemRepetir(string & , char);

int main(){

    string palavra1 = "Joao Vitor";
    string palavra2 = "Amanda Lemos";
    string comum = emComum(palavra1,palavra2);
    cout << comum << endl;

    return 0;
}

string emComum(string palavra1, string palavra2){
    string emComum;
    for(int i = 0 ; i < palavra2.length() ; i++){
        for(int j = 0 ; j < palavra1.length() ; j++){
            if(palavra1[j] == palavra2[i]){
                insereSemRepetir(emComum,palavra1[j]);
            } 
        }
    }
    return emComum;
}

void insereSemRepetir(string &emComum, char caracter){
    for(int i = 0 ; i < emComum.length() ; i++){
        if(caracter == emComum[i]){
            return ;
        }
    }
    emComum.push_back(caracter);

}