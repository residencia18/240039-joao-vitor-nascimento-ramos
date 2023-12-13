namespace SistemaMedico
{
    public class ListaDePlanosDeSaude
    {
        private List<PlanoDeSaude> planosDeSaude;

        public ListaDePlanosDeSaude()
        {
            planosDeSaude = new List<PlanoDeSaude>();
        }

        public List<PlanoDeSaude> PlanosDeSaude{
        get{ return planosDeSaude;}
            set{planosDeSaude = value;}
        }

        public void AdicionarPlanoDeSaude(PlanoDeSaude plano)
        {
            planosDeSaude.Add(plano);
        }

        public void ExibirPlanosDeSaude()
        {
            Console.WriteLine("---- Lista de Planos de Saúde ----");
            foreach (var plano in planosDeSaude)
            {
                Console.WriteLine(plano.ToString());
                Console.WriteLine("-----------------------------");
            }
        }


        public PlanoDeSaude ObterPlanoPorNome(string nomePlano)
        {
            return planosDeSaude.FirstOrDefault(plano => plano.Titulo == nomePlano);
        }

    }
}
