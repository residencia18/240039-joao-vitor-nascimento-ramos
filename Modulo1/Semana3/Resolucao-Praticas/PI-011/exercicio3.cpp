#include<iostream>
#include<algorithm>

using namespace std;

void ordena(float &, float &, float &, float &);

int main(){

    float f1 = 4 , f2 = 2.7 , f3 = 5.5 , f4 = 1;


    ordena(f1,f2,f3,f4);

    return 0;
}

void ordena(float &f1, float &f2, float &f3, float &f4){
    float vetor[4];
    vetor[0] = f1;
    vetor[1] = f2;
    vetor[2] = f3;
    vetor[3] = f4;
    sort(vetor,vetor+4);
    f1 = vetor[0];
    f2 = vetor[1];
    f3 = vetor[2];
    f4 = vetor[3];
}

