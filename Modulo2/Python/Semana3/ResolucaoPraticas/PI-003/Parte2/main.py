from controle import carregar_funcionarios,reajusta_dez_porcento,listar_funcionarios

def main():
    funcionarios= carregar_funcionarios()
    listar_funcionarios(funcionarios)
    reajusta_dez_porcento(funcionarios)
    listar_funcionarios(funcionarios)
    

if __name__ == "__main__":
    main()

