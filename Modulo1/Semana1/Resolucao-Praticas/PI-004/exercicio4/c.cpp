#include<iostream>
#include<iomanip>

using namespace std;

int main(){

    float pif = 3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679;

    cout << "pi :" << setprecision(3)  << pif << endl;
    cout << "pi :" << setprecision(5)  << pif << endl;
    cout << "pi :" << setprecision(9)  << pif << endl;
    cout << "pi :" << setprecision(17)  << pif << endl;


    return 0;
}