#include<iostream>
#include<iomanip>
#include<cmath>

using namespace std;

int main(){

    float A;
    float B;

    cout << "A = ";
    cin >> A;

    cout << "B = ";
    cin >> B;

    cout << fixed << setprecision(1) << noshowpoint;

    cout << endl << "Soma = " << A+B << endl;
    cout << "Subtração = " << A-B << endl;
    cout << "Multiplicação = " << A*B << endl;
    cout << "Divisão = " << floor((A/B) * 10) / 10 << endl;

    return 0;
}