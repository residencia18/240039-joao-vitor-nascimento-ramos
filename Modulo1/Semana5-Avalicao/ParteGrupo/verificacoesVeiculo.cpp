#include "verificacoesVeiculo.hpp"
#include <regex>

bool verificaRenavan(const string &renavam)
{
    if (regex_match(renavam, regex("^[0-9]+$")))
    {
        return true;
    }
    return false;
}

bool verificaPlaca(const string &placa)
{
    return regex_match(placa, regex("^\\w{3}\\d{4}$"));
}

bool verificaRetirada(const DataHora &retirada)
{
    time_t tempoAtual = time(0);
    tm *dataHoraAtual = localtime(&tempoAtual);

    int diaAtual = dataHoraAtual->tm_mday;
    int mesAtual = dataHoraAtual->tm_mon + 1;
    int anoAtual = dataHoraAtual->tm_year + 1900;
    int secAtual = dataHoraAtual->tm_sec;
    int minAtual = dataHoraAtual->tm_min;
    int horaAtual = dataHoraAtual->tm_hour;

    if (retirada.data.ano < anoAtual)
    {
        cout << "\n\tNão é possível agendar datas anteriores a atual\n" << endl;
        return false;
    }
    else if (retirada.data.ano == anoAtual)
    {
        if (retirada.data.mes < mesAtual)
        {
            cout << "\n\tNão é possível agendar datas anteriores a atual\n" << endl;
            return false;
        }
        else if (retirada.data.mes == mesAtual)
        {
            if (retirada.data.dia < diaAtual)
            {
                cout << "\n\tNão é possível agendar datas anteriores a atual\n" << endl;
                return false;
            }
            else if (retirada.data.dia == diaAtual)
            {
                if (horaAtual >= 21)
                {
                    cout << "\n\tÉ necessário agendar com pelo menos 3 horas de antecedência, tente novamente com uma data do proximo dia\n" << endl;
                    return false;
                }
                else if (retirada.hora.hora - horaAtual < 3)
                {
                    cout << "\n\tÉ necessário agendar com pelo menos 3 horas de antecedência\n" << endl;
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
    }
    return true;
}

bool verificaEntrega(const DataHora &retirada, const DataHora &entrega)
{
    if (entrega.data.ano < retirada.data.ano)
    {
        cout << "\n\tData de entrega deve ser superior pelo menos a 1 dia, a data de retirada\n" << endl;
        return false;
    }
    else if (entrega.data.ano == retirada.data.ano)
    {
        if (entrega.data.mes < retirada.data.mes)
        {
            cout << "\n\tData de entrega deve ser superior pelo menos a 1 dia, a data de retirada\n" << endl;
            return false;
        }
        else if (entrega.data.mes == retirada.data.mes)
        {
            if (entrega.data.dia <= retirada.data.dia)
            {
                cout << "\n\tData de entrega deve ser superior pelo menos a 1 dia, a data de retirada\n" << endl;
                return false;
            }
        }
    }

    return true;
}

bool verificaLoja(const string &loja)
{
    return regex_match(loja, regex("^[a-zA-Z0-9 ]*$"));
}
