using System.Globalization;

class Program
{
    static List<Tarefa> listaTarefas = new List<Tarefa>();

    static void Main()
    {
        while (true)
        {
            Console.WriteLine("===== Sistema de Gerenciamento de Tarefas =====");
            Console.WriteLine("1. Criar Tarefa");
            Console.WriteLine("2. Listar Todas as Tarefas");
            Console.WriteLine("3. Marcar Tarefa como Concluída");
            Console.WriteLine("4. Listar Tarefas Pendentes");
            Console.WriteLine("5. Listar Tarefas Concluídas");
            Console.WriteLine("6. Excluir Tarefa");
            Console.WriteLine("7. Pesquisar Tarefas por Palavra-chave");
            Console.WriteLine("8. Exibir Estatísticas");
            Console.WriteLine("0. Sair");

            Console.Write("Escolha uma opção: ");
            string? escolha = Console.ReadLine();

            switch (escolha)
            {
                case "1":
                    CriarTarefa();
                    break;
                case "2":
                    ListarTodasAsTarefas();
                    break;
                case "3":
                    MarcarTarefaConcluida();
                    break;
                case "4":
                    ListarTarefasPendentes();
                    break;
                case "5":
                    ListarTarefasConcluidas();
                    break;
                case "6":
                    ExcluirTarefa();
                    break;
                case "7":
                    PesquisarPorPalavraChave();
                    break;
                case "8":
                    ExibirEstatisticas();
                    break;
                case "0":
                    Environment.Exit(0);
                    break;
                default:
                    Console.WriteLine("Opção inválida. Tente novamente.");
                    break;
            }

            Console.WriteLine();
        }
    }

    static void CriarTarefa()
    {
        Console.Write("Digite o título da tarefa: ");
        string? titulo = Console.ReadLine();

        Console.Write("Digite a descrição da tarefa: ");
        string? descricao = Console.ReadLine();

        Console.Write("Digite a data de vencimento (dd/mm/yyyy): ");
        string? dataVencimentoInput = Console.ReadLine();

        if (titulo!= null && descricao!=null && dataVencimentoInput!=null && DateTime.TryParseExact(dataVencimentoInput, "dd/MM/yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out DateTime dataVencimento))
        {
            Tarefa novaTarefa = new Tarefa(titulo, descricao, dataVencimento);
            listaTarefas.Add(novaTarefa);

            Console.WriteLine("Tarefa criada com sucesso!");
        }
        else
        {
            Console.WriteLine("Formato de data inválido. Tarefa não criada.");
        }
    }

    static void ListarTodasAsTarefas()
    {
        Console.WriteLine("===== Lista de Todas as Tarefas =====");
        foreach (Tarefa tarefa in listaTarefas)
        {
            Console.WriteLine(tarefa);
        }
    }

    static void MarcarTarefaConcluida()
    {
        Console.Write("Digite o título da tarefa concluída: ");
        string? titulo = Console.ReadLine();

        if (!string.IsNullOrEmpty(titulo))
        {
            Tarefa tarefa = EncontrarTarefaPorTitulo(titulo);

            if (tarefa != null)
            {
                tarefa.ConcluirTarefa();
                Console.WriteLine("Tarefa marcada como concluída!");
            }
            else
            {
                Console.WriteLine("Tarefa não encontrada.");
            }
        }
        else
        {
            Console.WriteLine("Título inválido. Tarefa não marcada como concluída.");
        }
    }

    static void ListarTarefasPendentes()
    {
        Console.WriteLine("===== Lista de Tarefas Pendentes =====");
        foreach (Tarefa tarefa in listaTarefas.Where(tarefa => !tarefa.GetConcluida()))
        {
            Console.WriteLine(tarefa);
        }
    }

    static void ListarTarefasConcluidas()
    {
        Console.WriteLine("===== Lista de Tarefas Concluídas =====");
        foreach (Tarefa tarefa in listaTarefas.Where(tarefa => tarefa.GetConcluida()))
        {
            Console.WriteLine(tarefa);
        }
    }

    static void ExcluirTarefa()
    {
        Console.Write("Digite o título da tarefa a ser excluída: ");
        string? titulo = Console.ReadLine();

        if (!string.IsNullOrEmpty(titulo))
        {
            Tarefa tarefa = EncontrarTarefaPorTitulo(titulo);

            if (tarefa != null)
            {
                listaTarefas.Remove(tarefa);
                Console.WriteLine("Tarefa excluída com sucesso!");
            }
            else
            {
                Console.WriteLine("Tarefa não encontrada.");
            }
        }
        else
        {
            Console.WriteLine("Tarefa não encontrada.");
        }

    }

    static void PesquisarPorPalavraChave()
    {
        Console.Write("Digite a palavra-chave para pesquisa: ");
        string? palavraChave = Console.ReadLine();

        if(palavraChave!=null){

            List<Tarefa> tarefasEncontradas = new List<Tarefa>();

            foreach (Tarefa tarefa in listaTarefas)
            {
                if (tarefa.GetTitulo().Contains(palavraChave, StringComparison.OrdinalIgnoreCase) || tarefa.GetDescricao().Contains(palavraChave, StringComparison.OrdinalIgnoreCase))
                {
                    tarefasEncontradas.Add(tarefa);
                }
            }

            Console.WriteLine("===== Tarefas Encontradas =====");
            foreach (Tarefa tarefa in tarefasEncontradas)
            {
                Console.WriteLine(tarefa);
            }
        }
    }

    static void ExibirEstatisticas()
    {
        int tarefasConcluidas = listaTarefas.Count(t => t.GetConcluida());
        int tarefasPendentes = listaTarefas.Count(t => !t.GetConcluida());

        if (listaTarefas.Any())
        {
            Tarefa tarefaMaisAntiga = listaTarefas.OrderBy(t => t.GetDataVencimento()).First();
            Tarefa tarefaMaisRecente = listaTarefas.OrderByDescending(t => t.GetDataVencimento()).First();

            Console.WriteLine($"Número total de tarefas: {listaTarefas.Count}");
            Console.WriteLine($"Número de tarefas concluídas: {tarefasConcluidas}");
            Console.WriteLine($"Número de tarefas pendentes: {tarefasPendentes}");
            Console.WriteLine($"Tarefa mais antiga:\n{tarefaMaisAntiga}");
            Console.WriteLine($"Tarefa mais recente:\n{tarefaMaisRecente}");
        }
        else
        {
            Console.WriteLine("Não há tarefas cadastradas ainda.");
        }
    }

    static Tarefa EncontrarTarefaPorTitulo(string titulo)
    {   
        Tarefa tarefa = null;

        for (int i = 0; i < listaTarefas.Count; i++)
        {
            if (string.Equals(listaTarefas[i].GetTitulo(), titulo, StringComparison.OrdinalIgnoreCase))
            {
                tarefa = listaTarefas[i];
                break; 
            }
        }
         
        return tarefa;
    }

}

class Tarefa
{
    private string Titulo;
    private string Descricao;
    private DateTime DataVencimento;
    private bool Concluida;

    public Tarefa(string titulo, string descricao, DateTime dataVencimento)
    {
        Titulo = titulo;
        Descricao = descricao;
        DataVencimento = dataVencimento;
        Concluida = false;
    }

    public void SetTitulo(string titulo)
    {
        Titulo = titulo;
    }

    public string GetTitulo()
    {
        return Titulo;
    }

    public void SetDescricao(string descricao)
    {
        Descricao = descricao;
    }

    public string GetDescricao()
    {
        return Descricao;
    }

    public void SetDataVencimento(DateTime dataVencimento)
    {
        DataVencimento = dataVencimento;
    }

    public DateTime GetDataVencimento()
    {
        return DataVencimento;
    }

    public void SetConcluida(bool concluida)
    {
        Concluida = concluida;
    }

    public bool GetConcluida()
    {
        return Concluida;
    }

    public void ConcluirTarefa()
    {
        Concluida = true;
    }

    public override string ToString()
    {
        return $"Tarefa: \n[Título]: {Titulo}\n[Descrição]: {Descricao}\n[Data de Vencimento]: {DataVencimento.ToShortDateString()}\n[Concluída]: {(Concluida ? "Sim\n\n" : "Não\n\n")}";
    }
}
