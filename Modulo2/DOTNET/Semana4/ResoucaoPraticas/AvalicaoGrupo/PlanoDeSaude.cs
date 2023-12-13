namespace SistemaMedico{
    public class PlanoDeSaude
    {
        private string titulo;
        private double valorPorMes;

        public string Titulo
        {
            get { return titulo; }
            set { titulo = value; }
        }

        public double ValorPorMes
        {
            get { return valorPorMes; }
            set { valorPorMes = value; }
        }

        public PlanoDeSaude(string titulo, double valorPorMes)
        {
            Titulo = titulo;
            ValorPorMes = valorPorMes;
        }

        public override string ToString()
        {
            return $"Plano de Saúde: {Titulo}\nValor por mês: {ValorPorMes:C}";
        }
    }

}
