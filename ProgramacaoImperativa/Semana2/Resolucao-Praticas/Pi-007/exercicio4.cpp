#include<iostream>
#include<cmath>

using namespace std;

int main(){

    int n;
    cin >>  n; 

    printf("1\n");
    printf("2\n");

    for(int i = 2 ; i <= n; i++){
        bool primo = true;
        for(int j = 2 ; j < (sqrt(i)+1) ; j++){
            if(i%j==0){
                primo = false;
                break;
            }
        }
        if(primo){
            printf("%d\n",i);
        }
    }



    return 0;
}