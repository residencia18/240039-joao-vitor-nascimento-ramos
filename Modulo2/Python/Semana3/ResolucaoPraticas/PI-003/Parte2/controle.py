import csv

def carregar_funcionarios():
    try:
        with open("BancoDeDados/funcionarios.csv", "r", newline="") as file:
            reader = csv.DictReader(file)
            funcionarios = list(reader)
            for funcionario in funcionarios:
                funcionario['ano_de_nascimento'] = int(funcionario['ano_de_nascimento'])
                funcionario['ano_de_admissão'] = int(funcionario['ano_de_admissão'])
                funcionario['salario'] = float(funcionario['salario'])
            return funcionarios
    except FileNotFoundError:
        return []
    
def salvar_funcionarios(funcionarios):

    with open("BancoDeDados/funcionarios.csv", "w", newline="") as file:
        fieldnames = ["nome", "sobrenome", "ano_de_nascimento","RG","ano_de_admissão","salario"]
        writer = csv.DictWriter(file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(funcionarios)
    
def reajusta_dez_porcento(funcionarios):
    for funcionario in funcionarios:
        funcionario['salario'] *=1.1
    salvar_funcionarios(funcionarios)
    
def listar_funcionarios(funcionarios):
    print(f"-----------Lista de funcionarios-----------")
    for i, funcionario in enumerate(funcionarios, start=1):
        print(f"-----------Funcionario {i}-----------")
        print(f"Nome : {funcionario['nome']} ")
        print(f"Sobrenome : {funcionario['sobrenome']} ")
        print(f"Ano de nascimento : {funcionario['ano_de_nascimento']} ")
        print(f"RG : {funcionario['RG']} ")
        print(f"Ano de admissão : {funcionario['ano_de_admissão']} ")
        print(f"Salário : {funcionario['salario']:.2f} ")
