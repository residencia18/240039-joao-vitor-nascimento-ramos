namespace SistemaMedico
{
    public class App
    {
        private ListaDeMedicos medicos;
        private ListaDePacientes pacientes;
        private ListaDeAtendimentos atendimentos;
        private ListaDePlanosDeSaude planosDeSaude;

        public App()
        {
            medicos = new ListaDeMedicos();
            pacientes = new ListaDePacientes();
            atendimentos = new ListaDeAtendimentos();
            planosDeSaude = new ListaDePlanosDeSaude();
        }

        public void Run()
        {
            medicos.ListaMedicos = Persistencia.CarregarMedicos();
            pacientes.ListaPacientes = Persistencia.CarregarPacientes();
            atendimentos.ListaAtendimentos = Persistencia.CarregarAtendimento();
            planosDeSaude.PlanosDeSaude = Persistencia.CarregarPlanosDeSaude();
            MenuPrincipal();
        }

        private void MenuPrincipal()
        {   
            string? opcao;
            do{
                Console.Clear();
                Console.WriteLine("---- Menu Principal ----");
                Console.WriteLine("1. Cadastro");
                Console.WriteLine("2. Atendimentos");
                Console.WriteLine("3. Relatórios");
                Console.WriteLine("4. Salvar Dados");
                Console.WriteLine("5. Realizar Pagamento");
                Console.WriteLine("0. Sair");
                Console.Write("Escolha uma opção: ");
                opcao = Console.ReadLine() ?? "";

                switch (opcao)
                {
                    case "1":
                        MenuCadastro();
                        break;
                    case "2":
                        MenuAtendimentos();
                        break;
                    case "3":
                        MenuRelatorios();
                        break;
                    case "4":
                        try{
                            Persistencia.GravarMedicos(medicos);
                            Persistencia.GravarPacientes(pacientes);
                            Persistencia.GravarAtendimentos(atendimentos);
                            Persistencia.GravarPlanosDeSaude(planosDeSaude);
                        }catch (Exception ex){
                            Console.WriteLine($"Erro ao Salvar dados: {ex.Message}");  
                        }
                        break;
                    case "5":
                        Pagamentos.RealizarPagamento(pacientes);
                        break;
                    case "0":
                        Console.WriteLine("Saindo do sistema. Até mais!");
                        Environment.Exit(0);
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
            }while(opcao != "0");
        }

        private void MenuCadastro()
        {
            string? opcao;
            do{
                Console.Clear();
                Console.WriteLine("---- Menu de Cadastro ----");
                Console.WriteLine("1. Cadastrar Médico");
                Console.WriteLine("2. Cadastrar Paciente");
                Console.WriteLine("3. Cadastrar Plano De Saúde");
                Console.WriteLine("0. Voltar ao Menu Principal");
                Console.Write("Escolha uma opção: ");
                opcao = Console.ReadLine() ?? "";

                switch (opcao)
                {
                    case "1":
                        Cadastros.CadastrarMedico(medicos);
                        break;
                    case "2":
                        Cadastros.CadastrarPaciente(pacientes,planosDeSaude);
                        break;
                    case "3":
                        Cadastros.CadastrarPlanoDeSaude(planosDeSaude);
                        break;
                    case "0":
                        MenuPrincipal();
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
            }while(opcao != "0");
        }

        private void MenuAtendimentos()
        {

            string? opcao;
            do{
                Console.Clear();
                Console.WriteLine("---- Menu de Atendimentos ----");
                Console.WriteLine("1. Iniciar Atendimento");
                Console.WriteLine("2. Inserir exames ao atendimento");
                Console.WriteLine("3. Finalizar Atendimento");
                Console.WriteLine("0. Voltar ao Menu Principal");
                Console.Write("Escolha uma opção: ");
                opcao = Console.ReadLine() ?? "";

                switch (opcao)
                {
                    case "1":
                        Atendimento.IniciarAtendimento(pacientes,medicos,atendimentos);
                        break;
                    case "2":
                        Atendimento.CadastraExameNoAtendimento(pacientes,medicos,atendimentos);
                        break;
                    case "3":
                        Atendimento.FinalizarAtendimentoAtivo(atendimentos);
                        break;                    
                    case "0":
                        MenuPrincipal();
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
            }while(opcao != "0");

        }

        private void MenuRelatorios()
        {

            string? opcao;
            do{
                Console.Clear();
                Console.WriteLine("---- Menu de Relatórios ----");
                Console.WriteLine("1. Médicos com idade entre dois valores");
                Console.WriteLine("2. Pacientes com idade entre dois valores");
                Console.WriteLine("3. Pacientes do sexo informado pelo usuário");
                Console.WriteLine("4. Pacientes em ordem alfabética");
                Console.WriteLine("5. Pacientes cujos sintomas contenha texto informado pelo usuário");
                Console.WriteLine("6. Médicos e Pacientes aniversariantes do mês informado");
                Console.WriteLine("7. Atendimentos em aberto (sem finalizar) em ordem decrescente pela data de início.");
                Console.WriteLine("8. Médicos em ordem decrescente da quantidade de atendimentos concluídos");
                Console.WriteLine("9. Atendimentos cuja suspeita ou diagnóstico final contenham determinada palavra.");
                Console.WriteLine("10. Os 10 exames mais utilizados nos atendimentos.");
                Console.WriteLine("0. Voltar ao Menu Principal");
                Console.Write("Escolha uma opção: ");
                opcao = Console.ReadLine() ?? "";

                switch (opcao)
                {
                    case "1":
                        Relatorios.ImprimeMedicosComIdadeEntre(medicos.ListaMedicos);
                        break;
                    case "2":
                        Relatorios.ImprimePacientesComIdadeEntre(pacientes.ListaPacientes);
                        break;
                    case "3":
                        Relatorios.ImprimePacientesComSexoInformado(pacientes.ListaPacientes);
                        break;
                    case "4":
                        Relatorios.ImprimePacientesEmOrdemAfabetica(pacientes.ListaPacientes);
                        break;
                    case "5":
                        Relatorios.ImprimePacientesComSintomaInformado(pacientes.ListaPacientes);
                        break;
                    case "6":
                        Relatorios.ImprimeAniversariantesDoMes(medicos.ListaMedicos, pacientes.ListaPacientes);
                        break;
                    case "7":
                        Relatorios.ImprimeAtendimentosEmAberto(atendimentos.ListaAtendimentos);
                        break;
                    case "8":
                        Relatorios.ImprimeMedicosComAtendimentoConcluido(atendimentos.ListaAtendimentos);
                        break;
                    case "9":
                        Relatorios.ImprimeAtendimentosComPalavraChave(atendimentos.ListaAtendimentos);
                        break;
                    case "10":
                        Relatorios.ImprimeTop10ExamesMaisUtilizados(atendimentos.ListaAtendimentos);
                        break;
                    
                    case "0":
                        MenuPrincipal();
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
            }while(opcao != "0");
        }
    }
}
