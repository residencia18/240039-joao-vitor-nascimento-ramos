#include<iostream>

using namespace std;

float calc_serie(int n);

int main(){

    int n = 2;

    cout << calc_serie(n) << endl;

    return 0;
}

float calc_serie(int n){
    float somatorio = 0 ;

    for(int i = 1; i <= n ; i++){
        somatorio += (float)(i)/(n+1-i);
    }
    return somatorio;
}

