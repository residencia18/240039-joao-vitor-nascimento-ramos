#ifndef SISTEMA_HPP
#define SISTEMA_HPP

#include "Gerencia.hpp"

class Sistema{

    vector<Cliente> clientes;
    vector<Pacote> pacotes;
    vector<Evento> eventos;
    vector<Gerencia> gerencias;

    public:

    static void menuPrincipal(vector<Cliente> &clientes,vector<Pacote> &pacotes,vector<Evento> &eventos, vector<Gerencia> &gerencias);
    static void mostraMenuPrincipal();
    vector<Cliente>* getListaClientes();
    vector<Pacote>* getListaPacotes();
    vector<Evento>* getListaEventos();
    vector<Gerencia>* getListaGerencia();

    /*CLIENTES*/

        /*MENUS*/
        static void menuCliente(vector<Cliente> &clientes);
        static void mostraMenuCliente();
        /*FUNÇÃO MENU CLIENTES*/
        static void insereCliente(vector<Cliente> &clientes);
        static void insereDependente(vector<Cliente> &clientes);
        static void encontraCliente(vector<Cliente> &clientes);
        static void encontraDependente(vector<Cliente> &clientes);
        static void listaClientes(vector<Cliente> &clientes);
        static void listaDependentesPorCliente(vector<Cliente> &clientes);
        static void excluiClientes(vector<Cliente> &clientes);
        static void excluiDependente(vector<Cliente> &clientes);
        static void modificaCliente(vector<Cliente> &clientes);
        static void modificaDependente(vector<Cliente> &clientes);

        /*FUNÇÕES AUXILIARES*/
        static Cliente cadastraCliente();
        static Dependente cadastraDependente();
        static int recebeIDCliente();
        static int recebeIDDependente();
        static void pause();

        /**PERSISTENCIA**/
        static void insereClienteNoBanco(Cliente &cliente);
        static void atualizaClientesBanco(vector<Cliente> &clientes);
        static void insereDependenteNoBanco(int &idCliente, Dependente &dependente);
        static void atualizaDependentesNoBanco(vector<Cliente> &clientes);
        static void recuperaClientes(vector<Cliente> &clientes);
        static void recuperaDependentes(vector<Cliente> &clientes);

    /**EVENTOS**/
        /*MENUS*/
            static void menuEventos(vector<Evento> &eventos);
            static void mostraMenuEventos();

        /*ROTEIRO*/
            static void menuRoteiro(vector<Evento> &eventos);
            static void mostraMenuRoteiro();
            /***FUNÇÃO MENU ROTEIRO*/
            static void insereRoteiro(vector<Evento> &eventos);
            static void encontraRoteiro(vector<Evento> &eventos);
            static void excluiRoteiro(vector<Evento> &eventos);
            static void listaRoteiro(vector<Evento> &eventos);
            static void modificaRoteiro(vector<Evento> &eventos);
            /**FUNÇÕES AUXILIARES**/
            static Roteiro cadastraRoteiro();
            static int recebeIDRoteiro();

            /**PERSISTENCIA*/
            static void insereRoteiroNoBanco(Roteiro &eventos);
            static void atualizaRoteirosBanco(vector<Evento> &eventos);
            static void recuperaRoteiros(vector<Evento> &eventos);

    /*DESLOCAMENTO*/
            static void menuDeslocamento(vector<Evento> &eventos);
            static void mostraMenuDeslocamento();
            /***FUNÇÃO MENU ROTEIRO*/
            static void insereDeslocamento(vector<Evento> &eventos);
            static void encontraDeslocamento(vector<Evento> &eventos);
            static void excluiDeslocamento(vector<Evento> &eventos);
            static void listaDeslocamento(vector<Evento> &eventos);
            static void modificaDeslocamento(vector<Evento> &eventos);
            /**FUNÇÕES AUXILIARES**/
            static Deslocamento cadastraDeslocamento();
            static int recebeIDDeslocamento();

            /**PERSISTENCIA*/
            static void insereDeslocamentoNoBanco(Deslocamento &deslocamento);
            static void atualizaDeslocamentosBanco(vector<Evento> &eventos);
            static void recuperaDeslocamentos(vector<Evento> &eventos);

    /*PERNOITE*/
            static void menuPernoite(vector<Evento> &eventos);
            static void mostraMenuPernoite();
            /***FUNÇÃO MENU ROTEIRO*/
            static void inserePernoite(vector<Evento> &eventos);
            static void encontraPernoite(vector<Evento> &eventos);
            static void excluiPernoite(vector<Evento> &eventos);
            static void listaPernoite(vector<Evento> &eventos);
            static void modificaPernoite(vector<Evento> &eventos);
            /**FUNÇÕES AUXILIARES**/
            static Pernoite cadastraPernoite();
            static int recebeIDPernoite();

            /**PERSISTENCIA*/
            static void inserePernoiteNoBanco(Pernoite &pernoite);
            static void atualizaPernoitesBanco(vector<Evento> &eventos);
            static void recuperaPernoites(vector<Evento> &eventos);
            
    /*PACOTES*/
        /*MENUS*/
            static void menuPacotes(vector<Pacote> &pacotes, vector<Evento> &eventos);
            static void mostraMenuPacotes();
        /**FUNÇÕES MENU PACOTE*/
            static void inserePacote(vector<Pacote> &pacotes, vector<Evento> &eventos);
            static void insereEventoNoPacote(vector<Pacote> &pacotes, vector<Evento> &eventos);
            static void insereRoteiroNoPacote(Pacote *pacote, vector<Evento> &eventos);
            static void insereDeslocamentoNoPacote(Pacote *pacote, vector<Evento> &eventos);
            static void inserePernoiteNoPacote(Pacote *pacote, vector<Evento> &eventos);
            static void encontraPacote(vector<Pacote> &pacotes);
            static void excluiPacote(vector<Pacote> &pacotes,vector<Evento> &eventos);
            static void excluiEventoDeUmPacote(vector<Pacote> &pacotes,vector<Evento> &eventos);
            static void excluiRoteiroNoPacote(Pacote *pacote);
            static void excluiDeslocamentoNoPacote(Pacote *pacote);
            static void excluiPernoiteNoPacote(Pacote *pacote);
            static void listaPacotes(vector<Pacote> &pacotes);
            static void listaEventosDeUmPacote(vector<Pacote> &pacotes);
            static void modificaPacote(vector<Pacote> &pacotes,vector<Evento> &eventos);

        /**FUNÇÕES AUXILIARES*/
            static int recebeIDPacote();
            static Pacote cadastraPacote();

        /**PERSISTENCIA**/
            static void inserePacoteNoBanco(Pacote &pacote);
            static void atualizaPacotesBanco(vector<Pacote> &pacotes, vector<Evento> &eventos);
            static void recuperaPacote(vector<Pacote> &pacotes,vector<Evento> &eventos);

    /**GERENCIA**/
        /*MENUS*/
            static void menuGerencia(vector<Gerencia> &gerencias, vector<Cliente> &clientes,vector<Pacote> &pacotes);
            static void mostraMenuGerencia();
        /**FUNÇÕES MENU GERENCIA*/
            static void inserePacoteACliente(vector<Gerencia> &gerencias, vector<Cliente> &clientes,vector<Pacote> &pacotes);
            static void excluiPacoteDeCliente(vector<Gerencia> &gerencias);
            static void mostraPacotesDeCliente(vector<Gerencia> &gerencias);

        /**PERSISTENCIA**/
            static void insereGerenciaNoBanco(Gerencia &gerencia);
            static void atualizaGerenciaNoBanco(vector<Gerencia> &gerencias);
            static void recuperaGerenciaNoBanco(vector<Gerencia> &gerencias, vector<Cliente> &clientes,vector<Pacote> &pacotes);

};



#endif