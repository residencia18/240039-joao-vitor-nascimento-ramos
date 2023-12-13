# O que é uma classe em Java e qual é a diferença entre uma classe e um objeto? Dê 5 exemplos mostrando-os em C++ e em Java.

### Em Java (e em C++), uma classe é um modelo ou um plano para criar objetos. Uma classe define atributos (variáveis) e métodos (funções) que os objetos criados a partir dessa classe terão. Um objeto, por sua vez, é uma instância concreta de uma classe. A diferença básica é que uma classe é uma estrutura abstrata, enquanto um objeto é uma instância concreta dessa estrutura.

## Exemplos de definições de classes em C++


```cpp
#include<bits/stdc++.h>

using namespace std;

class Carro {
public:
    // Atributos
    string modelo;
    int ano;

    // Método
    void ligar() {
        cout << "O carro está ligado." << endl;
    }
};

class Ponto {
public:
    int x;
    int y;
    
    Ponto(int xCoord, int yCoord) : x(xCoord), y(yCoord) {}

    void imprimirCoordenadas() {
        cout << "Coordenadas: (" << x << ", " << y << ")" << endl;
    }
};

class Retangulo {
public:
    float comprimento;
    float largura;

    Retangulo(float c, float l) : comprimento(c), largura(l) {}

    float calcularArea() {
        return comprimento * largura;
    }
};

class Aluno {
public:
    string nome;
    int idade;
    float nota;

    Aluno(string n, int i, float nt) : nome(n), idade(i), nota(nt) {}

    void exibirInformacoes() {
        cout << "Nome: " << nome << ", Idade: " << idade << ", Nota: " << nota << endl;
    }
};

class Livro {
public:
    string titulo;
    string autor;
    int anoPublicacao;

    Livro(string tit, string aut, int ano) : titulo(tit), autor(aut), anoPublicacao(ano) {}

    void imprimirDetalhes() {
        cout << "Título: " << titulo << ", Autor: " << autor << ", Ano de Publicação: " << anoPublicacao << endl;
    }
};


```

## Exemplos de definições de classes em Java 

```java
public class Carro {
    // Atributos
    String modelo;
    int ano;

    // Método
    void ligar() {
        System.out.println("O carro está ligado.");
    }
}

class Ponto {
    int x;
    int y;
    
    public Ponto(int xCoord, int yCoord) {
        x = xCoord;
        y = yCoord;
    }

    void imprimirCoordenadas() {
        System.out.println("Coordenadas: (" + x + ", " + y + ")");
    }
}

class Retangulo {
    float comprimento;
    float largura;

    public Retangulo(float c, float l) {
        comprimento = c;
        largura = l;
    }

    float calcularArea() {
        return comprimento * largura;
    }
}

class Aluno {
    String nome;
    int idade;
    float nota;

    public Aluno(String n, int i, float nt) {
        nome = n;
        idade = i;
        nota = nt;
    }

    void exibirInformacoes() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Nota: " + nota);
    }
}

class Livro {
    String titulo;
    String autor;
    int anoPublicacao;

    public Livro(String tit, String aut, int ano) {
        titulo = tit;
        autor = aut;
        anoPublicacao = ano;
    }

    void imprimirDetalhes() {
        System.out.println("Título: " + titulo + ", Autor: " + autor + ", Ano de Publicação: " + anoPublicacao);
    }
}


```

# Como você declara uma variável em Java e quais são os tipos de dados primitivos mais comuns? Faça um paralelo entre isso e a mesma coisa na linguagem C++

### Por java uma linguagem totalmente orientada a objetos, toda variavel possui um modificador de acesso. A declaração da variavel é feita da seguinte maneira : <Modificador de Acesso> <Tipo da Variavel> <Nome da Variavel>. Caso o modificador seja omitido, o default é private. Já o C++ os modificadores de acesso são necessários somente para os atributos das classes.

### Os tipos de dados primitivos de C++ são:
```cpp
1. char
2. int
3. float
4. double
```

### Os tipos de dados primitivos de Java são:

```java
1. char
2. int
3. float
4. double
5. boolean
```


# Explique o conceito de herança em Java e como você pode criar uma subclasse a partir de uma classe existente. Faça um paralelo com C++, apresentando 5 exemplos.

### A herança é um conceito fundamental na programação orientada a objetos que permite criar uma nova classe (subclasse) baseada em uma classe existente (classe base ou superclasse). A subclasse herda características (atributos e métodos) da superclasse, permitindo a reutilização de código e a extensão de funcionalidades. 

### Exemplos de henraça em c++

```cpp

#include<bits/stdc++.h>

using namespace std;

class Veiculo {
public:
    Veiculo(string marca) : marca(marca) {}

    void ligar() {
        cout << "Veículo da marca " << marca << " ligado." << endl;
    }

private:
    string marca;
};

// Subclasse 1
class Carro : public Veiculo {
public:
    Carro(string marca, string modelo) : Veiculo(marca), modelo(modelo) {}

    void acelerar() {
        cout << "Carro " << modelo << " acelerando." << endl;
    }

private:
    string modelo;
};

// Subclasse 2
class Moto : public Veiculo {
public:
    Moto(string marca, string tipo) : Veiculo(marca), tipo(tipo) {}

    void empinar() {
        cout << "Moto " << tipo << " empinando." << endl;
    }

private:
    string tipo;
};

class Aviao : public Veiculo {
public:
    Aviao(string marca, int capacidade) : Veiculo(marca), capacidade(capacidade) {}

    void voar() {
        cout << "Avião voando com capacidade de " << capacidade << " passageiros." << endl;
    }

private:
    int capacidade;
};

class Barco : public Veiculo {
public:
    Barco(string marca, bool motorizado) : Veiculo(marca), motorizado(motorizado) {}

    void navegar() {
        cout << "Barco navegando." << endl;
    }

private:
    bool motorizado;
};

```

### Exemplos de herança em Java

```java

public class Veiculo {
    private String marca;

    public Veiculo(String marca) {
        this.marca = marca;
    }

    public void ligar() {
        System.out.println("Veículo da marca " + marca + " ligado.");
    }
}

class Carro extends Veiculo {
    private String modelo;

    public Carro(String marca, String modelo) {
        super(marca);
        this.modelo = modelo;
    }

    public void acelerar() {
        System.out.println("Carro " + modelo + " acelerando.");
    }
}

class Moto extends Veiculo {
    private String tipo;

    public Moto(String marca, String tipo) {
        super(marca);
        this.tipo = tipo;
    }

    public void empinar() {
        System.out.println("Moto " + tipo + " empinando.");
    }
}

class Aviao extends Veiculo {
    private int capacidade;

    public Aviao(String marca, int capacidade) {
        super(marca);
        this.capacidade = capacidade;
    }

    public void voar() {
        System.out.println("Avião voando com capacidade de " + capacidade + " passageiros.");
    }
}

class Barco extends Veiculo {
    private boolean motorizado;

    public Barco(String marca, boolean motorizado) {
        super(marca);
        this.motorizado = motorizado;
    }

    public void navegar() {
        System.out.println("Barco navegando.");
    }
}


```

## Quando declaramos uma variável em Java, temos, na verdade, um ponteiro. Em C++ é diferente. Discorra sobre esse aspecto.

**Java:**

Em Java, todas as variáveis de objetos são referências, mas não são explicitamente ponteiros. Quando você declara uma variável de objeto em Java, está criando uma referência a um objeto no heap. No entanto, o programador não tem acesso direto ao endereço de memória (como em ponteiros em C++). O gerenciamento de memória é feito automaticamente pelo coletor de lixo (`garbage collector`) em Java.

Exemplo em Java:

```java
String texto = "Exemplo";
```
Neste caso, texto é uma referência para uma instância de String no heap, mas o programador não lida explicitamente com ponteiros ou gerenciamento de memória.

**C++:**

Em C++, você tem mais controle sobre a manipulação de ponteiros e alocação/desalocação de memória. A declaração de variáveis de objetos pode ser feita diretamente ou através de ponteiros, dependendo da necessidade do programador. C++ permite operações de ponteiro e requer que o programador gerencie manualmente a memória (por exemplo, usando `new` e `delete`).

