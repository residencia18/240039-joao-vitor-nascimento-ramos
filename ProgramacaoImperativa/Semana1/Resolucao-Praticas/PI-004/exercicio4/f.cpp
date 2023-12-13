#include<iostream>
#include<limits>

using namespace std;

int main(){

    double maiorValordouble = numeric_limits<double>::max();
    long double maiorValorLongdouble = numeric_limits<long double>::max();

    cout << "Maior valor do double :" << maiorValordouble << endl;
    cout << "Maior valor do long double :" << maiorValorLongdouble << endl;

    /*
    Linux ubuntu : 
    Maior valor do double :1.79769e+308
    Maior valor do long double :1.18973e+4932
    */

    return 0;
}