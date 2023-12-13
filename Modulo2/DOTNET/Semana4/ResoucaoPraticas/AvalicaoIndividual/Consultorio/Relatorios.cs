using System.Linq;
using System.Collections.Generic;
public static class Relatorios
{

    public static void ImprimeMedicosComIdadeEntre(List<Medico> medicos, int idadeMin, int idadeMax){
        Console.WriteLine($"--------MEDICOS COM IDADE ENTRE {idadeMin} e {idadeMax}--------");
        foreach (var medico in MedicosComIdadeEntre(medicos, idadeMin, idadeMax))
        {
            Console.WriteLine(medico);
        }
    }

    public static void ImprimePacientesComIdadeEntre(List<Paciente> pacientes, int idadeMin, int idadeMax){
        Console.WriteLine($"--------PACIENTES COM IDADE ENTRE {idadeMin} e {idadeMax}--------");
        foreach (var paciente in PacientesComIdadeEntre(pacientes, idadeMin, idadeMax))
        {
            Console.WriteLine(paciente);
        }
    }

    public static void ImprimePacientesComSexoInformado(List<Paciente> pacientes){
        Console.WriteLine($"--------PACIENTES COM SEXO INFORMADO--------");
        foreach (var paciente in PacientesComSexoInformado(pacientes))
        {
            Console.WriteLine(paciente);
        }
    }

    public static void ImprimePacientesEmOrdemAfabetica(List<Paciente> pacientes){
        Console.WriteLine($"--------PACIENTES EM ORDEM ALFABETICA--------");
        foreach (var paciente in PacientesComSexoInformado(pacientes))
        {
            Console.WriteLine(paciente);
        }
    }

    public static void ImprimePacientesComSintomaInformado(List<Paciente> pacientes){
        Console.WriteLine($"--------PACIENTES COM SEXO INFORMADO--------");
        foreach (var paciente in PacientesComSintomasInformado(pacientes))
        {
            Console.WriteLine(paciente);
        }
    }

    public static void ImprimeAniversariantesDoMes(List<Medico> medicos, List<Paciente> pacientes, int mes){
        Console.WriteLine($"--------PACIENTES COM SEXO INFORMADO--------");
        foreach (var pessoa in AniversariantesDoMes(medicos,pacientes,mes))
        {
            Console.WriteLine(pessoa);
        }
    }

    public static List<Medico> MedicosComIdadeEntre(List<Medico> medicos, int idadeMin, int idadeMax)
    {
        return medicos.Where(m =>
        {
            DateTime dataAtual = DateTime.Today;
            DateTime aniversarioEsteAno = m.DataNascimento.AddYears(dataAtual.Year - m.DataNascimento.Year);

            int idade = dataAtual.Year - m.DataNascimento.Year;

            if (DateTime.Compare(dataAtual, aniversarioEsteAno) < 0)
            {
                idade--;
            }

            return idade >= idadeMin && idade <= idadeMax;
        }).ToList();
    }

    public static List<Paciente> PacientesComIdadeEntre(List<Paciente> pacientes, int idadeMin, int idadeMax)
    {
        return pacientes.Where(p =>
        {
            DateTime dataAtual = DateTime.Today;
            DateTime aniversarioEsteAno = p.DataNascimento.AddYears(dataAtual.Year - p.DataNascimento.Year);

            int idade = dataAtual.Year - p.DataNascimento.Year;

            if (DateTime.Compare(dataAtual, aniversarioEsteAno) < 0)
            {
                idade--;
            }

            return idade >= idadeMin && idade <= idadeMax;
        }).ToList();
    }

    public static List<Paciente> PacientesComSexoInformado(List<Paciente> pacientes){
        return pacientes.Where(p => p.Sexo != "Não informado").ToList();
    }

    public static List<Paciente> PacientesEmOrdemAlfabetica(List<Paciente> pacientes){
        return pacientes.OrderBy(p => p.Nome).ToList();
    }

    public static List<Paciente> PacientesComSintomasInformado(List<Paciente> pacientes){
        return pacientes.Where(p => p.Sintomas != "Não informado").ToList();
    }

    public static List<Pessoa> AniversariantesDoMes(List<Medico> medicos, List<Paciente> pacientes, int mes)
    {
        IEnumerable<Pessoa> pessoas = medicos.Cast<Pessoa>().Concat(pacientes);
        return pessoas.Where(p => p.DataNascimento.Month == mes).ToList();
    }


}
