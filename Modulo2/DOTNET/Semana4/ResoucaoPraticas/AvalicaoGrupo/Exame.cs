namespace SistemaMedico
{
    public class Exame
    {
        private string? titulo;
        private float valor;
        private string? descricao;
        private string? local;

        public Exame(string? titulo, float valor, string? descricao, string? local)
        {
            Titulo = titulo;
            Valor = valor;
            Descricao = descricao;
            Local = local;
        }

        public string? Titulo
        {
            get { return titulo; }
            set { titulo = value; }
        }

        public float Valor
        {
            get { return valor; }
            set
            {
                if (float.TryParse(value.ToString(), out float result))
                {
                    valor = result;
                }
                else
                {
                    throw new ArgumentException("O valor deve ser numérico.");
                }
            }
        }

        public string? Descricao
        {
            get { return descricao; }
            set { descricao = value; }
        }

        public string? Local
        {
            get { return local; }
            set { local = value; }
        }

        public override string ToString()
        {
            return $"Exame:\nTítulo: {Titulo ?? "Não informado"}\nValor: {Valor:C2}\nDescrição: {Descricao ?? "Não informada"}\nLocal: {Local ?? "Não informado"}";
        }
    }
}
