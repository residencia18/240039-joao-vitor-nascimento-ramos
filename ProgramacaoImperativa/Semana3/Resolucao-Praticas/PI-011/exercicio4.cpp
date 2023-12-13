#include<iostream>

using namespace std;

void somaSubtrai(int & , int&);

int main(){

    int x = 9;
    int y = -1;

    somaSubtrai(x,y);

    cout << x << endl;
    cout << y << endl;


    return 0;

}

void somaSubtrai(int &x , int &y){
    x = x + y ;
    y = (x - 2*y) ;
}