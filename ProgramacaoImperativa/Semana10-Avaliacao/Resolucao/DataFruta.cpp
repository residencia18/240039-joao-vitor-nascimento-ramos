#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Data {
    int dia, mes, ano;

public:
    Data(int _dia, int _mes, int _ano) : dia(_dia), mes(_mes), ano(_ano) {}
    Data(){

    }

	static int compara(Data d1, Data d2) {
		if (d1.getAno() != d2.getAno() ) {
			return d1.getAno() < d2.getAno() ;
		}
		if (d1.getMes() != d2.getMes()) {
			return d1.getMes() < d2.getMes();
		}
		return d1.getDia() < d2.getDia();
	}

    Data& operator=(const Data& outra) {
        if (this != &outra) { 
            dia = outra.dia;
            mes = outra.mes;
            ano = outra.ano;
        }
        return *this;  
    }

	int getDia(){
		return this->dia;
	}

	int getMes(){
		return this->mes;
	}

	int getAno(){
		return this->ano;
	}

    static bool ehAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    static bool ehDataValida(int dia, int mes, int ano) {
        if (ano >= 1900 && ano <= 9999) {
            if (mes >= 1 && mes <= 12) {
                int diasNoMes[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                if (mes == 2 && ehAnoBissexto(ano)) {
                    diasNoMes[2] = 29;
                }
                return (dia >= 1 && dia <= diasNoMes[mes]);
            }
        }
    return false;
    }

    string toString() const {
        return to_string(dia) + "/" + to_string(mes) + "/" + to_string(ano);
    }
};

class Lista {
public:
    virtual void entradaDeDados() = 0;
    virtual void mostraMediana() = 0;
    virtual void mostraMenor() = 0;
    virtual void mostraMaior() = 0;
	virtual void listarEmOrdem()= 0;
	virtual void listarEmQuantidade(int n)= 0;

};

class ListaNomes : public Lista {
    vector<string> lista;

private:

    static bool ehNomeValido(const string& nome) {
        for (char c : nome) {
            if (!isalpha(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
    static bool compararStrings(const string& a, const string& b) {
        string aSemEspacos;
        string bSemEspacos;

        for (char ch : a) {
            if (!isspace(ch)) {
                aSemEspacos += tolower(ch);
            }
        }

        for (char ch : b) {
            if (!isspace(ch)) {
                bSemEspacos += tolower(ch);
            }
        }

        return aSemEspacos < bSemEspacos;
    }

public:

	vector<string>* getLista(){
		return &(this->lista);
	}

    void entradaDeDados() override {
        cout << "\n----LISTA DE NOMES----\n" << endl;
        int qtd;
        do{
            cout << "\tInforme o numero de elementos da lista: ";
            cin >> qtd;
            cin.ignore(); 
            if(qtd<1){
				cout << "\n\tOps, quantidade de elementos do vetor incorreto!...";
				cout << "\n\tPressione qualquer tecla para continuar...";
				cin.get();
            }
        }while(qtd<1);
        string nome;
        for (int i = 0; i < qtd; i++) {
            do{
				cout << "\n\tInforme a " << i + 1 << "º nome: ";
                getline(cin, nome);
                if(!ehNomeValido(nome)){
                    cout << "Nome inválido" << endl;
                }
                }while(!ehNomeValido(nome));
            getLista()->push_back(nome);
        }
    }

    void mostraMediana() override {
        vector<string> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), compararStrings);
        string nomeMediana;
        if(listaParaOrdenar.size()%2==0){
            nomeMediana = listaParaOrdenar.at(listaParaOrdenar.size() / 2 -1);
        }else{
            nomeMediana = listaParaOrdenar.at(listaParaOrdenar.size() / 2 );
        }
		cout << "\n\tMediana de nomes : " << nomeMediana << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();    
    }

    void mostraMenor() override {
        vector<string> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), compararStrings);
        string primeiroNome = listaParaOrdenar.front();
		cout << "\nPrimeiro dos nomes : " << primeiroNome << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
    }

    void mostraMaior() override {
        vector<string> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), compararStrings);
        string ultimoNome = listaParaOrdenar.back();
		cout << "\nUltimo dos nomes : " << ultimoNome << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();   
    }

	void listarEmOrdem() override{
        vector<string> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), compararStrings);
        cout << "\nLista de Nomes ordenada : " << endl;
		for(auto it=listaParaOrdenar.begin() ; it!=listaParaOrdenar.end() ; it++){
			cout << *it << endl;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void listarEmQuantidade(int quant) override{
		int i = 0;
        cout << endl << quant << " primeiros nomes do vetor" << endl;
		for(auto it=getLista()->begin() ; i < quant && it!=getLista()->end() ; it++){
			cout << *it << endl;
			i++;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

};

class ListaDatas : public Lista {
    vector<Data> lista;

public:

	vector<Data>* getLista(){
		return &(this->lista);
	}

    void entradaDeDados() override {
        cout << "\n----LISTA DE DATAS----\n" << endl;
        int qtd;
        do{
            cout << "\n\tInforme o numero de elementos da lista: ";
            cin >> qtd;
            cin.get(); 
            if(qtd<1){
				cout << "\n\tOps, quantidade de elementos do vetor incorreto!...";
				cout << "\n\tPressione qualquer tecla para continuar...";
				cin.get();
            }
        }while(qtd<1);
        int dia, mes, ano;
        for (int i = 0; i < qtd; i++) {
            do{
				cout << "\n\tInforme a " << i + 1 << "º data: \n";
                cout << "\tDigite o dia: ";
                cin >> dia;
                cin.get(); 
                cout << "\tDigite o mes: ";
                cin >> mes;
                cin.get(); 
                cout << "\tDigite o ano: ";
                cin >> ano;
                cin.get(); 
                if(!Data::ehDataValida(dia,mes,ano)){
                    cout << "Data inválida" << endl;
                }
            }while(!Data::ehDataValida(dia,mes,ano));
            Data data(dia, mes, ano);
            lista.push_back(data);
        }
    }

    void mostraMediana() override {
        vector<Data> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), Data::compara);
        Data dataMediana;

        if(listaParaOrdenar.size() % 2 == 0) {
            int posMediana = listaParaOrdenar.size() / 2 - 1;
            dataMediana = listaParaOrdenar[posMediana];
        } else {
            int posMediana = listaParaOrdenar.size() / 2;
            dataMediana = listaParaOrdenar[posMediana];
        }
        cout << "\n\tMediana de datas: " << dataMediana.toString() << endl;
        cout << "\n\tPressione qualquer tecla para continuar...";
        cin.get();
    }

    void mostraMenor() override {
        vector<Data> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), Data::compara);
		cout << "\n\tMenor das datas : " << (listaParaOrdenar.front()).toString() << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
    }

    void mostraMaior() override {
        vector<Data> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), Data::compara);
		cout << "\n\tMaior das datas : " << (listaParaOrdenar.back()).toString() << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
    }

	void listarEmOrdem() override{
        vector<Data> listaParaOrdenar = *getLista();
        sort(listaParaOrdenar.begin(), listaParaOrdenar.end(), Data::compara);
        cout << "\nLista de datas ordenada : " << endl;
		for(auto it=listaParaOrdenar.begin() ; it!=listaParaOrdenar.end() ; it++){
			cout << it->toString() << endl;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void listarEmQuantidade(int quant) override{
		int i = 0;
        cout << endl << quant << " primeiras datas do vetor" << endl;
		for(auto it=getLista()->begin() ; i < quant && it!=getLista()->end() ; it++){
			cout << it->toString() << endl;
			i++;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

};

class ListaSalarios : public Lista
{
private:
	vector<float> lista;

	static bool compara(float a, float b)
	{
		return a < b;
	}

public:
	vector<float> *getLista()
	{
		return &(lista);
	}

	/*
	O metodo abaixo pergunta ao usuarios quantos
	elementos, existir na lista e depois
	solicita a digitar de cada um deles
	*/
	void entradaDeDados() override
	{
        cout << "\n----LISTA DE SALARIOS----\n" << endl;
		int n = 0;
		float salario = 0;

		do
		{
			cout << "\n\tInforme a quantidade de elementos do vetor: ";
			cin >> n;
			cin.get();

			if (n <= 0)
			{
				cout << "\n\tOps, quantidade de elementos do vetor incorreto!...";
				cout << "\n\tPressione qualquer tecla para continuar...";
				cin.get();
			}

		} while (n <= 0);

		for (int i = 0; i < n; i++)
		{
			do
			{
				cout << "\n\tInforme o " << i + 1 << "º salário: ";
				cin >> salario;
				cin.get();

				if (salario <= 0)
				{
					cout << "\n\tOps, salário invalido!...";
					cout << "\n\tPressione qualquer tecla para continuar...";
					cin.get();
				}
			} while (salario <= 0);

			getLista()->push_back(salario);
		}
	}

	void mostraMediana() override
	{
		vector<float> aux;
		aux = *getLista();
		sort(aux.begin(), aux.end(), compara);
		float mediana;

		if (aux.size() % 2 == 0)
		{
			float posMedianaEsquerda, posMedianaDireita;
			float medianaEsquerda, medianaDireita;
			posMedianaEsquerda = aux.size() / 2;
			posMedianaDireita = aux.size() / 2 + 1;

			medianaEsquerda = aux.at(posMedianaEsquerda - 1);
			medianaDireita = aux.at(posMedianaDireita - 1);
			mediana = (medianaDireita + medianaEsquerda) / 2;
		}
		else
		{
			float pos = aux.size() / 2;
			mediana = aux.at(pos);
		}
		cout << "\n\tMediana salários: " << mediana << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void mostraMenor() override
	{
		vector<float> auxMenor;
		auxMenor = *getLista();
		sort(auxMenor.begin(), auxMenor.end(), compara);
		float menor;

		menor = auxMenor.front();

		cout << "\n\tMenor salário: " << menor << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void mostraMaior() override
	{
		vector<float> auxMaior;
		auxMaior = *getLista();
		sort(auxMaior.begin(), auxMaior.end(), compara);
		float maior;

		maior = auxMaior.back();
		cout << "\n\tMaior salário: " << maior << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void listarEmOrdem() override
	{
		vector<float> salarioOrdenado;
		salarioOrdenado = *getLista();
		sort(salarioOrdenado.begin(), salarioOrdenado.end(), compara);
        cout << "\nLista de Salarios ordenada : " << endl;
		for (auto it = salarioOrdenado.begin(); it != salarioOrdenado.end(); it++)
		{
			cout << "\n\t" << *it;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void listarEmQuantidade(int quant) override
	{
		int i = 0;
        cout << endl << quant << " primeiros salarios do vetor" << endl;

		for (auto it = getLista()->begin(); i < quant && it != getLista()->end(); it++)
		{
			cout << "\n\t" << *it;
			i++;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}
};

class ListaIdades : public Lista
{

private:
	vector<int> lista;

	static bool compara(int a, int b)
	{
		return a < b;
	}

public:
	vector<int> *getLista()
	{
		return &(lista);
	}

	/*
O metodo abaixo pergunta ao usuarios quantos
elementos existi na lista e depois
solicita a digitar de cada um deles
*/
	void entradaDeDados() override
	{
        cout << "\n----LISTA DE IDADES----\n" << endl;
		int n = 0;
		int idade = 0;

		do
		{
			cout << "\n\tInforme a quantidade de elementos do vetor: ";
			cin >> n;
			cin.get();

			if (n <= 0)
			{
				cout << "\n\tOps, quantidade de elementos do vetor incorreto!...";
				cout << "\n\tPressione qualquer tecla para continuar...";
				cin.get();
			}

		} while (n <= 0);

		for (int i = 0; i < n; i++)
		{
			do
			{
				cout << "\n\tInforme a " << i + 1 << "º idade: ";
				cin >> idade;
				cin.get();

				if (idade <= 0)
				{
					cout << "\n\tOps, idade invalida!...";
					cout << "\n\tPressione qualquer tecla para continuar...";
					cin.get();
				}

			} while (idade <= 0);

			getLista()->push_back(idade);
		}
	}

	void mostraMediana() override
	{
		vector<int> aux;
		aux = *getLista();
		sort(aux.begin(), aux.end(), compara);
		int mediana;

		if (aux.size() % 2 == 0)
		{
			int posMedianaEsquerda, posMedianaDireita;
			int medianaEsquerda, medianaDireita;
			posMedianaEsquerda = aux.size() / 2;
			posMedianaDireita = aux.size() / 2 + 1;

			medianaEsquerda = aux.at(posMedianaEsquerda - 1);
			medianaDireita = aux.at(posMedianaDireita - 1);
			mediana = (medianaDireita + medianaEsquerda) / 2;
		}
		else
		{
			int pos = aux.size() / 2;
			mediana = aux.at(pos);
		}
		cout << "\n\tMediana de idades : " << mediana << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void mostraMenor() override
	{
		vector<int> auxMenor;
		auxMenor = *getLista();
		sort(auxMenor.begin(), auxMenor.end(), compara);
		int menor;

		menor = auxMenor.front();

		cout << "\n\tMenor idade: " << menor << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void mostraMaior() override
	{
		vector<int> auxMaior;
		auxMaior = *getLista();
		sort(auxMaior.begin(), auxMaior.end(), compara);
		int maior;

		maior = auxMaior.back();
		cout << "\n\tMaior idade: " << maior << endl;
		cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void listarEmOrdem() override
	{
		vector<int> ordenado;
		ordenado = *getLista();
		sort(ordenado.begin(), ordenado.end(), compara);
        cout << "\nLista de Idades ordenada : " << endl;
		for (auto it = ordenado.begin(); it != ordenado.end(); it++)
		{
			cout << "\n\t" << *it;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}

	void listarEmQuantidade(int quant) override
	{
		int i = 0;
        cout << endl << quant << " primeiras idades do vetor" << endl;
		for (auto it = getLista()->begin(); i < quant && it != getLista()->end(); it++)
		{
			cout << "\n\t" << *it;
			i++;
		}
        cout << "\n\tPressione qualquer tecla para continuar...";
		cin.get();
	}
};

int main()
{
	vector<Lista *> listaDeListas;
	int quant = 2;

	ListaSalarios listaSalarios;
	listaSalarios.entradaDeDados();
	listaDeListas.push_back(&listaSalarios);

	ListaIdades listaIdades;
	listaIdades.entradaDeDados();
	listaDeListas.push_back(&listaIdades);

    ListaNomes listaNomes;
    listaNomes.entradaDeDados();
    listaDeListas.push_back(&listaNomes);

    ListaDatas ListaDatas;
    ListaDatas.entradaDeDados();
    listaDeListas.push_back(&ListaDatas);

	for (Lista *l : listaDeListas)
	{   
        system("clear || cls");
		l->mostraMediana();
		l->mostraMenor();
		l->mostraMaior();
		l->listarEmOrdem();
		l->listarEmQuantidade(quant);
	}
}
