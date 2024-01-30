# Sistema de Controle para Empresa de Transporte Viário

Este projeto visa desenvolver e implementar os casos de uso para uma Empresa de Transporte Viário, comumente referida como Empresa de Ônibus. O sistema tem como objetivo manter o controle de dados sobre veículos (ônibus), motoristas, cobradores, passageiros com cartão, e pontos de parada. Além disso, o sistema gerencia trajetos, trechos, jornadas e registra informações relevantes durante o percurso.

## Casos de Uso

### Cadastros
**Cadastro de Veículos**: O sistema permite o registro de informações dos ônibus utilizados pela empresa.

**Cadastro de Motoristas**: Permite a inclusão e atualização de dados dos motoristas responsáveis pelos veículos.

**Cadastro de Cobradores**: (Opcional) Permite a inclusão e atualização de dados dos cobradores, considerando que estão em extinção.

**Cadastro de Passageiros com Cartão**: Registra informações sobre passageiros que utilizam cartões, como cartão estudantil, de idoso ou de transporte.

**Cadastro de Pontos de Parada**: Permite a inclusão e atualização de informações sobre os locais utilizados pelos ônibus para embarques e desembarques.

### Trajetos e Trechos
**Cadastro de Trajetos**: O sistema possibilita o registro de trajetos, especificando os trechos que o compõem e os pontos de parada associados.

**Registro de Jornadas**: Seleciona trajetos, associa motorista (e cobrador, se aplicável), além de um veículo para a execução da jornada.

**Registro de Início de Trajeto**: Com base na jornada, registra a data e a hora do início do trajeto, considerando que os demais dados estão previamente registrados na jornada.

### Checkpoints e Passageiros
**Registro de Checkpoints**: Identifica e registra a hora em que um veículo chega a um ponto de parada especial (Checkpoint).

**Registro de Passageiro Embarcado com Cartão**: Registra o ponto de embarque e os dados do cartão quando um passageiro utiliza algum tipo de cartão.

## Recomendações
Recomenda-se fortemente criar os casos de uso à medida que desenvolve o diagrama de classes. Além disso, a implementação de cada caso de uso deve ser realizada à medida em que são desenvolvidos. Certifique-se de seguir as boas práticas de programação e design orientado a objetos durante o desenvolvimento do sistema.
