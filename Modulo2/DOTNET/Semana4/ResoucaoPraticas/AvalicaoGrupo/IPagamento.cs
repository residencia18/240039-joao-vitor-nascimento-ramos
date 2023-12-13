public interface IPagamento
{
    string Descricao { get; set; }
    double ValorBruto { get; set; }
    double Desconto { get; set; }
    DateTime DataHora { get; set; }

    double CalcularValorComDesconto();
    void ImprimirRecibo();
    void RealizarPagamento();
    
}