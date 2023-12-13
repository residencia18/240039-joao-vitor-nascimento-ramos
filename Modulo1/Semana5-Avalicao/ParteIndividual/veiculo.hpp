#ifndef VEICULO_HPP
#define VEICULO_HPP

#include<iostream>
#include "verificacoesVeiculo.hpp"

using namespace std;

typedef struct{
    string renavan;
    string placa;
    DataHora retirada;
    DataHora entrega;
    string lojaRetirada;

    bool inserirRenavan(){
        string renavanInserido;
        bool renavanValido;
        do{
            cout << "Insira um renavan válido(apenas numeros) : ";
            cin >> renavanInserido;
            renavanValido = verificaRenavan(renavanInserido);
            if(!renavanValido){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }
        }while(!renavanValido);
        renavan = renavanInserido;
        return true;
    }

    bool inserirPlaca(){
        string placaInserida;
        bool placaValida;
        do{
            cout << "Insira uma placa válida(Ex : AAA1111) : ";
            cin >> placaInserida;
            placaValida = verificaPlaca(placaInserida);
            if(!placaValida){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }
        }while(!placaValida);
        placa = placaInserida;
        return true;
    }

    bool inserirRetirada(){
        DataHora retiradaInserida;
        string data;
        string hora;
        bool dataHoraValida;
        do{ 
            bool dataValida;
            do{
                
                cout << "Insira uma data de retirada válida(dd/mm/aaaa) : ";
                limpaBuffer();
                getline(cin,data);
                dataValida = (retiradaInserida.data.preencheData(data));
            }while(!dataValida);
            
            bool horaValida;

            do{
                cout << "Insira uma hora de retirada válida(hh:mm:ss) : ";
                limpaBuffer();
                getline(cin,hora);
                horaValida = retiradaInserida.hora.preencheHorario(hora);
            }while(!horaValida);

            dataHoraValida = verificaRetirada(retiradaInserida);

            if(!dataHoraValida){
                pause();
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }

        }while(!dataHoraValida);
        retirada = retiradaInserida;
        return true;
    }

    bool inserirEntrega(){
        DataHora entregaInserida;
        string data;
        string hora;
        bool dataHoraValida;
        do{
            bool dataValida;
            do{
                cout << "Insira uma data de entrega válida(dd/mm/aaaa) : ";
                limpaBuffer();
                getline(cin,data);
                dataValida = entregaInserida.data.preencheData(data);
            }while(!dataValida);

            bool horaValida;
            do{
                cout << "Insira uma hora de entrega válida(hh:mm:ss) : ";
                limpaBuffer();
                getline(cin,hora);
                horaValida =  entregaInserida.hora.preencheHorario(hora);
            }while(!horaValida);

            dataHoraValida = verificaEntrega(retirada,entregaInserida);

            if(!dataHoraValida){
                pause();
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }

        }while(!dataHoraValida);
        entrega = entregaInserida;
        return true;
    }

   bool inserirLoja(){
        string lojaInserido;
        bool lojaValida;
        do{
            cout << "Insira uma loja de retirada : ";
            cin >> lojaInserido;
            lojaValida = verificaLoja(lojaInserido);
            if(!lojaValida){
                if(!verificaProsseguimento()){
                    return false;
                }
                limpaTela();
            }
        }while(!lojaValida);
        lojaRetirada = lojaInserido;
        return true;
    }

    bool preencheVeiculo(){
        if(inserirRenavan()){
            if(inserirPlaca()){
                if(inserirRetirada()){
                    if(inserirEntrega()){
                        if(inserirLoja()){
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    void mostraVeiculo(){
        cout << endl << "Veiculo" << endl;
        cout << "Renavan : " << renavan << endl;
        cout << "Placa : " << placa << endl ;
        cout << endl << "Retirada : " << endl;
        retirada.data.mostraData();
        retirada.hora.mostraHorario();
        cout << endl << "Entrega : " << endl;
        entrega.data.mostraData();
        entrega.hora.mostraHorario();
        cout << endl << "Loja de retirada : " << lojaRetirada << endl;
    }




}Veiculo;

#endif