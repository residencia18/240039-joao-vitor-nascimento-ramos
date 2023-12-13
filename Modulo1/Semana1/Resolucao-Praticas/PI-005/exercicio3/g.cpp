#include <iostream>

using namespace std;

int main() {
    int a, b, c;

    cout << "Digite o valor de a: ";
    cin >> a;
    cout << "Digite o valor de b: ";
    cin >> b;

    cout << ((b == 0) ? "Não é possível dividir por zero" : ("Quociente = " + to_string(c = a / b))) << endl;

    return 0;
}
