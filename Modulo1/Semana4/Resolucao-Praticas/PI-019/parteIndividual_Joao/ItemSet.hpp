#include<iostream>
#include<vector>

using namespace std;

class ItemSet{

    private:
        vector<string> listaItens;
    public:
        string getItem();
        void insereItem(string item);
        void removeItem(string item);
};