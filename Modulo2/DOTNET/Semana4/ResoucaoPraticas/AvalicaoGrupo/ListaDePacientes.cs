namespace SistemaMedico{

    public class ListaDePacientes
    {
    private List<Paciente> pacientes;

    public ListaDePacientes()
    {
        pacientes = new List<Paciente>();
    }

    public List<Paciente> ListaPacientes{
        get{ return pacientes;}
            set{pacientes = value;}
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

    public static Paciente? ObterPacientePorCPF(ListaDePacientes listaDePacientes, string cpfPaciente){
        return listaDePacientes.pacientes.FirstOrDefault(p => p.CPF == cpfPaciente);
    }


    }
}