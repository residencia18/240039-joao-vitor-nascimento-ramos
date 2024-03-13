# Sistema Petshop

Este é um sistema de gestão para petshops, que permite o gerenciamento de vendas, produtos, tipos de produtos, clientes, pagamentos e vendas de produtos.

# Modelo

![Modelo do sistema ](Modelo.png)

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de produtos.
- Cadastro, consulta, atualização e exclusão de tipos de produtos.
- Cadastro, consulta, atualização e exclusão de marcas.
- Cadastro, consulta, atualização e exclusão de clientes.
- Cadastro, consulta, atualização e exclusão de vendas.
- Cadastro, consulta, atualização e exclusão de pagamentos.
- Registro, consulta e devolução de vendas de produtos.

## Tecnologias Utilizadas

- Java
- Spring Framework
- Spring Data JPA
- PostgreSQL

## Configuração do Ambiente de Desenvolvimento

1. Clone este repositório.
2. Abra o projeto na sua IDE preferida.
3. Configure as credenciais do banco de dados no arquivo `application.properties`.
4. Execute a aplicação.

Para mais detalhes sobre os endpoints da API e como utilizá-los, consulte a seção de [Endpoints da API](#endpoints-da-api).


## Autor

João Vitor Nascimento Ramos

## Endpoints da API

### Clientes

- **Cadastro de Cliente**
  - URL: `/clientes/`
  - Método: `POST`
  - Descrição: Cria um novo cliente.
  - Body: JSON contendo os dados do cliente a ser cadastrado.
  - Exemplo de Body:
    ```json
    {
      "nome": "Nome do Cliente",
      "cpf": "123.456.789-00"
    }
    ```
  - Retorno: Retorna os dados do cliente cadastrado.
  - Códigos de Retorno:
    - 201 (Created): Cliente criado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consulta de Todos os Clientes**
  - URL: `/clientes/`
  - Método: `GET`
  - Descrição: Retorna todos os clientes cadastrados.
  - Retorno: Lista de todos os clientes cadastrados.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhum cliente encontrado.

- **Consulta de Cliente por ID**
  - URL: `/clientes/{id}`
  - Método: `GET`
  - Descrição: Retorna os dados de um cliente específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do cliente a ser consultado.
  - Retorno: Dados do cliente especificado.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Cliente não encontrado.

- **Atualização de Cliente**
  - URL: `/clientes/{id}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de um cliente específico pelo ID.
  - Parâmetros:
    - `{id}`: ID do cliente a ser atualizado.
  - Body: JSON contendo os dados atualizados do cliente.
  - Exemplo de Body:
    ```json
    {
      "nome": "Novo Nome do Cliente",
      "cpf": "987.654.321-00"
    }
    ```
  - Retorno: Retorna os dados atualizados do cliente.
  - Códigos de Retorno:
    - 200 (OK): Cliente atualizado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Cliente não encontrado.

- **Exclusão de Cliente**
  - URL: `/clientes/{id}`
  - Método: `DELETE`
  - Descrição: Exclui um cliente específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do cliente a ser excluído.
  - Retorno: Retorna os dados do cliente excluído.
  - Códigos de Retorno:
    - 200 (OK): Cliente excluído com sucesso.
    - 404 (Not Found): Cliente não encontrado.


### Marcas

- **Cadastro de Marca**
  - URL: `/produtos/marcas/`
  - Método: `POST`
  - Descrição: Cria uma nova marca de produto.
  - Body: JSON contendo os dados da marca a ser cadastrada.
  - Exemplo de Body:
    ```json
    {
      "nome": "Nome da Marca"
    }
    ```
  - Retorno: Retorna os dados da marca cadastrada.
  - Códigos de Retorno:
    - 201 (Created): Marca criada com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consulta de Todas as Marcas**
  - URL: `/produtos/marcas/`
  - Método: `GET`
  - Descrição: Retorna todas as marcas cadastradas.
  - Retorno: Lista de todas as marcas cadastradas.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhuma marca encontrada.

- **Consulta de Marca por ID**
  - URL: `/produtos/marcas/{id}`
  - Método: `GET`
  - Descrição: Retorna os dados de uma marca específica pelo ID.
  - Parâmetro:
    - `{id}`: ID da marca a ser consultada.
  - Retorno: Dados da marca especificada.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Marca não encontrada.

- **Atualização de Marca**
  - URL: `/produtos/marcas/{id}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de uma marca específica pelo ID.
  - Parâmetros:
    - `{id}`: ID da marca a ser atualizada.
  - Body: JSON contendo os dados atualizados da marca.
  - Exemplo de Body:
    ```json
    {
      "nome": "Novo Nome da Marca"
    }
    ```
  - Retorno: Retorna os dados atualizados da marca.
  - Códigos de Retorno:
    - 200 (OK): Marca atualizada com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Marca não encontrada.

- **Exclusão de Marca**
  - URL: `/produtos/marcas/{id}`
  - Método: `DELETE`
  - Descrição: Exclui uma marca específica pelo ID.
  - Parâmetro:
    - `{id}`: ID da marca a ser excluída.
  - Retorno: Retorna os dados da marca excluída.
  - Códigos de Retorno:
    - 200 (OK): Marca excluída com sucesso.
    - 404 (Not Found): Marca não encontrada.
    

### Tipos de Pagamento

- **Cadastro de Tipo de Pagamento**
  - URL: `/pagamentos/tipos/`
  - Método: `POST`
  - Descrição: Cria um novo tipo de pagamento.
  - Body: JSON contendo os dados do tipo de pagamento a ser cadastrado.
  - Exemplo de Body:
    ```json
    {
      "nome": "Nome do Tipo de Pagamento"
    }
    ```
  - Retorno: Retorna os dados do tipo de pagamento cadastrado.
  - Códigos de Retorno:
    - 201 (Created): Tipo de pagamento criado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consulta de Todos os Tipos de Pagamento**
  - URL: `/pagamentos/tipos/`
  - Método: `GET`
  - Descrição: Retorna todos os tipos de pagamento cadastrados.
  - Retorno: Lista de todos os tipos de pagamento cadastrados.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhum tipo de pagamento encontrado.

- **Consulta de Tipo de Pagamento por ID**
  - URL: `/pagamentos/tipos/{id}`
  - Método: `GET`
  - Descrição: Retorna os dados de um tipo de pagamento específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do tipo de pagamento a ser consultado.
  - Retorno: Dados do tipo de pagamento especificado.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Tipo de pagamento não encontrado.

- **Atualização de Tipo de Pagamento**
  - URL: `/pagamentos/tipos/{id}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de um tipo de pagamento específico pelo ID.
  - Parâmetros:
    - `{id}`: ID do tipo de pagamento a ser atualizado.
  - Body: JSON contendo os dados atualizados do tipo de pagamento.
  - Exemplo de Body:
    ```json
    {
      "nome": "Novo Nome do Tipo de Pagamento"
    }
    ```
  - Retorno: Retorna os dados atualizados do tipo de pagamento.
  - Códigos de Retorno:
    - 200 (OK): Tipo de pagamento atualizado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Tipo de pagamento não encontrado.

- **Exclusão de Tipo de Pagamento**
  - URL: `/pagamentos/tipos/{id}`
  - Método: `DELETE`
  - Descrição: Exclui um tipo de pagamento específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do tipo de pagamento a ser excluído.
  - Retorno: Retorna os dados do tipo de pagamento excluído.
  - Códigos de Retorno:
    - 200 (OK): Tipo de pagamento excluído com sucesso.
    - 404 (Not Found): Tipo de pagamento não encontrado.


### Pagamentos

- **Registro de Pagamento**
  - URL: `/pagamentos/`
  - Método: `POST`
  - Descrição: Registra um novo pagamento para uma venda.
  - Body: JSON contendo os dados do pagamento a ser registrado.
  - Exemplo de Body:
    ```json
    {
      "idTipoPagamento": 1,
      "idVenda": 1
    }
    ```
  - Retorno: Retorna os dados do pagamento registrado.
  - Códigos de Retorno:
    - 201 (Created): Pagamento registrado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consulta de Todos os Pagamentos**
  - URL: `/pagamentos/`
  - Método: `GET`
  - Descrição: Retorna todos os pagamentos registrados.
  - Retorno: Lista de todos os pagamentos registrados.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhum pagamento encontrado.

- **Consulta de Pagamento por ID**
  - URL: `/pagamentos/{id}`
  - Método: `GET`
  - Descrição: Retorna os dados de um pagamento específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do pagamento a ser consultado.
  - Retorno: Dados do pagamento especificado.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Pagamento não encontrado.

- **Atualização de Pagamento**
  - URL: `/pagamentos/{id}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de um pagamento específico pelo ID.
  - Parâmetros:
    - `{id}`: ID do pagamento a ser atualizado.
  - Body: JSON contendo os dados atualizados do pagamento.
  - Exemplo de Body:
    ```json
    {
      "idTipoPagamento": 2,
      "idVenda": 2
    }
    ```
  - Retorno: Retorna os dados atualizados do pagamento.
  - Códigos de Retorno:
    - 200 (OK): Pagamento atualizado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Pagamento não encontrado.

- **Exclusão de Pagamento**
  - URL: `/pagamentos/{id}`
  - Método: `DELETE`
  - Descrição: Exclui um pagamento específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do pagamento a ser excluído.
  - Retorno: Retorna os dados do pagamento excluído.
  - Códigos de Retorno:
    - 200 (OK): Pagamento excluído com sucesso.
    - 404 (Not Found): Pagamento não encontrado.


### Tipos de Produto

- **Cadastro de Tipo de Produto**
  - URL: `/produtos/tipos/`
  - Método: `POST`
  - Descrição: Cria um novo tipo de produto.
  - Body: JSON contendo os dados do tipo de produto a ser cadastrado.
  - Exemplo de Body:
    ```json
    {
      "nome": "Nome do Tipo de Produto"
    }
    ```
  - Retorno: Retorna os dados do tipo de produto cadastrado.
  - Códigos de Retorno:
    - 201 (Created): Tipo de produto criado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consulta de Todos os Tipos de Produto**
  - URL: `/produtos/tipos/`
  - Método: `GET`
  - Descrição: Retorna todos os tipos de produto cadastrados.
  - Retorno: Lista de todos os tipos de produto cadastrados.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhum tipo de produto encontrado.

- **Consulta de Tipo de Produto por ID**
  - URL: `/produtos/tipos/{id}`
  - Método: `GET`
  - Descrição: Retorna os dados de um tipo de produto específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do tipo de produto a ser consultado.
  - Retorno: Dados do tipo de produto especificado.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Tipo de produto não encontrado.

- **Atualização de Tipo de Produto**
  - URL: `/produtos/tipos/{id}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de um tipo de produto específico pelo ID.
  - Parâmetros:
    - `{id}`: ID do tipo de produto a ser atualizado.
  - Body: JSON contendo os dados atualizados do tipo de produto.
  - Exemplo de Body:
    ```json
    {
      "nome": "Novo Nome do Tipo de Produto"
    }
    ```
  - Retorno: Retorna os dados atualizados do tipo de produto.
  - Códigos de Retorno:
    - 200 (OK): Tipo de produto atualizado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Tipo de produto não encontrado.

- **Exclusão de Tipo de Produto**
  - URL: `/produtos/tipos/{id}`
  - Método: `DELETE`
  - Descrição: Exclui um tipo de produto específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do tipo de produto a ser excluído.
  - Retorno: Retorna os dados do tipo de produto excluído.
  - Códigos de Retorno:
    - 200 (OK): Tipo de produto excluído com sucesso.
    - 404 (Not Found): Tipo de produto não encontrado.

### Produtos

- **Cadastro de Produto**
  - URL: `/produtos/`
  - Método: `POST`
  - Descrição: Cria um novo produto.
  - Body: JSON contendo os dados do produto a ser cadastrado.
  - Exemplo de Body:
    ```json
    {
      "nome": "Nome do Produto",
      "preco": 10.5,
      "descricao": "Descrição do Produto",
      "idTipoProduto": 1,
      "idMarca": 1
    }
    ```
  - Retorno: Retorna os dados do produto cadastrado.
  - Códigos de Retorno:
    - 201 (Created): Produto criado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consulta de Todos os Produtos**
  - URL: `/produtos/`
  - Método: `GET`
  - Descrição: Retorna todos os produtos cadastrados.
  - Retorno: Lista de todos os produtos cadastrados.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhum produto encontrado.

- **Consulta de Produto por ID**
  - URL: `/produtos/{id}`
  - Método: `GET`
  - Descrição: Retorna os dados de um produto específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do produto a ser consultado.
  - Retorno: Dados do produto especificado.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Produto não encontrado.

- **Atualização de Produto**
  - URL: `/produtos/{id}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de um produto específico pelo ID.
  - Parâmetros:
    - `{id}`: ID do produto a ser atualizado.
  - Body: JSON contendo os dados atualizados do produto.
  - Exemplo de Body:
    ```json
    {
      "nome": "Novo Nome do Produto",
      "preco": 15.75,
      "descricao": "Nova Descrição do Produto",
      "idTipoProduto": 2,
      "idMarca": 2
    }
    ```
  - Retorno: Retorna os dados atualizados do produto.
  - Códigos de Retorno:
    - 200 (OK): Produto atualizado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Produto não encontrado.

- **Exclusão de Produto**
  - URL: `/produtos/{id}`
  - Método: `DELETE`
  - Descrição: Exclui um produto específico pelo ID.
  - Parâmetro:
    - `{id}`: ID do produto a ser excluído.
  - Retorno: Retorna os dados do produto excluído.
  - Códigos de Retorno:
    - 200 (OK): Produto excluído com sucesso.
    - 404 (Not Found): Produto não encontrado.

### Venda de Produtos

- **Adicionar Produto à Venda**
  - URL: `/vendas/{id}/produtos/`
  - Método: `POST`
  - Descrição: Adiciona um produto à venda especificada pelo ID.
  - Parâmetros:
    - `{id}`: ID da venda à qual o produto será adicionado.
  - Body: JSON contendo os dados do produto a ser adicionado.
  - Exemplo de Body:
    ```json
    {
      "idProduto": 1,
      "quantidade": 2
    }
    ```
  - Retorno: Retorna os dados do produto adicionado à venda.
  - Códigos de Retorno:
    - 201 (Created): Produto adicionado à venda com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.

- **Consultar Todos os Produtos de uma Venda**
  - URL: `/vendas/{id}/produtos/`
  - Método: `GET`
  - Descrição: Retorna todos os produtos associados a uma venda específica pelo ID.
  - Parâmetros:
    - `{id}`: ID da venda cujos produtos serão consultados.
  - Retorno: Lista de todos os produtos associados à venda.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Nenhum produto encontrado para a venda especificada.

- **Consultar Produto de uma Venda por ID**
  - URL: `/vendas/{id}/produtos/{produtoId}`
  - Método: `GET`
  - Descrição: Retorna os dados de um produto específico associado a uma venda pelo ID da venda e do produto.
  - Parâmetros:
    - `{id}`: ID da venda.
    - `{produtoId}`: ID do produto associado à venda.
  - Retorno: Dados do produto associado à venda.
  - Códigos de Retorno:
    - 200 (OK): Requisição bem sucedida.
    - 404 (Not Found): Produto não encontrado.

- **Atualizar Produto de uma Venda**
  - URL: `/vendas/{id}/produtos/{produtoId}`
  - Método: `PUT`
  - Descrição: Atualiza os dados de um produto associado a uma venda pelo ID da venda e do produto.
  - Parâmetros:
    - `{id}`: ID da venda.
    - `{produtoId}`: ID do produto associado à venda.
  - Body: JSON contendo os novos dados do produto.
  - Exemplo de Body:
    ```json
    {
      "idProduto": 2,
      "quantidade": 3
    }
    ```
  - Retorno: Retorna os dados atualizados do produto associado à venda.
  - Códigos de Retorno:
    - 200 (OK): Produto atualizado com sucesso.
    - 400 (Bad Request): Erro ao processar a requisição.
    - 404 (Not Found): Produto não encontrado.

- **Remover Produto de uma Venda**
  - URL: `/vendas/{id}/produtos/{produtoId}`
  - Método: `DELETE`
  - Descrição: Remove um produto específico associado a uma venda pelo ID da venda e do produto.
  - Parâmetros:
    - `{id}`: ID da venda.
    - `{produtoId}`: ID do produto associado à venda.
  - Retorno: Retorna os dados do produto removido da venda.
  - Códigos de Retorno:
    - 200 (OK): Produto removido com sucesso.
    - 404 (Not Found): Produto não encontrado.



