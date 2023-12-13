#include "data.h"


bool verificaData(int dia, int mes, int ano) {
    int diasNoMes[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (ano < 0) {
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

bool anoBissexto(int ano) {
    return (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) ? true : false;
}

int separaDia(int pos1, string data) {
    return stoi(data.substr(0, pos1));
}

int separaMes(int pos1, int pos2, string data) {
    return stoi(data.substr(pos1 + 1, pos2));
}

int separaAno(int pos2, string data) {
    return stoi(data.substr(pos2 + 1));
}

int posicaoDaPrimeiraBarra(string data) {
    return data.find('/');
}

int posicaoDaSegundaBarra(string data) {
    return data.find_last_of('/');
}


void separaData(int &dia, int &mes, int &ano, string data) {
    int pos1 = posicaoDaPrimeiraBarra(data);
    int pos2 = posicaoDaSegundaBarra(data);
    dia = separaDia(pos1, data);
    mes = separaMes(pos1, pos2, data);
    ano = separaAno(pos2, data);
}

string buscaData() {
    int dia, mes, ano;
    string data;
    do {
        cout << "Entre com uma data : ";
        cin >> data;
        separaData(dia, mes, ano, data);
        if (!verificaData(dia, mes, ano)) {
            system("clear");
            cout << "Data inválida" << endl;
        }
    } while (!verificaData(dia, mes, ano));
    return data;
}