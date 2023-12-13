from abc import ABC, abstractmethod

class Data:
    
    def __init__(self, dia = 1, mes = 1, ano = 2000):
        if dia < 1 or dia > 31:
            raise ValueError("Dia inválido")
        if mes < 1 or mes > 12:
            raise ValueError("Mês inválido")
        if ano < 2000 or ano > 2100:
            raise ValueError("Ano inválido")
        self.__dia = dia
        self.__mes = mes
        self.__ano = ano

    @property
    def dia(self):
        return self.__dia
    
    @dia.setter
    def dia(self, dia):
        if dia < 1 or dia > 31:
            raise ValueError("Dia inválido")
        self.__dia = dia

    @property
    def mes(self):
        return self.__mes
    
    @mes.setter
    def mes(self, mes):
        if mes < 1 or mes > 12:
            raise ValueError("Mês inválido")
        self.__mes = mes

    @property
    def ano(self):
        return self.__ano
    
    @ano.setter
    def ano(self, ano):
        if ano < 2000 or ano > 2100:
            raise ValueError("Ano inválido")
        self.__ano = ano
    
    def __str__(self):
        return f"{self.__dia:02d}/{self.__mes:02d}/{self.__ano}"


    def __eq__(self, outraData):
        return  self.__dia == outraData.__dia and \
                self.__mes == outraData.__mes and \
                self.__ano == outraData.__ano
    
    def __lt__(self, outraData):
        if self.__ano < outraData.__ano:
            return True
        elif self.__ano == outraData.__ano:
            if self.__mes < outraData.__mes:
                return True
            elif self.__mes == outraData.__mes:
                if self.__dia < outraData.__dia:
                    return True
        return False
    
    def __gt__(self, outraData):
        if self.__ano > outraData.__ano:
            return True
        elif self.__ano == outraData.__ano:
            if self.__mes > outraData.__mes:
                return True
            elif self.__mes == outraData.__mes:
                if self.__dia > outraData.__dia:
                    return True
        return False
    

class AnaliseDados(ABC): 

    @abstractmethod
    def __init__(self, tipoDeDados):
        self.__tipoDeDados = tipoDeDados

    @abstractmethod
    def entradaDeDados(self):
        pass

    @abstractmethod
    def mostraMediana(self):
        pass
    
    @abstractmethod
    def mostraMenor(self):
        pass

    @abstractmethod
    def mostraMaior(self):
        pass
    
    @abstractmethod
    def listarEmOrdem(self):
        pass
    
    @abstractmethod
    def adicionar(self):
        pass

class ListaNomes(AnaliseDados):
    
    def __init__(self):
        super().__init__(type("String"))
        self.__lista = []        

    def entradaDeDados(self):
        
        while True:
            try:
                qtdElementos = int(input("Digite o numero de elementos da lista de nomes : "))
            except Exception as ex:
                print(f"Erro : {ex}")
                
            if qtdElementos <= 0:
                print("A Lista precisa ter tamanho mínimo de 1")
            else:
                break
            
        for i in range(1,qtdElementos+1):
            while True: 
                try:
                    valor = input(f"Digite o {i} elemento : ")
                except Exception as ex:
                    print(f"Erro : {ex}")
                if valor=="":
                    print("Digite um nome válido!")
                    continue
                else:
                    self.__lista.append(valor)
                    break
                
    def adicionar(self):
        while True:
            valor = input(f"Digite um nome : ")
            if valor=="":
                print("Digite um nome válido!")
                continue
            else:
                self.__lista.append(valor)
                break

    def mostraMediana(self):
        tamanhoDaLista= len(self.__lista)
        listaTemporaria = sorted(self.__lista)
        if tamanhoDaLista%2==0:
            indice = int(tamanhoDaLista / 2 - 1)
            print(f"Mediana é : {listaTemporaria[indice]}")
        else:
            indice = int(tamanhoDaLista / 2)
            print(f"Mediana é : {listaTemporaria[indice]}")


    def mostraMenor(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Alfabeticamente o menor é : {listaTemporaria[0]}")
        else:
            print("Lista está vazia!")

    def mostraMaior(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Alfabeticamente o maior é : {listaTemporaria[tamanhoDaLista-1]}")
        else:
            print("Lista está vazia!")
            
    def listarEmOrdem(self):
        listaOrdenada = sorted(self.__lista)
        print(listaOrdenada)

    def __str__(self):
        for i in self.__lista:
            print(i)

    def __iter__(self):
        return iter(self.__lista)
	
class ListaDatas(AnaliseDados):
        
    def __init__(self):
        super().__init__(type(Data))
        self.__lista = []        
    
    def entradaDeDados(self):
        
        while True:
            try:
                qtdElementos = int(input("Digite o numero de elementos da lista de datas : "))
            except Exception as ex:
                print(f"Erro : {ex}")
            if qtdElementos <= 0:
                print("A Lista precisa ter tamanho mínimo de 1")
            else:
                break       
             
        for i in range(1,qtdElementos+1):
            while True: 
                dia = int(input(f"Digite o o dia {i} data : "))
                mes = int(input(f"Digite o o mês {i} data : "))
                ano = int(input(f"Digite o o ano {i} data : "))
                try:
                    dataNova = Data(dia,mes,ano)
                except ValueError as erro:
                    print(f"Erro : {erro}")
                else:
                    self.__lista.append(dataNova)
                    break
    
    def adicionar(self):
        while True: 
            try:
                dia = int(input(f"Digite o o dia : "))
                mes = int(input(f"Digite o o mês : "))
                ano = int(input(f"Digite o o ano : "))
                dataNova = Data(dia,mes,ano)
            except ValueError as erro:
                print(f"Erro : {erro}")
            except Exception as ex:
                print(f"Erro : {ex}")
            else:
                self.__lista.append(dataNova)
                break
    
    def mostraMediana(self):    
        tamanhoDaLista= len(self.__lista)
        listaTemporaria = sorted(self.__lista)
        if tamanhoDaLista%2==0:
            indice = int(tamanhoDaLista / 2 - 1)
            print(f"Mediana é : {listaTemporaria[indice]}")
        else:
            indice = int(tamanhoDaLista / 2)
            print(f"Mediana é : {listaTemporaria[indice]}")
     
    def mostraMenor(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Menor Data é : {listaTemporaria[0]}")
        else:
            print("Lista está vazia!")
    
    def mostraMaior(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Maior Data é : {listaTemporaria[tamanhoDaLista-1]}")
        else:
            print("Lista está vazia!")
    
    def listarEmOrdem(self):
        listaOrdenada = sorted(self.__lista)
        for data in listaOrdenada:
            print(data)
            
    def modificarDatas(self):
        datas_anteriores_2019 = filter(lambda data: data.ano < 2019, self.__lista)

        for data in datas_anteriores_2019:
            data.dia = 1

    def __str__(self):
        result = ""
        for i in self.__lista:
            result += str(i) + "\n"
        return result


    def __iter__(self):
        return iter(self.__lista)

class ListaSalarios(AnaliseDados):

    def __init__(self):
        super().__init__(type(float))
        self.__lista = []        

    def entradaDeDados(self):
        while True:
            try:
                qtdElementos = int(input("Digite o numero de elementos da lista de salários : "))
            except Exception as ex:
                print(f"Erro : {ex}")
            if qtdElementos <= 0:
                print("A Lista precisa ter tamanho mínimo de 1")
            else:
                break       
                
        for i in range(1,qtdElementos+1):
            while True:
                salario = float(input(f"Digite o {i} salário : "))
                if salario <= 0.0:
                    print("O salário deve ser maior que 0!")
                else:
                    self.__lista.append(salario)
                    break
    
    def adicionar(self):
        while True:
            try:
                salario = float(input(f"Digite o salário : "))
            except Exception as ex:
                print(f"Erro : {ex}")
            if salario <= 0.0:
                print("O salário deve ser maior que 0!")
            else:
                self.__lista.append(salario)
                break

    def mostraMediana(self):
        tamanhoDaLista= len(self.__lista)
        listaTemporaria = sorted(self.__lista)
        if tamanhoDaLista%2==0:
            indiceMenor = int(tamanhoDaLista / 2 - 1)
            indiceMaior =  indiceMenor +1
            mediana = (listaTemporaria[indiceMenor] + listaTemporaria[indiceMaior])/2
            print(f"Mediana é : {mediana}")
        else:
            indice = int(tamanhoDaLista / 2)
            print(f"Mediana é : {listaTemporaria[indice]}")

    def mostraMenor(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Menor salário é : {listaTemporaria[0]}")
        else:
            print("Lista está vazia!")

    def mostraMaior(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Maior salário é : {listaTemporaria[tamanhoDaLista-1]}")
        else:
            print("Lista está vazia!")
    
    def listarEmOrdem(self):
        listaOrdenada = sorted(self.__lista)
        print(listaOrdenada)
        
    def calcularFolha(self):
        folhaPagamento = sum(map(lambda x: x * 1.1, self.__lista))
        return folhaPagamento
    
    def __str__(self):
        for i in self.__lista:
            print(f"{i:.2}")

    def __iter__(self):
        return iter(self.__lista)
    
    

class ListaIdades(AnaliseDados):
    
    def __init__(self):
        super().__init__(type(int))
        self.__lista = []        
    
    def entradaDeDados(self):
        while True:
            try:
                qtdElementos = int(input("Digite o numero de elementos da lista de idades : "))
            except Exception as ex:
                print(f"Erro : {ex}")
                
            if qtdElementos <= 0:
                print("A Lista precisa ter tamanho mínimo de 1")
            else:
                break       
                
        for i in range(1,qtdElementos+1):
            while True:
                idade = int(input(f"Digite a {i} idade : "))
                if idade <= 0:
                    print("A idade deve ser maior que 0!")
                else:
                    self.__lista.append(idade)
                    break
                
    def adicionar(self):
        while True:
            try:
                idade = int(input(f"Digite a idade : "))
            except Exception as ex:
                print(f"Erro : {ex}")
                
            if idade <= 0:
                print("A idade deve ser maior que 0!")
            else:
                self.__lista.append(idade)
                break
    
    def mostraMediana(self):
        tamanhoDaLista= len(self.__lista)
        listaTemporaria = sorted(self.__lista)
        if tamanhoDaLista%2==0:
            indiceMenor = int(tamanhoDaLista / 2 - 1)
            indiceMaior =  indiceMenor +1
            mediana = (listaTemporaria[indiceMenor] + listaTemporaria[indiceMaior])/2
            print(f"Mediana é : {mediana}")
        else:
            indice = int(tamanhoDaLista / 2)
            print(f"Mediana é : {listaTemporaria[indice]}")
    
    def mostraMenor(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Menor idade é : {listaTemporaria[0]}")
        else:
            print("Lista está vazia!")
    
    def mostraMaior(self):
        tamanhoDaLista= len(self.__lista)
        if tamanhoDaLista > 0:
            listaTemporaria = sorted(self.__lista)
            print(f"Maior idade é : {listaTemporaria[tamanhoDaLista-1]}")
        else:
            print("Lista está vazia!")

    def listarEmOrdem(self):
        listaOrdenada = sorted(self.__lista)
        print(listaOrdenada)

    def __str__(self):
        for i in self.__lista:
            print(i)

    def __iter__(self):
        return iter(self.__lista)
    
def percorrerListasNomesSalarios(nomes, salarios):
    for nome, salario in zip(nomes, salarios):
        print(f"{nome} - {salario}")
    
def main():
    nomes = ListaNomes()
    datas = ListaDatas()
    salarios = ListaSalarios()
    idades = ListaIdades()

    while True:
        print("\n-------- MENU --------")
        print("1. Incluir um nome na lista de nomes")
        print("2. Incluir um salário na lista de salários")
        print("3. Incluir uma data na lista de datas")
        print("4. Incluir uma idade na lista de idades")
        print("5. Percorrer as listas de nomes e salários")
        print("6. Calcular o valor da folha com um reajuste de 10%")
        print("7. Modificar o dia das datas anteriores a 2019")
        print("8. Sair")

        opcao = input("Escolha uma opção (1-8): ")

        if opcao == "1":
            nomes.adicionar()
        elif opcao == "2":
            salarios.adicionar()
        elif opcao == "3":
            datas.adicionar()
        elif opcao == "4":
            idades.adicionar()
        elif opcao == "5":
            percorrerListasNomesSalarios(nomes,salarios)
        elif opcao == "6":
            folhaPagamento = salarios.calcularFolha()
            print(f"Valor da folha com reajuste de 10%: {folhaPagamento:.2f}")
        elif opcao == "7":
            datas.modificarDatas()
            print("Datas modificadas com sucesso!")
        elif opcao == "8":
            print("Saindo do programa. Até mais!")
            break
        else:
            print("Opção inválida. Tente novamente.")

    

    print("Fim do teste!!!")

if __name__ == "__main__":
    main()