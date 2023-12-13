#include "menuCliente.hpp"
#include "listaClientes.hpp"

void mostraMenuCliente()
{
    Data data;
    int escolha = 0;

    limpaTela();
    data.mostraDataAtual();
    cout << "\t=========MENU CLIENTE=========";
    cout << "\n\t[1] - INCLUIR:";
    cout << "\n\t[2] - EXCLUIR:";
    cout << "\n\t[3] - ALTERAR INFORMAÇÕES:";
    cout << "\n\t[4] - LISTAR:";
    cout << "\n\t[5] - LOCALIZAR:";
    cout << "\n\t[0] - SAIR";
    cout << "\n\tENTRADA ->  ";
}

void menuCliente(vector<Cliente> &listaClientes)
{
    int escolha;
    do
    {
        escolha = recebeEscolha();
        realizaEscolha(escolha, listaClientes);

    } while (escolha != 0);
}

int recebeEscolha()
{
    int escolha;
    bool escolhaValida;

    do
    {
        mostraMenuCliente();
        cin >> escolha;
        limpaBuffer();

        escolhaValida = (escolha == 0 || escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4 || escolha == 5);

        if (!escolhaValida)
        {
            limpaTela();
            cout << "\n\tOps, escolha inválida! Tente novamente.\n";
            pause();
            limpaBuffer();
        }

    } while (!escolhaValida);

    return escolha;
}

void realizaEscolha(const int &escolha, vector<Cliente> &listaClientes)
{
    switch (escolha)
    {
    case 1:

        insereCliente(listaClientes);
        break;

    case 2:

        excluiCliente(listaClientes);
        break;

    case 3:

        alteraCliente(listaClientes);
        break;

    case 4:

        listarClientes(listaClientes);
        break;

    case 5:

        localizaCliente(listaClientes);
        break;

    case 0:

        limpaTela();
        return;

    default:
        break;
    }
}