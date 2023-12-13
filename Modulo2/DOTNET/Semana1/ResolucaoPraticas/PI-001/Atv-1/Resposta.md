# Como verificar qual a versão do dotnet está instalada no seu sistema

### Ao dar o comando 'dotnet' no terminal, se estiver instalado será possivel visualizar uma lista de opções. Ao acrescentar o comando para 'dotnet --info' aparecerá uma serie de informações relativas ao software, incluindo sua versão, caso queria apenas a versão, basta 'dotnet --version'

### O comando 'dotnet --list-sdks', além de listar a versão, também irá mostrar em que local da arvore de diretorios está instalado o software. O comando 'dotnet --list-runtimes' irá acrescentar mais informações relativas a versão e a instalação.

# Para realiza a remoção

### A remoção pode ser realizada com o auxilio do gerenciador de pacotes, no caso do linux ubuntu seria a apt. Um exemplo seria o sudo apt remove dotnet-runtime-31.

# Para instalação

### Para realizar a instalação, basta buscar quais os tipos de versões atraves do snap do apt ou do search, e após isso utilizar o sudo apt install <nome-do-pacote>

