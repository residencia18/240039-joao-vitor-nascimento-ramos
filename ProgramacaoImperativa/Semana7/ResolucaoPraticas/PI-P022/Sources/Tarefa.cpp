#include "../Headers/Tarefa.hpp"

int Tarefa::quantidadeDeIdentificadores = 0;

Tarefa::Tarefa() {}

Tarefa::~Tarefa() {}

Tarefa::Tarefa(const string &titulo, const string &descricao, const string &dataDeCriacao, const bool &concluida) : titulo(titulo), descricao(descricao), dataDeCriacao(dataDeCriacao), concluida(concluida)
{
  Tarefa::quantidadeDeIdentificadores++;
  setId(Tarefa::quantidadeDeIdentificadores);
}

int Tarefa::getQuantidadeDeIdentificadores()
{
  return quantidadeDeIdentificadores;
}

void Tarefa::setQuantidadeDeIdentificadores(int quantidadeDeIdentificadores)
{
  Tarefa::quantidadeDeIdentificadores = quantidadeDeIdentificadores;
}

int Tarefa::getId()
{
  return this->id;
}

void Tarefa::setId(int id)
{
  this->id = id;
}

string Tarefa::getTitulo()
{
  return this->titulo;
}

void Tarefa::setTitulo(string titulo)
{
  this->titulo = titulo;
}

string Tarefa::getDescricao()
{
  return this->descricao;
}

void Tarefa::setDescricao(string descricao)
{
  this->descricao = descricao;
}

string Tarefa::getDataDeCriacao()
{
  return this->dataDeCriacao;
}

void Tarefa::setDataDeCriacao(string dataDeCriacao)
{
  this->dataDeCriacao = dataDeCriacao;
}

// string Tarefa::getDataDeConclusao()
// {
//   return this->dataDeConclusao;
// }

// void Tarefa::setDataDeConclusao(string dataDeConclusao)
// {
//   this->dataDeConclusao = dataDeConclusao;
// }

bool Tarefa::getConcluida()
{
  return this->concluida;
}

void Tarefa::setConcluida(bool concluida)
{
  this->concluida = concluida;
}

void Tarefa::mostrarTarefa()
{
  cout << "\n\tID: " << getId();
  cout << "\n\tTitulo: " << getTitulo();
  cout << "\n\tDescricao: " << getDescricao();
  cout << "\n\tData de criacao: " << getDataDeCriacao();

  if (getConcluida() == true)
  {
    cout << "\n\tConcluida! " << endl;
  }
  else
  {
    cout << "\n\tPendente! " << endl;
  }
  cout << "\t========================================";
}
