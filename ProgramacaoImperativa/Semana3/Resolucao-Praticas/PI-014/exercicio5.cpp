#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

typedef struct
{
    int id = 0;
    string marca;
    string placa;
    string modelo;
    string cor;
    int marchaAtual;
    double velocidadeAtual;

} Veiculo;

void editar(vector<Veiculo> &veiculos);

void remover(vector<Veiculo> &veiculos);



Veiculo retornarVeiculo(vector<Veiculo> &veiculos, int id)
{
    if (id > veiculos.size())
    {
        cout << "\nVeiculo não encontrado!\n";
        pause();
    }
    else if (id < 0)
    {
        cout << "\nVeiculo não encontrado!\n";
        pause();
    }
    for (auto it = veiculos.begin(); it != veiculos.end(); it++)
    {
        if (it->id == id)
        {
            cout << "\n" << it->id << "º Veiculo";
            cout << "\n======================================";
            cout << "\nMarca do Veiculo: " << it->marca << endl;
            cout << "\nModelo do Veiculo: " << it->modelo << endl;
            cout << "\nCor do Veiculo: " << it->cor << endl;
            cout << "\nPlaca do Veiculo: " << it->placa << endl;
            cout << "======================================\n";
            return *it; // retorna o veiculo
        }
    }

    cout << "\nVeiculo não encontrado!\n";
    pause();
}


void editar(vector<Veiculo> &veiculos)
{
    limparTela();
    auto it = veiculos.begin();

    if (veiculos.empty())
    {
        cout << "\nNenhum veiculo cadastrado!\n";
        pause();
        return;
    }

    int id = 0;

    cout << "\n===============EDITAR===============\n";

    cout << "\nInforme o id do veiculo para pesquisar: ";
    cin >> id;
    cin.get();
    limparTela();

    *it = retornarVeiculo(veiculos, id);

    if (it->id == id)
    {
        cout << "\n==========EDITAR VEICULOS==========\n";
        cout << "\nInforme a marca do veículo: ";
        getline(cin, it->marca.assign(it->marca));

        cout << "\nInforme o modelo do veiculo: ";
        getline(cin, it->modelo.assign(it->modelo));

        cout << "\nInforme a cor do veiculo: ";
        getline(cin, it->cor.assign(it->cor));

        cout << "\nInforme a placa do veiculo: ";
        getline(cin, it->placa.assign(it->placa));

        pause();
    }
    else
    {
        cout << "\nVeiculo não encontrado!\n";
        pause();
        return;
    }
}

void remover(vector<Veiculo> &veiculos)
{
    limparTela();
    int id = 0;
    auto it = veiculos.begin();

    if (veiculos.empty())
    {
        cout << "\nNenhum veiculo cadastrado!\n";
        pause();
        return;
    }

    cout << "\n===============REMOVER===============\n";

    cout << "\nInforme o id do veiculo para remover: ";
    cin >> id;
    cin.get();
    limparTela();

    *it = retornarVeiculo(veiculos, id);

    if (it->id == id)
    {
        veiculos.erase(it);
        cout << "\nVeiculo removido com sucesso!\n";
        pause();
        return;
    }
}

void limparTela()
{
#ifdef _WIN32
    system("cls");
#else
    system("clear");
#endif
}

void pause()
{
    cout << "\nDigite enter para continuar!\n";
    cin.get();
    limparTela();
}

int main(){


    return 0;
}

