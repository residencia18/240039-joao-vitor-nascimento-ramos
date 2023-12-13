using System.Globalization;

namespace SistemaMedico
{
    public static class Cadastros
    {
        public static void CadastrarMedico(ListaDeMedicos medicos)
        {
            Console.Clear();

            Console.WriteLine("---- Cadastro de Médico ----");

            Console.Write("Nome: ");
            string? nome = Console.ReadLine() ?? "";

            Console.Write("Data de Nascimento (DD/MM/AAAA): ");
            if (DateTime.TryParseExact(Console.ReadLine(), "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out DateTime dataNascimento))
            {
                Console.Write("CPF: ");
                string? cpf = Console.ReadLine() ?? "";

                Console.Write("CRM: ");
                string? crm = Console.ReadLine() ?? "";

                try
                {
                    Medico novoMedico = new Medico(nome, dataNascimento, cpf, crm);
                    medicos.AdicionarMedico(novoMedico);
                    Console.WriteLine("Médico cadastrado com sucesso!");
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Erro ao cadastrar médico: {ex.Message}");
                }
            }
            else
            {
                Console.WriteLine("Formato de data inválido. Tente novamente.");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

        public static void CadastrarPaciente(ListaDePacientes pacientes, ListaDePlanosDeSaude planosDeSaude)
        {
            Console.Clear();

            Console.WriteLine("---- Cadastro de Pacientes ----");

            Console.Write("Nome: ");
            string nome = Console.ReadLine() ?? "";

            Console.Write("Data de Nascimento (DD/MM/AAAA): ");
            if (DateTime.TryParseExact(Console.ReadLine(), "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out DateTime dataNascimento))
            {
                Console.Write("CPF: ");
                string? cpf = Console.ReadLine() ?? "";

                Console.Write("Sexo (opcional - pressione Enter para pular): ");
                string? sexo = Console.ReadLine();

                Console.Write("Plano de Saúde: ");
                string nomePlano = Console.ReadLine() ?? "";

                PlanoDeSaude plano = planosDeSaude.ObterPlanoPorNome(nomePlano);

                try
                {
                    if (plano != null)
                    {
                        Paciente novoPaciente = string.IsNullOrWhiteSpace(sexo)
                            ? new Paciente(nome, dataNascimento, cpf, plano)
                            : new Paciente(nome, dataNascimento, cpf, plano , sexo);

                        Console.WriteLine("Adicione os sintomas do paciente. Digite '0' para parar.");
                        do
                        {
                            Console.Write("Sintoma: ");
                            string? sintoma = Console.ReadLine() ?? "";

                            if (sintoma.ToLower() == "0")
                                break;

                            novoPaciente.AdicionarSintoma(sintoma);

                            Console.WriteLine("Sintoma adicionado com sucesso!");
                        } while (true);

                        pacientes.AdicionarPaciente(novoPaciente);
                        Console.WriteLine("Paciente cadastrado com sucesso!");
                    }
                    else
                    {
                        Console.WriteLine("Plano de Saúde não encontrado. O paciente não será cadastrado.");
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Erro ao cadastrar paciente: {ex.Message}");
                }
            }
            else
            {
                Console.WriteLine("Formato de data inválido. Tente novamente.");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

        public static void CadastrarPlanoDeSaude(ListaDePlanosDeSaude planosDeSaude)
        {
            Console.Clear();

            Console.WriteLine("---- Cadastro de Plano de Saúde ----");

            Console.Write("Título: ");
            string titulo = Console.ReadLine() ?? "";

            Console.Write("Valor por Mês: ");
            if (double.TryParse(Console.ReadLine(), NumberStyles.Currency, CultureInfo.CurrentCulture, out double valorPorMes))
            {
                try
                {
                    PlanoDeSaude novoPlano = new PlanoDeSaude(titulo, valorPorMes);
                    planosDeSaude.AdicionarPlanoDeSaude(novoPlano);
                    Console.WriteLine("Plano de Saúde cadastrado com sucesso!");
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Erro ao cadastrar Plano de Saúde: {ex.Message}");
                }
            }
            else
            {
                Console.WriteLine("Formato de valor inválido. Tente novamente.");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

    }

}