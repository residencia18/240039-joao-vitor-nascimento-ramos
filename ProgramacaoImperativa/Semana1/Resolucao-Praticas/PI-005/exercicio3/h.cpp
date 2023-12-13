#include <iostream>

using namespace std;

int main() {
    int a, b, c;

    cout << "Digite o valor de a: ";
    cin >> a;
    cout << "Digite o valor de b: ";
    cin >> b;
    c= a/b;

    cout << ((b == 0) ? "Não é possível dividir por zero" : (a%b != 0) ? 
    (to_string(a) + " não apresenta divisão exata por " + to_string(b)) : "A/B = " + to_string(c)) << endl;



    return 0;
}
