#ifndef TAREFA_HPP
#define TAREFA_HPP

#include <iostream>
#include <ctime>

using namespace std;

class Tarefa
{

private:
  static int quantidadeDeIdentificadores;
  int id;
  string titulo;
  string descricao;
  string dataDeCriacao;
  string dataDeConclusao;
  bool concluida;

public:

  Tarefa();

  ~Tarefa();

  Tarefa(const string &titulo, const string &descricao, const string &dataDeCriacao, const bool &concluida);

  static int getQuantidadeDeIdentificadores();
  static void setQuantidadeDeIdentificadores(int quantidadeDeIdentificadores);

  int getId();
  void setId(int id);

  string getTitulo();
  void setTitulo(string titulo);

  string getDescricao();
  void setDescricao(string descricao);

  string getDataDeCriacao();
  void setDataDeCriacao(string dataDeCriacao);

  string getDataDeConclusao();
  void setDataDeConclusao(string dataDeConclusao);

  bool getConcluida();
  void setConcluida(bool concluida);

  void mostrarTarefa();
};

#endif