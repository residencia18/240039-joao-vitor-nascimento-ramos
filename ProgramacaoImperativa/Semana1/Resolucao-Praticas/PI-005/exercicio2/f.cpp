#include<iostream>
#include<cctype>

using namespace std;

int main(){

    char ch1,ch2,ch3;

    ch2 = 81;
    ch3 = ch2 + (97-65);

    cout << dec << (int)ch3 << " - " << oct << (int)ch3 << " - " << hex << (int)ch3 << " - " << ch3 << endl;

    return 0;
}