using System.Text;

namespace SistemaMedico
{
    public class Atendimento
    {
        private DateTime dataHoraInicial;
        private string suspeitaInicial;
        private List<(Exame, string)> listaExamesResultado;
        private float valor;
        private DateTime? dataHoraFinal;
        private Medico medicoResponsavel;
        private Paciente pacienteAtendido;
        private string diagnosticoFinal;

        private bool atendimentoEmAberto;

        public DateTime DataHoraInicial
        {
            get { return dataHoraInicial; }
        }

        public string SuspeitaInicial
        {
            get { return suspeitaInicial; }
            private set { suspeitaInicial = value; }
        }

        public List<(Exame, string)> ListaExamesResultado
        {
            get { return listaExamesResultado; }
        }

        public float Valor
        {
            get { return valor; }
            private set { valor = value; }

        }

        public DateTime? DataHoraFinal
        {
            get { return dataHoraFinal; }
        }

        public Medico MedicoResponsavel
        {
            get { return medicoResponsavel; }
            private set { medicoResponsavel = value; }
        }

        public Paciente PacienteAtendido
        {
            get { return pacienteAtendido; }
            private set { pacienteAtendido = value; }
        }

        public string DiagnosticoFinal
        {
            get { return diagnosticoFinal; }
            private set { diagnosticoFinal = value; }
        }

        public bool AtendimentoEmAberto
        {
            get { return atendimentoEmAberto; }
            private set { atendimentoEmAberto = value; }
        }

        public Atendimento(Medico medico, Paciente paciente, string suspeitaInicial)
        {
            dataHoraInicial = DateTime.Now;
            SuspeitaInicial = suspeitaInicial;
            listaExamesResultado = new List<(Exame, string)>();
            MedicoResponsavel = medico;
            PacienteAtendido = paciente;
            valor = 0;
            atendimentoEmAberto = true;
        }

        public void AdicionarExameResultado(Exame exame, string resultado)
        {
            if (atendimentoEmAberto)
            {
                ListaExamesResultado.Add((exame, resultado));
                valor += exame.Valor;
            }
            else
            {
                throw new ArgumentException("Esse atendimento já foi encerrado!");
            }
        }

        public void FinalizaAtendimento(string diagnosticoFinal , float valorAtendimento)
        {

            dataHoraFinal = DateTime.Now;
            DiagnosticoFinal = diagnosticoFinal;
            atendimentoEmAberto = false;
            valor+=valorAtendimento;

        }

        public static void IniciarAtendimento(ListaDePacientes pacientes, ListaDeMedicos medicos, ListaDeAtendimentos atendimentos)
        {
            Console.Clear();

            Console.WriteLine("---- Iniciar Atendimento ----");

            Console.Write("CPF do Paciente: ");
            string? cpfPaciente = Console.ReadLine() ?? "";

            Console.Write("CRM do Médico: ");
            string? crmMedico = Console.ReadLine() ?? "";

            Paciente? paciente = ListaDePacientes.ObterPacientePorCPF(pacientes, cpfPaciente);
            Medico? medico = ListaDeMedicos.ObterMedicoPorCRM(medicos, crmMedico);

            if (paciente == null)
            {
                Console.WriteLine($"Paciente com CPF {cpfPaciente} não encontrado.");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }

            if (medico == null)
            {
                Console.WriteLine($"Médico com CRM {crmMedico} não encontrado.");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }

            if(paciente.CPF == medico.CPF){
                Console.WriteLine("Um médico não pode se auto-atender!");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }


            if (ListaDeAtendimentos.ObterAtendimentoAtivo(atendimentos, cpfPaciente, crmMedico) != null)
            {
                Console.WriteLine($"Existe um atendimento ativo do Médico {medico.Nome} para com o paciente {paciente.Nome}!");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }



            Console.Write("Suspeita Inicial: ");
            string? suspeitaInicial = Console.ReadLine() ?? "";

            try
            {
                Atendimento novoAtendimento = new Atendimento(medico, paciente, suspeitaInicial);
                atendimentos.AdicionarAtendimento(novoAtendimento);
                Console.WriteLine("Atendimento iniciado com sucesso!");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Erro ao iniciar atendimento: {ex.Message}");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

        public static void CadastraExameNoAtendimento(ListaDePacientes pacientes, ListaDeMedicos medicos, ListaDeAtendimentos atendimentos)
        {
            Console.Clear();

            Console.WriteLine("---- Cadastrar Exame no Atendimento ----");

            Console.Write("CPF do Paciente: ");
            string? cpfPaciente = Console.ReadLine() ?? "";

            Console.Write("CRM do Médico: ");
            string? crmMedico = Console.ReadLine() ?? "";

            Paciente? paciente = ListaDePacientes.ObterPacientePorCPF(pacientes, cpfPaciente);
            Medico? medico = ListaDeMedicos.ObterMedicoPorCRM(medicos, crmMedico);

            if (paciente == null)
            {
                Console.WriteLine($"Paciente com CPF {cpfPaciente} não encontrado.");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }

            if (medico == null)
            {
                Console.WriteLine($"Médico com CRM {crmMedico} não encontrado.");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }

            Atendimento? atendimento = ListaDeAtendimentos.ObterAtendimentoAtivo(atendimentos, cpfPaciente, crmMedico);

            if (atendimento == null)
            {
                Console.WriteLine($"Não foi encontrado um atendimento ativo do Médico {medico.Nome} com o paciente {paciente.Nome}.");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }

            Console.Write("Título do Exame: ");
            string? tituloExame = Console.ReadLine() ?? "";


            Console.Write("Local do Exame: ");
            string? localExame = Console.ReadLine() ?? "";

            Console.Write("Valor do Exame: ");
            if (float.TryParse(Console.ReadLine(), out float valorExame))
            {
                Console.Write("Descrição do Exame: ");
                string? descricaoExame = Console.ReadLine() ?? "";

                Exame novoExame = new Exame(tituloExame, valorExame, descricaoExame, localExame);


                Console.Write("Resultado do Exame: ");
                string? resultadoExame = Console.ReadLine() ?? "";

                try
                {
                    atendimento.AdicionarExameResultado(novoExame, resultadoExame);
                    Console.WriteLine("Exame cadastrado com sucesso no atendimento!");
                }
                catch (Exception ex)
                {
                    Console.WriteLine($"Erro ao cadastrar exame: {ex.Message}");
                }
            }
            else
            {
                Console.WriteLine("Valor do exame inválido. Tente novamente.");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

    public static void FinalizarAtendimentoAtivo(ListaDeAtendimentos atendimentos)
        {
            Console.Clear();

            Console.WriteLine("---- Finalizar Atendimento ----");

            Console.Write("CPF do Paciente: ");
            string? cpfPaciente = Console.ReadLine() ?? "";

            Console.Write("CRM do Médico: ");
            string? crmMedico = Console.ReadLine() ?? "";

            Atendimento? atendimento = ListaDeAtendimentos.ObterAtendimentoAtivo(atendimentos, cpfPaciente, crmMedico);

            if (atendimento == null)
            {
                Console.WriteLine($"Não foi encontrado um atendimento ativo do Médico com o paciente.");
                Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
                Console.ReadKey();
                return;
            }

            if (atendimento.AtendimentoEmAberto)
            {
                Console.Write("Diagnóstico Final: ");
                string? diagnosticoFinal = Console.ReadLine() ?? "";

                Console.Write("Valor do Atendimento: ");
                if (float.TryParse(Console.ReadLine(), out float valorAtendimento))
                {
                    try
                    {
                        atendimento.FinalizaAtendimento(diagnosticoFinal, valorAtendimento);
                        Console.WriteLine("Atendimento finalizado com sucesso!");
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine($"Erro ao finalizar atendimento: {ex.Message}");
                    }
                }
                else
                {
                    Console.WriteLine("Valor do atendimento inválido. Tente novamente.");
                }
            }
            else
            {
                Console.WriteLine("Esse atendimento já foi encerrado!");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

        public override string ToString()
        {
            StringBuilder sb = new StringBuilder();
            
            sb.AppendLine("---- Dados do Atendimento ----");
            sb.AppendLine($"Data e Hora Inicial: {DataHoraInicial}");
            sb.AppendLine($"Suspeita Inicial: {SuspeitaInicial}");
            
            if (atendimentoEmAberto)
            {
                sb.AppendLine("Status: Em Aberto");
            }
            else
            {
                sb.AppendLine("Status: Encerrado");
                sb.AppendLine($"Data e Hora Final: {DataHoraFinal}");
                sb.AppendLine($"Diagnóstico Final: {DiagnosticoFinal}");
                sb.AppendLine($"Valor do Atendimento: {Valor:C}");
            }
            
            sb.AppendLine($"Médico Responsável: {MedicoResponsavel.Nome} (CRM: {MedicoResponsavel.CRM})");
            sb.AppendLine($"Paciente Atendido: {PacienteAtendido.Nome} (CPF: {PacienteAtendido.CPF})");

            if (ListaExamesResultado.Count > 0)
            {
                sb.AppendLine("---- Exames Resultado ----");
                foreach (var (exame, resultado) in ListaExamesResultado)
                {
                    sb.AppendLine($"Título do Exame: {exame.Titulo}");
                    sb.AppendLine($"Resultado: {resultado}");
                    sb.AppendLine($"Valor do Exame: {exame.Valor:C}");
                    sb.AppendLine($"Local do Exame: {exame.Local}");
                    sb.AppendLine($"Descrição do Exame: {exame.Descricao}");
                    sb.AppendLine();
                }
            }

            return sb.ToString();
        }




    }
}