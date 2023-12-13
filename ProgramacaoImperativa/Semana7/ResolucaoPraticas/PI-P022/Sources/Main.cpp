#include "../Headers/GerenciadorTarefas.hpp"
#include "../Headers/Tarefa.hpp"
#include "../Headers/BancoDeDados.hpp"

#include <iostream>

using namespace std;

int main()
{
  GerenciadorTarefas gerenciadorTarefas;
  BancoDeDados::recuperarTarefas(*gerenciadorTarefas.getTarefas());
  gerenciadorTarefas.menuTarefas(*gerenciadorTarefas.getTarefas());
}