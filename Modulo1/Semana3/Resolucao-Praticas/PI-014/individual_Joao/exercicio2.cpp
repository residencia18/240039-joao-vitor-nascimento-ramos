#include<iostream>

void converteClecius_Fahrenheit(float celsius);
float conversao(float celsius);


int main(){

    float v1=100,v2=150,v3=-32;

    converteClecius_Fahrenheit(v1);
    converteClecius_Fahrenheit(v2);
    converteClecius_Fahrenheit(v3);


}

void converteClecius_Fahrenheit(float celsius){
    std::cout <<  celsius << "ºC == " << conversao(celsius) << "ºF" << std::endl;
}

float conversao(float celsius){
    return (float)9*(celsius/5) + 32;
}