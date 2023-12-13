#ifndef GERENCIADORTAREFAS_HPP
#define GERENCIADORTAREFAS_HPP
#include "Tarefa.hpp"

#include <iostream>
#include <vector>
#include <ctime>

using namespace std;

class GerenciadorTarefas
{
private:
  vector<Tarefa> tarefas;

public:
  vector<Tarefa> *getTarefas();
  vector<Tarefa> setTarefas(vector<Tarefa> tarefas);

  static void pause();
  static void limpaTela();
  // static tm *getTempo();

  static void mostraMenuPrincipal();
  static void menuTarefas(vector<Tarefa> &tarefas);

  static void adicionarTarefa(vector<Tarefa> &tarefas);
  static  void listarTarefas(vector<Tarefa> &tarefas);
  static void marcarTarefaComoConcluida(vector<Tarefa> &tarefas);
};

#endif