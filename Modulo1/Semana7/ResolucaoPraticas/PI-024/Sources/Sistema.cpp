#include "../Headers/Sistema.hpp"
#include<fstream>

/**GET LISTAS */

vector<Cliente>* Sistema::getListaClientes() { 
    return &(this->clientes); 
}

vector<Pacote>* Sistema::getListaPacotes(){
    return &(this->pacotes);
}

vector<Evento>* Sistema::getListaEventos(){
    return &(this->eventos);
}

vector<Gerencia>* Sistema::getListaGerencia(){
    return &(this->gerencias);
}

/**MENU PRINCIPAL**/

void Sistema::menuPrincipal(vector<Cliente> &clientes,vector<Pacote> &pacotes,vector<Evento> &eventos, vector<Gerencia> &gerencias){
    int escolha;
    do{
        Sistema::mostraMenuPrincipal();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            menuCliente(clientes);
            break;
        case 2: 
            menuEventos(eventos);
            break;
        case 3:
            menuPacotes(pacotes,eventos); 
            break;
        case 4:
            menuGerencia(gerencias,clientes,pacotes);
            break;
        default:
            break;
        }
    }while(escolha!=0);

}

void Sistema::mostraMenuPrincipal(){
    system("clear || cls");
    cout << "----------MENU PRINCIPAL----------" << endl;
    cout << "[1] MENU CLIENTES" << endl;
    cout << "[2] MENU EVENTOS " << endl;
    cout << "[3] MENU PACOTES" << endl;
    cout << "[4] MENU GERENCIA " << endl;
    cout << "[0] SAIR" << endl;
}

/***CLIENTES***/

/**MENU**/

void Sistema::menuCliente(vector<Cliente> &clientes){
    int escolha;
    do{
        Sistema::mostraMenuCliente();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            insereCliente(clientes);
            break;
        case 2: 
            insereDependente(clientes);
            break;
        case 3:
            encontraCliente(clientes);
            break;
        case 4:
            encontraDependente(clientes);
            break;
        case 5:
            listaClientes(clientes);
            break;
        case 6:
            listaDependentesPorCliente(clientes);
            break;
        case 7:
            excluiClientes(clientes);
            break;
        case 8:
            excluiDependente(clientes);
            break;
        case 9:
            modificaCliente(clientes);
            break;
        case 10:
            modificaDependente(clientes);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuCliente(){
    system("clear || cls");
    cout << "----------MENU CLIENTE----------" << endl;
    cout << "[1] CADASTRA CLIENTE" << endl;
    cout << "[2] CADASTRA DEPENDENTES A UM CLIENTE" << endl;
    cout << "[3] MOSTRA DADOS DE UM CLIENTE" << endl;
    cout << "[4] MOSTRA DADOS DE UM DEPENDENTE " << endl;
    cout << "[5] LISTA CLIENTES " << endl;
    cout << "[6] LISTA DEPENDENTES DE UM CLIENTE " << endl;
    cout << "[7] EXCLUI CLIENTE " << endl;
    cout << "[8] EXCLUI DEPENDENTE DE UM CLIENTE " << endl;
    cout << "[9] MODIFICA CLIENTE" << endl;
    cout << "[10] MODIFICA DEPENDENTE DE UM CLIENTE " << endl;
    cout << "[0] SAIR" << endl;
}

/**FUNÇÕES MENU CLIENTE**/

void Sistema::insereCliente(vector<Cliente> &clientes){
    system("clear || cls");
    Cliente cliente = cadastraCliente();
    clientes.push_back(cliente);
    insereClienteNoBanco(cliente);
    cout << "--CLIENTE CADASTRADO COM SUCESSO " << endl;
    pause();
}

void Sistema::insereDependente(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    bool clienteEncontrado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado = true;
            Dependente dependente = cadastraDependente();
            it->getListaDependentes()->push_back(dependente);
            insereDependenteNoBanco(idCliente,dependente);
            cout << "----DEPENDENTE INSERIDO COM SUCESSO----" << endl;
            pause();
            return;
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
        pause();
    }
}

void Sistema::encontraCliente(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    bool clienteEncontrado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado = true;
            it->mostraDados();
            pause();
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
    }
}

void Sistema::encontraDependente(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    int idDependente = recebeIDDependente();
    bool clienteEncontrado = false;
    bool dependenteEncontrado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado=true;
            auto listaDependentes = it->getListaDependentes();
            for(auto itera=listaDependentes->begin() ; itera!=listaDependentes->end() ; itera++){
                if(itera->getId()  == idDependente){
                    dependenteEncontrado=true;
                    itera->mostraDados();
                    pause();
                    return;
                }
            }
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
    }else if(!dependenteEncontrado){
        cout << "Depenente não encontrado em nosso banco de dados!" << endl;
    }
    
}

void Sistema::listaClientes(vector<Cliente> &clientes){
    system("clear || cls");
    cout << "------LISTA DE CLIENTES-------" << endl;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        cout << "---------------------" << endl;
        it->mostraDados();
    }
    cout << "------FIM DA LISTA-------" << endl;
    pause();
}

void Sistema::listaDependentesPorCliente(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    bool clienteEncontrado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado=true;
            cout << "------LISTA DE DEPENDENTES-------" << endl;
            auto listaDepenentes = it->getListaDependentes();
            for(auto itera=listaDepenentes->begin() ; itera!=listaDepenentes->end() ; itera++){
                cout << "---------------------" << endl;
                itera->mostraDados();
            }
            cout << "------FIM DA LISTA-------" << endl;
            pause();
            break;
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
        pause();
    }
    
}

void Sistema::excluiClientes(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    bool clienteEncontrado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado = true;
            clientes.erase(it);
            cout << "----CLIENTE EXCLUIDO COM SUCESSO----" << endl;
            atualizaClientesBanco(clientes);
            pause();
            return;
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
    }
}

void Sistema::excluiDependente(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    int idDependente = recebeIDDependente();
    bool clienteEncontrado = false;
    bool dependenteEncontrado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado=true;
            auto listaDependentes = it->getListaDependentes();
            for(auto itera=listaDependentes->begin() ; itera!=listaDependentes->end() ; itera++){
                if(itera->getId()  == idDependente){
                    dependenteEncontrado=true;
                    listaDependentes->erase(itera);
                    cout << "----DEPENDENTE EXCLUIDO COM SUCESSO----" << endl;
                    atualizaDependentesNoBanco(clientes);
                    pause();
                    break;
                }
            }
            break;
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
        pause();
    }else if(!dependenteEncontrado){
        cout << "Depenente não encontrado em nosso banco de dados!" << endl;
        pause();
    }
    
}

void Sistema::modificaCliente(vector<Cliente> &clientes){
    system("clear || cls");
    int idCliente = recebeIDCliente();
    bool clienteEncontrado = false;
    bool clienteModificado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            clienteEncontrado = true;
            int escolha;
            do{
                system("clear || cls");
                cout << "----MENU MODIFICA CLIENTE----" << endl;
                cout << "[1] NOME " << endl;
                cout << "[2] CPF " << endl;
                cout << "[0] SAIR "<< endl;
                cout << "Escolha : " ;
                cin >> escolha;
                string nome,cpf;
                cin.get();
                switch (escolha)
                {
                case 1:
                    system("clear || cls");
                    cout << "Novo nome : " ;
                    getline(cin,nome);
                    it->setNome(nome);
                    clienteModificado = true;
                    break;
                case 2:
                    system("clear || cls");
                    cout << "Novo cpf : " ;
                    getline(cin,cpf);
                    it->setCpf(cpf);
                    clienteModificado = true;
                    break;
                default:
                    break;
                }
                 
            }while(escolha!=0);
            
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
        pause();
    }

    if(clienteModificado){
        atualizaClientesBanco(clientes);
    }
}

void Sistema::modificaDependente(vector<Cliente> &clientes){
   system("clear || cls");
    int idCliente = recebeIDCliente();
    int idDependente = recebeIDDependente();
    bool clienteEncontrado = false;
    bool dependenteEncontrado = false;
    bool dependenteModificado = false;
    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            auto listaDependentes = it->getListaDependentes();
            for(auto itera=listaDependentes->begin() ; itera!=listaDependentes->end() ; itera++){
                if(itera->getId()  == idDependente){
                    system("clear || cls");
                    int escolha;
                    do{
                        system("clear || cls");
                        cout << "----MENU MODIFICA DEPENDENTE----" << endl;
                        cout << "[1] NOME " << endl;
                        cout << "[2] CPF " << endl;
                        cout << "[0] SAIR "<< endl;
                        cout << "Escolha : " ;
                        cin >> escolha;
                        string nome,cpf;
                        cin.get();
                        switch (escolha)
                        {
                        case 1:
                            system("clear || cls");
                            cout << "Novo nome : " ;
                            getline(cin,nome);
                            itera->setNome(nome);
                            dependenteModificado = true;
                            break;
                        case 2:
                            system("clear || cls");
                            cout << "Novo cpf : " ;
                            getline(cin,cpf);
                            itera->setCpf(cpf);
                            dependenteModificado = true;
                            break;
                        default:
                            break;
                        }
                        
                    }while(escolha!=0);
                }
            }
            break;
        }
    }
    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
        pause();
    }else if(!dependenteEncontrado){
        cout << "Depenente não encontrado em nosso banco de dados!" << endl;
        pause();
    }

    if(dependenteModificado){
        atualizaDependentesNoBanco(clientes);
    }

}

/**FUNÇÕES AUXILIARES**/

Cliente Sistema::cadastraCliente(){
    string nome,cpf;

    cout << "Nome : ";
    getline(cin,nome);

    cout << "CPF : ";
    getline(cin,cpf);

    Cliente cliente(nome,cpf);

    return cliente;
}

Dependente Sistema::cadastraDependente(){
    string nome,cpf;

    cout << "Nome : ";
    getline(cin,nome);

    cout << "CPF : ";
    getline(cin,cpf);

    Dependente dependente(nome,cpf);

    return dependente;
}

int Sistema::recebeIDCliente(){
    int idCliente ;
    cout << "Insira o ID do cliente  :" ;
    cin >> idCliente;
    cin.get();
    return idCliente;
}

int Sistema::recebeIDDependente(){
    int idDependente ;
    cout << "Insira o ID do dependente :" ;
    cin >> idDependente;
    cin.get();
    return idDependente;
}

/**UTILS**/

void Sistema::pause(){
    cout << "Pressione <Enter> para continuar...";
    getchar();
}

/**PERSISTENCIA CLIENTE**/

void Sistema::insereClienteNoBanco(Cliente &cliente){
    ofstream inClientes;
    inClientes.open("../BancoDeDados/Clientes.txt",ios_base::app);
    if(inClientes.is_open()){
        inClientes << cliente.getId() << endl;
        inClientes << cliente.getNome() << endl;
        inClientes << cliente.getCpf() << endl; 
        inClientes.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Clientes.txt" << endl;
        exit(0);
    }
}

void Sistema::atualizaClientesBanco(vector<Cliente> &clientes){
    ofstream inClientes;
    inClientes.open("../BancoDeDados/Clientes.txt",ios_base::out);
    if(inClientes.is_open()){
        for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
            inClientes << it->getId() << endl;
            inClientes << it->getNome() << endl;
            inClientes << it->getCpf() << endl;
        }
        inClientes.close();
        
    }else{
        cout << "Não foi possivel abrir o arquivo Clientes.txt" << endl;
        exit(0);
    }
}

void Sistema::insereDependenteNoBanco(int &idCliente, Dependente &dependente){
    ofstream inDependentes;
    inDependentes.open("../BancoDeDados/Dependentes.txt",ios_base::app);
    if(inDependentes.is_open()){
        inDependentes << idCliente << endl;
        inDependentes << dependente.getId() << endl;
        inDependentes << dependente.getNome() << endl;
        inDependentes << dependente.getCpf() << endl; 
        inDependentes.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Dependentes.txt" << endl;
        exit(0);
    }
}

void Sistema::atualizaDependentesNoBanco(vector<Cliente> &clientes){
    ofstream inDependentes;
    inDependentes.open("../BancoDeDados/Dependentes.txt",ios_base::out);
    if(inDependentes.is_open()){
        for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
            for(auto itera=it->getListaDependentes()->begin() ; itera!=it->getListaDependentes()->end() ; itera++){
                inDependentes << it->getId() << endl;
                inDependentes << itera->getId() << endl;
                inDependentes << itera->getNome() << endl;
                inDependentes << itera->getCpf() << endl;
            }
        }
        inDependentes.close();
    }
}

void Sistema::recuperaClientes(vector<Cliente> &clientes){
    ifstream inClientes;

    inClientes.open("../BancoDeDados/Clientes.txt",ios_base::in);
    if(inClientes.is_open()){
        string id,nome,cpf;
        int idd;
        while (inClientes.eof() == false)
        {
            
            getline(inClientes,id);
            getline(inClientes,nome);
            getline(inClientes,cpf);

            if(!id.empty()){
                Cliente cliente(nome,cpf);
                idd = stoi(id);
                cliente.setId(idd);
                if(idd > Cliente::getNumeroDeIdentificadores()){
                    Cliente::setNumeroDeIdentificadores(idd);
                }
                clientes.push_back(cliente);
            }
        }
        inClientes.close();
    }
}

void Sistema::recuperaDependentes(vector<Cliente> &clientes){
    ifstream inDependentes;

    inDependentes.open("../BancoDeDados/Dependentes.txt",ios_base::out);
    if(inDependentes.is_open()){
        string idCliente,idDependente,nome,cpf;
        int iddCliente;
        int iddDependente;
        while (inDependentes.eof() == false)
        {
            getline(inDependentes,idCliente);
            getline(inDependentes,idDependente);
            getline(inDependentes,nome);
            getline(inDependentes,cpf);

            if(!idCliente.empty()){
                iddCliente = stoi(idCliente);
                for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
                    if(iddCliente == it->getId()){
                        iddDependente = stoi(idDependente);
                        Dependente dependente(nome,cpf);
                        dependente.setId(iddDependente);
                        if(iddDependente > Dependente::getNumeroDeIdentificadores()){
                            Dependente::setNumeroDeIdentificadores(iddDependente);
                        }
                        it->getListaDependentes()->push_back(dependente);
                    }
                }
                inDependentes.close();
            }
        }
    }
}

/**EVENTOS**/

/**MENU**/

void Sistema::menuEventos(vector<Evento> &eventos){
    int escolha;
    do{
        Sistema::mostraMenuEventos();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            menuRoteiro(eventos);
            break;
        case 2: 
            menuDeslocamento(eventos);
            break;
        case 3:
            menuPernoite(eventos);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuEventos(){
    system("clear || cls");
    cout << "----------MENU EVENTOS----------" << endl;
    cout << "[1] MENU ROTEIRO" << endl;
    cout << "[2] MENU DESLOCAMENTO" << endl;
    cout << "[3] MENU PERNOITE " << endl;
    cout << "[0] SAIR" << endl;
}

/**ROTEIRO**/

/*MENU*/

void Sistema::menuRoteiro(vector<Evento> &eventos){
   int escolha;
    do{
        Sistema::mostraMenuRoteiro();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            insereRoteiro(eventos);
            break;
        case 2: 
            encontraRoteiro(eventos);
            break;
        case 3:
            excluiRoteiro(eventos);
            break;
        case 4:
            listaRoteiro(eventos);
            break;
        case 5:
            modificaRoteiro(eventos);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuRoteiro(){
    system("clear || cls");
    cout << "----------MENU ROTEIRO----------" << endl;
    cout << "[1] INSERE " << endl;
    cout << "[2] ENCONTRA " << endl;
    cout << "[3] EXCLUI " << endl;
    cout << "[4] LISTA " << endl;
    cout << "[5] MODIFICA " << endl;
    cout << "[0] SAIR " << endl;
}

Roteiro Sistema::cadastraRoteiro(){
    system("clear || cls");
    string nome,descricao;
    double preco;
    int DuracaoHoras;

    cout << "Nome : ";
    getline(cin,nome);

    cout << "Descrição : ";
    getline(cin,descricao);

    cout << "Duração em horas : " ;
    cin >> DuracaoHoras;
    cin.get();

    cout << "Preço : ";
    cin >> preco;
    cin.get();



    Roteiro roteiro(nome,descricao,DuracaoHoras,preco);

    return roteiro;
}

/*FUNÇÕES MENU ROTEIRO*/

void Sistema::insereRoteiro(vector<Evento>& eventos) {
    Roteiro roteiro = cadastraRoteiro();
    eventos.push_back(roteiro);
    cout << "--ROTEIRO CADASTRADO COM SUCESSO " << endl;
    insereRoteiroNoBanco(roteiro);
    pause();
}

void Sistema::encontraRoteiro(vector<Evento> &eventos){
    system("clear || cls");
    int idRoteiro = recebeIDRoteiro();
    bool eventoEncontrado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 1){
            if(it->getId() == idRoteiro){
                eventoEncontrado = true;
                it->mostraEvento();
                pause();
                return;
            }
        }
    }
    if(!eventoEncontrado){
        cout << "Roteiro não encontrado em nosso banco de dados!" << endl;
    }
}

void Sistema::excluiRoteiro(vector<Evento> &eventos){
    system("clear || cls");
    int idRoteiro = recebeIDRoteiro();
    bool eventoEncontrado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 1){
            if(it->getId() == idRoteiro){
                eventoEncontrado = true;
                eventos.erase(it);
                cout << "----ROTEIRO EXCLUIDO COM SUCESSO----" << endl;
                pause();
                atualizaRoteirosBanco(eventos);
                return;
            }
        }
    }
    if(!eventoEncontrado){
        cout << "Roteiro não encontrado em nosso banco de dados!" << endl;
    }

}

void Sistema::listaRoteiro(vector<Evento> &eventos){
    system("clear || cls");
    cout << "------LISTA DE ROTEIROS-------" << endl;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 1){
            cout << "---------------------" << endl;
            it->mostraEvento();
        }
    }
    cout << "------FIM DA LISTA-------" << endl;
    pause();
}

void Sistema::modificaRoteiro(vector<Evento> &eventos){
    system("clear || cls");
    int idRoteiro = recebeIDRoteiro();
    bool eventoEncontrado = false;
    bool eventoModificado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 1){
            if(it->getId() == idRoteiro){
                eventoEncontrado = true;
                int escolha;
                do{
                    system("clear || cls");
                    cout << "----MENU MODIFICA ROTEIRO----" << endl;
                    cout << "[1] NOME " << endl;
                    cout << "[2] DESCRIÇÃO " << endl;
                    cout << "[3] DURAÇÃO EM HORAS " << endl;
                    cout << "[4] PREÇO " << endl;
                    cout << "[0] SAIR "<< endl;
                    cout << "Escolha : " ;
                    cin >> escolha;
                    string nome,descricao;
                    int duracaoEmHoras;
                    double preco;
                    cin.get();
                    switch (escolha)
                    {
                    case 1:
                        system("clear || cls");
                        cout << "Novo nome : " ;
                        getline(cin,nome);
                        it->setNome(nome);
                        eventoModificado = true;
                        break;
                    case 2:
                        system("clear || cls");
                        cout << "Descrição : " ;
                        getline(cin,descricao);
                        it->setDescricao(descricao);
                        eventoModificado = true;
                        break;
                    case 3:
                        system("clear || cls");
                        cout << "Duracao em Horas : " ;
                        cin >> duracaoEmHoras;
                        cin.get();
                        it->setDuracaoHoras(duracaoEmHoras);
                        eventoModificado = true;
                        break;
                    case 4:
                        system("clear || cls");
                        cout << "Preço : " ;
                        cin >> preco;
                        cin.get();
                        it->setPreco(preco);
                        eventoModificado = true;
                        break;
                    default:
                        break;
                    }
                    
                }while(escolha!=0);
            }
        }
    }
    if(!eventoEncontrado){
        cout << "Roteiro não encontrado em nosso banco de dados!" << endl;
        pause();
    }

    if(eventoModificado){
        atualizaRoteirosBanco(eventos);
    }

}

/**PERSISTENCIA**/

void Sistema::insereRoteiroNoBanco(Roteiro &roteiro){
    ofstream inRoteiro;
    inRoteiro.open("../BancoDeDados/Roteiros.txt",ios_base::app);
    if(inRoteiro.is_open()){
        inRoteiro << roteiro.getId() << endl;
        inRoteiro << roteiro.getTipo() << endl;
        inRoteiro << roteiro.getNome() << endl;
        inRoteiro << roteiro.getDescricao() << endl;
        inRoteiro << roteiro.getDuracaoHoras() << endl; 
        inRoteiro << roteiro.getPreco() << endl;
        inRoteiro.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Roteiros.txt" << endl;
        exit(0);
    }
}

void Sistema::atualizaRoteirosBanco(vector<Evento> &eventos){
    ofstream inRoteiro;
    inRoteiro.open("../BancoDeDados/Roteiros.txt",ios_base::out);
    if(inRoteiro.is_open()){
        for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
            if(it->getTipo() == 1){
                inRoteiro << it->getId() << endl;
                inRoteiro << it->getTipo() << endl;
                inRoteiro << it->getNome() << endl;
                inRoteiro << it->getDescricao() << endl;
                inRoteiro << it->getDuracaoHoras() << endl; 
                inRoteiro << it->getPreco() << endl;
            }
        }
        inRoteiro.close();
        
    }else{
        cout << "Não foi possivel abrir o arquivo Roteiros.txt" << endl;
        exit(0);
    }
}

void Sistema::recuperaRoteiros(vector<Evento> &eventos){
    ifstream inEventos;

    inEventos.open("../BancoDeDados/Roteiros.txt",ios_base::in);
    if(inEventos.is_open()){
        string id,tipo,nome,descricao,duracaoHoras,preco;
        int idd,duracaoHorasINT;
        double precoFLOAT;
        while (inEventos.eof() == false)
        {
            
            getline(inEventos,id);
            getline(inEventos,tipo);
            getline(inEventos,nome);
            getline(inEventos,descricao);
            getline(inEventos,duracaoHoras);
            getline(inEventos,preco);

            if(!id.empty()){
                idd = stoi(id);
                duracaoHorasINT = stoi(duracaoHoras);
                precoFLOAT = stof(preco);
                Roteiro roteiro(nome,descricao,duracaoHorasINT,precoFLOAT);
                roteiro.setId(idd);
                if(idd > Roteiro::getNumeroDeIdentificadores()){
                    Roteiro::setNumeroDeIdentificadores(idd);
                }
                eventos.push_back(roteiro);
            }
        }
        inEventos.close();
    }
}

/**FUNÇÕES AUXILIARES**/

int Sistema::recebeIDRoteiro(){
    int idEvento ;
    cout << "Insira o ID do roteiro  :" ;
    cin >> idEvento;
    cin.get();
    return idEvento;
}

/**DESLOCAMENTO**/

/*MENU*/

void Sistema::menuDeslocamento(vector<Evento> &eventos){
   int escolha;
    do{
        Sistema::mostraMenuDeslocamento();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            insereDeslocamento(eventos);
            break;
        case 2: 
            encontraDeslocamento(eventos);
            break;
        case 3:
            excluiDeslocamento(eventos);
            break;
        case 4:
            listaDeslocamento(eventos);
            break;
        case 5:
            modificaDeslocamento(eventos);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuDeslocamento(){
    system("clear || cls");
    cout << "----------MENU DESLOCAMENTO----------" << endl;
    cout << "[1] INSERE " << endl;
    cout << "[2] ENCONTRA " << endl;
    cout << "[3] EXCLUI " << endl;
    cout << "[4] LISTA " << endl;
    cout << "[5] MODIFICA " << endl;
    cout << "[0] SAIR " << endl;
}

/***FUNÇÕES AUXILIARES*/

Deslocamento Sistema::cadastraDeslocamento(){
    system("clear || cls");
    string nome,descricao;
    double preco;
    int DuracaoHoras;

    cout << "Nome : ";
    getline(cin,nome);

    cout << "Descrição : ";
    getline(cin,descricao);

    cout << "Duração em horas : " ;
    cin >> DuracaoHoras;
    cin.get();

    cout << "Preço : ";
    cin >> preco;
    cin.get();


    Deslocamento deslocamento(nome,descricao,DuracaoHoras,preco);

    return deslocamento;
}

int Sistema::recebeIDDeslocamento(){
    int idDependente ;
    cout << "Insira o ID do deslocamento :" ;
    cin >> idDependente;
    cin.get();
    return idDependente;
}

/**FUNÇÕES MENU DESLOCAMENTO*/

void Sistema::insereDeslocamento(vector<Evento>& eventos) {
    Deslocamento deslocamento = cadastraDeslocamento();
    eventos.push_back(deslocamento);
    cout << "--DESLOCAMENTO CADASTRADO COM SUCESSO " << endl;
    insereDeslocamentoNoBanco(deslocamento);
    pause();
}

void Sistema::encontraDeslocamento(vector<Evento> &eventos){
    system("clear || cls");
    int idDeslocamento = recebeIDDeslocamento();
    bool deslocamentoEncontrado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 2){
            if(it->getId() == idDeslocamento){
                deslocamentoEncontrado = true;
                it->mostraEvento();
                pause();
                return;
            }
        }
    }
    if(!deslocamentoEncontrado){
        cout << "Deslocamento não encontrado em nosso banco de dados!" << endl;
        pause();
    }
}

void Sistema::excluiDeslocamento(vector<Evento> &eventos){
    system("clear || cls");
    int idDeslocamento = recebeIDDeslocamento();
    bool deslocamentoEncontrado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 2){
            if(it->getId() == idDeslocamento){
                deslocamentoEncontrado = true;
                eventos.erase(it);
                cout << "----DESLOCAMENTO EXCLUIDO COM SUCESSO----" << endl;
                pause();
                atualizaDeslocamentosBanco(eventos);
                return;
            }
        }
    }
    if(!deslocamentoEncontrado){
        cout << "Cliente não encontrado em nosso banco de dados!" << endl;
        pause();
    }

}

void Sistema::listaDeslocamento(vector<Evento> &eventos){
    system("clear || cls");
    cout << "------LISTA DE DESLOCAMENTOS-------" << endl;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 2){
            cout << "---------------------" << endl;
            it->mostraEvento();
        }
    }
    cout << "------FIM DA LISTA-------" << endl;
    pause();
}

void Sistema::modificaDeslocamento(vector<Evento> &eventos){
    system("clear || cls");
    int idDeslocamento = recebeIDDeslocamento();
    bool eventoEncontrado = false;
    bool eventoModificado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 2){
            if(it->getId() == idDeslocamento){
                eventoEncontrado = true;
                int escolha;
                do{
                    system("clear || cls");
                    cout << "----MENU MODIFICA ROTEIRO----" << endl;
                    cout << "[1] NOME " << endl;
                    cout << "[2] DESCRIÇÃO " << endl;
                    cout << "[3] DURAÇÃO EM HORAS " << endl;
                    cout << "[4] PREÇO " << endl;
                    cout << "[0] SAIR "<< endl;
                    cout << "Escolha : " ;
                    cin >> escolha;
                    string nome,descricao;
                    int duracaoEmHoras;
                    double preco;
                    cin.get();
                    switch (escolha)
                    {
                    case 1:
                        system("clear || cls");
                        cout << "Novo nome : " ;
                        getline(cin,nome);
                        it->setNome(nome);
                        eventoModificado = true;
                        break;
                    case 2:
                        system("clear || cls");
                        cout << "Descrição : " ;
                        getline(cin,descricao);
                        it->setDescricao(descricao);
                        eventoModificado = true;
                        break;
                    case 3:
                        system("clear || cls");
                        cout << "Duracao em Horas : " ;
                        cin >> duracaoEmHoras;
                        cin.get();
                        it->setDuracaoHoras(duracaoEmHoras);
                        eventoModificado = true;
                        break;
                    case 4:
                        system("clear || cls");
                        cout << "Preço : " ;
                        cin >> preco;
                        cin.get();
                        it->setPreco(preco);
                        eventoModificado = true;
                        break;
                    default:
                        break;
                    }
                    
                }while(escolha!=0);
            }
        }
    }
    if(!eventoEncontrado){
        cout << "Deslocamento não encontrado em nosso banco de dados!" << endl;
        pause();
    }

    if(eventoModificado){
        atualizaDeslocamentosBanco(eventos);
    }

}

/**PERSISTENCIA**/

void Sistema::insereDeslocamentoNoBanco(Deslocamento &deslocamento){
    ofstream inDeslocamento;
    inDeslocamento.open("../BancoDeDados/Deslocamentos.txt",ios_base::app);
    if(inDeslocamento.is_open()){
        inDeslocamento << deslocamento.getId() << endl;
        inDeslocamento << deslocamento.getTipo() << endl;
        inDeslocamento << deslocamento.getNome() << endl;
        inDeslocamento << deslocamento.getDescricao() << endl;
        inDeslocamento << deslocamento.getDuracaoHoras() << endl; 
        inDeslocamento << deslocamento.getPreco() << endl;
        inDeslocamento.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Deslocamentos.txt" << endl;
        exit(0);
    }
}

void Sistema::atualizaDeslocamentosBanco(vector<Evento> &eventos){
    ofstream inDeslocamento;
    inDeslocamento.open("../BancoDeDados/Deslocamentos.txt",ios_base::out);
    if(inDeslocamento.is_open()){
        for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
            if(it->getTipo() == 2){
                inDeslocamento << it->getId() << endl;
                inDeslocamento << it->getTipo() << endl;
                inDeslocamento << it->getNome() << endl;
                inDeslocamento << it->getDescricao() << endl;
                inDeslocamento << it->getDuracaoHoras() << endl; 
                inDeslocamento << it->getPreco() << endl;
            }
        }
        inDeslocamento.close();
        
    }else{
        cout << "Não foi possivel abrir o arquivo Deslocamentos.txt" << endl;
        exit(0);
    }
}

void Sistema::recuperaDeslocamentos(vector<Evento> &eventos){
    ifstream inEventos;

    inEventos.open("../BancoDeDados/Deslocamentos.txt",ios_base::in);
    if(inEventos.is_open()){
        string id,tipo,nome,descricao,duracaoHoras,preco;
        int idd,duracaoHorasINT;
        double precoFLOAT;
        while (inEventos.eof() == false)
        {
            
            getline(inEventos,id);
            getline(inEventos,tipo);
            getline(inEventos,nome);
            getline(inEventos,descricao);
            getline(inEventos,duracaoHoras);
            getline(inEventos,preco);

            if(!id.empty()){
                idd = stoi(id);
                duracaoHorasINT = stoi(duracaoHoras);
                precoFLOAT = stof(preco);
                Deslocamento deslocamento(nome,descricao,duracaoHorasINT,precoFLOAT);
                deslocamento.setId(idd);
                if(idd > Deslocamento::getNumeroDeIdentificadores()){
                    Deslocamento::setNumeroDeIdentificadores(idd);
                }
                eventos.push_back(deslocamento);
            }
        }
        inEventos.close();
    }
}

/**PERNOITE**/

/*MENU*/

void Sistema::menuPernoite(vector<Evento> &eventos){
   int escolha;
    do{
        Sistema::mostraMenuPernoite();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            inserePernoite(eventos);
            break;
        case 2: 
            encontraPernoite(eventos);
            break;
        case 3:
            excluiPernoite(eventos);
            break;
        case 4:
            listaPernoite(eventos);
            break;
        case 5:
            modificaPernoite(eventos);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuPernoite(){
    system("clear || cls");
    cout << "----------MENU PERNOITE----------" << endl;
    cout << "[1] INSERE " << endl;
    cout << "[2] ENCONTRA " << endl;
    cout << "[3] EXCLUI " << endl;
    cout << "[4] LISTA " << endl;
    cout << "[5] MODIFICA " << endl;
    cout << "[0] SAIR " << endl;
}

/***FUNÇÕES AUXILIARES*/

Pernoite Sistema::cadastraPernoite(){
    system("clear || cls");
    string nome,descricao;
    double preco;
    int DuracaoHoras;

    cout << "Nome : ";
    getline(cin,nome);

    cout << "Descrição : ";
    getline(cin,descricao);

    cout << "Duração em horas : " ;
    cin >> DuracaoHoras;
    cin.get();

    cout << "Preço : ";
    cin >> preco;
    cin.get();


    Pernoite pernoite(nome,descricao,DuracaoHoras,preco);

    return pernoite;
}

int Sistema::recebeIDPernoite(){
    int idPernoite ;
    cout << "Insira o ID do pernoite :" ;
    cin >> idPernoite;
    cin.get();
    return idPernoite;
}


/**FUNÇÕES MENU PERNOITE*/

void Sistema::inserePernoite(vector<Evento>& eventos) {
    Pernoite pernoite = cadastraPernoite();
    eventos.push_back(pernoite);
    cout << "--PERNOITE CADASTRADO COM SUCESSO " << endl;
    inserePernoiteNoBanco(pernoite);
    pause();
}

void Sistema::encontraPernoite(vector<Evento> &eventos){
    system("clear || cls");
    int idPernoite = recebeIDPernoite();
    bool pernoiteEncontrado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 3){
            if(it->getId() == idPernoite){
                pernoiteEncontrado = true;
                it->mostraEvento();
                pause();
                return;
            }
        }
    }
    if(!pernoiteEncontrado){
        cout << "Pernoite não encontrado em nosso banco de dados!" << endl;
        pause();
    }
}

void Sistema::excluiPernoite(vector<Evento> &eventos){
    system("clear || cls");
    int idPernoite = recebeIDPernoite();
    bool pernoiteEncontrado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 3){
            if(it->getId() == idPernoite){
                pernoiteEncontrado = true;
                eventos.erase(it);
                cout << "----PERNOITE EXCLUIDO COM SUCESSO----" << endl;
                pause();
                atualizaPernoitesBanco(eventos);
                return;
            }
        }
    }
    if(!pernoiteEncontrado){
        cout << "Pernoite não encontrado em nosso banco de dados!" << endl;
        pause();
    }

}

void Sistema::listaPernoite(vector<Evento> &eventos){
    system("clear || cls");
    cout << "------LISTA DE PERNOITES-------" << endl;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 3){
            cout << "---------------------" << endl;
            it->mostraEvento();
        }
    }
    cout << "------FIM DA LISTA-------" << endl;
    pause();
}

void Sistema::modificaPernoite(vector<Evento> &eventos){
    system("clear || cls");
    int idPernoite = recebeIDPernoite();
    bool eventoEncontrado = false;
    bool eventoModificado = false;
    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 3){
            if(it->getId() == idPernoite){
                eventoEncontrado = true;
                int escolha;
                do{
                    system("clear || cls");
                    cout << "----MENU MODIFICA ROTEIRO----" << endl;
                    cout << "[1] NOME " << endl;
                    cout << "[2] DESCRIÇÃO " << endl;
                    cout << "[3] DURAÇÃO EM HORAS " << endl;
                    cout << "[4] PREÇO " << endl;
                    cout << "[0] SAIR "<< endl;
                    cout << "Escolha : " ;
                    cin >> escolha;
                    string nome,descricao;
                    int duracaoEmHoras;
                    double preco;
                    cin.get();
                    switch (escolha)
                    {
                    case 1:
                        system("clear || cls");
                        cout << "Novo nome : " ;
                        getline(cin,nome);
                        it->setNome(nome);
                        eventoModificado = true;
                        break;
                    case 2:
                        system("clear || cls");
                        cout << "Descrição : " ;
                        getline(cin,descricao);
                        it->setDescricao(descricao);
                        eventoModificado = true;
                        break;
                    case 3:
                        system("clear || cls");
                        cout << "Duracao em Horas : " ;
                        cin >> duracaoEmHoras;
                        cin.get();
                        it->setDuracaoHoras(duracaoEmHoras);
                        eventoModificado = true;
                        break;
                    case 4:
                        system("clear || cls");
                        cout << "Preço : " ;
                        cin >> preco;
                        cin.get();
                        it->setPreco(preco);
                        eventoModificado = true;
                        break;
                    default:
                        break;
                    }
                    
                }while(escolha!=0);
            }
        }
    }
    if(!eventoEncontrado){
        cout << "Pernoite não encontrado em nosso banco de dados!" << endl;
        pause();
    }

    if(eventoModificado){
        atualizaPernoitesBanco(eventos);
    }

}

/**PERSISTENCIA**/

void Sistema::inserePernoiteNoBanco(Pernoite &pernoite){
    ofstream inPernoite;
    inPernoite.open("../BancoDeDados/Pernoites.txt",ios_base::app);
    if(inPernoite.is_open()){
        inPernoite << pernoite.getId() << endl;
        inPernoite << pernoite.getTipo() << endl;
        inPernoite << pernoite.getNome() << endl;
        inPernoite << pernoite.getDescricao() << endl;
        inPernoite << pernoite.getDuracaoHoras() << endl; 
        inPernoite << pernoite.getPreco() << endl;
        inPernoite.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Pernoites.txt" << endl;
        exit(0);
    }
}

void Sistema::atualizaPernoitesBanco(vector<Evento> &eventos){
    ofstream inPernoite;
    inPernoite.open("../BancoDeDados/Pernoites.txt",ios_base::out);
    if(inPernoite.is_open()){
        for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
            if(it->getTipo() == 3){
                inPernoite << it->getId() << endl;
                inPernoite << it->getTipo() << endl;
                inPernoite << it->getNome() << endl;
                inPernoite << it->getDescricao() << endl;
                inPernoite << it->getDuracaoHoras() << endl; 
                inPernoite << it->getPreco() << endl;
            }
        }
        inPernoite.close();
        
    }else{
        cout << "Não foi possivel abrir o arquivo Pernoites.txt" << endl;
        exit(0);
    }
}

void Sistema::recuperaPernoites(vector<Evento> &eventos){
    ifstream inEventos;

    inEventos.open("../BancoDeDados/Pernoites.txt",ios_base::in);
    if(inEventos.is_open()){
        string id,tipo,nome,descricao,duracaoHoras,preco;
        int idd,duracaoHorasINT;
        double precoFLOAT;
        while (inEventos.eof() == false)
        {
            
            getline(inEventos,id);
            getline(inEventos,tipo);
            getline(inEventos,nome);
            getline(inEventos,descricao);
            getline(inEventos,duracaoHoras);
            getline(inEventos,preco);

            if(!id.empty()){
                idd = stoi(id);
                duracaoHorasINT = stoi(duracaoHoras);
                precoFLOAT = stof(preco);
                Pernoite pernoite(nome,descricao,duracaoHorasINT,precoFLOAT);
                pernoite.setId(idd);
                if(idd > Pernoite::getNumeroDeIdentificadores()){
                    Pernoite::setNumeroDeIdentificadores(idd);
                }
                eventos.push_back(pernoite);
            }
        }
        inEventos.close();
    }
}

/**PACOTES**/

void Sistema::menuPacotes(vector<Pacote> &pacotes, vector<Evento> &eventos){
    int escolha;
    do{
        Sistema::mostraMenuPacotes();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            inserePacote(pacotes,eventos);
            break;
        case 2: 
            insereEventoNoPacote(pacotes,eventos);
            break;
        case 3:
            encontraPacote(pacotes);
            break;
        case 4:
            excluiPacote(pacotes,eventos);
            break;
        case 5:
            excluiEventoDeUmPacote(pacotes,eventos);
            break;
        case 6:
            listaPacotes(pacotes);
            break;
        case 7:
            listaEventosDeUmPacote(pacotes);
            break;
        case 8:
            modificaPacote(pacotes,eventos);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuPacotes(){
    system("clear || cls");
    cout << "----------MENU PACOTES----------" << endl;
    cout << "[1] INSERE PACOTE" << endl;
    cout << "[2] INSERE EVENTOS A UM PACOTE" << endl;
    cout << "[3] ENCONTRA PACOTE" << endl;
    cout << "[4] EXCLUI PACOTE" << endl;
    cout << "[5] EXCLUI EVENTO DE UM PACOTE" << endl;
    cout << "[6] LISTA PACOTES" << endl;
    cout << "[7] LISTA EVENTOS DE UM PACOTE" << endl;
    cout << "[8] MODIFICA PACOTE" << endl;
    cout << "[0] SAIR " << endl;
}

void Sistema::inserePacote(vector<Pacote> &pacotes, vector<Evento> &eventos){
    Pacote pacote = cadastraPacote();
    pacotes.push_back(pacote);
    inserePacoteNoBanco(pacote);
    cout << "--PACOTE CADASTRADO COM SUCESSO " << endl;
    pause();
}

void Sistema::insereEventoNoPacote(vector<Pacote> &pacotes, vector<Evento> &eventos){
    system("clear || cls");
    int idPacote = recebeIDPacote();
    bool pacoteEncontrado = false;
    Pacote* pacote;

    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            pacote = &(*it);
            pacoteEncontrado = true;
            break;
        }
    }

    if(!pacoteEncontrado){
        cout << "Pacote não encontrado em nosso banco de dados!" << endl;
        pause();
        return;
    }else{
        int escolha;
        do{
            system("clear || cls");
            cout << "-----MENU INSERÇÃO DE EVENTOS EM PACOTE----" << endl;
            cout << "[1] ROTEIRO " << endl;
            cout << "[2] DESLOCAMENTO " << endl;
            cout << "[3] PERNOITE " << endl;
            cout << "[0] SAIR " << endl;
            cout << "Escolha :  ";
            cin >> escolha;
            cin.get();
            switch (escolha)
            {
            case 1:
                insereRoteiroNoPacote(pacote,eventos);
                atualizaPacotesBanco(pacotes,eventos);
                break;
            case 2:
                insereDeslocamentoNoPacote(pacote,eventos);
                atualizaPacotesBanco(pacotes,eventos);
                break;
            case 3:
                inserePernoiteNoPacote(pacote,eventos);
                atualizaPacotesBanco(pacotes,eventos);
                break;
            default:
                break;
            }
        }while(escolha!=0);
    }


}

void Sistema::encontraPacote(vector<Pacote> &pacotes){
    system("clear || cls");
    int idPacote = recebeIDPacote();
    bool pacoteEncontrado = false;

    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            it->mostraPacote();
            pacoteEncontrado = true;
            pause();
            return;
        }
    }

    if(!pacoteEncontrado){
        cout << "Pacote não encontrado em nosso banco de dados" << endl;
        pause();
    }
}

void Sistema::excluiPacote(vector<Pacote> &pacotes,vector<Evento> &eventos){
    system("clear || cls");
    int idPacote = recebeIDPacote();
    bool pacoteExcluido = false;

    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            pacotes.erase(it);
            pacoteExcluido = true;
            cout << "Pacote excluido com sucesso!" << endl;
            atualizaPacotesBanco(pacotes,eventos);
            pause();
            return;
        }
    }

    if(!pacoteExcluido){
        cout << "Pacote não encontrado em nosso banco de dados" << endl;
        pause();
    }
}

void Sistema::excluiEventoDeUmPacote(vector<Pacote> &pacotes , vector<Evento> &eventos){
    system("clear || cls");
    int idPacote = recebeIDPacote();
    bool pacoteEncontrado = false;
    Pacote *pacote;

    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            pacoteEncontrado = true;
            pacote = &(*it);
            break;
        }
    }

    if(!pacoteEncontrado){
        cout << "Pacote não encontrado em nosso banco de dados" << endl;
        pause();
    }else{
        int escolha;
        do{
            system("clear || cls");
            cout << "-----MENU EXCLUSÃO DE EVENTO DO PACOTE----" << endl;
            cout << "[1] ROTEIRO " << endl;
            cout << "[2] DESLOCAMENTO " << endl;
            cout << "[3] PERNOITE " << endl;
            cout << "[0] SAIR " << endl;
            cout << "Escolha :  ";
            cin >> escolha;
            cin.get();
            switch (escolha)
            {
            case 1:
                excluiRoteiroNoPacote(pacote);
                atualizaPacotesBanco(pacotes,eventos);
                break;
            case 2:
                excluiDeslocamentoNoPacote(pacote);
                atualizaPacotesBanco(pacotes,eventos);
                break;
            case 3:
                excluiPernoiteNoPacote(pacote);
                atualizaPacotesBanco(pacotes,eventos);
                break;
            default:
                break;
            }
        }while(escolha!=0);
    }


}

void Sistema::excluiRoteiroNoPacote(Pacote *pacote){
    int idRoteiro = recebeIDRoteiro();
    bool roteiroExcluido = false;
    Evento* evento;

    for(auto it=pacote->getListaEventos()->begin() ; it!=pacote->getListaEventos()->end() ; it++){
        if( (*it)->getTipo() == 1 && (*it)->getId() == idRoteiro ){
            pacote->getListaEventos()->erase(it);
            cout << "Roteiro excluido com sucesso" << endl;
            pause();
            roteiroExcluido = true;
            break;
        }
    }

    if(!roteiroExcluido){
        cout << "Roteiro nao encontrado em nosso banco de Dados";
        pause();    
        return;
    }else{
            pacote->setQtdRoteiros(pacote->getQtdRoteiros()-1);
    }

}

void Sistema::excluiDeslocamentoNoPacote(Pacote *pacote){
    int idDeslocamento = recebeIDDeslocamento();
    bool deslocamentoExcluido = false;
    Evento* evento;

    for(auto it=pacote->getListaEventos()->begin() ; it!=pacote->getListaEventos()->end() ; it++){
        if( (*it)->getTipo() == 2 && (*it)->getId() == idDeslocamento ){
            pacote->getListaEventos()->erase(it);
            cout << "Deslocamento excluido com sucesso" << endl;
            pause();
            deslocamentoExcluido = true;
            break;
        }
    }

    if(!deslocamentoExcluido){
        cout << "Deslocamento nao encontrado em nosso banco de Dados";
        pause();    
        return;
    }else{
            pacote->setQtdDeslocamentos(pacote->getQtdDeslocamentos()-1);
    }

}

void Sistema::excluiPernoiteNoPacote(Pacote *pacote){
    int idPernoite = recebeIDPernoite();
    bool pernoiteExcluido = false;
    Evento* evento;

    for(auto it=pacote->getListaEventos()->begin() ; it!=pacote->getListaEventos()->end() ; it++){
        if( (*it)->getTipo() == 3 && (*it)->getId() == idPernoite ){
            pacote->getListaEventos()->erase(it);
            cout << "Pernoite excluido com sucesso" << endl;
            pause();
            pernoiteExcluido = true;
            break;
        }
    }

    if(!pernoiteExcluido){
        cout << "Pernoite nao encontrado em nosso banco de Dados";
        pause();    
        return;
    }else{
            pacote->setQtdPernoite(pacote->getQtdPernoite()-1);
    }

}

void Sistema::listaPacotes(vector<Pacote> &pacotes ){
    system("clear || cls");

    cout << "-----LISTA DE PACOTES------" << endl;
    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        it->mostraPacote();
        cout << "----------------" << endl;
    }
    cout << "-----FIM DA LISTA------" << endl;
    pause();

}

void Sistema::listaEventosDeUmPacote(vector<Pacote> &pacotes){
    system("clear || cls");
    int idPacote = recebeIDPacote();
    bool pacoteEncontrado = false;
    Pacote* pacote;

    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            pacote =&(*it);
            pacoteEncontrado = true;
            break;
        }
    }

    if(!pacoteEncontrado){
        cout << "Pacote não encontrado em nosso banco de dados" << endl;
        pause();
    }else{
        cout << "-----LISTA DE EVENTOS------" << endl;
        for(auto it=pacote->getListaEventos()->begin(); it!=pacote->getListaEventos()->end(); it++){
            (*it)->mostraEvento();
            cout << "----------------" << endl;
        }
        cout << "-----FIM DA LISTA------" << endl;
        pause();
    }
}

void Sistema::modificaPacote(vector<Pacote> &pacotes,vector<Evento> &eventos){
    system("clear || cls");
    int idPacote = recebeIDPacote();
    bool pacoteExcluido = false;
    string nome;
    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            system("clear || cls");
            pacoteExcluido = true;
            cout << "Novo nome : ";
            getline(cin,nome);
            it->setNome(nome);
            cout << "Nome atualizado com sucesso!" << endl;
            atualizaPacotesBanco(pacotes,eventos);
            pause();
            return;
        }
    }

    if(!pacoteExcluido){
        cout << "Pacote não encontrado em nosso banco de dados" << endl;
        pause();
    }
}

/*FUNÇÕES AUXILIARES*/

Pacote Sistema::cadastraPacote(){
    system("clear || cls");
    string nome;

    cout << "Nome : ";
    getline(cin,nome);

    Pacote pacote(nome);

    return pacote;
}

int Sistema::recebeIDPacote(){
    int idPernoite ;
    cout << "Insira o ID do pacote :" ;
    cin >> idPernoite;
    cin.get();
    return idPernoite;
}

/*PERSISTENCIA**/

void Sistema::inserePacoteNoBanco(Pacote &pacote){
    ofstream inPacotes;
    inPacotes.open("../BancoDeDados/Pacotes.txt",ios_base::app);
    if(inPacotes.is_open()){
        inPacotes << pacote.getId() << endl;
        inPacotes << pacote.getNome() << endl;
        inPacotes << pacote.getQtdRoteiros() << endl;
        inPacotes << pacote.getQtdDeslocamentos() << endl;
        inPacotes << pacote.getQtdPernoite() << endl;
    }
}

void Sistema::insereRoteiroNoPacote(Pacote *pacote, vector<Evento> &eventos){
    int idRoteiro = recebeIDRoteiro();
    bool roteiroEncontrado = false;
    Evento* evento;

    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 1  &&  it->getId() == idRoteiro){
            evento = &(*it);
            roteiroEncontrado = true;
            break;
        }
    }
    
    
    if(!roteiroEncontrado){
        cout << "Roteiro nao encontrado em nosso banco de Dados";
        pause();    
        return;
    }

    pacote->getListaEventos()->push_back(evento);
    pacote->setQtdRoteiros(pacote->getQtdRoteiros()+1);

}

void Sistema::atualizaPacotesBanco(vector<Pacote> &pacotes, vector<Evento> &eventos){
    ofstream ofPacotes;
    int qtdRoteiros, qtdDeslocamento, qtdPernoite;
    ofPacotes.open("../BancoDeDados/Pacotes.txt",ios_base::out);
    if(ofPacotes.is_open()){
        for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
            ofPacotes << it->getId() << endl;
            ofPacotes << it->getNome() << endl;
            ofPacotes << it->getQtdRoteiros() << endl;
            for(auto itera = it->getListaEventos()->begin() ; itera!=it->getListaEventos()->end() ; itera++){
                if((*itera)->getTipo() == 1){
                    ofPacotes << (*itera)->getId() << endl;
                }
            }
            ofPacotes << it->getQtdDeslocamentos() << endl;
            for(auto itera = it->getListaEventos()->begin() ; itera!=it->getListaEventos()->end() ; itera++){
                if((*itera)->getTipo() == 2){
                    ofPacotes << (*itera)->getId() << endl;
                }
            }
            ofPacotes << it->getQtdPernoite() << endl;
            for(auto itera = it->getListaEventos()->begin() ; itera!=it->getListaEventos()->end() ; itera++){
                if((*itera)->getTipo() == 3){
                    ofPacotes << (*itera)->getId() << endl;
                }
            }
        }
        ofPacotes.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Pacotes.txt" << endl;
        exit(0);
    }
}

void Sistema::insereDeslocamentoNoPacote(Pacote *pacote, vector<Evento> &eventos){
    int idDeslocamento = recebeIDDeslocamento();
    bool deslocamentoEncontrado = false;
    Evento* evento;

    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 2  &&  it->getId() == idDeslocamento){
            evento = &(*it);
            deslocamentoEncontrado = true;
            break;
        }
    }
    
    
    if(!deslocamentoEncontrado){
        cout << "Deslocamento não encontrado em nosso banco de Dados";
        pause();    
        return;
    }

    pacote->getListaEventos()->push_back(evento);
    pacote->setQtdDeslocamentos(pacote->getQtdDeslocamentos()+1);

}

void Sistema::inserePernoiteNoPacote(Pacote *pacote, vector<Evento> &eventos){
    int idPernoite = recebeIDPernoite();
    bool pernoiteEncontrado = false;
    Evento* evento;

    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
        if(it->getTipo() == 3  &&  it->getId() == idPernoite){
            evento = &(*it);
            pernoiteEncontrado = true;
            break;
        }
    }
    
    
    if(!pernoiteEncontrado){
        cout << "Pernoite não encontrado em nosso banco de Dados";
        pause();    
        return;
    }

    pacote->getListaEventos()->push_back(evento);
    pacote->setQtdPernoite(pacote->getQtdPernoite()+1);

}

void Sistema::recuperaPacote(vector<Pacote> &pacotes,vector<Evento> &eventos){
    ifstream inPacotes;
    inPacotes.open("../BancoDeDados/Pacotes.txt",ios_base::in);
    if(inPacotes.is_open()){
        string id, nome, qtdRoteiro, qtdDeslocamento, qtdPernoite , idRoteiro, idDeslocamento , idPernoite;
        int idd, qtdRoteiroINT , qtdDeslocamentoINT , qtdPernoiteINT;
        while(inPacotes.eof() == false){
            getline(inPacotes,id);
            if(id.empty()==false){
                getline(inPacotes,nome);
                Pacote pacote(nome);

                getline(inPacotes,qtdRoteiro);
                qtdRoteiroINT = stoi(qtdRoteiro);
                pacote.setQtdRoteiros(qtdRoteiroINT);
                for(int i = 0 ; i < qtdRoteiroINT ; i++){
                    getline(inPacotes,idRoteiro);
                    int idRoteiroINT = stoi(idRoteiro);
                    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
                        if(it->getId() == idRoteiroINT && it->getTipo()== 1){
                            pacote.getListaEventos()->push_back(&(*it));
                            break;
                        }
                    }
                }

                getline(inPacotes,qtdDeslocamento);
                qtdDeslocamentoINT = stoi(qtdDeslocamento);
                pacote.setQtdDeslocamentos(qtdDeslocamentoINT);
                for(int i = 0 ; i < qtdDeslocamentoINT ; i++){
                    getline(inPacotes,idDeslocamento);
                    int idDeslocamentoINT = stoi(idDeslocamento);
                    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
                        if(it->getId() == idDeslocamentoINT && it->getTipo()== 2){
                            pacote.getListaEventos()->push_back(&(*it));
                            break;
                        }
                    }
                }

                getline(inPacotes,qtdPernoite);
                qtdPernoiteINT = stoi(qtdPernoite);
                pacote.setQtdPernoite(qtdPernoiteINT);
                for(int i = 0 ; i < qtdPernoiteINT ; i++){
                    getline(inPacotes,idPernoite);
                    int idPernoiteINT = stoi(idPernoite);
                    for(auto it=eventos.begin() ; it!=eventos.end() ; it++){
                        if(it->getId() == idPernoiteINT && it->getTipo()== 3){
                            pacote.getListaEventos()->push_back(&(*it));
                            break;
                        }
                    }
                }
                pacotes.push_back(pacote);
            }
        }
        inPacotes.close();
    }
}

/**GERENCIA**/

void Sistema::menuGerencia(vector<Gerencia> &gerencias, vector<Cliente> &clientes,vector<Pacote> &pacotes){
    int escolha;
    do{
        Sistema::mostraMenuGerencia();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            inserePacoteACliente(gerencias,clientes,pacotes);
            break;
        case 2: 
            excluiPacoteDeCliente(gerencias);
            break;
        case 3:
            mostraPacotesDeCliente(gerencias);
            break;
        default:
            break;
        }
    }while(escolha!=0);
}

void Sistema::mostraMenuGerencia(){
    system("clear || cls");
    cout << "----------MENU GERENCIA----------" << endl;
    cout << "[1] INSERE PACOTE A UM CLIENTE" << endl;
    cout << "[2] EXCLUI PACOTE DE UM CLIENTE " << endl;
    cout << "[3] MOSTRA PACOTES DE UM CLIENTE" << endl;
    cout << "[0] SAIR " << endl;
}

void Sistema::inserePacoteACliente(vector<Gerencia> &gerencias, vector<Cliente> &clientes,vector<Pacote> &pacotes){
    int idCliente , idPacote;
    bool clienteEncontrado = false , pacoteEncontrado = false;
    Cliente* cliente;
    Pacote* pacote;
    cout << "Digite o Id do cliente : ";
    cin >> idCliente;
    cin.get();

    for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
        if(it->getId() == idCliente){
            cliente = &(*it);
            clienteEncontrado = true;
            break;
        }
    }

    if(!clienteEncontrado){
        cout << "Cliente não encontrado em nossa base de dados!" << endl;
        pause();
        return;
    }

    cout << "Digite o Id do pacote : ";
    cin >> idPacote;
    cin.get();
    
    for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
        if(it->getId() == idPacote){
            pacote = &(*it);
            pacoteEncontrado = true;
            break;
        }
    }

    if(!pacoteEncontrado){
        cout << "Pacote não encontrado em nossa base de dados!" << endl;
        pause();
        return;
    }

    Gerencia gerencia(cliente,pacote);

    gerencias.push_back(gerencia);
    insereGerenciaNoBanco(gerencia);
    cout << "Registro realizado com sucesso!" << endl;
    pause();

}

void Sistema::excluiPacoteDeCliente(vector<Gerencia> &gerencias){
    int idCliente , idPacote;
    bool compraEncontrada = false ;
    cout << "Digite o Id do cliente : ";
    cin >> idCliente;
    cin.get();

    cout << "Digite o Id do pacote : ";
    cin >> idPacote;
    cin.get();
    
    for(auto it=gerencias.begin() ; it!=gerencias.end() ; it++){
        if(it->getCliente()->getId() == idCliente && it->getPacote()->getId()== idPacote){
            gerencias.erase(it);
            compraEncontrada = true;
            cout << "Exclusão realizada com sucesso ! "<< endl;
            atualizaGerenciaNoBanco(gerencias);
            pause();
            break;
        }
    }

    if(!compraEncontrada){
        cout << "Não há pacote indexado a esse cliente em nossa base de dados!" << endl;
        pause();
        return;
    }



}

void Sistema::mostraPacotesDeCliente(vector<Gerencia> &gerencias){
    system("clear || cls");
    int idCliente;
    cout << "Digite o Id do cliente : ";
    cin >> idCliente;
    cin.get();
    cout << "LISTA DE PACOTES A ESSE CLIENTE" << endl;
    for(auto it=gerencias.begin() ; it!=gerencias.end() ; it++){
        if(it->getCliente()->getId() == idCliente){
            it->mostraGerencia();
            cout << "-------------------------" << endl;
        }
    }
    cout << "---------FIM DA LISTA---------" << endl;
    pause();

}

void Sistema::insereGerenciaNoBanco(Gerencia &gerencia){
    ofstream inGerencia;
    inGerencia.open("../BancoDeDados/Gerencias.txt",ios_base::app);
    if(inGerencia.is_open()){
        inGerencia << gerencia.getId() << endl;
        inGerencia << gerencia.getCliente()->getId()<< endl;
        inGerencia << gerencia.getPacote()->getId()<< endl;
        inGerencia.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Gerencias.txt" << endl;
        exit(0);
    }
}

void Sistema::atualizaGerenciaNoBanco(vector<Gerencia> &gerencias){
    ofstream inGerencia;
    inGerencia.open("../BancoDeDados/Gerencias.txt",ios_base::out);
    if(inGerencia.is_open()){
        for(auto it=gerencias.begin() ; it!=gerencias.end() ; it++){
            inGerencia << it->getId() << endl;
            inGerencia << it->getCliente()->getId()<< endl;
            inGerencia << it->getPacote()->getId()<< endl;
        }
        inGerencia.close();
        
    }else{
        cout << "Não foi possivel abrir o arquivo Gerencias.txt" << endl;
        exit(0);
    }
}

void Sistema::recuperaGerenciaNoBanco(vector<Gerencia> &gerencias, vector<Cliente> &clientes,vector<Pacote> &pacotes){
    ifstream inGerencia;

    inGerencia.open("../BancoDeDados/Gerencias.txt",ios_base::in);
    if(inGerencia.is_open()){
        string id,idCliente, idPacote;
        int idd,idClienteINT , idPacoteINT;
        Cliente* cliente;
        Pacote* pacote;
        while (inGerencia.eof() == false)
        {
            
            getline(inGerencia,id);
            getline(inGerencia,idCliente);
            getline(inGerencia,idPacote);

            if(!id.empty()){
                idd = stoi(id);
                idClienteINT = stoi(idCliente);

                for(auto it=clientes.begin() ; it!=clientes.end() ; it++){
                    if(it->getId() == idClienteINT){
                        cliente = &(*it);
                        break;
                    }
                }

                idPacoteINT = stoi(idPacote);

                for(auto it=pacotes.begin() ; it!=pacotes.end() ; it++){
                    if(it->getId() == idPacoteINT){
                        pacote = &(*it);
                        break;
                    }
                }


                Gerencia gerencia(cliente,pacote);
                gerencia.setId(idd);
                if(idd > Gerencia::getNumeroDeIdentificadores()){
                    Gerencia::setNumeroDeIdentificadores(idd);
                }
                gerencias.push_back(gerencia);
            }
        }
        inGerencia.close();
    }
}

