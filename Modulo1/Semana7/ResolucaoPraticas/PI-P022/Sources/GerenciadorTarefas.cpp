#include "../Headers/GerenciadorTarefas.hpp"
#include "../Headers/Tarefa.hpp"
#include "../Headers/BancoDeDados.hpp"

vector<Tarefa> *GerenciadorTarefas::getTarefas()
{
  return &(this->tarefas);
}

void GerenciadorTarefas::pause()
{
  cout << "\n\tPressione qualquer tecla para continuar...";
  cin.get();
}

void GerenciadorTarefas::limpaTela()
{
#ifdef _WIN32
  system("cls");
#else
  system("clear");
#endif
}

tm *getTempo()
{
  time_t t;
  time(&t);
  struct tm *data;
  data = localtime(&t);
  return data;
}

void GerenciadorTarefas::mostraMenuPrincipal()
{
  limpaTela();
  cout << "\n\n\t===========MENU PRINCIPAL===========";
  cout << "\n\t[1] - ADICIONAR NOVAS TAREFAS:";
  cout << "\n\t[2] - MARCAR TAREFAS COMO CONCLUÍDAS:";
  cout << "\n\t[3] - LISTAR TAREFAS PENDENTES:";
  cout << "\n\t[0] - SAIR";
  cout << "\n\tENTRADA ->  ";
}

void GerenciadorTarefas::menuTarefas(vector<Tarefa> &tarefas)
{
  int escolha;

  do
  {
    GerenciadorTarefas::mostraMenuPrincipal();
    cin >> escolha;
    cin.get();

    switch (escolha)
    {
    case 1:
      GerenciadorTarefas::adicionarTarefa(tarefas);
      break;

    case 2:
      GerenciadorTarefas::marcarTarefaComoConcluida(tarefas);
      break;

    case 3:
      GerenciadorTarefas::listarTarefas(tarefas);
      break;

    case 0:
      cout << "\n\tPrograma finalizado com sucesso!...\n";
      pause();
      break;

    default:
      cout << "\n\tOps, opção inválida!...\n";
      pause();
      break;
    }

  } while (escolha != 0);
}

void GerenciadorTarefas::adicionarTarefa(vector<Tarefa> &tarefas)
{
  string titulo;
  string descricao;
  string dataDeCriacao;
  string dataDeConclusao;
  bool concluida = false;

  limpaTela();
  cout << "\n\t===========ADICIONAR NOVA TAREFA===========\n";
  cout << "\n\tTÍTULO: ";
  getline(cin, titulo);

  cout << "\n\tDESCRIÇÃO: ";
  getline(cin, descricao);

  dataDeCriacao = asctime(getTempo());

  Tarefa tarefa(titulo, descricao, dataDeCriacao, concluida);
  tarefas.push_back(tarefa);

  BancoDeDados::salvarTarefas(tarefa);
}

void GerenciadorTarefas::listarTarefas(vector<Tarefa> &tarefas)
{
  limpaTela();
  cout << "\n\t===========TAREFAS PENDENTES===========";
  for (auto it = tarefas.begin(); it != tarefas.end(); it++)
  {
    if (it->getConcluida() == false)
    {
      it->mostrarTarefa();
    }
  }

  cout << "\n\t===========TAREFAS CONCLUÍDAS===========";
  for (auto it = tarefas.begin(); it != tarefas.end(); it++)
  {
    if (it->getConcluida() == true)
    {
      it->mostrarTarefa();
    }
  }
  pause();
}

void GerenciadorTarefas::marcarTarefaComoConcluida(vector<Tarefa> &tarefas)
{
  int id;
  limpaTela();
  cout << "\n\t===========MARCAR TAREFA COMO CONCLUÍDA===========\n";
  cout << "\n\tInforme o ID da tarefa: ";
  cin >> id;
  cin.get();

  for (auto it = tarefas.begin(); it != tarefas.end(); it++)
  {
    if (it->getId() == id)
    {
      it->setConcluida(true);
      BancoDeDados::salvarTarefasConcluidas(tarefas);
      break;
    }
  }
}