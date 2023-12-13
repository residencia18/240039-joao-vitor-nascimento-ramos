#include "../Headers/RedeSocial.hpp"
#include<fstream>

vector<Usuario>* RedeSocial::getListaDeUsuarios(){
    return &(this->listaUsuarios);
} 

void RedeSocial::menuPrincipal(vector<Usuario> &listaUsuarios){
    int escolha;
    do{
        mostraMenuPrincipal();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            cadastraUsuario(listaUsuarios);
            break;
        case 2:
            loginUsuario(listaUsuarios);
            break;
        case 3:
            excluiUsuario(listaUsuarios);
            break;
        case 4:
            listarUsuarios(listaUsuarios);
            break;
        default:
            break;
        }
    }while(escolha!=0);

}

void RedeSocial::menuUsuario(Usuario* usuario,vector<Usuario> &listaUsuarios){
    int escolha;
    do{
        mostraMenuUsuario();
        cin >> escolha;
        cin.get();
        switch (escolha)
        {
        case 1:
            seguirUsuario(usuario,listaUsuarios);
            break;
        case 2:
            postarTweet(usuario);
            break;
        case 3:
            deixarDeSeguir(usuario,listaUsuarios);
            break;
        case 4:
            listarSeguidores(usuario);
            break;
        case 5:
            listarSeguindo(usuario);
            break;
        case 6:
            listaDeTweets(usuario);
            break;
        case 7:
            mostraFeed(usuario);
        default:
            break;
        }
    }while(escolha!=0);
}

void RedeSocial::mostraMenuUsuario(){
    system("clear || cls");
    cout << "-------MENU USUARIO--------" << endl;
    cout << "[1] SEGUIR USUARIO" << endl;
    cout << "[2] POSTAR TWEET " << endl;
    cout << "[3] DEIXAR DE SEGUIR USUARIO  " << endl;
    cout << "[4] LISTA DE SEGUIDORES" << endl;
    cout << "[5] LISTA DE SEGUINDO" << endl;
    cout << "[6] LISTA DE TWEETS PROPRIOS" << endl;
    cout << "[7] MOSTRA FEED" << endl;
    cout << "[0] SAIR " << endl; 
}

void RedeSocial::loginUsuario(vector<Usuario> &listaUsuarios){
    system("clear || cls");
    string user,senha;
    cout << "User : ";
    getline(cin,user);
    cout << "Senha : ";
    getline(cin,senha);

    for(auto it =listaUsuarios.begin() ; it!=listaUsuarios.end() ;it++){
        if(it->getUser() == user && it->getSenha() == senha){
            menuUsuario(&(*it),listaUsuarios);
            return;
        }
    }

    cout << "Essas credenciais nao correspondem a nenhum usuario cadastrado" << endl;
    pause();
}

void RedeSocial::excluiUsuario(vector<Usuario> &listaUsuarios){
    system("clear || cls");
    string user,senha;
    cout << "User : ";
    getline(cin,user);
    cout << "Senha : ";
    getline(cin,senha);
    bool usuarioExcluido= false;

    for(auto it =listaUsuarios.begin() ; it!=listaUsuarios.end() ;it++){
        if(it->getUser() == user && it->getSenha() == senha){
            listaUsuarios.erase(it);
            usuarioExcluido = true;

        }
    }

    if(usuarioExcluido){
        cout << "Usuario excluido com sucesso!" << endl;
        atualizaBancoDeUsuarios(listaUsuarios);
        pause();
        for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
            for(auto itera=it->getListaDeSeguidores()->begin() ; itera!=it->getListaDeSeguidores()->end() ; itera++){
                if((*itera)->getUser() == user){
                    it->getListaDeSeguidores()->erase(itera);
                    break;
                }
            }

            for(auto itera=it->getListaDeSeguindo()->begin() ; itera!=it->getListaDeSeguindo()->end() ; itera++){
                if((*itera)->getUser() == user){
                    it->getListaDeSeguindo()->erase(itera);
                    break;
                }
            }
        }
        atualizaSeguindoNoBanco(listaUsuarios);
    }else{
        cout << "Essas credenciais nao correspondem a nenhum usuario cadastrado" << endl;
        pause();
    }


}

void RedeSocial::seguirUsuario(Usuario* usuario,vector<Usuario> &listaUsuarios){
    system("clear || cls");
    string user;
    cout << "Digite user de um usuario para seguir : ";
    getline(cin,user);
    bool usuarioEncontrado;
    if(usuario->getUser() == user){
        cout << "Não pode seguir a si proprio" << endl;
        pause();
        return;
    }

    for(auto it=usuario->getListaDeSeguindo()->begin() ; it!=usuario->getListaDeSeguindo()->end() ; it++){
        if((*it)->getUser() == user){
            cout << "Já segue este usuario " << endl;
            pause();
            return;
        }
    }

    for(auto it = listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
        if(it->getUser() == user && it->getID() != usuario->getID()){
            usuario->getListaDeSeguindo()->push_back(&(*it));
            it->getListaDeSeguidores()->push_back(usuario);
            cout << "Você agora segue " << user << " !" << endl;
            pause();
            usuarioEncontrado = true;
            atualizaSeguindoNoBanco(listaUsuarios);
            break;
        }
    }

    if(!usuarioEncontrado){
        cout << "Usuario nao encontrado " << endl;
        pause();
    }

}

void RedeSocial::deixarDeSeguir(Usuario* usuario,vector<Usuario> &listaUsuarios){
    system("clear || cls");
    string user;
    cout << "Digite user de um usuario para deixar de seguir : ";
    getline(cin,user);
    bool usuarioEncontrado;

    for(auto it=usuario->getListaDeSeguindo()->begin() ; it!=usuario->getListaDeSeguindo()->end() ; it++){
        if((*it)->getUser() == user){
            usuarioEncontrado = true;
            usuario->getListaDeSeguindo()->erase(it);
            cout << "Agora você não segue mais " << user << " !" << endl;
            pause();
            break;
        }
    }

    if(usuarioEncontrado){
        for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
            for(auto itera= it->getListaDeSeguidores()->begin() ; itera!= it->getListaDeSeguidores()->end() ; itera++){
                if(it->getUser() == user && (*itera)->getUser() == usuario->getUser()){
                    it->getListaDeSeguidores()->erase(itera);
                    break;
                }
            }
        }
    }else{
        cout << "Você não segue nenhum " << user << " !"  << endl;
        pause();
    }
}

void RedeSocial::postarTweet(Usuario* usuario){
    string descricao;
    cout << "Digite a descrição do tweet : ";
    getline(cin,descricao);

    Tweet tweet(usuario,descricao);

    usuario->postarTweet(tweet);
    insereTweetNoBanco(&tweet);
    cout << "Tweet postado com sucesso!" << endl;
    pause();

}

void RedeSocial::listarSeguidores(Usuario* usuario){
    system("clear || cls");
    cout << "-------LISTA DE SEGUIDORES-------" << endl;
    for(auto it=usuario->getListaDeSeguidores()->begin() ; it!=usuario->getListaDeSeguidores()->end() ; it++){
        (*it)->mostraUsuario();
        cout << "------------------" << endl;
    }
    pause();
    cout << "-------FIM DA LISTA-------" << endl;
}

void RedeSocial::listarSeguindo(Usuario* usuario){
    system("clear || cls");
    cout << "-------LISTA DE SEGUINDO-------" << endl;
    for(auto it=usuario->getListaDeSeguindo()->begin() ; it!=usuario->getListaDeSeguindo()->end() ; it++){
        (*it)->mostraUsuario();
        cout << "------------------" << endl;
    }
    pause();
    cout << "-------FIM DA LISTA-------" << endl;
}

void RedeSocial::listaDeTweets(Usuario* usuario){
    system("clear || cls");
    cout << "-------LISTA DE TWEETS-------" << endl;
    for(auto it=usuario->getDeTweets()->begin() ; it!=usuario->getDeTweets()->end() ; it++){
        it->mostraTweet();
        cout << "-----------------------" << endl;
    }
    cout << "-------FIM DA LISTA-------" << endl;
    pause();
}

void RedeSocial::mostraFeed(Usuario* usuario){
    usuario->receber_feed();
    pause();
}

void RedeSocial::mostraMenuPrincipal(){
    system("clear || cls");
    cout << "-------MENU TWITTER--------" << endl;
    cout << "[1] CADASTRAR USUARIO" << endl;
    cout << "[2] LOGAR EM USUARIO " << endl;
    cout << "[3] EXCLUIR USUARIO " << endl;
    cout << "[4] LISTA DE USUARIOS" << endl;
    cout << "[0] SAIR " << endl; 
}

void RedeSocial::cadastraUsuario(vector<Usuario> &listaUsuarios){
    system("clear || cls");
    bool clienteCadastrado = true;
    Usuario usuario = preencheUsuario();
    for(auto it= listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
        if(it->getUser() == usuario.getUser()){
            clienteCadastrado = false;
            break;
        }
    }
    if(clienteCadastrado){
        listaUsuarios.push_back(usuario);
        cout << "Usuario cadastrado com sucesso!"  << endl;
        insereUsuarioNoBanco(&usuario);
        pause();
    }else{
        cout << "Já existe um usuario com esse user " << endl;
        Usuario::setNumeroDeIdentificadores(Usuario::getNumeroDeIdentificadores()-1);
        pause();
    }

}

Usuario RedeSocial::preencheUsuario(){
    string user,nome, senha;
    cout << "Insira o User do usuario : " ;
    getline(cin,user);
    cout << "Nome do usuario : ";
    getline(cin,nome);
    cout << "Senha do usuario : ";
    getline(cin,senha);
    Usuario usuario(nome,user,senha);
    return usuario;
}

void RedeSocial::listarUsuarios(vector<Usuario> &listaUsuarios){
    system("clear || cls");
    for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
        it->mostraUsuario();
        cout << "------------------" << endl;
    }
    pause();
}

void RedeSocial::insereUsuarioNoBanco(Usuario* usuario){
    ofstream inUsuarios;
    inUsuarios.open("../BancoDeDados/Usuarios.txt",ios_base::app);
    if(inUsuarios.is_open()){
        inUsuarios << usuario->getID() << endl;
        inUsuarios << usuario->getUser() << endl;
        inUsuarios << usuario->getSenha() << endl;
        inUsuarios << usuario->getNome() << endl;
        inUsuarios.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Usuarios.txt" << endl;
        exit(0);
    }
}

void RedeSocial::atualizaBancoDeUsuarios(vector<Usuario> &listaUsuarios){
    ofstream inUsuarios;
    inUsuarios.open("../BancoDeDados/Usuarios.txt",ios_base::out);
    if(inUsuarios.is_open()){
        for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
        inUsuarios << it->getID() << endl;
        inUsuarios << it->getUser() << endl;
        inUsuarios << it->getSenha() << endl;
        inUsuarios << it->getNome() << endl;
        }
    }else{
        cout << "Não foi possivel abrir o arquivo Usuarios.txt" << endl;
        exit(0);
    }
}

void RedeSocial::recuperaUsuarios(vector<Usuario> &listaUsuarios){
    ifstream inUsuarios;
    inUsuarios.open("../BancoDeDados/Usuarios.txt",ios_base::in);
    if(inUsuarios.is_open()){
        string id,user,senha,nome;
        int idd;
        while(inUsuarios.eof() == false){
            getline(inUsuarios,id);
            getline(inUsuarios,user);
            getline(inUsuarios,senha);
            getline(inUsuarios,nome);
            if(!id.empty()){
                idd = stoi(id);
                Usuario usuario(nome,user,senha);
                usuario.setID(idd);
                if(idd > Usuario::getNumeroDeIdentificadores()){
                    Usuario::setNumeroDeIdentificadores(idd);
                }
                listaUsuarios.push_back(usuario);
            }
        }
    }else{
        cout << "Não foi possivel abrir o arquivo Usuarios.txt" << endl;
        exit(0);
    }
}

void RedeSocial::insereTweetNoBanco(Tweet* tweet){
    ofstream inTweet;
    inTweet.open("../BancoDeDados/Tweets.txt",ios_base::app);
    if(inTweet.is_open()){
        inTweet << tweet->getID() << endl;
        inTweet << tweet->getAuthor()->getID() << endl;
        inTweet << tweet->getDescricao() << endl;
        inTweet << tweet->getDataPublicacao().tm_mday << endl;
        inTweet << tweet->getDataPublicacao().tm_mon << endl;
        inTweet << tweet->getDataPublicacao().tm_year << endl;
        inTweet << tweet->getDataPublicacao().tm_sec << endl;
        inTweet << tweet->getDataPublicacao().tm_min << endl;
        inTweet << tweet->getDataPublicacao().tm_hour << endl;
        inTweet.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Tweets.txt" << endl;
        exit(0);
    }
}

void RedeSocial::atualizaBancoDeTweets(vector<Usuario> &listaUsuarios){
    ofstream inTweets;
    inTweets.open("../BancoDeDados/Tweets.txt",ios_base::out);
    if(inTweets.is_open()){
        for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
            for(auto itera=it->getDeTweets()->begin() ; itera!=it->getDeTweets()->end() ; itera++){
                inTweets << itera->getID() << endl;
                inTweets << itera->getAuthor()->getID() << endl;
                inTweets << itera->getDescricao() << endl;
                inTweets << itera->getDataPublicacao().tm_mday << endl;
                inTweets << itera->getDataPublicacao().tm_mon << endl;
                inTweets << itera->getDataPublicacao().tm_year << endl;
                inTweets << itera->getDataPublicacao().tm_sec << endl;
                inTweets << itera->getDataPublicacao().tm_min << endl;
                inTweets << itera->getDataPublicacao().tm_hour << endl;
            }
        }
    }else{
        cout << "Não foi possivel abrir o arquivo Tweets.txt" << endl;
        exit(0);
    }
}

void RedeSocial::recuperaTweets(vector<Usuario> &listaUsuarios){
    ifstream inTweets;
    inTweets.open("../BancoDeDados/Tweets.txt",ios_base::in);
    if(inTweets.is_open()){
        string id,idUser,descricao,dia,mes,ano,segundo,minuto,hora;
        int idd , idUserINT, diaINT , mesINT , anoINT , segundoINT , minutoINT , horaINT;
        Usuario *usuario;
        while(inTweets.eof() == false){
            getline(inTweets,id);
            getline(inTweets,idUser);
            getline(inTweets,descricao);
            getline(inTweets,dia);
            getline(inTweets,mes);
            getline(inTweets,ano);
            getline(inTweets,segundo);
            getline(inTweets,minuto);
            getline(inTweets,hora);

            if(!id.empty()){
                idd = stoi(id);
                idUserINT = stoi(idUser);
                diaINT = stoi(dia);
                mesINT = stoi(mes);
                anoINT  = stoi(ano);
                segundoINT = stoi(segundo);
                minutoINT = stoi(minuto);
                horaINT =  stoi(hora);
                for(auto it = listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
                    if(it->getID() == idUserINT){
                        usuario = &(*it);
                        break;
                    }
                }

                Data data;
                data.tm_mday = diaINT;
                data.tm_mon = mesINT;
                data.tm_year = anoINT;
                data.tm_sec = segundoINT;
                data.tm_min = minutoINT;
                data.tm_hour = horaINT;

                Tweet tweet(usuario,descricao,data);
                tweet.setID(idd);
                if(idd > Tweet::getNumeroDeIdentificadores()){
                    Tweet::setNumeroDeIdentificadores(idd);
                }
                usuario->getDeTweets()->push_back(tweet);
            }
        }
    }else{
        cout << "Não foi possivel abrir o arquivo Usuarios.txt" << endl;
        exit(0);
    }
}

void RedeSocial::atualizaSeguindoNoBanco(vector<Usuario> &listaUsuarios){
    ofstream inSeguindo;
    inSeguindo.open("../BancoDeDados/Seguindo.txt",ios_base::out);
    if(inSeguindo.is_open()){
        for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
            for(auto itera= it->getListaDeSeguindo()->begin() ; itera!=it->getListaDeSeguindo()->end() ; itera++){
                inSeguindo << it->getID() << endl;
                inSeguindo << (*itera)->getID() << endl;
            }
        }
        inSeguindo.close();
    }else{
        cout << "Não foi possivel abrir o arquivo Seguindo.txt" << endl;
        exit(0);
    }
}

void RedeSocial::recuperaSeguindo(vector<Usuario> &listaUsuarios){
    ifstream inSeguindo;
    inSeguindo.open("../BancoDeDados/Seguindo.txt",ios_base::in);
    Usuario* seguidor;
    Usuario* seguido;
    if(inSeguindo.is_open()){
        string idSeguidor, idSeguido;
        int iddSeguidor, iddSeguido;
        while(inSeguindo.eof() == false){
            getline(inSeguindo,idSeguidor);
            getline(inSeguindo,idSeguido);

            if(!idSeguidor.empty()){
                iddSeguidor = stoi(idSeguidor);
                iddSeguido = stoi(idSeguido);

                for(auto it=listaUsuarios.begin() ; it!=listaUsuarios.end() ; it++){
                    if(it->getID() == iddSeguidor){
                        seguidor = &(*it);
                    }

                    if(it->getID() == iddSeguido){
                        seguido = &(*it);
                    }
                }

                seguidor->getListaDeSeguindo()->push_back(seguido);
                seguido->getListaDeSeguidores()->push_back(seguidor);

            }
        }
    }else{
        cout << "Não foi possivel abrir o arquivo Usuarios.txt" << endl;
        exit(0);
    }
}

void RedeSocial::pause(){
    cout << "Pressione <Enter> para prosseguir....";
    getchar();
}