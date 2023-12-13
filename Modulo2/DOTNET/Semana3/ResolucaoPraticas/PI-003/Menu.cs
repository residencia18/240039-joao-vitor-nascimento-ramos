public static class Menu
{
    public static void Pause()
    {
        Console.WriteLine("Pressione uma tecla para continuar...");
        Console.ReadKey();
    }

    public static int MenuPrincipal()
    {
        Console.Clear();
        Console.WriteLine("1. Cadastrar Produto");
        Console.WriteLine("2. Consultar Produto");
        Console.WriteLine("3. Atualizar Estoque");
        Console.WriteLine("4. Relatórios");
        Console.WriteLine("0. Sair");

        return int.Parse(Console.ReadLine() ?? "5");
    }

    public static int MenuRelatorios()
    {
        Console.Clear();
        Console.WriteLine("1. Lista de produtos abaixo de uma determinada quantidade");
        Console.WriteLine("2. Lista de produtos com valor entre um mínimo e um máximo");
        Console.WriteLine("3. Valor total do estoque e o valor total de cada produto conforme o estoque");
        Console.WriteLine("0. Sair");

        return int.Parse(Console.ReadLine() ?? "5");
    }

    public static void GerenciarMenuPrincipal(int opcao)
    {
        switch (opcao)
        {
            case 1:
                Estoque.CadastrarProduto();
                break;
            case 2:
                Estoque.ConsultarProduto();
                break;
            case 3:
                Estoque.AtualizarEstoque();
                break;
            case 4:
                GerarRelatorios();
                break;
            case 0:
                Environment.Exit(0);
                break;
            default:
                Console.WriteLine("Opção inválida. Tente novamente.");
                Pause();
                break;
        }
    }

    public static void GerenciarMenuRelatorios(int opcao)
    {
        switch (opcao)
        {
            case 1:
                Estoque.Relatorio1();
                break;
            case 2:
                Estoque.Relatorio2();
                break;
            case 3:
                Estoque.Relatorio3();
                break;
            case 0:
                break;
            default:
                Console.WriteLine("Opção inválida. Tente novamente.");
                Pause();
                break;
        }
    }

    public static void GerarRelatorios()
    {
        int opcao = MenuRelatorios();
        GerenciarMenuRelatorios(opcao);
    }
}
