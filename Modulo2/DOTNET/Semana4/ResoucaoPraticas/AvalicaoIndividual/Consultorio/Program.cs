using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {

    List<Medico> medicos = new List<Medico>();
    List<Paciente> pacientes = new List<Paciente>();
        int opcao;

        do
        {
            Console.WriteLine("----------- Menu -----------");
            Console.WriteLine("1. Inserir Médico");
            Console.WriteLine("2. Inserir Paciente");
            Console.WriteLine("3. Imprimir Médicos com idade entre");
            Console.WriteLine("4. Imprimir Pacientes com idade entre");
            Console.WriteLine("5. Imprimir Pacientes com sexo informado");
            Console.WriteLine("6. Imprimir Pacientes em ordem alfabética");
            Console.WriteLine("7. Imprimir Pacientes com sintomas informados");
            Console.WriteLine("8. Imprimir Aniversariantes do Mês");
            Console.WriteLine("0. Sair");
            Console.Write("Escolha uma opção: ");

            if (int.TryParse(Console.ReadLine(), out opcao))
            {
                switch (opcao)
                {
                    case 1:
                    try{
                        InserirMedico(medicos);
                    }catch(Exception e ){
                        LogError(e, "Shape processing failed.");
                    }
                        break;
                    case 2:
                    try{
                        InserirPaciente(pacientes);
                    }catch(Exception e){
                        LogError(e, "Shape processing failed.");
                    }
                        break;
                    case 3:
                        Console.Write("Idade Mínima: ");
                        int idadeMinMedico = int.Parse(Console.ReadLine());
                        Console.Write("Idade Máxima: ");
                        int idadeMaxMedico = int.Parse(Console.ReadLine());
                        Relatorios.ImprimeMedicosComIdadeEntre(medicos, idadeMinMedico, idadeMaxMedico);
                        break;
                    case 4:
                        Console.Write("Idade Mínima: ");
                        int idadeMinPaciente = int.Parse(Console.ReadLine());
                        Console.Write("Idade Máxima: ");
                        int idadeMaxPaciente = int.Parse(Console.ReadLine());
                        Relatorios.ImprimePacientesComIdadeEntre(pacientes, idadeMinPaciente, idadeMaxPaciente);
                        break;
                    case 5:
                        Relatorios.ImprimePacientesComSexoInformado(pacientes);
                        break;
                    case 6:
                        Relatorios.ImprimePacientesEmOrdemAfabetica(pacientes);
                        break;
                    case 7:
                        Relatorios.ImprimePacientesComSintomaInformado(pacientes);
                        break;
                    case 8:
                        Console.Write("Mês (1 a 12): ");
                        int mesAniversario = int.Parse(Console.ReadLine());
                        Relatorios.ImprimeAniversariantesDoMes(medicos, pacientes, mesAniversario);
                        break;
                    case 0:
                        Console.WriteLine("Programa encerrado.");
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
            }
            else
            {
                Console.WriteLine("Entrada inválida. Tente novamente.");
            }

            Console.WriteLine();
        } while (opcao != 0);
    }

    static void InserirMedico(List<Medico> medicos)
    {
        Console.Write("Nome do médico: ");
        string nome = Console.ReadLine();
        Console.Write("Data de Nascimento (YYYY-MM-DD): ");
        DateTime dataNascimento = DateTime.Parse(Console.ReadLine());
        Console.Write("CPF: ");
        string cpf = Console.ReadLine();
        Console.Write("CRM: ");
        string crm = Console.ReadLine();

        medicos.Add(new Medico(nome, dataNascimento, cpf, crm));
        Console.WriteLine("Médico inserido com sucesso.");
    }

    static void InserirPaciente(List<Paciente> pacientes)
    {
        Console.Write("Nome do paciente: ");
        string nome = Console.ReadLine();
        Console.Write("Data de Nascimento (YYYY-MM-DD): ");
        DateTime dataNascimento = DateTime.Parse(Console.ReadLine());
        Console.Write("CPF: ");
        string cpf = Console.ReadLine();
        Console.Write("Sexo (masculino - feminino)");
        string sexo = Console.ReadLine();
        Console.Write("Sintomas : ");
        string sintomas = Console.ReadLine();

        pacientes.Add(new Paciente(nome, dataNascimento, cpf, sexo, sintomas));
        Console.WriteLine("Paciente inserido com sucesso.");
    }

    
}


