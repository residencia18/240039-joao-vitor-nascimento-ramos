#include "listaLocacoes.hpp"

void insereLocacao(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao)
{
    Locacao locacao;
    string cpf;
    string placa;
    string data;
    string hora;
    int escolha;
    bool clienteExiste, veiculoExiste;

    if(listasVaziasLocacao(listaClientes,listaVeiculos)){
        return;
    }

    do
    {
        limpaTela();
        cout << "\n\t===========INCLUIR LOCAÇÃO===========" << endl;

        do
        {
            cout << "\n\tDigite o CPF do cliente: ";
            getline(cin, cpf);
        } while (!verificaCPF(cpf));

        do
        {
            cout << "\n\tDigite a placa do veículo: ";
            getline(cin, placa);
        } while (!verificaPlaca(placa));

        limpaTela();

        clienteExiste = verificaCliente(locacao, listaClientes, cpf);
        veiculoExiste = verificaVeiculo(locacao, listaVeiculos, placa);

        if (!clienteExiste || !veiculoExiste)
        {
            cout << "\n\tCliente ou veículo não encontrado!" << endl;
            pause();
        }

    } while (!clienteExiste || !veiculoExiste);

    for (auto it = listaLocacao.begin(); it != listaLocacao.end(); it++)
    {
        if (it->cliente.cpf == cpf && it->veiculo.placa == placa)
        {
            cout << "\n\tCliente ou veículo já locado!" << endl;
            pause();
            return;
        }
    }

    if (clienteExiste && veiculoExiste)
    {
        do
        {
            cout << "\n\tDigite a data de retirada: ";
            getline(cin, data);
        } while (!locacao.retirada.data.preencheData(data));

        do
        {
            cout << "\n\tDigite a hora de retirada: ";
            getline(cin, hora);
        } while (!locacao.retirada.hora.preencheHorario(hora));

        do
        {
            cout << "\n\tDigite a data de entrega: ";
            getline(cin, data);
        } while (!locacao.entrega.data.preencheData(data));

        do
        {
            cout << "\n\tDigite a hora de entrega: ";
            getline(cin, hora);
        } while (!locacao.entrega.hora.preencheHorario(hora));

        do
        {
            cout << "\n\tDigite se a locação foi realizada: ";
            cout << "\n\t[1] - SIM:";
            cout << "\n\t[2] - NÃO:";
            cout << "\n\tENTRADA ->  ";
            cin >> escolha;
            limpaBuffer();

            if (escolha == 1)
            {
                locacao.realizada = true;
            }
            else if (escolha == 2)
            {
                locacao.realizada = false;
            }
            else
            {
                cout << "\n\tOpção inválida!" << endl;
            }

        } while (escolha != 1 && escolha != 2);

        listaLocacao.push_back(locacao);

        limpaTela();
        cout << "\n\tLocação realizada com sucesso!" << endl;
    }
    else
    {
        cout << "\n\tCliente ou veículo não encontrado!" << endl;
    }
    pause();
}

void excluiLocacao(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao)
{
    Locacao locacao;
    string cpf;
    string placa;
    bool clienteExiste, veiculoExiste;

    if(listasVaziasLocacao(listaClientes,listaVeiculos)){
        return;
    }

    do
    {
        limpaTela();
        cout << "\n\t===========EXCLUIR LOCAÇÃO===========" << endl;

        do
        {
            cout << "\n\tDigite o CPF do cliente: ";
            getline(cin, cpf);
        } while (!verificaCPF(cpf));

        do
        {
            cout << "\n\tDigite a placa do veículo: ";
            getline(cin, placa);
        } while (!verificaPlaca(placa));

        limpaTela();

        clienteExiste = verificaCliente(locacao, listaClientes, cpf);
        veiculoExiste = verificaVeiculo(locacao, listaVeiculos, placa);

        if (!clienteExiste || !veiculoExiste)
        {
            cout << "\n\tCliente ou veículo não encontrado!" << endl;
            pause();
        }

    } while (!clienteExiste || !veiculoExiste);

    if (clienteExiste && veiculoExiste)
    {
        for (auto it = listaLocacao.begin(); it != listaLocacao.end(); it++)
        {
            if (it->cliente.cpf == cpf && it->veiculo.placa == placa)
            {
                listaLocacao.erase(it);
                cout << "\n\tLocação excluída com sucesso!" << endl;
                break;
            }
        }
    }
    else
    {
        cout << "\n\tCliente ou veículo não encontrado!" << endl;
    }

    pause();
}

void alteraLocacao(vector<Cliente> &listaClientes, vector<Veiculo> &listaVeiculos, vector<Locacao> &listaLocacao)
{
    Locacao locacao;
    string cpf;
    string placa;
    string data;
    string hora;
    int escolha;
    bool clienteExiste, veiculoExiste;

    if(listasVaziasLocacao(listaClientes,listaVeiculos)){
        return;
    }

    do
    {
        limpaTela();
        cout << "\n\t===========ALTERAR LOCAÇÃO===========" << endl;

        cout << "\n\tDigite o CPF do cliente: ";
        getline(cin, cpf);

        cout << "\n\tDigite a placa do veículo: ";
        getline(cin, placa);
        limpaTela();

        clienteExiste = verificaCliente(locacao, listaClientes, cpf);
        veiculoExiste = verificaVeiculo(locacao, listaVeiculos, placa);

        if (!clienteExiste || !veiculoExiste)
        {
            cout << "\n\tCliente ou veículo não encontrado!" << endl;
            pause();
        }

    } while (!clienteExiste || !veiculoExiste);

    if (clienteExiste && veiculoExiste)
    {
        for (auto it = listaLocacao.begin(); it != listaLocacao.end(); it++)
        {
            if (it->cliente.cpf == cpf && it->veiculo.placa == placa)
            {
                cout << "\n\tDigite a nova data de retirada: ";
                getline(cin, data);
                it->retirada.data.preencheData(data);

                cout << "\n\tDigite a nova data de entrega: ";
                getline(cin, data);
                it->entrega.data.preencheData(data);

                do
                {
                    cout << "\n\tDigite se a locação foi realizada: ";
                    cout << "\n\t[1] - SIM:";
                    cout << "\n\t[2] - NÃO:";
                    cout << "\n\tENTRADA ->  ";
                    cin >> escolha;
                    limpaBuffer();

                    if (escolha == 1)
                    {
                        it->realizada = true;
                    }
                    else if (escolha == 2)
                    {
                        it->realizada = false;
                    }
                    else
                    {
                        cout << "\n\tOpção inválida!" << endl;
                    }

                } while (escolha != 1 && escolha != 2);

                cout << "\n\tLocação alterada com sucesso!" << endl;
                break;
            }
        }
    }
    else
    {
        cout << "\n\tCliente ou veículo não encontrado!" << endl;
    }

    pause();
}

bool listasVaziasLocacao(vector<Cliente> &listaCliente, vector<Veiculo> &listaVeiculo){

     if(listaCliente.empty() && listaVeiculo.empty()){
        limpaTela();
        cout << "\tNão há clientes e veiculos cadastrados" << endl;
        pause();
        return true;
    }else{
        if(listaCliente.empty()){
            limpaTela();
            cout << "\tNão há clientes cadastrados" << endl;
            pause();
            return true;
        }else{
            if(listaVeiculo.empty()){
                limpaTela();
                cout << "\tNão há veiculos cadastrados" << endl;
                pause();
                return true;
            }
        }
    }
    return false;
}

void listarLocacao(vector<Locacao> &listaLocacao, vector<Cliente> &listaCliente , vector<Veiculo> &listaVeiculo)
{
    limpaTela();

    if(listasVaziasLocacao(listaCliente,listaVeiculo)){
        return;
    }

    cout << "\n\t===========LISTAR LOCAÇÕES===========" << endl;

    for (auto it = listaLocacao.begin(); it != listaLocacao.end(); it++)
    {
        cout << "\n\tCliente Nome: " << it->cliente.nome << endl;
        cout << "\n\tCliente CPF: " << it->cliente.cpf << endl;
        cout << "\n\tVeículo Placa: " << it->veiculo.placa << endl;
        cout << "\n\tData de retirada: ";
        it->retirada.data.mostraData();
        cout << "\n\tHora de retirada: ";
        it->retirada.hora.mostraHorario();
        cout << "\n\tData de entrega: ";
        it->entrega.data.mostraData();
        cout << "\n\tHora de entrega: ";
        it->entrega.hora.mostraHorario();

        if (it->realizada)
        {
            cout << "\n\tLocação realizada: SIM" << endl;
        }
        else
        {
            cout << "\n\tLocação realizada: NÃO" << endl;
        }
        cout << "\n\t======================================" << endl;
    }
    pause();
}

bool verificaCliente(Locacao &locacao, vector<Cliente> &listaClientes, string cpf)
{
    for (auto it = listaClientes.begin(); it != listaClientes.end(); it++)
    {
        if (it->cpf == cpf)
        {
            locacao.cliente = *it;
            return true;
        }
    }
    return false;
}

bool verificaVeiculo(Locacao &locacao, vector<Veiculo> &listaVeiculos, string placa)
{
    for (auto it = listaVeiculos.begin(); it != listaVeiculos.end(); it++)
    {
        if (it->placa == placa)
        {
            locacao.veiculo = *it;
            return true;
        }
    }
    return false;
}
