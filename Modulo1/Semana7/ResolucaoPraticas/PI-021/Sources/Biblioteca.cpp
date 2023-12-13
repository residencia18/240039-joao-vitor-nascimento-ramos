#include "../Headers/Biblioteca.hpp"

vector<Livro> *Biblioteca::getLivros()
{
    return &(this->listaLivros);
}

vector<Usuario> *Biblioteca::getUsuarios()
{
    return &(this->listaUsuario);
}

void Biblioteca::mostraMenuPrincipal()
{

    cout << "\t======MENU PRINCIPAL======";
    cout << "\n\t[1] - GERENCIA USUARIO:";
    cout << "\n\t[2] - GERENCIA LIVROS:";
    cout << "\n\t[3] - GERENCIA ALUGUEIS:";
    cout << "\n\t[0] - SAIR";
    cout << "\n\tENTRADA ->  ";
}

void Biblioteca::mostraMenuSecundario()
{

    cout << "\t===========MENU===========";
    cout << "\n\t[1] - INSERIR:";
    cout << "\n\t[2] - ENCONTRAR:";
    cout << "\n\t[3] - EXCLUIR:";
    cout << "\n\t[4] - LISTAR:";
    cout << "\n\t[5] - EDITAR:";
    cout << "\n\t[0] - SAIR";
    cout << "\n\tENTRADA ->  ";
}

void Biblioteca::menuPrincipal(vector<Usuario> usuarios)
{

    int escolha;
    Biblioteca::mostraMenuPrincipal();
    cin >> escolha;
    cin.get();

    do
    {
        switch (escolha)
        {

        case 1:

            // insere usuário

            break;

        case 2:

            // encontra usuário

            break;

        case 3:

            // exclui usuarios

            break;

        case 4:

            // lista usuários

            break;

        case 5:

            // editar usuarios

            break;

        default:
            break;
        }
    } while (escolha != 0);
}

void Biblioteca::menuUsuarios()
{
    int escolha;
    Biblioteca::mostraMenuSecundario();
}

void Biblioteca::menuLivros()
{
    int escolha;
    Biblioteca::mostraMenuSecundario();
}

void Biblioteca::menuAlugueis()
{
    int escolha;
    Biblioteca::mostraMenuSecundario();
}

void Biblioteca::insereUsuario(vector<Usuario> &usuarios)
{

    string nome, cpf, endereco, telefone;

    cout << "\n\tInforme o nome: ";
    getline(cin, nome);

    cout << "\n\tInforme o cpf:";
    getline(cin, cpf);

    cout << "\n\tInforme o endereço: ";
    getline(cin, endereco);

    cout << "\n\tInforme o telefone: ";
    getline(cin, telefone);

    Usuario usuario(nome, cpf, endereco, telefone);

    usuarios.push_back(usuario);
    
}

void Biblioteca::encontraUsuario(vector<Usuario> &usuarios){

    int id;
    cout << "\n\tInforme o número identificador do usuario: ";
    cin >> id;

    for(auto it = usuarios.begin(); it != usuarios.end(); it++){

        if(id == it->getId()){
            it->mostrarUsuario();
            return;
        }
    }

    cout << "\n\tUsuario não encontrado na base de dados!";
}

void Biblioteca::listarUsuarios(vector<Usuario> &usuarios){

    for(auto it = usuarios.begin(); it != usuarios.end(); it++){
        it->mostrarUsuario();
    }

    cout << "\n\t============FIM DA LISTA============";
}

void Biblioteca::excluirUsuario(vector<Usuario> &usuarios){

    int id;
    cout << "\n\tInforme o identificador do usuario: ";
    cin >> id;

    for(auto it = usuarios.begin(); it != usuarios.end(); it++){
        if(id == it->getId()){
            usuarios.erase(it);
            return;
        }
    }
    cout << "\n\tUsuario não encontrado!";
}
