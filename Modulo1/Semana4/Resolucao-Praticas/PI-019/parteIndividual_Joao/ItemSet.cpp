#include "ItemSet.hpp"


void ItemSet::insereItem(string item){
    for(auto it=this->listaItens.begin() ; it!=this->listaItens.end() ; it++){
        if(*it==item){
            return;
        }
    }
    this->listaItens.push_back(item);
}

void ItemSet::removeItem(string item){
    for(auto it=this->listaItens.begin() ; it!=this->listaItens.end() ; it++){
        if(*it==item){
            listaItens.erase(it);
        }
    }
}

