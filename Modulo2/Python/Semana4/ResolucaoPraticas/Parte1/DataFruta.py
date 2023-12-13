
from abc import ABC, abstractmethod
import os

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
        return "{}/{}/{}".format(self.__dia, self.__mes, self.__ano)

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

class ListaNomes(AnaliseDados):
    
    def __init__(self):
        super().__init__(type("String"))
        self.__lista = []        

    def entradaDeDados(self):
        
        while True:
            qtdElementos = int(input("Digite o numero de elementos da lista de nomes : "))
            if qtdElementos <= 0:
                print("A Lista precisa ter tamanho mínimo de 1")
            else:
                break
            
        for i in range(1,qtdElementos+1):
            while True: 
                valor = input(f"Digite o {i} elemento : ")
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

    def __str__(self):
        for i in self.__lista:
            print(i)
	
class ListaDatas(AnaliseDados):
        
    def __init__(self):
        super().__init__(type(Data))
        self.__lista = []        
    
    def entradaDeDados(self):
        
        while True:
            qtdElementos = int(input("Digite o numero de elementos da lista de datas : "))
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
    
    
    def __str__(self):
        for i in self.__lista:
            print(i)

class ListaSalarios(AnaliseDados):

    def __init__(self):
        super().__init__(type(float))
        self.__lista = []        

    def entradaDeDados(self):

        while True:
            qtdElementos = int(input("Digite o numero de elementos da lista de salários : "))
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
    
    def __str__(self):
        for i in self.__lista:
            print(i)

class ListaIdades(AnaliseDados):
    
    def __init__(self):
        super().__init__(type(int))
        self.__lista = []        
    
    def entradaDeDados(self):
        while True:
            qtdElementos = int(input("Digite o numero de elementos da lista de idades : "))
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
            print(f"Maior salário é : {listaTemporaria[tamanhoDaLista-1]}")
        else:
            print("Lista está vazia!")

    def __str__(self):
        for i in self.__lista:
            print(i)

def main():
    nomes = ListaNomes()
    datas = ListaDatas()
    salarios = ListaSalarios()
    idades = ListaIdades()

    listaListas = [nomes, datas, salarios, idades]

    for lista in listaListas:
        lista.entradaDeDados()
        lista.mostraMediana()
        lista.mostraMenor()
        lista.mostraMaior()
        print("___________________")

    print("Fim do teste!!!")

if __name__ == "__main__":
    main()