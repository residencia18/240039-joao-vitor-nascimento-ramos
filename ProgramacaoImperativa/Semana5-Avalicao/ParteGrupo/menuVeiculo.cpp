#include "menuVeiculo.hpp"

using namespace std;

void mostraMenuVeiculo()
{
    Data data;
    int escolha = 0;

    limpaTela();
    data.mostraDataAtual();
    cout << "\t=========MENU VEÍCULO=========";
    cout << "\n\t[1] - INCLUIR:";
    cout << "\n\t[2] - EXCLUIR:";
    cout << "\n\t[3] - ALTERAR INFORMAÇÕES:";
    cout << "\n\t[4] - LISTAR:";
    cout << "\n\t[5] - LOCALIZAR:";
    cout << "\n\t[0] - SAIR";
    cout << "\n\tENTRADA ->  ";
}

void menuVeiculo(vector<Veiculo> &listaVeiculos)
{
    int escolha;

    do
    {
        escolha = recebeEscolhaVeiculo();
        realizaEscolhaVeiculo(escolha, listaVeiculos);

    } while (escolha != 0);
}

int recebeEscolhaVeiculo()
{
    int escolha;
    bool escolhaValida;

    do
    {
        mostraMenuVeiculo();
        cin >> escolha;
        cin.get();

        escolhaValida = (escolha == 0 || escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4 || escolha == 5);

        if (!escolhaValida)
        {
            limpaTela();
            cout << "\n\tOps, escolha inválida! Tente novamente.\n";
            cin.clear();
            limpaBuffer();
            pause();
        }

    } while (!escolhaValida);

    return escolha;
}

void realizaEscolhaVeiculo(const int &escolha, vector<Veiculo> &listaVeiculos)
{
    switch (escolha)
    {
    case 1:

        insereVeiculo(listaVeiculos);
        break;

    case 2:

        excluiVeiculo(listaVeiculos);
        break;

    case 3:

        alteraVeiculo(listaVeiculos);
        break;

    case 4:

        listarVeiculos(listaVeiculos);
        break;

    case 5:

        localizaVeiculo(listaVeiculos);
        break;

    case 0:

        limpaTela();
        return;

    default:

        break;
    }
}