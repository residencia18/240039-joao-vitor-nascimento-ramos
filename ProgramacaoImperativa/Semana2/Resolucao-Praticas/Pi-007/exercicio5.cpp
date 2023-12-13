#include<iostream>
#include<ctime>

int main(){

    srand(time(0));

    int numero = rand()%100 + 1;
    bool acertou = false;
    int chute;

    printf("\nEntre com um numero : ");
    scanf("%d",&chute);

    while(!acertou){

        if(chute == numero){
            acertou = true;
            printf("Acertou, Parabens!!!!\n");
        }else if(chute > numero){
            printf("Chutou muito alto, chute um número menor : ");
            scanf("%d",&chute);
        }else{
            printf("Chutou muito baixo, chute um número maior : ");
            scanf("%d",&chute);
        }
    }




    return 0;
}