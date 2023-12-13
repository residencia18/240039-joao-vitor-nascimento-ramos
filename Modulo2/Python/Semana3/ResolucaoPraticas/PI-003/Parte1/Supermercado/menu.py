import os
from controle import inserir_produto, excluir_produto, listar_produtos, consultar_preco

def Menu(produtos):
    while True:
        os.system("cls" if os.name == "nt" else "clear")
        print("=== Sistema de Supermercado ===")
        print("1. Inserir novo produto")
        print("2. Excluir produto cadastrado")
        print("3. Listar todos os produtos")
        print("4. Consultar preço de um produto")
        print("0. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == "1":
            inserir_produto(produtos)
        elif opcao == "2":
            excluir_produto(produtos)
        elif opcao == "3":
            listar_produtos(produtos)
        elif opcao == "4":
            consultar_preco(produtos)
        elif opcao == "0":
            print("Saindo do programa. Até mais!")
            break
        else:
            print("Opção inválida. Tente novamente.")
