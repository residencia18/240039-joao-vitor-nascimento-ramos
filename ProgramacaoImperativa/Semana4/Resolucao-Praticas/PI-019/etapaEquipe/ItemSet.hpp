#include<iostream>
#include<vector>

using namespace std;

class ItemSet{

    private:
        vector<string> listaItens;
    public:
        void insereItem(string item);
        void removeItem(string item);
        void operator=(ItemSet vector2);
        ItemSet operator+(ItemSet vector2);
        ItemSet operator*(ItemSet vector2);
        ItemSet operator-(ItemSet vector2);
        ItemSet operator%(ItemSet vector2);
        bool operator==(ItemSet vector2);
        void MostraSet();
};