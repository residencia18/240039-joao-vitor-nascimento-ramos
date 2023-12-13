using System;
using System.Collections.Generic;

namespace SistemaMedico
{
    public class Paciente : Pessoa
    {
        private string sexo = "NI";
        private List<string> sintomas = new List<string>();
        private List<IPagamento> pagamentos = new List<IPagamento>();
        private PlanoDeSaude planoDeSaude;

        public string Sexo
        {
            get { return sexo; }
            set
            {
                if (Validacoes.ValidarSexo(value))
                {
                    sexo = value;
                }
                else
                {
                    throw new ArgumentException("Inválida inserção de sexo (Insira (masculino) ou (feminino))");
                }
            }
        }

        public List<string> Sintomas
        {
            get { return sintomas; }
            set { sintomas = value; }
        }

        public List<IPagamento> Pagamentos
        {
            get { return pagamentos; }
            set { pagamentos = value; }
        }

        public PlanoDeSaude PlanoDeSaude
        {
            get { return planoDeSaude; }
            set { planoDeSaude = value; }
        }

        public void AdicionarSintoma(string sintoma)
        {
            if (string.IsNullOrWhiteSpace(sintoma))
            {
                throw new ArgumentException("O sintoma não pode ser vazio ou nulo", nameof(sintoma));
            }

            sintomas.Add(sintoma);
        }

        public void AdicionarPagamento(IPagamento pagamento)
        {
            pagamentos.Add(pagamento);
        }
        public Paciente(string nome, DateTime dataNascimento, string cpf, PlanoDeSaude planoDeSaude, string? sexo = "NI")
            : base(nome, dataNascimento, cpf)
        {
            if (!sexo.Equals("NI") && !Validacoes.ValidarSexo(sexo))
            {
                throw new ArgumentException("Inválida inserção de sexo (Insira (masculino) ou (feminino))");
            }

            Sintomas = new List<string>();
            PlanoDeSaude = planoDeSaude;
        }

        public override string ToString()
        {
            string sintomasStr = Sintomas.Count > 0 ? string.Join(", ", Sintomas) : "Não informado";
            string planoStr = PlanoDeSaude.ToString();

            string pagamentosStr = Pagamentos.Count > 0
                ? "\nPagamentos:\n" + string.Join("\n", Pagamentos.Select(p => p.ToString()))
                : "Sem pagamentos";

            return $"Paciente:\nNome: {Nome}\nData de Nascimento: {DataNascimento:dd/MM/yyyy}\nCPF: {CPF}\nSexo: {Sexo}\nSintomas: {sintomasStr}\nPlano de Saúde: {planoStr}{pagamentosStr}";
        }

    }
}
