#include "listaClientes.hpp"

void insereCliente(vector<Cliente> &listaClientes)
{
    limpaTela();
    Cliente cliente;


    if (cliente.preencheCliente())
    {
        for (auto it = listaClientes.begin(); it != listaClientes.end(); ++it)
        {
            if (it->cpf == cliente.cpf)
            {
                limpaTela();
                cout << "\n\t===============ERRO============\n";

                cout << "\tCPF já cadastrado" << endl;
                cout << "\n\tOps, não foi possivel inserir novo cliente" << endl;
                cin.get();
                pause();
                return;
            }
        }

        limpaTela();
        listaClientes.push_back(cliente);
        cout << "\n\tCliente inserido com sucesso" << endl;
        pause();
    }
    else
    {
        limpaTela();
        cout << "\n\tOps, não foi possivel inserir novo cliente" << endl;
        cin.get();
        pause();
    }
}

void excluiCliente(vector<Cliente> &listaClientes)
{
    string cpfParaBusca;
    string nomeDoExcluido;

    if(listasVaziasCliente(listaClientes)){
        return;
    }

    do
    {
        limpaTela();
        cout << "\t===============REMOVER CLIENTE============\n";

        cout << "\tInsira um cpf válido para exclusão : ";
        cin >> cpfParaBusca;

    } while (!verificaCPF(cpfParaBusca));

    for (auto it = listaClientes.begin(); it != listaClientes.end(); ++it)
    {
        if (it->cpf == cpfParaBusca)
        {
            nomeDoExcluido = it->nome;
            listaClientes.erase(it);
            cout << "\n\tCliente " << nomeDoExcluido << " excluido com sucesso" << endl;
            pause();
            return;
        }
    }
    limpaTela();
    cout << "\n\tO CPF " << cpfParaBusca << " não se encontra em nosso banco de dados" << endl;
    cin.get();
    pause();
}

void alteraCliente(vector<Cliente> &listaClientes)
{
    limpaTela();
    Data data;
    string cpfParaAlteracao;
    string nomeDoExcluido;

    if(listasVaziasCliente(listaClientes)){
        return;
    }

    cout << "\t==========PESQUISAR CLIENTE===========\n";
    do
    {
        cout << "\n\tInsira o cpf do cliente que deseja alterar dados : ";
        cin >> cpfParaAlteracao;
        limpaBuffer();
        limpaTela();

    } while (!verificaCPF(cpfParaAlteracao));

    for (auto it = listaClientes.begin(); it != listaClientes.end(); ++it)
    {
        if (it->cpf == cpfParaAlteracao)
        {   
            cout << "\n\t===============CLIENTE ENCONTRADO============\n";
            it->mostraCliente();
            pause();
            int escolha;
            do
            {   
                limpaTela();
                data.mostraDataAtual();
                cout << "\t==========MENU ALTERAR===========\n";
                cout << "\n\t[1] - ALTERAR CPF:";
                cout << "\n\t[2] - ALTERAR NOME:";
                cout << "\n\t[3] - ALTERAR CNH:";
                cout << "\n\t[4] - ALTERAR IDADE DE NASCIMENTO:";
                cout << "\n\t[0] - SAIR";
                cout << "\n\tENTRADA ->  ";
                cin >> escolha;
                limpaBuffer();

                switch (escolha)
                {
                case 1:

                    limpaTela();
                    if (it->inserirCPF())
                    {
                        limpaTela();
                        cout << "\n\t===============CPF ALTERADO============\n";
                        it->mostraCliente();
                        pause();
                        break;
                    }
                    else
                    {
                        cout << "\n\tAlteração de cpf cancelada" << endl;
                        it->mostraCliente();
                        pause();
                        break;
                    }
                case 2:
                   
                    limpaTela();
                    if (it->inserirNome())
                    {
                        limpaTela();
                        cout << "\n\t===============NOME ALTERADO============\n";
                        it->mostraCliente();
                        pause();
                        break;
                    }
                    else
                    {
                        cout << "\n\tAlteração de nome cancelada" << endl;
                        pause();
                        break;
                    }
                case 3:

                    limpaTela();
                    if (it->inserirCNH())
                    {
                        limpaTela();
                        cout << "\n\t===============CNH ALTERADO============\n";
                        it->mostraCliente();
                        pause();
                        break;
                    }
                    else
                    {
                        cout << "\n\tAlteração de numero da cnh cancelada" << endl;
                        pause();
                        break;
                    }
                case 4:

                    limpaTela();
                    if (it->inserirDataNascimento())
                    {
                        limpaTela();
                        cout << "\n\t===============DATA DE NASCIMENTO ALTERADO============\n";
                        it->mostraCliente();
                        pause();
                        break;
                    }
                    else
                    {
                        cout << "\n\tAlteração de data de nascimento cancelada" << endl;
                        pause();
                        break;
                    }
                case 5:
                    pause();
                    break;
                default:
                    break;
                }
            } while (escolha != 0);
        }
    }
}

void listarClientes(vector<Cliente> &listaClientes)
{
    limpaTela();

    if(listasVaziasCliente(listaClientes)){
        return;
    }
    cout << "\t==========LISTA DE CLIENTES===========\n";
    for (auto it = listaClientes.begin(); it != listaClientes.end(); ++it)
    {
        it->mostraCliente();
    }
    limpaBuffer();
    pause();
}

void localizaCliente(vector<Cliente> &listaClientes)
{
    string cpfParaBusca;

    do
    {
        limpaTela();
        cout << "\t==========PESQUISAR CLIENTE===========\n";
        cout << "\n\tInsira um cpf válido para busca : ";
        cin >> cpfParaBusca;
        limpaBuffer();

    } while (!verificaCPF(cpfParaBusca));

    for (auto it = listaClientes.begin(); it != listaClientes.end(); ++it)
    {
        if (it->cpf == cpfParaBusca)
        {
            limpaTela();
            cout << "\t==========CLIENTE ENCONTRADO===========\n";
            it->mostraCliente();
            pause();
            return;
        }
    }

    cout << "\t==========CLIENTE NÃO ENCONTRADO===========\n";
    pause();
    return;
}


bool listasVaziasCliente(vector<Cliente> &listaClientes){
    if(listaClientes.empty()){
        limpaTela();
        cout << "Nenhum cliente cadastrado" << endl;
        pause();
        return true;
    }
    return false;
}