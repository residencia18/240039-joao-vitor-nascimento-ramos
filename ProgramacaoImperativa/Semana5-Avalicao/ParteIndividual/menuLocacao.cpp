#include "menuLocacao.hpp"
#include "listaLocacoes.hpp"

void menuLocacao(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos,vector<Locacao> &listaLocacao){
    int escolha;
    mostraMenuLocacao();
    escolha = recebeEscolhaLocacao();
    realizaEscolhaLocacao(escolha,listaClientes,listaVeiculos,listaLocacao);
}

void mostraMenuLocacao(){
    limpaTela();
    cout << "Menu de Locação" << endl << endl;
    cout << "1. Incluir Locacao" << endl;
    cout << "2. Excluir Locacao" << endl;
    cout << "3. Alterar Locacao" << endl;
    cout << "4. Lista Todas as Locacoes" << endl;
    cout << "0. Sair" << endl << endl;
    cout << "Escolha : ";
}

int recebeEscolhaLocacao(){
    int escolha;
    bool escolhaValida;
    do{
        mostraMenuLocacao();
        cin >> escolha;
        escolhaValida = (escolha== 0 || escolha== 1 || escolha== 2 || escolha== 3 || escolha== 4);
        if(!escolhaValida){
            limpaTela();
            cout << "------Escolha Inválida------" << endl;
            pause();
            limpaBuffer();
        }
    }while(!escolhaValida);
    return escolha;
}


void realizaEscolhaLocacao(const int &escolha, vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao){
  switch (escolha)
    {
    case 1 :
        insereLocacao(listaClientes,listaVeiculos,listaLocacao);
        break;
    case 2 :
        excluiLocacao(listaClientes,listaVeiculos,listaLocacao);
        break;
    case 3 :
        alteraLocacao(listaClientes,listaVeiculos,listaLocacao);
        break;
    case 4 :
        listarLocacao(listaClientes,listaVeiculos,listaLocacao);
        break;

    case 0 :
            limpaTela();
            return;
    default:
        break;
    }
}
