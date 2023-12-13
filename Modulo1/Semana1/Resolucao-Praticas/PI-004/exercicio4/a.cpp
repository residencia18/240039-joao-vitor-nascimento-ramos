#include<iostream>
#include<limits>

using namespace std;

int main(){

    float maiorValorFloat = numeric_limits<float>::max();

    cout << "Maior valor float :" << maiorValorFloat << endl;

    return 0;
}