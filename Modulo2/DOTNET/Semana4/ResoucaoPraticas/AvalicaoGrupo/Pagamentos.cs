namespace SistemaMedico
{
    public static class Pagamentos
    {
        public static void RealizarPagamento(ListaDePacientes pacientes)
        {
            Console.Clear();

            Console.WriteLine("---- Realizar Pagamento ----");

            Console.Write("CPF do Paciente: ");
            string cpf = Console.ReadLine() ?? "";

            Paciente paciente = ListaDePacientes.ObterPacientePorCPF(pacientes, cpf);

            if (paciente != null)
            {
                Console.WriteLine("Selecione o método de pagamento:");
                Console.WriteLine("1 - Cartão de Crédito");
                Console.WriteLine("2 - Boleto Bancário");
                Console.WriteLine("3 - Dinheiro em Espécie");

                int opcao;
                if (int.TryParse(Console.ReadLine(), out opcao) && opcao >= 1 && opcao <= 3)
                {
                    IPagamento pagamento;

                    switch (opcao)
                    {
                        case 1:
                            pagamento = CriarPagamentoCartao(paciente);
                            break;
                        case 2:
                            pagamento = CriarPagamentoBoleto(paciente);
                            break;
                        case 3:
                            pagamento = CriarPagamentoDinheiro(paciente);
                            break;
                        default:
                            Console.WriteLine("Opção inválida. Pagamento não realizado.");
                            return;
                    }

                    paciente.AdicionarPagamento(pagamento);

                    Console.WriteLine($"Pagamento realizado com sucesso para o paciente:\n{paciente}");
                }
                else
                {
                    Console.WriteLine("Opção inválida. Pagamento não realizado.");
                }
            }
            else
            {
                Console.WriteLine("Paciente não encontrado.");
            }

            Console.WriteLine("Pressione qualquer tecla para voltar ao Menu Principal...");
            Console.ReadKey();
        }

        private static IPagamento CriarPagamentoCartao(Paciente paciente)
        {
            Console.WriteLine("---- Criar Pagamento com Cartão de Crédito ----");

            Console.Write("Descrição: ");
            string descricao = Console.ReadLine() ?? "";

            double valorBruto = paciente.PlanoDeSaude.ValorPorMes;

            double desconto = 0;

            return new CartaoDeCredito
            {
                Descricao = descricao,
                ValorBruto = valorBruto,
                Desconto = desconto,
                DataHora = DateTime.Now
            };
        }

        private static IPagamento CriarPagamentoBoleto(Paciente paciente)
        {
            Console.WriteLine("---- Criar Pagamento com Boleto Bancário ----");

            Console.Write("Descrição: ");
            string descricao = Console.ReadLine() ?? "";

            double valorBruto = paciente.PlanoDeSaude.ValorPorMes;

            double desconto = valorBruto * 0.1;

            return new BoletoBancario
            {
                Descricao = descricao,
                ValorBruto = valorBruto,
                Desconto = desconto,
                DataHora = DateTime.Now
            };
        }

        private static IPagamento CriarPagamentoDinheiro(Paciente paciente)
        {
            Console.WriteLine("---- Criar Pagamento em Dinheiro ----");

            Console.Write("Descrição: ");
            string descricao = Console.ReadLine() ?? "";

            double valorBruto = paciente.PlanoDeSaude.ValorPorMes;

            double desconto = valorBruto * 0.2;

            return new DinheiroEspecie
            {
                Descricao = descricao,
                ValorBruto = valorBruto,
                Desconto = desconto,
                DataHora = DateTime.Now
            };
        }
    }
}
