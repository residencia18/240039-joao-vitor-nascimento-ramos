#include "verificacoesOcorrencia.hpp"
#include <regex>

bool verificaApolice(string apolice)
{

    for (char c : apolice)
    {
        if (!isdigit(c))
            return false;
    }
    return true;
}
