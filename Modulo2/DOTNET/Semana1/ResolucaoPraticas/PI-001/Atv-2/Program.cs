/*Os valores escolhidos são os limites positivos de 
armazenamento de cada tipo de dado inteiro
*/

Console.WriteLine("Maximo de um sbyte : " + sbyte.MaxValue);
Console.WriteLine("Maximo de um byte : " + byte.MaxValue);
Console.WriteLine("Maximo de um short : " + short.MaxValue);
Console.WriteLine("Maximo de um ushort : " + ushort.MaxValue);
Console.WriteLine("Maximo de um int : " + int.MaxValue);
Console.WriteLine("Maximo de um uint : " + uint.MaxValue);
Console.WriteLine("Maximo de um long : " + long.MaxValue);
Console.WriteLine("Maximo de um ulong : " + ulong.MaxValue);
Console.WriteLine("Maximo de um nint(depende da plataforma, pode ser 64bits) : " + nint.MaxValue);
Console.WriteLine("Maximo de um unint(depende da plataforma, pode ser 64bits) : "+ nuint.MaxValue);


/*O uso vai depender do valor necessário para a aplicação 
e a possibilidade de ignorar os valores negativos;*/
