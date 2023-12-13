#include "viagem.h"
#define VALOR_PASSAGEM 80

bool verificaOnibusCheio(Viagem &viagem) {
    return (viagem.assentosDisponiveis > 0) ? false : true;
}

bool verificaAssentoDisponivel(Viagem &viagem, int assento) {
    for (auto it = viagem.passagensVendidas.begin(); it != viagem.passagensVendidas.end(); ++it) {
        if (assento == it->assento) {
            return false;
        }
    }
    return true;
}

int valorArrecadadoViagem(Viagem &viagem) {
    return viagem.passagensVendidas.size() * VALOR_PASSAGEM;
}

bool validaAssento(int assento) {
    if (assento > 40 || assento < 1) {
        return false;
    }
    return true;
}


void mostraHorarios(int trajeto) {

    cout << "Escolha um horário : " << endl << endl;
    if (trajeto == 1) {
        cout << "Rio de Janeiro -- São Paulo " << endl << endl;
        cout << "1-   08:00" << endl << "2-   10:00" << endl << "3-   12:00" << endl << "4-   14:00" << endl
             << "5-   16:00" << endl << endl;
    } else {
        cout << "São Paulo -- Rio de Janeiro " << endl << endl;
        cout << "1-   08:30" << endl << "2-   10:30" << endl << "3-   12:30" << endl << "4-   14:30" << endl
             << "5-   16:30" << endl << endl;
    }
}

int escolherOnibus() {
    int trajeto = escolheTrajeto();
    int horario = escolheHorario(trajeto);
    return (trajeto == 1) ? horario : (horario + 5);
}

int escolheTrajeto() {
    int trajeto;
    do {
        cout << "Escolha o trajeto da viagem" << endl << endl << "(1- Rio de Janeiro->São Paulo  / 2-São Paulo->Rio de Janeiro) : ";
        cin >> trajeto;
        if (trajeto != 1 && trajeto != 2) {
            system("clear");
            cout << "Trajeto Invalido\n";
        }
    } while (trajeto != 1 && trajeto != 2);

    return trajeto;
}

int escolheHorario(int trajeto) {
    int horario;
    do {
        mostraHorarios(trajeto);
        cin >> horario;
        if (horario > 5 || horario < 1) {
            system("clear");
            cout << "Horário inválido" << endl;
        }
    } while (horario > 5 || horario < 1);

    return horario;
}

void cadastraPassagem(Viagem &viagem, int assento, Passageiro passageiro) {
    Passagem passagem = {assento, passageiro};
    viagem.passagensVendidas.push_back(passagem);
    viagem.assentosDisponiveis--;
}

bool venderPassagem(Viagem &viagem, int assento, Passageiro passageiro) {
    if (validaAssento(assento)) {
        if (verificaOnibusCheio(viagem)) {
            cout << "Não Há Assentos disponíveis para esta viagem" << endl;
            return false;
        } else {
            if (verificaAssentoDisponivel(viagem, assento)) {
                cadastraPassagem(viagem, assento, passageiro);
                cout << "Passagem cadastrada com sucesso" << endl;
                return true;
            } else {
                cout << "Acento " << assento << " ocupado" << endl;
                return false;
            }
        }
    } else {
        cout << "Acento inválido" << endl;
        return false;
    }
}

string buscaHorario(int onibus) {
    switch (onibus) {
        case 1:
            return "08:00";
        case 2:
            return "10:00";
        case 3:
            return "12:00";
        case 4:
            return "14:00";
        case 5:
            return "16:00";
        case 6:
            return "18:30";
        case 7:
            return "10:40";
        case 8:
            return "12:30";
        case 9:
            return "14:30";
        case 10:
            return "16:30";
    }
    return " ";
}

int solicitaAssento(){
    int assento;
    cout << "Numero do assento : " ;
    cin >> assento;
    return assento;
}


Passageiro cadastraPassageiro(){
    Passageiro passageiro;
    string cpf;
    string nome;
    int idade;

    cout << "Nome do passageiro : ";
    cin >> nome;
    cout << "CPF : ";
    cin >> cpf;
    cout << "Idade :";
    cin >> idade;
    passageiro.cpf = cpf;
    passageiro.idade = idade;
    passageiro.nome = nome;

    return passageiro;
}
