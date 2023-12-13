#include "ItemSet.hpp"
#include <algorithm>

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

void ItemSet::MostraSet(){
    for(auto it=this->listaItens.begin() ; it!= this->listaItens.end() ; it++){
        cout << *it << endl;
    }
}

void ItemSet::operator=(ItemSet vector2){
    this->listaItens.clear();
    for(auto it=vector2.listaItens.begin() ; it!=vector2.listaItens.end() ; it++){
        listaItens.push_back(*it);
    }
}

ItemSet ItemSet::operator+(ItemSet vector2){
    ItemSet uniao;
    for(auto it=this->listaItens.begin() ; it!=listaItens.end() ; it++){
        uniao.insereItem(*it);
    }

    for(auto it=vector2.listaItens.begin() ; it!=vector2.listaItens.end() ; it++){
        uniao.insereItem(*it);
    }

    return uniao;
}

ItemSet ItemSet::operator*(ItemSet vector2) {
    ItemSet inter;

    for(auto it = this->listaItens.begin(); it != listaItens.end(); it++) {
        inter.insereItem(*it);
    }

    auto it = inter.listaItens.begin();
    while (it != inter.listaItens.end()) {
        if (find(vector2.listaItens.begin(), vector2.listaItens.end(), *it) == vector2.listaItens.end()) {
            it = inter.listaItens.erase(it); 
        } else {
            ++it; 
        }
    }

    return inter;
}

ItemSet ItemSet::operator-(ItemSet vector2){
    ItemSet diferenca;

    for(auto it = this->listaItens.begin(); it != listaItens.end(); it++) {
        diferenca.insereItem(*it);
    }

    auto it=diferenca.listaItens.begin();

    while(it!= diferenca.listaItens.end()){
        if (find(vector2.listaItens.begin(), vector2.listaItens.end(), *it) != vector2.listaItens.end()) {
            it = diferenca.listaItens.erase(it); 
        } else {
            ++it; 
        }
    }

    return diferenca;
}

ItemSet ItemSet::operator%(ItemSet vector2){
    ItemSet delta = ((*this)-(vector2))+((vector2)-(*this));


    return delta;
}

bool ItemSet::operator==(ItemSet vector2) {

    if (this->listaItens.size() != vector2.listaItens.size()) {
        return false;
    }

    for(auto it=this->listaItens.begin() ; it!=this->listaItens.end() ; it++){
        if (find(vector2.listaItens.begin(), vector2.listaItens.end(), *it) == vector2.listaItens.end()) {
            return false;
        } 
    }
    

    return true;
}


