#include "verificacoes.h"

bool codigoDisponivel(vector<Produto> &produtos , string codigo){
    for(int i = 0 ; i < produtos.size() ; i++){
        if(produtos[i].codigo == codigo){
            cout << "Codigo já existente" << endl;
            return false;
        }
    }
    return true;
}

bool nomeDisponivel(vector<Produto> &produtos, string nome){
    for(int i = 0 ; i < produtos.size() ;i++){
        if(produtos[i].nome == nome){
            cout << "Nome já existente" << endl;
            return false;
        }
    }
    return true;
}

bool verificaPreco(float preco){
    if(preco <=0){
        cout << "**Preço deve ser um valor acima de 0**" << endl;
        return false;
    }else{
        return true;
    }
}

bool nomePadrao(string nome){
    if(nome.length() > 20){
        cout << "**Nome deve ser ter no máximo 20 caracteres**" << endl;
        return false;
    }
    return true;
}

bool codigoPadrao(string codigo){
    if(codigo.length() > 13){
        cout << "**Codigo deve ser ter no máximo 13 caracteres**" << endl;
        return false;
    }else{
        for(int i = 0 ; i < codigo.length() ; i++){
            if(!isdigit(codigo[i])){
                cout << "**Codigo deve ter apenas caracteres numéricos**" << endl;
                return false;
            }
        }
    }

    return true;
}