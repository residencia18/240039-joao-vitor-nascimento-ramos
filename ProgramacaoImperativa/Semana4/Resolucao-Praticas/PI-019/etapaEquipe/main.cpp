#include<iostream>
#include "ItemSet.hpp"

int main(){

    ItemSet a;
    ItemSet b;
    ItemSet c;
    ItemSet d;
    ItemSet e;
    ItemSet f;
    ItemSet g;

    a.insereItem("a");
    a.insereItem("b");
    a.insereItem("c");
    a.insereItem("d");
    a.insereItem("e");

    b.insereItem("c");
    b.insereItem("a");
    b.insereItem("f");

    //atribuicao
    c = a;
    c.MostraSet();
    cout << "------------------" << endl;

    //união
    d = a+b;
    d.MostraSet();
    cout << "------------------" << endl;

    //intersecção
    e = a*b;
    e.MostraSet();
    cout << "------------------" << endl;


    //Diferença
    f=a-b;
    f.MostraSet();
    cout << "------------------" << endl;

    //Delta
    g=a%b;
    g.MostraSet();
    cout << "------------------" << endl;


    //Igualdade
    if(c==a){
        cout << "São Iguais" << endl;
    }
    cout << "------------------" << endl;


    return 0;
}