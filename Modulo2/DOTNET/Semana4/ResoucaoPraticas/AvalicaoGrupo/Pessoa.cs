namespace SistemaMedico
{
    public abstract class Pessoa
    {
        private string nome; 
        private DateTime dataNascimento;
        private string cpf;

        public string Nome
        {
            get { return nome; }
            set
            {
                if (Validacoes.ValidarNome(value))
                {
                    nome = value;
                }
                else
                {
                    throw new ArgumentException("Nome deve conter apenas letras e espaços");
                }
            }
        }

        public string CPF
        {
            get { return cpf; }
            set
            {
                if (Validacoes.ValidarCPF(value))
                {
                    cpf = value;
                }
                else
                {
                    throw new ArgumentException("CPF inválido");
                }
            }
        }

        public DateTime DataNascimento{
            get{return dataNascimento;}
            set
            {
                if (Validacoes.ValidarDataNascimento(value))
                {
                    dataNascimento = value;
                }
                else
                {
                    throw new ArgumentException("Data de nascimento não pode ser no futuro", nameof(dataNascimento));
                }
            }
        }

        public Pessoa(string nome, DateTime dataNascimento, string cpf)
        {
            if (string.IsNullOrWhiteSpace(nome))
            {
                throw new ArgumentException("Nome não pode ser vazio ou nulo", nameof(nome));
            }

            Nome = nome;

            if (!Validacoes.ValidarDataNascimento(dataNascimento))
            {
                throw new ArgumentException("Data de nascimento não pode ser no futuro", nameof(dataNascimento));
            }

            DataNascimento = dataNascimento;

            cpf = cpf ?? throw new ArgumentNullException(nameof(cpf), "CPF não pode ser nulo");

            if (!Validacoes.ValidarCPF(cpf))
            {
                throw new ArgumentException("CPF inválido", nameof(cpf));
            }

            CPF = cpf;
        }
    }
}
