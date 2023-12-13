#include "../Headers/BancoDeDados.hpp"
#include "../Headers/Tarefa.hpp"
#include <fstream>

void BancoDeDados::recuperarTarefas(vector<Tarefa> &tarefas)
{
  ifstream tarefasFile("../BancoDeDados/tarefas.txt");

  if (tarefasFile.is_open())
  {
    string id, descricao, titulo, dataDeCriacao, concluida;
    int idInt, concluidaInt;

    while (!tarefasFile.eof())
    {
      getline(tarefasFile, id);
      getline(tarefasFile, titulo);
      getline(tarefasFile, descricao);
      getline(tarefasFile, dataDeCriacao);
      getline(tarefasFile, concluida);

      if (id != "")
      {
        idInt = stoi(id);
        concluidaInt = stoi(concluida);
        Tarefa tarefa(titulo, descricao, dataDeCriacao, true);
        if (concluidaInt == 0)
        {
          tarefa.setConcluida(false);
        }
        tarefa.setId(idInt);

        if (idInt > Tarefa::getQuantidadeDeIdentificadores())
        {
          Tarefa::setQuantidadeDeIdentificadores(idInt);
        }
        tarefas.push_back(tarefa);
      }
    }
    tarefasFile.close();
  }
  else
  {
    cout << "Não foi possível abrir o arquivo de tarefas." << endl;
  }
}

void BancoDeDados::salvarTarefas(Tarefa &tarefa)
{
  ofstream tarefasFile;
  tarefasFile.open("../BancoDeDados/tarefas.txt", ios_base::app);

  if (tarefasFile.is_open())
  {
    tarefasFile << tarefa.getId() << endl;
    tarefasFile << tarefa.getTitulo() << endl;
    tarefasFile << tarefa.getDescricao() << endl;
    tarefasFile << tarefa.getDataDeCriacao() << endl;
    tarefasFile << tarefa.getConcluida() << endl;
    tarefasFile.close();
  }
  else
  {
    cout << "Não foi possível abrir o arquivo de tarefas." << endl;
  }
}

void BancoDeDados::salvarTarefasConcluidas(vector<Tarefa> &tarefas)
{
  ofstream tarefasFile;
  tarefasFile.open("../BancoDeDados/tarefas.txt", ios_base::out);

  if (tarefasFile.is_open())
  {
    for (auto it = tarefas.begin(); it != tarefas.end(); it++)
    {
      tarefasFile << it->getId() << endl;
      tarefasFile << it->getTitulo() << endl;
      tarefasFile << it->getDescricao() << endl;
      tarefasFile << it->getDataDeCriacao();
      tarefasFile << it->getConcluida() << endl;
    }
    tarefasFile.close();
  }
  else
  {
    cout << "Não foi possível abrir o arquivo de tarefas." << endl;
  }
}