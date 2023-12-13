public class Medico : Pessoa
{
    public string CRM { get; set; }

    public Medico(string nome, DateTime dataNascimento, string cpf, string crm) 
        : base(nome, dataNascimento, cpf)
    {
        if (!ValidarCRM(crm)){
            throw new ArgumentException("CRM inválido");
        }   
        
        CRM = crm;
    }

    private bool ValidarCRM(string crm)
    {
        foreach (char c in crm){

            if (!char.IsDigit(c))
            {
                return false;
            }
        
        }

        return true;
    }

    public override string ToString()
    {
        return $"Médico: {Nome}, CRM: {CRM}";
    }
}