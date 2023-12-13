using System.Text.RegularExpressions;

namespace SistemaMedico{

    public static class Validacoes
    {
        public static bool ValidarCPF(string cpf)
        {
            cpf = new string(cpf.Where(char.IsDigit).ToArray());

            if (cpf.Length != 11)
            {
                return false;
            }

            int soma = 0;
            for (int i = 0; i < 9; i++)
            {
                soma += int.Parse(cpf[i].ToString()) * (10 - i);
            }

            int resto = soma % 11;
            int digito1 = resto < 2 ? 0 : 11 - resto;

            soma = 0;
            for (int i = 0; i < 10; i++)
            {
                soma += int.Parse(cpf[i].ToString()) * (11 - i);
            }

            resto = soma % 11;
            int digito2 = resto < 2 ? 0 : 11 - resto;

            return digito1 == int.Parse(cpf[9].ToString()) && digito2 == int.Parse(cpf[10].ToString());
        }

        public static bool ValidarCRM(string crm)
        {
            string[] partes = crm.Split('/');
            
            if (partes.Length != 2)
            {
                return false;
            }

            string uf = partes[0];
            string numero = partes[1];

            if (!ValidarUF(uf))
            {
                return false;
            }

            if (!numero.All(char.IsDigit))
            {
                return false;
            }

            return true;
        }

        private static bool ValidarUF(string uf)
        {
            string[] ufsValidas = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };

            return Array.Exists(ufsValidas, element => element == uf);
        }

        public static bool ValidarDataNascimento(DateTime dataNascimento)
        {
            return dataNascimento <= DateTime.Now;
        }

        public static bool ValidarSexo(string sexo)
        {
            return sexo.ToLower() == "masculino" || sexo.ToLower() == "feminino";
        }

        public static bool ValidarNome(string nome)
        {
            // Verifica se a string contém apenas letras ou espaços
            return !string.IsNullOrEmpty(nome) && Regex.IsMatch(nome, @"^[a-zA-Z\s]+$");
        }

    }

}