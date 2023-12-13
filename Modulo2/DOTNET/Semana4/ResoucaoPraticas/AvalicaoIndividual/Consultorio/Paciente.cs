public class Paciente : Pessoa
{
    private string sexo = "Não informado";
    private string sintomas = "Não informado";

    public string Sexo
    {
        get { return sexo; }
        set
        {
            if (IsSexo(value))
            {
                sexo = value;
            }
            else
            {
                throw new ArgumentException("Inválida inserção de sexo (Insira (masculino) ou (feminino))");
            }
        }
    }

    public string Sintomas
    {
        get { return sintomas; }
        set { sintomas = value; }
    }

    public Paciente(string nome, DateTime dataNascimento, string cpf, string sexo = "Não informado", string sintomas = "Não informado")
        : base(nome, dataNascimento, cpf)
    {
        if (sexo != "Não informado" && !IsSexo(sexo))
        {
            throw new ArgumentException("Inválida inserção de sexo (Insira (masculino) ou (feminino))");
        }

        Sexo = sexo;
        Sintomas = sintomas;
    }


    private bool IsSexo(string sexo)
    {
        return sexo == "masculino" || sexo == "feminino";
    }

    public override string ToString()
    {
        return $"Paciente: {Nome}, CPF: {CPF}, Sexo: {Sexo}, Sintomas: {sintomas}";
    }

}