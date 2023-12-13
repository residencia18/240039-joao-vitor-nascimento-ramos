namespace SistemaMedico{

public class Medico : Pessoa
{

    private string crm;

    public string CRM
    {
        get { return crm; }
        set
        {
            if (Validacoes.ValidarCRM(value))
            {
                crm = value;
            }
            else
            {
                throw new ArgumentException("CRM inválido");
            }
        }
    }

    public Medico(string nome, DateTime dataNascimento, string cpf, string crm) 
        : base(nome, dataNascimento, cpf)
    {
        if (!Validacoes.ValidarCRM(crm)){
            throw new ArgumentException("CRM inválido");
        }   
        
        CRM = crm;
    }

    public override string ToString()
    {
        return $"Médico:\nNome: {Nome}\nData de Nascimento: {DataNascimento:dd/MM/yyyy}\nCPF: {CPF}\nCRM: {CRM}";
    }
}

}