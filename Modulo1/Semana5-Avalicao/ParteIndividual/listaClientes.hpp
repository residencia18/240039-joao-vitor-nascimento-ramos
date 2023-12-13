#ifndef LISTA_CLIENTES_HPP
#define LISTA_CLIENTES_HPP
#include<vector>
#include "cliente.hpp"

void insereCliente(vector<Cliente> &listaClientes);
void excluiCliente(vector<Cliente> &listaClientes);
void alteraCliente(vector<Cliente> &listaClientes);
void listarClientes(vector<Cliente> &listaClientes);
void localizaCliente(vector<Cliente> &listaClientes);

#endif

