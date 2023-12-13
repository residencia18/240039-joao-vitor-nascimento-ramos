import csv ,os

produto_existe = lambda codigo, produtos: any(produto["codigo"] == codigo for produto in produtos)

def inserir_produto(produtos):
    codigo = input("Informe o código do produto (13 dígitos): ")
    if len(codigo) != 13 or not codigo.isdigit():
        print("Código inválido. Deve conter 13 dígitos numéricos.")
        input("Pressione <Enter> para continuar!")
        return
    
    if produto_existe(codigo, produtos):
        print("Produto com esse código já cadastrado.")
        input("Pressione <Enter> para continuar!")
        return

    nome = input("Informe o nome do produto (começando com letra maiúscula): ")
    if not nome[0].isupper():
        print("Nome inválido. Deve começar com letra maiúscula.")
        input("Pressione <Enter> para continuar!")
        return

    preco = input("Informe o preço do produto: ")
    try:
        preco = float(preco)
    except ValueError:
        print("Preço inválido. Deve ser um número válido.")
        input("Pressione <Enter> para continuar!")
        return

    produto = {"codigo": codigo, "nome": nome, "preco": preco}
    produtos.append(produto)
    salvar_produtos(produtos)
    print("Produto adicionado com sucesso!")
    input("Pressione <Enter> para continuar!")


def excluir_produto(produtos):
    codigo = input("Informe o código do produto a ser excluído: ")
    for produto in produtos:
        if produto["codigo"] == codigo:
            produtos.remove(produto)
            print("Produto excluído com sucesso!")
            input("Pressione <Enter> para continuar!")
            salvar_produtos(produtos)
            return
    print("Produto não encontrado.")
    input("Pressione <Enter> para continuar!")
    
def listar_produtos(produtos):
    if not produtos:
        print("Nenhum produto cadastrado.")
        input("Pressione <Enter> para continuar!")
        return

    print("Lista de produtos:")
    for produto in produtos:
        print(f"Código: {produto['codigo']}, Nome: {produto['nome']}, Preço: R${produto['preco']:.2f}")
    input("Pressione <Enter> para continuar!")

def consultar_preco(produtos):
    codigo = input("Informe o código do produto para consultar o preço: ")
    for produto in produtos:
        if produto["codigo"] == codigo:
            print(f"O preço do produto {produto['nome']} é R${produto['preco']:.2f}")
            input("Pressione <Enter> para continuar!")
            return
    print("Produto não encontrado.")
    input("Pressione <Enter> para continuar!")
    
def listar_produtos(produtos):
    if not produtos:
        print("Nenhum produto cadastrado.")
        input("Pressione <Enter> para continuar!")
        return

    print("Lista de produtos:")
    for i, produto in enumerate(produtos, start=1):
        print(f"Código: {produto['codigo']}, Nome: {produto['nome']}, Preço: R${produto['preco']:.2f}")

        if i % 10 == 0:
            input("Pressione <Enter> para continuar!")
            os.system("cls" if os.name == "nt" else "clear")

            
    input("Pressione <Enter> para continuar!")
    
    
def salvar_produtos(produtos):
    with open("BancoDeDados/produtos.csv", "w", newline="") as file:
        fieldnames = ["codigo", "nome", "preco"]
        writer = csv.DictWriter(file, fieldnames=fieldnames)

        writer.writeheader()
        writer.writerows(produtos)
        
def carregar_produtos():
    try:
        with open("BancoDeDados/produtos.csv", "r", newline="") as file:
            reader = csv.DictReader(file)
            produtos = list(reader)
            for produto in produtos:
                produto['preco'] = float(produto['preco'])
            return produtos
    except FileNotFoundError:
        return []
