import os

class Tarefa:
    quantidadeDeTarefasCriadas = 0
    
    def __init__(self, descricao, concluida=False):
        self.id = Tarefa.quantidadeDeTarefasCriadas + 1 
        self.descricao = descricao
        self.concluida = concluida
        Tarefa.quantidadeDeTarefasCriadas += 1
    
    def __str__(self):
        status = "[x]" if self.concluida else "[ ]"
        return f"{self.id}. {self.descricao} {status}"

class ListaDeTarefas:
    def __init__(self):
        self.tarefas = []
        self.arquivo_tarefas = "tarefas.txt"

        # Carregar tarefas do arquivo
        self.carregar_tarefas()

    def carregar_tarefas(self):
        if os.path.exists(self.arquivo_tarefas):
            with open(self.arquivo_tarefas, "r") as arquivo:
                linhas = arquivo.readlines()

            for linha in linhas:
                partes = linha.strip().split("|")
                id_tarefa = int(partes[0])
                descricao = partes[1]
                concluida = partes[2] == "True"
                tarefa = Tarefa(descricao, concluida)
                tarefa.id = id_tarefa  
                self.tarefas.append(tarefa)

    def salvar_tarefas(self):
        with open(self.arquivo_tarefas, "w") as arquivo:
            for tarefa in self.tarefas:
                arquivo.write(f"{tarefa.id}|{tarefa.descricao}|{tarefa.concluida}\n")

    def listar_tarefas(self):
        tarefas_ordenadas = sorted(self.tarefas, key=lambda tarefa: tarefa.id)
        for tarefa in tarefas_ordenadas:
            print(tarefa)

    def adicionar_tarefa(self, descricao):
        if not descricao[0].isupper():
            print("A descrição da tarefa deve começar com maiúscula.")
            return

        nova_tarefa = Tarefa(descricao)
        self.tarefas.append(nova_tarefa)
        self.salvar_tarefas()
        print("Tarefa registrada!!!")

    def marcar_como_concluida(self, id_tarefa):
        for tarefa in self.tarefas:
            if tarefa.id == id_tarefa:
                tarefa.concluida = True
                self.salvar_tarefas()
                print("Tarefa marcada como realizada!")
                return

        print("Tarefa não encontrada ou já foi realizada.")

    def editar_tarefa(self, id_tarefa, nova_descricao):
        if not nova_descricao[0].isupper():
            print("A descrição da tarefa deve começar com maiúscula.")
            return

        for tarefa in self.tarefas:
            if tarefa.id == id_tarefa:
                tarefa.descricao = nova_descricao
                self.salvar_tarefas()
                print("Tarefa editada com sucesso!")
                return

        print("Tarefa não encontrada.")

def exibir_menu():
    print("\n----- MENU -----")
    print("1. Listar Tarefas")
    print("2. Adicionar Tarefa")
    print("3. Marcar como Concluída")
    print("4. Editar Tarefa")
    print("0. Sair")

lista_tarefas = ListaDeTarefas()

while True:
    exibir_menu()
    escolha = input("Escolha uma opção: ")

    if escolha == "1":
        lista_tarefas.listar_tarefas()
    elif escolha == "2":
        descricao = input("Digite a descrição da nova tarefa: ")
        lista_tarefas.adicionar_tarefa(descricao)
    elif escolha == "3":
        id_tarefa = int(input("Digite o ID da tarefa a ser marcada como concluída: "))
        lista_tarefas.marcar_como_concluida(id_tarefa)
    elif escolha == "4":
        id_tarefa = int(input("Digite o ID da tarefa a ser editada: "))
        nova_descricao = input("Digite a nova descrição da tarefa: ")
        lista_tarefas.editar_tarefa(id_tarefa, nova_descricao)
    elif escolha == "0":
        print("Saindo do programa. Até mais!")
        break
    else:
        print("Opção inválida. Tente novamente.")

