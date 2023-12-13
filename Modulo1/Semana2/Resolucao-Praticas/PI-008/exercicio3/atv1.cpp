#include <iostream>
#include <sstream>
#include <string>
#include<cctype>

using namespace std;

int posicaoDaPrimeiraBarra(string );
int posicaoDaSegundaBarra(string );
string armazenaData();
void separaData(int & , int &, int&, string);
bool verificaData(int , int , int );
bool anoBissexto(int);
void armazenaVerificaSepara(string &,int &,int & ,int &);
int separaDia(int, string);
int separaMes(int , int , string);
int separaAno(int , string);
string nomeDoMes(int);
void imprimeData(int , int , int );

int main(){

    string data;
    int dia,mes,ano;

    armazenaVerificaSepara(data,dia,mes,ano);

    imprimeData(dia,mes,ano);


    return 0;
}

void imprimeData(int dia, int mes, int ano){
    cout << dia << " de " << nomeDoMes(mes) << " de " << ano << endl;
}

string nomeDoMes(int mes){
    string nomes[] = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    return nomes[mes-1];
}

void armazenaVerificaSepara(string &data,int &dia ,int &mes ,int &ano){
    do{
    data = armazenaData();
    separaData(dia,mes,ano,data);
    if(!verificaData(dia,mes,ano)){
        system("clear");
        cout << "Data invalida" << endl;
    }else{
        break;
    }
    }while(1);

}

bool anoBissexto(int ano){
    return (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) ? true : false;
}

bool verificaData(int dia , int mes, int ano){

    int diasNoMes[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if(ano < 0 ){
        return false;
    }

    if (mes < 1 || mes > 12) {
        return false;
    }

    if (mes == 2 && anoBissexto(ano)) {
        if (dia < 1 || dia > 29) {
            return false;
        }
    } else {
        if (dia < 1 || dia > diasNoMes[mes]) {
            return false;
        }
    }

    return true;

}

void separaData(int &dia, int &mes, int &ano, string data){
    int pos1 = posicaoDaPrimeiraBarra(data);
    int pos2 = posicaoDaSegundaBarra(data);
    dia = separaDia(pos1,data);
    mes = separaMes(pos1,pos2,data);
    ano = separaAno(pos2,data);
}

int separaDia(int pos1, string data){
    return stoi(data.substr(0,pos1));
}

int separaMes(int pos1, int pos2 , string data){
    return stoi(data.substr(pos1+1,pos2));
}

int separaAno(int pos2, string data){
    return stoi(data.substr(pos2+1));
}

string armazenaData(){
    string data;
    cout << "Entre com uma data no formato 'xx/xx/xxxx' : ";
    getline(cin,data);
    return data;
}

int posicaoDaPrimeiraBarra(string data){
    return  data.find('/');
}

int posicaoDaSegundaBarra(string data){
    return data.find_last_of('/');
}