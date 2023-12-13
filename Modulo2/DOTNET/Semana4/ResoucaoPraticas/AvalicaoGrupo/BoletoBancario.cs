namespace SistemaMedico
{
    public class BoletoBancario : IPagamento
    {
        private string descricao;
        private double valorBruto;
        private double desconto;
        private DateTime dataHora;

        public string Descricao
        {
            get { return descricao; }
            set { descricao = value; }
        }

        public double ValorBruto
        {
            get { return valorBruto; }
            set { valorBruto = value; }
        }

        public double Desconto
        {
            get { return desconto; }
            set { desconto = value; }
        }

        public DateTime DataHora
        {
            get { return dataHora; }
            set { dataHora = value; }
        }

        public double CalcularValorComDesconto()
        {
            return valorBruto - desconto;
        }

        public void ImprimirRecibo()
        {
            Console.WriteLine($"Recibo para pagamento com boleto bancário:");
            Console.WriteLine($"Descrição: {descricao}");
            Console.WriteLine($"Valor Bruto: {valorBruto:C}");
            Console.WriteLine($"Desconto: {desconto:C}");
            Console.WriteLine($"Valor Final: {CalcularValorComDesconto():C}");
            Console.WriteLine($"Data e Hora: {dataHora}");
        }

        public void RealizarPagamento()
        {
            Console.WriteLine("Pagamento via boleto bancário realizado com sucesso!");
        }

        public override string ToString()
        {
            return $"Boleto bancário:\nDescrição: {Descricao}\nValor Bruto: {ValorBruto:C}\nDesconto: {Desconto:C}\nData e Hora: {DataHora}";
        }
    }
}
