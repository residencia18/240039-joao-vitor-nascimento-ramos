public class ListaDePacientes
{
    private List<Paciente> pacientes;

    public ListaDePacientes()
    {
        pacientes = new List<Paciente>();
    }

    public void AdicionarPaciente(Paciente paciente)
    {
        if (!VerificarCpfUnico(paciente.CPF))
        {
            throw new ArgumentException("CPF já está em uso por outro paciente.");
        }

        pacientes.Add(paciente);
    }

    private bool VerificarCpfUnico(string cpf)
    {
        foreach (var paciente in pacientes)
        {
            if (paciente.CPF == cpf)
            {
                return false; 
            }
        }
        return true; 
    }

}
