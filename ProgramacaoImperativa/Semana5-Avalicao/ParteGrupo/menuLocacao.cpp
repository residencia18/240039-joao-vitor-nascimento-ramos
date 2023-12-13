#include "menuLocacao.hpp"
#include "listaLocacoes.hpp"

void menuLocacao(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao)
{
    int escolha;

    do
    {
        escolha = recebeEscolhaLocacao();
        realizaEscolhaLocacao(escolha, listaClientes, listaVeiculos, listaLocacao);
    } while (escolha != 0);
}

void mostraMenuLocacao()
{
    Data data;

    limpaTela();
    data.mostraDataAtual();
    cout << "\t===========MENU LOCAÇÃO===========";
    cout << "\n\t[1] - INCLUIR LOCAÇÃO:";
    cout << "\n\t[2] - EXCLUIR LOCAÇÃO:";
    cout << "\n\t[3] - ALTERAR LOCAÇÃO:";
    cout << "\n\t[4] - LISTAR LOCAÇÕES:";
    cout << "\n\t[0] - SAIR";
    cout << "\n\tENTRADA ->  ";
}

int recebeEscolhaLocacao()
{
    int escolha;
    bool escolhaValida;

    do
    {
        mostraMenuLocacao();
        cin >> escolha;
        limpaBuffer();

        escolhaValida = (escolha == 0 || escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4);

        if (!escolhaValida)
        {
            limpaTela();
            cout << "\n\tOps, escolha inválida!" << endl;
            pause();
            limpaBuffer();
        }

    } while (!escolhaValida);
    return escolha;
}

void realizaEscolhaLocacao(const int &escolha, vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao)
{
    switch (escolha)
    {
    case 1:

        insereLocacao(listaClientes, listaVeiculos, listaLocacao);
        break;

    case 2:

        excluiLocacao(listaClientes, listaVeiculos, listaLocacao);
        break;

    case 3:

        alteraLocacao(listaClientes, listaVeiculos, listaLocacao);
        break;

    case 4:

        listarLocacao(listaLocacao,listaClientes,listaVeiculos);
        break;

    case 0:

        limpaTela();
        return;

    default:
        break;
    }
}
