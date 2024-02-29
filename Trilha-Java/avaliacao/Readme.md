# Leilão Secreto Online

Um leilão é um processo de alienação (venda) de algum bem no qual os interessados concorrem entre si e quem oferecer a maior oferta financeira pelo referido bem ganha e paga o valor que ofereceu. O Leilão Secreto é uma modalidade em que cada concorrente faz seu lance sem o conhecimento dos lances dos demais.

## Modelo do Sistema

Vamos implementar quatro endpoints com ações diversas em cada um. Todos os dados serão transferidos em arquivos no formato JSON.

## Banco de Dados 

O Banco de Dados utilizado está localizado na nuvem do clevercloud, com os seus parametros localizados no /leilao/src/main/resources/application.properties

### Observação

O leilao estar aberto ou fechado está regido pelo atributo estado da entidade Leilao

### Endpoint /leilao/

#### Verbo GET

- Com um Id (/leilao/{id}): devolve um JSON com o DTO do leilão indicado pelo Id. O DTO contém todos os dados do leilão. Caso o Id seja inválido, devolve o status 404 (Not Found).
- Sem um Id (/leilao/): devolve um JSON com os DTOs de todos os leilões cadastrados. Caso a operação seja bem sucedida, retorna o status 200 (Ok).

#### Verbo POST

Segue um DTO (Form) com os dados de um Leilão (mas sem o ID) dentro do corpo da requisição. O sistema deverá incluir o referido leilão e retornar o status 201 (Created). Caso algum problema ocorra, deve retornar o status 400 (Bad Request).

#### Verbo PUT

- Com um Id (/leilao/{id}): altera o dado de um leilão indicado pelo Id e devolve o DTO com o dado atualizado. Segue um DTO (Form) com os dados de um Leilão (mas sem o ID) dentro do corpo da requisição. Caso Id seja inválido, ou não seja passado, devolve o status 404 (Not Found). Caso a operação seja bem sucedida, retorna o status 200 (Ok) e o DTO com o dado do leilão atualizado.

#### Verbo DELETE

Com um Id (/leilao/{id}): exclui um leilão indicado pelo Id e devolve o DTO com o dado atualizado. Caso o Id seja inválido, ou não seja passado, devolve o status 404 (Not Found). Caso a operação seja bem sucedida, retorna o status 200 (Ok).

### Endpoint /concorrente/

#### Verbo GET

- Com um Id (/concorrente/{id}): devolve um JSON com o DTO do concorrente indicado pelo Id. O DTO consta apenas o nome do concorrente. Caso o Id seja inválido, devolve o status 404 (Not Found).
- Sem um Id (/concorrente/): devolve um JSON com os DTOs de todos os concorrentes cadastrados. Caso a operação seja bem sucedida, retorna o status 200 (Ok).

#### Verbo POST

Segue um DTO (Form) com os dados de um Concorrente (mas sem o ID) dentro do corpo da requisição. O sistema deverá incluir o referido Concorrente e retornar o status 201 (Created). Caso algum problema ocorra, deve retornar o status 400 (Bad Request).

#### Verbo PUT

- Com um Id (/concorrente/{id}): altera o dado de um Concorrente indicado pelo Id e devolve o DTO com o dado atualizado. Segue um DTO (Form) com os dados de um Concorrente (mas sem o ID) dentro do corpo da requisição. Caso Id seja inválido, ou não seja passado, devolve o status 404 (Not Found). Caso a operação seja bem sucedida, retorna o status 200 (Ok) e o DTO com o dado do Concorrente atualizados.

#### Verbo DELETE

Com um Id (/concorrente/{id}): exclui um Concorrente indicado pelo Id e devolve o DTO com o dado atualizado. Caso o Id seja inválido, ou não seja passado, devolve o status 404 (Not Found). Caso a operação seja bem sucedida, retorna o status 200 (Ok).

### Endpoint /lance/

#### Verbo GET

- Com um Id (/lance/{id}): devolve um JSON com o DTO do lance indicado pelo Id. O DTO consta o Id do Leilao, o Id do Concorrente e o valor do lance. Caso o Id (parâmetro) seja inválido, devolve o status 404 (Not Found).
- Sem um Id (/lance/): devolve um JSON com os DTOs de todos os lances cadastrados. Com parâmetros /lance/leilao={Id} - devolve DTOs com todos os lances do leilão indicado por ID (Status 200: OK) ou status 404 (Not Found). /lance/concorrente={Id} - devolve DTOs com todos os lances do concorrente indicado por ID (Status 200: OK) ou status 404 (Not Found). Caso a operação seja bem sucedida, retorna o status 200 (Ok).

#### Verbo POST

Segue um DTO (Form) com os dados de um Lance (Id do Leilão, Id do Concorrente e valor) (mas sem o ID) dentro do corpo da requisição. O sistema deverá incluir o referido Lance e retornar o status 201 (Created). Caso o Id do Concorrente não exista, retorna status 403 (Forbidden). Caso o Id do Leilão não exista, retorna o status 400 (Bad Request). Caso o Id do Leilão refira a um leilão que está com status fechado, retorna o status 404 (Forbidden).

#### Verbo PUT

- Com um Id (/lance/{id}): altera o dado de um Lance indicado pelo Id e devolve o DTO com o dado atualizado. Segue um DTO (Form) com os dados de um Lance (mas sem o ID) dentro do corpo da requisição. Caso Id seja inválido, ou não seja passado, devolve o status 404 (Not Found). Caso o Id do Concorrente não exista, retorna status 403 (Forbidden). Caso o Id do Leilão não exista, retorna o status 400 (Bad Request). Caso o Id do Leilão refira a um leilão que está com status fechado, retorna o status 404 (Forbidden). Caso a operação seja bem sucedida, retorna o status 200 (Ok) e o DTO com o dado do lance atualizados.

#### Verbo DELETE

Com um Id (/lance/{id}): exclui um Concorrente indicado pelo Id e devolve o DTO com o dado atualizado. Caso o Id seja inválido, ou não seja passado, devolve o status 404 (Not Found). Caso o id refira a um lance cujo leilão está com status "Fechado", retorna o status 403 (Forbidden). Caso a operação seja bem sucedida, retorna o status 200 (Ok).

### Endpoint /vencedor_leilao/

Apenas o verbo GET deve ser implementado.

Com um Id (/leilao/{id}): devolve um JSON que inclui os dados do leilão indicado pelo Id, mais o valor do maior lance que aquele leilão recebeu, mais o DTO do Concorrente que fez o referido lance. Caso o Id seja inválido, devolve o status 404 (Not Found). Caso o Id não seja passado, devolve o status 400 (Bad Request). Caso o id refira a um lance cujo leilão está com status "Fechado", reorna o status 403 (Forbidden).
