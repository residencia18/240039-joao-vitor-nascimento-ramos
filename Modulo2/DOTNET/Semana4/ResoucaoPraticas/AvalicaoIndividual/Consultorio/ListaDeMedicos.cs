public class ListaDeMedicos
{
    private List<Medico> medicos;

    public ListaDeMedicos()
    {
        medicos = new List<Medico>();
    }

    public void AdicionarMedico(Medico medico)
    {
        if (!VerificarCpfUnico(medico.CPF))
        {
            throw new ArgumentException("CPF já está em uso por outro médico.");
        }

        if (!VerificarCrmUnico(medico.CRM))
        {
            throw new ArgumentException("CRM já está em uso por outro médico.");
        }

        medicos.Add(medico);
    }

    private bool VerificarCpfUnico(string cpf)
    {
        foreach (var medico in medicos)
        {
            if (medico.CPF == cpf)
            {
                return false; 
            }
        }
        return true; 
    }

    private bool VerificarCrmUnico(string crm)
    {
        foreach (var medico in medicos)
        {
            if (medico.CRM == crm)
            {
                return false; 
            }
        }
        return true; 
    }

}