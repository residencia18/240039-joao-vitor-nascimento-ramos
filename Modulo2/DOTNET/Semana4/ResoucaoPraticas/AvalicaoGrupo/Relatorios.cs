    namespace SistemaMedico{
    public static class Relatorios
    {

        public static void ImprimeMedicosComIdadeEntre(List<Medico> medicos)
        {
            Console.Write("Idade Mínima: ");
            int idadeMin = int.Parse(Console.ReadLine() ?? "0");

            Console.Write("Idade Máxima: ");
            int idadeMax = int.Parse(Console.ReadLine() ?? "0");

            Console.WriteLine($"--------MEDICOS COM IDADE ENTRE {idadeMin} e {idadeMax}--------");
            foreach (var medico in MedicosComIdadeEntre(medicos,idadeMin, idadeMax))
            {
                Console.WriteLine(medico);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimePacientesComIdadeEntre(List<Paciente> pacientes)
        {
            Console.Write("Idade Mínima: ");
            int idadeMin = int.Parse(Console.ReadLine() ?? "0");

            Console.Write("Idade Máxima: ");
            int idadeMax = int.Parse(Console.ReadLine() ?? "0");

            Console.WriteLine($"--------PACIENTES COM IDADE ENTRE {idadeMin} e {idadeMax}--------");
            foreach (var paciente in PacientesComIdadeEntre(pacientes,idadeMin, idadeMax))
            {
                Console.WriteLine(paciente);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimePacientesComSexoInformado(List<Paciente> pacientes){
            Console.WriteLine($"--------PACIENTES COM SEXO INFORMADO--------");
            foreach (var paciente in PacientesComSexoInformado(pacientes))
            {
                Console.WriteLine(paciente);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimePacientesEmOrdemAfabetica(List<Paciente> pacientes){
            Console.WriteLine($"--------PACIENTES EM ORDEM ALFABETICA--------");
            foreach (var paciente in PacientesComSexoInformado(pacientes))
            {
                Console.WriteLine(paciente);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimePacientesComSintomaInformado(List<Paciente> pacientes){
            Console.WriteLine($"--------PACIENTES COM SEXO INFORMADO--------");
            foreach (var paciente in PacientesComSintomasInformado(pacientes))
            {
                Console.WriteLine(paciente);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimeAniversariantesDoMes(List<Medico> medicos,List<Paciente> pacientes)
        {
            Console.WriteLine("--------ANIVERSARIANTES DO MÊS--------");
            Console.Write("Informe o mês (1 a 12): ");
            
            if (int.TryParse(Console.ReadLine(), out int mes) && mes >= 1 && mes <= 12)
            {
                foreach (var pessoa in AniversariantesDoMes(medicos, pacientes, mes))
                {
                    Console.WriteLine(pessoa);
                }
            }
            else
            {
                Console.WriteLine("Mês inválido. Tente novamente.");
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimeAtendimentosEmAberto(List<Atendimento> atendimentos){
            Console.WriteLine($"--------ATENDIMENTOS EM ABERTO--------");
            foreach (var atendimento in AtendimentosEmAberto(atendimentos))
            {
                Console.WriteLine(atendimento);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimeMedicosComAtendimentoConcluido(List<Atendimento> atendimentos){
            Console.WriteLine($"--------MEDICOS COM ATENDIMENTOS CONCLUIDOS--------");
            foreach (var medico in MedicosComAtendimentoConcluido(atendimentos))
            {
                Console.WriteLine(medico);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimeAtendimentosComPalavraChave(List<Atendimento> atendimentos){
            Console.WriteLine("Digite a palavra-chave:");
            string? palavraChave = Console.ReadLine() ?? "";
            Console.WriteLine($"--------ATENDIMENTOS COM A PALAVRA CHAVE : {palavraChave}--------");
            foreach (var atendimento in AtendimentosComPalavraChave(atendimentos,palavraChave))
            {
                Console.WriteLine(atendimento);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        public static void ImprimeTop10ExamesMaisUtilizados(List<Atendimento> atendimentos){
            Console.WriteLine($"--------TOP 10 Exames mais utilizados--------");
            foreach (var exame in Top10ExamesMaisUtilizados(atendimentos))
            {
                Console.WriteLine(exame);
            }
            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Relatórios...");
            Console.ReadKey();
        }

        //------------Retornam Lista-----------//

        private static List<Medico> MedicosComIdadeEntre(List<Medico> medicos, int idadeMin, int idadeMax)
        {
            return medicos.Where(m =>
            {
                DateTime dataAtual = DateTime.Today;
                DateTime aniversarioEsteAno = m.DataNascimento.AddYears(dataAtual.Year - m.DataNascimento.Year);

                int idade = dataAtual.Year - m.DataNascimento.Year;

                if (DateTime.Compare(dataAtual, aniversarioEsteAno) < 0)
                {
                    idade--;
                }

                return idade >= idadeMin && idade <= idadeMax;
            }).ToList();
        }

        private static List<Paciente> PacientesComIdadeEntre(List<Paciente> pacientes, int idadeMin, int idadeMax)
        {
            return pacientes.Where(p =>
            {
                DateTime dataAtual = DateTime.Today;
                DateTime aniversarioEsteAno = p.DataNascimento.AddYears(dataAtual.Year - p.DataNascimento.Year);

                int idade = dataAtual.Year - p.DataNascimento.Year;

                if (DateTime.Compare(dataAtual, aniversarioEsteAno) < 0)
                {
                    idade--;
                }

                return idade >= idadeMin && idade <= idadeMax;
            }).ToList();
        }

        private static List<Paciente> PacientesComSexoInformado(List<Paciente> pacientes){
            return pacientes.Where(p => p.Sexo != "Não informado").ToList();
        }

        private static List<Paciente> PacientesEmOrdemAlfabetica(List<Paciente> pacientes){
            return pacientes.OrderBy(p => p.Nome).ToList();
        }

        private static List<Paciente> PacientesComSintomasInformado(List<Paciente> pacientes){
            return pacientes.Where(p => p.Sintomas.Count!=0).ToList();
        }

        private static List<Pessoa> AniversariantesDoMes(List<Medico> medicos, List<Paciente> pacientes, int mes)
        {
            IEnumerable<Pessoa> pessoas = medicos.Cast<Pessoa>().Concat(pacientes);
            return pessoas.Where(p => p.DataNascimento.Month == mes).ToList();
        }

        private static List<Atendimento> AtendimentosEmAberto(List<Atendimento> atendimentos){
            return atendimentos.Where(p => p.AtendimentoEmAberto).ToList().OrderByDescending(p => p.DataHoraInicial).ToList();
        }

        private static List<Medico> MedicosComAtendimentoConcluido(List<Atendimento> atendimentos)
        {
            var medicosOrdenados = atendimentos
                .Where(a => !a.AtendimentoEmAberto)
                .GroupBy(a => a.MedicoResponsavel)
                .Select(group => new
                {
                    Medico = group.Key,
                    AtendimentosConcluidos = group.Count()
                })
                .OrderByDescending(item => item.AtendimentosConcluidos)
                .Select(item => item.Medico)
                .ToList();

            return medicosOrdenados;
        }

        private static List<Atendimento> AtendimentosComPalavraChave(List<Atendimento> atendimentos, string palavraChave)
        {
            return atendimentos.Where(a => a.SuspeitaInicial?.Contains(palavraChave, StringComparison.OrdinalIgnoreCase) == true ||
            a.DiagnosticoFinal?.Contains(palavraChave, StringComparison.OrdinalIgnoreCase) == true).ToList();
        }

        private static List<Exame> Top10ExamesMaisUtilizados(List<Atendimento> atendimentos)
        {
            List<Exame> todosExames = atendimentos
                .SelectMany(a => a.ListaExamesResultado.Select(e => e.Item1))
                .ToList();

            var examesAgrupados = todosExames
                .GroupBy(exame => exame.Titulo)
                .Select(group => new
                {
                    Exame = group.First(), 
                    QuantidadeUtilizada = group.Count()
                })
                .OrderByDescending(item => item.QuantidadeUtilizada)
                .Take(10);

            List<Exame> top10Exames = examesAgrupados
                .Select(item => item.Exame)
                .ToList();

            return top10Exames;
        }

    }
}