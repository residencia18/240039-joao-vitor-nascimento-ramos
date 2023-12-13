#ifndef ITEMSET_H
#define ITEMSET_H
#include <iostream>
#include <ctime>
#include <string>
#include <vector>

using namespace std;

#pragma once

class ItemSet
{
public:
    vector<string> items;

    ItemSet(){}

    ~ItemSet(){}

    ItemSet(vector<string> items)
    {
        this->items = items;
    }

    vector<string> getItems()
    {
        return items;
    }

    void setItems(vector<string> items)
    {
        this->items = items;
    }

    void limpaBuffer()
    {
        cin.get();
    }

    void limpaTela()
    {
#ifdef _WIN32
        system("cls");
#else
        system("clear");
#endif
    }

    void pause()
    {
        cout << "\n\tDigite enter para continuar!...\n";
        cin.get();
        limpaTela();
    }

    int menu()
    {

        limpaTela();
        int opcao = 0;

        do
        {
            mostraDataAtual();
            cout << "\t=========MENU=========";
            cout << "\n\t[1] - INCLUIR:";
            cout << "\n\t[2] - LISTAR:";
            cout << "\n\t[3] - ALTERAR:";
            cout << "\n\t[4] - EXCLUIR:";
            cout << "\n\t[5] - LOCALIZAR:";
            cout << "\n\t[0] - SAIR";
            cout << "\n\tENTRADA ->  ";
            cin >> opcao;
            limpaBuffer();

            if (opcao > 5 || opcao < 0)
            {
                limpaTela();
                cout << "Ops, escolha invalida!...\n";
                pause();
            }

        } while (opcao > 5 || opcao < 0);

        return opcao;
    }

    void gestaoDeStrings()
    {
        int escolha = 0;
        string s;

        do
        {
            switch (escolha = menu())
            {

            case 1:

                incluir(s);

                break;

            case 2:

                if (items.empty())
                {
                    cout << "\n\tNão há strings cadastradas!...\n";
                    pause();
                }
                else
                {
                    listar();
                }

                break;

            case 3:

                if (items.empty())
                {
                    cout << "\n\tNão há strings cadastradas!...\n";
                    pause();
                }
                else
                {
                    update();
                }

                break;

            case 4:

                if (items.empty())
                {
                    cout << "\n\tNão há strings cadastradas!...\n";
                    pause();
                }
                else
                {
                    excluir(s);
                }

                break;

            case 5:

                if (items.empty())
                {
                    cout << "\n\tNão há strings cadastradas!...\n";
                    pause();
                }
                else
                {
                    localizar(s);
                }

                break;

            case 0:

                cout << "\n\tPrograma encerrado com sucesso!..." << endl;
                escolha = 0;
                exit(0);

            default:
                cout << "\tOps, Opção invalida!";
            }
        } while (escolha != 0);
    }

    tm *getTempo()
    {
        time_t t;
        time(&t);
        struct tm *data;
        data = localtime(&t);
        return data;
    }

    void mostraDataAtual()
    {
        // int diaSemana = getTempo()->tm_wday;

        printf("\n\tDATA ATUAL: %02d/%02d/%4d, %s\n ", getTempo()->tm_mday, getTempo()->tm_mon + 1, getTempo()->tm_year + 1900, diaDaSemana().c_str());
        printf("\tHORA ATUAL: %02d:%02d:%02d\n", getTempo()->tm_hour, getTempo()->tm_min, getTempo()->tm_sec);

        if (bissexto())
        {
            printf("\tANO BISSEXTO, FALTA %i PARA TERMINAR O ANO!...\n", 366 - getTempo()->tm_yday);
        }
        else
        {
            printf("\tANO NÃO BISSEXTO, FALTA %i PARA TERMINAR O ANO!...\n", 365 - getTempo()->tm_yday);
        }
        if (getTempo()->tm_hour >= 0 && getTempo()->tm_hour < 12)
        {
            printf("\tBOM DIA!...\n");
        }
        else if (getTempo()->tm_hour >= 12 && getTempo()->tm_hour < 18)
        {
            printf("\tBOA TARDE!...\n");
        }
        else
        {
            printf("\tBOA NOITE!...\n");
        }

        printf("\n");
    }

    string diaDaSemana()
    {

        switch (getTempo()->tm_wday)
        {
        case 0:
            return "DOMINGO";
        case 1:
            return "SEGUNDA-FEIRA";
        case 2:
            return "TERÇA-FEIRA";
        case 3:
            return "QUARTA-FEIRA";
        case 4:
            return "QUINTA-FEIRA";
        case 5:
            return "SEXTA-FEIRA";
        case 6:
            return "SABADO";
        }
        return "ERRO";
    }

    bool bissexto()
    {
        // getTempo()->tm_year + 1900 siguinifica o ano atual
        if (getTempo()->tm_year + 1900 % 4 == 0 && getTempo()->tm_year + 1900 % 100 != 0)
        {
            return true;
        }
        else
        {
            if (getTempo()->tm_year + 1900 % 400 == 0)
            {
                return true;
            }
        }
        return false;
    }

    void incluir(string s)
    {
        char opcao;

        do
        {
            limpaTela();

            do
            {
                cout << "\n\t===========INCLUIR===========\n";
                cout << "\n\tDigite o item: ";
                getline(cin, s);

                if (verificaSeExiste(s))
                {
                    cout << "\n\tItem já existe!...\n";
                    pause();
                }

            } while (verificaSeExiste(s));

            items.push_back(s);
            cout << "\n\tItem incluido com sucesso!...\n";
            pause();

            do
            {
                cout << "\n\tDeseja continuar incluindo strings? [s/n]: ";
                cin >> opcao;
                limpaBuffer();

                if (opcao != 's' && opcao != 'S' && opcao != 'n' && opcao != 'N')
                {
                    cout << "\n\tOpção invalida!...\n";
                    pause();
                }

            } while (opcao != 's' && opcao != 'S' && opcao != 'n' && opcao != 'N');

        } while (opcao != 'n' && opcao != 'N');
    }

    bool verificaSeExiste(string item)
    {
        for (auto it = items.begin(); it != items.end(); it++)
        {
            if (*it == item)
            {
                return true;
            }
        }
        return false;
    }

    void listar()
    {
        int i = 0;
        limpaTela();
        cout << "\n\t===========LISTAR===========";
        for (auto it = items.begin(); it != items.end(); it++, i++)
        {
            cout << "\n\t" << i + 1 << "º " << *it;
        }
        cout << "\n\t============================\n";
        pause();
    }

    void update()
    {
        int opcao = 0;
        string item;
        char opcao2;

        do
        {
            limpaTela();
            listar();
            cout << "\n\t===========ALTERAR===========\n";
            cout << "\n\tDigite o numero do item que deseja alterar: ";
            cin >> opcao;
            limpaBuffer();

            if (opcao > items.size() || opcao < 1)
            {
                cout << "\n\tOps, opção invalida!\n\tNão existe string com esse nome!...\n";
                pause();
            }

        } while (opcao > items.size() || opcao < 1);

        do
        {
            limpaTela();
            cout << "\n\t===========ALTERAR===========\n";
            cout << "\n\tDigite o novo item: ";
            getline(cin, item);

            items[opcao - 1] = item;

            listar();

            do
            {
                cout << "\n\tItem alterado com sucesso!...\n";
                pause();

                cout << "\n\tDeseja continuar alterando a mesma strings? [s/n]: ";
                cin >> opcao2;
                limpaBuffer();

                if (opcao2 != 's' && opcao2 != 'S' && opcao2 != 'n' && opcao2 != 'N')
                {
                    cout << "\n\tOpção invalida!...\n";
                    pause();
                }

            } while (opcao2 != 's' && opcao2 != 'S' && opcao2 != 'n' && opcao2 != 'N');

        } while (opcao2 != 'n' && opcao2 != 'N');
    }

    void excluir(string s)
    {
        char opcao;
        bool existe;

        do
        {
            do
            {
                limpaTela();
                listar();
                cout << "\n\t===========EXCLUIR===========\n";
                cout << "\n\tInforme o nome da string que deseja excluir: ";
                getline(cin, s);

                if (verificaSeExiste(s))
                {
                    for (auto it = items.begin(); it != items.end(); it++)
                    {
                        if (*it == s)
                        {
                            items.erase(it);
                            cout << "\n\tItem excluido com sucesso!...\n";
                            pause();
                        }
                    }
                    existe = false;
                }
                else
                {
                    cout << "\n\tOps, opção invalida!\n\tNão existe string com esse nome!...\n";
                    pause();
                    existe = true;
                }

            } while (existe);

            do
            {
                listar();

                cout << "\n\tDeseja continuar excluindo strings? [s/n]: ";
                cin >> opcao;
                limpaBuffer();

                if (opcao != 's' && opcao != 'S' && opcao != 'n' && opcao != 'N')
                {
                    cout << "\n\tOpção invalida!...\n";
                    pause();
                }

            } while (opcao != 's' && opcao != 'S' && opcao != 'n' && opcao != 'N');

        } while (opcao != 'n' && opcao != 'N');
    }

    void localizar(string s)
    {
        bool existe;

        do
        {
            limpaTela();
            listar();
            cout << "\n\t===========LOCALIZAR===========\n";
            cout << "\n\tInforme o nome da string que deseja localizar: ";
            getline(cin, s);

            if (verificaSeExiste(s))
            {
                for (auto it = items.begin(); it != items.end(); it++)
                {
                    if (*it == s)
                    {
                        limpaTela();
                        cout << "\n\tString ( " << *it << " ) localizada com sucesso!...\n";
                        pause();
                    }
                }
                existe = false;
            }
            else
            {
                cout << "\n\tOps, opção invalida!\n\tNão existe string com esse nome!...\n";
                pause();
                existe = true;
            }

        } while (existe);
    }

private:
};

#endif