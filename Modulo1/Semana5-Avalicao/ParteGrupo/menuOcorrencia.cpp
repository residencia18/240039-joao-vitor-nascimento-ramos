#include "menuOcorrencia.hpp"
#include "listaOcorrencias.hpp"

using namespace std;

void mostraMenuOcorrencia()
{
    Data data;
    int escolha = 0;

    limpaTela();
    data.mostraDataAtual();
    cout << "\t=======MENU OCORRENCIA=======";
    cout << "\n\t[1] - INCLUIR:";
    cout << "\n\t[2] - EXCLUIR:";
    cout << "\n\t[3] - ALTERAR INFORMAÇÕES:";
    cout << "\n\t[4] - LISTAR POR CLIENTE:";
    cout << "\n\t[5] - LISTAR POR VEICULO:";
    cout << "\n\t[6] - REGISTRAR OCORRÊNCIA POR VEICULO";
    cout << "\n\t[0] - SAIR";
    cout << "\n\tENTRADA ->  ";
}

void menuOcorrencia(vector<Cliente> &listaCliente, vector<Veiculo> &listaVeiculo, vector<Locacao> &listaLocacao)
{
    int escolha;

    do
    {
        escolha = recebeEscolhaOcorrencia();
        realizaEscolhaOcorrencia(escolha, listaCliente, listaVeiculo, listaLocacao);

    } while (escolha != 0);
}

int recebeEscolhaOcorrencia()
{
    int escolha;
    bool escolhaValida;

    do
    {
        mostraMenuOcorrencia();
        cin >> escolha;
        cin.get();

        escolhaValida = (escolha == 0 || escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4 || escolha == 5 || escolha==6);

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

void realizaEscolhaOcorrencia(const int &escolha, vector<Cliente> &listaCliente, vector<Veiculo> &listaVeiculo, vector<Locacao> &listaLocacao)
{
    switch (escolha)
    {
    case 1:

        insereOcorrencia(listaCliente, listaVeiculo, listaLocacao);
        break;

    case 2:

        excluiOcorrencia(listaCliente, listaVeiculo, listaLocacao);
        break;

    case 3:

        alteraOcorrencia(listaCliente, listaVeiculo, listaLocacao);
        break;

    case 4:

        listarOcorrenciaPorCliente(listaLocacao);
        break;
    case 5:

        listarOcorrenciaPorVeiculo(listaLocacao);
        break;

    case 6:
        registrarOcorrenciaPorVeiculo(listaLocacao);
        break;

    case 0:

        limpaTela();
        return;

    default:
        break;
    }
}
