from menu import Menu
from controle import carregar_produtos
def main():
    produtos = carregar_produtos()
    Menu(produtos)

if __name__ == "__main__":
    main()

