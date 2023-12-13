#include "menuPrincipal.hpp"

void menuPrincipal(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao)
{
    int escolha;
    Data data;

    do
    {
        limpaTela();
        data.mostraDataAtual();
        cout << "\t======MENU PRINCIPAL======";
        cout << "\n\t[1] - GESTÃO CLIENTE:";
        cout << "\n\t[2] - GESTÃO VEICULO:";
        cout << "\n\t[3] - GESTÃO LOCAÇÃO:";
        cout << "\n\t[4] - GESTÃO OCORRÊNCIA:";
        cout << "\n\t[0] - SAIR";
        cout << "\n\tENTRADA ->  ";
        cin >> escolha;
        cin.get();

        if (escolha > 4 || escolha < 0)
        {
            limpaTela();
            cout << "\n\tOps, escolha invalida!...\n";
            pause();
        }

        switch (escolha)
        {
        case 1:

            menuCliente(listaClientes);
            break;

        case 2:

            menuVeiculo(listaVeiculos);
            break;

        case 3:

            menuLocacao(listaClientes, listaVeiculos, listaLocacao);
            break;

        case 4:
            menuOcorrencia(listaClientes, listaVeiculos, listaLocacao);
            break;

        default:

            break;
        }

    } while (escolha != 0);
}