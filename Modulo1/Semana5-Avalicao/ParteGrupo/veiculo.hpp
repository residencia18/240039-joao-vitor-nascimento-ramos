#ifndef VEICULO_HPP
#define VEICULO_HPP

#include <iostream>
#include "verificacoesVeiculo.hpp"

using namespace std;

typedef struct
{
    string renavan;
    string placa;
    DataHora retirada;
    DataHora entrega;
    string lojaRetirada;
    string lojaEntrega;

    bool inserirRenavan()
    {
        string renavanInserido;
        bool renavanValido;

        do
        {
            cout << "\n\tInsira um renavan válido (apenas números) : ";
            getline(cin, renavanInserido);

            renavanValido = verificaRenavan(renavanInserido);

            if (!renavanValido)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }
        } while (!renavanValido);

        renavan = renavanInserido;
        return true;
    }

    bool inserirPlaca()
    {
        string placaInserida;
        bool placaValida;

        do
        {
            cout << "\n\tInsira uma placa válida (Ex : AAA1111) : ";
            getline(cin, placaInserida);

            placaValida = verificaPlaca(placaInserida);

            if (!placaValida)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }
        } while (!placaValida);

        placa = placaInserida;
        return true;
    }

    bool inserirRetirada()
    {
        DataHora retiradaInserida;
        string data;
        string hora;
        bool dataHoraValida;

        do
        {
            bool dataValida;
            do
            {
                cout << "\n\tInsira uma data de retirada válida (dd/mm/aaaa) : ";
                getline(cin, data);
                dataValida = (retiradaInserida.data.preencheData(data));

            } while (!dataValida);

            bool horaValida;

            do
            {
                cout << "\n\tInsira uma hora de retirada válida (hh:mm:ss) : ";
                getline(cin, hora);

                horaValida = retiradaInserida.hora.preencheHorario(hora);

            } while (!horaValida);

            dataHoraValida = verificaRetirada(retiradaInserida);

            if (!dataHoraValida)
            {
                pause();
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }

        } while (!dataHoraValida);

        retirada = retiradaInserida;
        return true;
    }

    bool inserirEntrega()
    {
        DataHora entregaInserida;
        string data;
        string hora;
        bool dataHoraValida;

        do
        {
            bool dataValida;
            do
            {
                cout << "\n\tInsira uma data de entrega válida (dd/mm/aaaa) : ";
                getline(cin, data);

                dataValida = entregaInserida.data.preencheData(data);

            } while (!dataValida);

            bool horaValida;
            do
            {
                cout << "\n\tInsira uma hora de entrega válida (hh:mm:ss) : ";
                getline(cin, hora);

                horaValida = entregaInserida.hora.preencheHorario(hora);

            } while (!horaValida);

            dataHoraValida = verificaEntrega(retirada, entregaInserida);

            if (!dataHoraValida)
            {
                pause();
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }

        } while (!dataHoraValida);

        entrega = entregaInserida;
        return true;
    }

    bool inserirLoja()
    {
        string lojaInserido;
        bool lojaValida;

        do
        {
            cout << "\n\tInsira uma loja de retirada : ";
            getline(cin, lojaInserido);

            lojaValida = verificaLoja(lojaInserido);

            if (!lojaValida)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }
        } while (!lojaValida);

        lojaRetirada = lojaInserido;
        return true;
    }

    bool inserirLojaEntrega()
    {
        string lojaInserido;
        bool lojaValida;

        do
        {
            cout << "\n\tInsira uma loja de entrega : ";
            getline(cin, lojaInserido);

            lojaValida = verificaLoja(lojaInserido);

            if (!lojaValida)
            {
                if (!verificaProsseguimento())
                {
                    return false;
                }
                limpaTela();
            }
        } while (!lojaValida);

        lojaEntrega = lojaInserido;
        return true;
    }

    bool preencheVeiculo()
    {
        if (inserirRenavan())
        {
            if (inserirPlaca())
            {
                if (inserirRetirada())
                {
                    if (inserirEntrega())
                    {
                        if (inserirLoja())
                        {
                            if(inserirLojaEntrega()){

                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    void mostraVeiculo()
    {
        cout << "\tVeiculo" << endl;
        cout << "\tRenavan : " << renavan << endl;
        cout << "\tPlaca : " << placa << endl;
        cout << "\n\tRetirada : "; retirada.data.mostraData();
        cout << "\n\tHorário : "; retirada.hora.mostraHorario();
        cout << "\n\tEntrega : "; entrega.data.mostraData();
        cout << "\n\tHorário : "; entrega.hora.mostraHorario();
        cout << "\tLoja de retirada : " << lojaRetirada << endl;
        cout << "\tLoja de entrega : " << lojaEntrega << endl;
        cout << "\t========================================\n";
    }
    
    void mostraVeiculo(int posicao)
    {
        cout << "\t" << (posicao+1) <<  "° Veiculo" << endl;
        cout << "\tRenavan : " << renavan << endl;
        cout << "\tPlaca : " << placa << endl;
        cout << "\n\tRetirada : "; retirada.data.mostraData();
        cout << "\n\tHorário : "; retirada.hora.mostraHorario();
        cout << "\n\tEntrega : "; entrega.data.mostraData();
        cout << "\n\tHorário : "; entrega.hora.mostraHorario();
        cout << "\tLoja de retirada : " << lojaRetirada << endl;
        cout << "\tLoja de entrega : " << lojaEntrega << endl;
        cout << "\t========================================\n";
    }

} Veiculo;

#endif