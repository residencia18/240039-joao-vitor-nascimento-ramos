#ifndef PONTO_HPP
#define PONTO_HPP

class Ponto{

    private:
        float x , y;

    public:
        Ponto(float , float );
        float getX();
        void setX(float x);

        float getY();
        void setY(float y);

        void setCoordenadas(float x , float y);
        double calcularDistancia();

};


#endif