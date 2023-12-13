#include "listaVeiculos.hpp"

void insereVeiculo(vector<Veiculo> &listaVeiculos){
    limpaTela();
    Veiculo veiculo;
    if(veiculo.preencheVeiculo()){
         for(auto it=listaVeiculos.begin() ; it!=listaVeiculos.end() ; ++it){
            if(it->placa == veiculo.placa){
                cout << "Placa já cadastrado" << endl;
                cout << "Não foi possivel inserir novo veiculo" << endl;
                cin.get();
                pause();
                return;
            }
        }
        listaVeiculos.push_back(veiculo);
        cout << "Veiculo inserido com sucesso" << endl;
        pause();
    }else{
        cout << "Não foi possivel inserir novo veiculo" << endl;
        cin.get();
        pause();
    }

}

void excluiVeiculo(vector<Veiculo> &listaVeiculos){
    limpaTela();
    string placaParaBusca;
    do{
        cout << "Insira uma placa válida para exclusão : ";
        cin >> placaParaBusca;
    }while(!verificaPlaca(placaParaBusca));

    for(auto it=listaVeiculos.begin() ; it!=listaVeiculos.end() ; ++it){
        if(it->placa == placaParaBusca){
            listaVeiculos.erase(it);
            cout << "Veiculo excluido com sucesso" << endl;
            pause();
            return;
        }
    }
    limpaTela();
    cout << "O Veiculo de placa " << placaParaBusca << " não se encontra em nosso banco de dados" << endl;
    cin.get();
    pause();
}

void listarVeiculos(vector<Veiculo> &listaVeiculos){
    cout << "-----------VEICULOS------------";
    for(auto it=listaVeiculos.begin() ; it!=listaVeiculos.end() ; ++it){
        cout << "-------------------------------" << endl;
        it->mostraVeiculo();
    }
    cin.get();
    cout << "-------------------------------" << endl;
    pause();
}

void localizaVeiculo(vector<Veiculo> &listaVeiculos){

    string placaParaBusca;
    do{
        cout << "Insira um cpf válido para busca : ";
        cin >> placaParaBusca;
    }while(!verificaPlaca(placaParaBusca));

    for(auto it=listaVeiculos.begin() ; it!=listaVeiculos.end() ; ++it){
        if(it->placa == placaParaBusca){
            cout << "-------------------------------" << endl;
            it->mostraVeiculo();
            return;
        }
    }

}

void alteraVeiculo(vector<Veiculo> &listaVeiculos){
    string placaParaAlteracao;
    do{
        cout << "Insira a placa do veiculo que deseja alterar dados : ";
        cin >> placaParaAlteracao;
    }while(!verificaPlaca(placaParaAlteracao));

    for(auto it=listaVeiculos.begin() ; it!=listaVeiculos.end() ; ++it){
        if(it->placa == placaParaAlteracao){
            int escolha;
            do{
                limpaTela();
                cout << "-------------------------------" << endl;
                it->mostraVeiculo();
                cout << endl << "Qual dados deseja alterar? " << endl;
                cout << "1. Renavan" << endl << "2. Placa" << endl << "3. Data de Retirada" << endl << "4. Data de Entrega" << endl << "5. Loja de retirada" << endl << "6. Nenhum" << endl;
                cout << "Escolha : ";
                cin >> escolha;
                switch (escolha)
                {
                case 1:
                    if(it->inserirRenavan()){
                        cout << "Renavan alterado com sucesso" << endl;
                        pause();
                        break;
                    }else{
                        cout << "Alteração de renavan cancelada" << endl;
                        pause();
                        break;
                    }                       
                case 2 :
                    if(it->inserirPlaca()){
                        cout << "Placa alterada com sucesso" << endl;
                        pause();
                        break;
                    }else{
                        cout << "Alteração de placa cancelada" << endl;
                        pause();
                        break;
                    }                       
                case 3 :
                    if(it->inserirRetirada()){
                        cout << "Data de retirada alterada com sucesso" << endl;
                        pause();
                        break;
                    }else{
                        cout << "Alteração de data de retirada cancelada" << endl;
                        pause();
                        break;
                    }                       
                case 4 :
                    if(it->inserirEntrega()){
                        cout << "Data de entrega alterada com sucesso" << endl;
                        pause();
                        break;
                    }else{
                        cout << "Alteração de data de entrega cancelada" << endl;
                        pause();
                        break;
                    }   
                case 5:
                    if(it->inserirLoja()){
                        cout << "Loja de retirada alterada com sucesso" << endl;
                        pause();
                        break;
                    }else{
                        cout << "Alteração de loja de retirada cancelada" << endl;
                        pause();
                        break;
                    }
                case 6:
                    pause();
                    break;
                default:
                    break;
                }
            }while(escolha!=6);
        }
    }
}