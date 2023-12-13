
public static class Estoque
{
    public static List<(int, string, int, double)> estoque = new List<(int, string, int, double)>();

    public static void CadastrarProduto()
    {
        Console.WriteLine("Digite o código do produto:");
        int codigo = int.Parse(Console.ReadLine() ?? "-1");

        Console.WriteLine("Digite o nome do produto:");
        string? nome = Console.ReadLine() ?? "null";

        Console.WriteLine("Digite a quantidade em estoque:");
        int quantidade = int.Parse(Console.ReadLine() ?? "-1");

        Console.WriteLine("Digite o preço unitário:");
        double preco = double.Parse(Console.ReadLine() ?? "-1");
        if(codigo != -1 && nome!="null" && quantidade!=-1 && preco!=-1){
            estoque.Add((codigo, nome, quantidade, preco));
            Console.WriteLine("Produto cadastrado com sucesso!");
        }else{
            Console.WriteLine("Erro ao cadastrar produto, formato inválido!");
        }
        Menu.Pause();

    }

    public static void ConsultarProduto()
    {
        Console.WriteLine("Digite o código do produto:");
        int codigo = int.Parse(Console.ReadLine() ?? "-1");

        var produto = estoque.SingleOrDefault(p => p.Item1 == codigo);

        if (produto.Equals(default(ValueTuple<int, string, int, double>)))
        {
            throw new Exception("Produto não encontrado.");
        }

        Console.WriteLine($"Código: {produto.Item1}");
        Console.WriteLine($"Nome: {produto.Item2}");
        Console.WriteLine($"Quantidade em Estoque: {produto.Item3}");
        Console.WriteLine($"Preço Unitário: {produto.Item4}");
        Menu.Pause();

    }

    public static void AtualizarEstoque()
    {
        Console.WriteLine("Digite o código do produto:");
        int codigo = int.Parse(Console.ReadLine() ?? "-1");

        var produto = estoque.SingleOrDefault(p => p.Item1 == codigo);

        if (produto.Equals(default(ValueTuple<int, string, int, double>)))
        {
            throw new Exception("Produto não encontrado.");
        }

        Console.WriteLine("Digite a quantidade a ser adicionada (positiva) ou removida (negativa):");
        int quantidadeAtualizacao = int.Parse(Console.ReadLine() ?? "0");

        if (produto.Item3 + quantidadeAtualizacao < 0)
        {
            throw new Exception("Estoque insuficiente para essa operação.");
        }

        int novoEstoque = produto.Item3 + quantidadeAtualizacao;

        estoque.Remove(produto);
        estoque.Add((produto.Item1, produto.Item2, novoEstoque, produto.Item4));

        Console.WriteLine("Estoque atualizado com sucesso!");
        Menu.Pause();

    }

    public static void Relatorio1()
    {
        Console.WriteLine("Digite o limite de quantidade para o relatório de produtos em estoque:");
        int limiteQuantidade = int.Parse(Console.ReadLine() ?? "0");

        var produtosAbaixoLimite = estoque.Where(p => p.Item3 < limiteQuantidade);
        Console.WriteLine("\nProdutos com quantidade em estoque abaixo do limite:");
        foreach (var produto in produtosAbaixoLimite)
        {
            Console.WriteLine($"Nome: {produto.Item2}, Quantidade em Estoque: {produto.Item3}");
        }
        Menu.Pause();
    }

    public static void Relatorio2()
    {
        Console.WriteLine("\nDigite o valor mínimo para o relatório de produtos por faixa de preço:");
        double minimo = double.Parse(Console.ReadLine() ?? "0");

        Console.WriteLine("Digite o valor máximo para o relatório de produtos por faixa de preço:");
        double maximo = double.Parse(Console.ReadLine() ?? "0");

        var produtosPorFaixaDePreco = estoque.Where(p => p.Item4 >= minimo && p.Item4 <= maximo);
        Console.WriteLine("\nProdutos com valor entre o mínimo e máximo informados:");
        foreach (var produto in produtosPorFaixaDePreco)
        {
            Console.WriteLine($"Nome: {produto.Item2}, Preço Unitário: {produto.Item4}");
        }
        Menu.Pause();
    }

    public static void Relatorio3()
    {
        var valorTotalEstoque = estoque.Sum(p => p.Item3 * p.Item4);
        Console.WriteLine($"\nValor total do estoque: {valorTotalEstoque:C}");

        Console.WriteLine("\nValor total de cada produto de acordo com seu estoque:");
        foreach (var produto in estoque)
        {
            var valorTotalProduto = produto.Item3 * produto.Item4;
            Console.WriteLine($"Nome: {produto.Item2}, Valor Total: {valorTotalProduto:C}");
        }
        Menu.Pause();

    }
}
