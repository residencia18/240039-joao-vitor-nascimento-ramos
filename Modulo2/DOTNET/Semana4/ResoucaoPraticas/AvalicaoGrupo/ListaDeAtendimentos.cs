namespace SistemaMedico
{
    public class ListaDeAtendimentos
    {
        private List<Atendimento> listaAtendimentos;

        public ListaDeAtendimentos()
        {
            listaAtendimentos = new List<Atendimento>();
        }

        public List<Atendimento> ListaAtendimentos{
            get{ return listaAtendimentos;}
            set{listaAtendimentos = value;}
        }

        public void AdicionarAtendimento(Atendimento novoAtendimento)
        {
            listaAtendimentos.Add(novoAtendimento);
        }

        public static Atendimento? ObterAtendimentoAtivo(ListaDeAtendimentos atendimentos, string cpfPaciente, string crmMedico)
        {
            return atendimentos.listaAtendimentos.FirstOrDefault(p => p.MedicoResponsavel.CRM == crmMedico && p.PacienteAtendido.CPF == cpfPaciente && p.AtendimentoEmAberto == true);
        }
    }
}
