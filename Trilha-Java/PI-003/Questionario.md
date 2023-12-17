# Perguntas sobre Exceções em Java

## 1. O que é uma exceção em Java e qual é o propósito de usá-las em programas?

**Resposta:**
Uma exceção em Java é um evento que ocorre durante a execução de um programa, indicando que algo inesperado ou errado aconteceu. O propósito de usar exceções é lidar com erros de forma estruturada, permitindo que o programa trate situações excepcionais de maneira apropriada, evitando a quebra inesperada e proporcionando uma saída controlada para o usuário ou para o próprio sistema.

## 2. Pesquisa sobre a diferença entre exceções verificadas e não verificadas em Java. Dê exemplos de cada uma.

**Resposta:**
Em Java, exceções verificadas são aquelas que o compilador obriga o programador a tratar ou declarar em uma cláusula `throws`. Exemplos incluem `IOException` e `SQLException`. Por outro lado, exceções não verificadas são aquelas que o compilador não obriga o tratamento, como `NullPointerException` e `ArithmeticException`.

## 3. Como você pode lidar com exceções em Java? Quais são as palavras-chave e as práticas comuns para o tratamento de exceções?

**Resposta:**
Para lidar com exceções em Java, você pode usar as palavras-chave `try`, `catch`, `finally`, `throw` e `throws`. O bloco `try` envolve o código propenso a exceções, `catch` trata a exceção capturada e `finally` contém código que sempre será executado. A palavra-chave `throw` é usada para lançar exceções manualmente, e `throws` é usado para declarar exceções que um método pode lançar.

Práticas comuns incluem log de exceções, tratamento adequado da exceção (como tentar novamente, notificar o usuário, ou encerrar o programa graciosamente), e não capturar exceções de maneira genérica.

## 4. O que é o bloco "try-catch" em Java? Como ele funciona e por que é importante usá-lo ao lidar com exceções?

**Resposta:**
O bloco `try-catch` em Java é usado para envolver o código que pode gerar exceções. O bloco `try` contém o código propenso a exceções, e o bloco `catch` contém o código que será executado caso uma exceção do tipo especificado ocorra. Isso evita a interrupção abrupta do programa e permite um tratamento controlado de exceções, melhorando a robustez do código.

## 5. Quando é apropriado criar suas próprias exceções personalizadas em Java e como você pode fazer isso? Dê um exemplo de quando e por que você criaria uma exceção personalizada.

**Resposta:**
É apropriado criar exceções personalizadas em Java quando você tem cenários específicos no seu programa que não são cobertos pelas exceções padrão. Isso ajuda a fornecer informações mais específicas sobre o erro ocorrido. Para criar uma exceção personalizada, você pode criar uma classe que estende `Exception` ou suas subclasses.

Exemplo:
```java
public class MeuErroPersonalizado extends Exception {
    public MeuErroPersonalizado(String mensagem) {
        super(mensagem);
    }
}
