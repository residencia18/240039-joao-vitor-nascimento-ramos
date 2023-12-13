class Program
{
    static void Main()
    {
        while (true)
        {
            int opcao = Menu.MenuPrincipal();
            Menu.GerenciarMenuPrincipal(opcao);
        }
    }
}
