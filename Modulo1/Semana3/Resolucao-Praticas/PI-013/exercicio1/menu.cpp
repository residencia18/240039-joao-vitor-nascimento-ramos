#include "menu.h"

void menu(MapaDeViagens &mapaDeViagens) {
    int escolha;
    while (1) {
        cout << "-------------MENU-------------" << endl << endl;
        cout << "1 - Cadastrar Nova Passagem" << endl;
        cout << "2 - Visualizar o total arrecadado em determinado mês" << endl;
        cout << "3 - Buscar por passageiro" << endl;
        cout << "4 - Mostrar Horário mais rentável " << endl;
        cout << "5 - Mostrar Média de idade dos passageiros " << endl;
        cout << "6 - Sair do Programa" << endl;


        cout << endl;
        cin >> escolha;
        system("clear");
        switch(escolha){
            case 1:
                iniciaCadastro(mapaDeViagens);
                break;
            case 2:
                totalArrecadadoNoMes(mapaDeViagens);
                break;
            case 3:
                buscaPessoa(mapaDeViagens);
                break;
            case 4:
                visualizarHorarioMaisRentavel(mapaDeViagens);
                break;
            case 5:
                mediaIdadePassageiros(mapaDeViagens);
                break;
            case 6:
                exit(0);
            default:
                break;
        }
    }
}

void iniciaCadastro(MapaDeViagens &mapaDeViagens) {
    int onibus = escolherOnibus();
    string data = buscaData();
    Chave chave1 = make_pair(data, onibus);
    Passageiro passageiro = cadastraPassageiro();
    int assento = solicitaAssento();

    if (mapaDeViagens.find(chave1) != mapaDeViagens.end()) {
        venderPassagem(mapaDeViagens[chave1], assento, passageiro);
    } else {
        Viagem novaViagem;
        mapaDeViagens[chave1] = novaViagem;
        venderPassagem(mapaDeViagens[chave1], assento, passageiro);
    }
}

void totalArrecadadoNoMes(MapaDeViagens &mapaDeViagens){
    string dataMes;
    string parteDia = "02/";
    int arrecadacao=0;
    string dataCompleta;
    int dia,mes,ano;
    bool dataValida = true;
    do{
        cout << "Entre com o mês no formato(xx/xxxx) : " << endl;
        cin >> dataMes;
        dataCompleta = parteDia+dataMes;
        separaData(dia,mes,ano,dataCompleta);
        dataValida = verificaData(dia,mes,ano);
        if(!dataValida){
            cout << "**Data Inválida** " << endl;
        }
    }while(!dataValida);

    for(const auto &par : mapaDeViagens) {
        string chave = par.first.first;
        string MesAno = chave.substr(3);
        if (MesAno == dataMes) {
            arrecadacao +=80;
        }
    }
     cout << "Arrecadação : " << arrecadacao << endl;

}

void buscaPessoa(MapaDeViagens &mapaDeViagens){
    
    string data = buscaData();
    int onibus = escolherOnibus();
    Chave chave = make_pair(data, onibus);
    int assento = solicitaAssento();
    if (!validaAssento(assento)) {
        cout << "Assento inválido " << endl;
        cout << "O Assento " + to_string(assento) + " não é válido" << endl;
        return;
    }

    for(auto it=mapaDeViagens[chave].passagensVendidas.begin();it!=mapaDeViagens[chave].passagensVendidas.end();++it){
        cout << assento << endl;
        if(assento == it->assento){
            cout << "Passageiro : " << it->passageiro.nome << endl;
            return;
        }
    }

    cout << "Assento sem passageiro" << endl;

}

void visualizarHorarioMaisRentavel(MapaDeViagens &mapaDeViagens){
    int Arrecadacao[10]={};
    int horario;
    int maiorArrecadacao = 0;
    int horarioMaiorArrecadao;
    for(const auto &par : mapaDeViagens) {
        Arrecadacao[par.first.second -1] +=80;
    }

    for(int i = 0 ; i < 10 ; i++){
        if(Arrecadacao[i] > maiorArrecadacao){
            maiorArrecadacao = Arrecadacao[i];
            horarioMaiorArrecadao = i+1;
        }
    }

    switch (horarioMaiorArrecadao)
    {
    case 1:
        cout << "Rio de Janeiro -> São Paulo " << endl << "08:00" << endl;
        break;
    case 2:
        cout << "Rio de Janeiro -> São Paulo " << endl << "10:00" << endl;
        break;
    case 3:
        cout << "Rio de Janeiro -> São Paulo " << endl << "12:00" << endl;
        break;
    case 4:
        cout << "Rio de Janeiro -> São Paulo " << endl << "14:00" << endl;
        break;
    case 5:
        cout << "Rio de Janeiro -> São Paulo " << endl << "16:00" << endl;
        break;
    case 6:
        cout << "São Paulo -> Rio de Janeiro " << endl << "08:30" << endl;
        break;
    case 7:
        cout << "São Paulo -> Rio de Janeiro " << endl << "10:30" << endl;
        break;
    case 8:
        cout << "São Paulo -> Rio de Janeiro " << endl << "12:30" << endl;
        break;
    case 9:
        cout << "São Paulo -> Rio de Janeiro " << endl << "14:30" << endl;
        break;
    case 10:
        cout << "São Paulo -> Rio de Janeiro " << endl << "16:30" << endl;
        break;
    default:
        break;
    }

}

void mediaIdadePassageiros(MapaDeViagens &mapaDeViagens){

    double idadeMedia=0;

    for(const auto &par : mapaDeViagens) {
        for(auto it=par.second.passagensVendidas.begin() ; it!=par.second.passagensVendidas.end() ; ++it){
            idadeMedia +=  it->passageiro.idade;
        }
    }

    cout << "Idade media dos passageiros : " << idadeMedia/(mapaDeViagens.size()) << endl;
}


