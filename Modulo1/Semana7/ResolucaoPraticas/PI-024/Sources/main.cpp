#include "../Headers/Sistema.hpp"

int main(){

    Sistema sistema;
    Sistema::recuperaClientes(*sistema.getListaClientes());
    Sistema::recuperaDependentes(*sistema.getListaClientes());
    Sistema::recuperaRoteiros(*sistema.getListaEventos());
    Sistema::recuperaDeslocamentos(*sistema.getListaEventos());
    Sistema::recuperaPernoites(*sistema.getListaEventos());
    Sistema::recuperaPacote(*sistema.getListaPacotes(),*sistema.getListaEventos());
    Sistema::recuperaGerenciaNoBanco(*sistema.getListaGerencia(),*sistema.getListaClientes(),*sistema.getListaPacotes());
    Sistema::menuPrincipal(*sistema.getListaClientes(),*sistema.getListaPacotes(),*sistema.getListaEventos(),*sistema.getListaGerencia());


    return 0;
}