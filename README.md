# Exclusão mútua distribuída

## Alunos
[Carlos Rodrigues](https://github.com/CarlosAsrc)

[Paulo Aranha](https://github.com/aranha)

## Descrição
Simulação de exclusão mútua distribuída baseado em um algoritmo com coordenador (centralizado).
O recurso compartilhado é o arquivo resource.txt, e é escrito em um arquivo chamado historico-acessos.txt 
o log dos acessos ao recursos, seguindo a seguinte estrutura   
<iteração> - <identificação do processo> - <resultado da operação> 


## Enunciado
Escrever um programa que implemente exclusão mútua distribuída baseado em um dos seguintes algoritmos:

baseado em um coordenador (centralizado);
baseado na garantia de acesso a partir da autorização de todos os participantes (distribuída);
token ring (anel);

O acesso à região crítica deverá ser controlado pelo par de métodos lock() / unlock(). A fim de testar a funcionalidade implementada, considere que o recurso compartilhado pelos diversos processos é um arquivo que está em uma das diversas máquinas onde os processos vão ser executados (o arquivo pode ser acessado por operações read() e write(), abstraídos por uma API).

No início este arquivo tem uma linha com o valor 1000 (interpretado como um inteiro). Cada processo deve ler o último valor que se encontra neste arquivo (read()), realizar uma operação (única) sobre o valor (por exemplo, somar o valor 100 / 200 / 300 ...), e armazenar o resultado na próxima linha (append, write()). Cada vez que um processo acessa o recurso (arquivo) ele deve imprimir o valor que lá se encontrava e o novo valor que lá foi armazenado. Cada processo deve realizar no mínimo 50 operações sobre o valor do arquivo, e essas operações devem ser executadas todas de uma vez (atomicidade garantida pela exclusão mútua). A sequência de ações realizada por cada processo é:

lock()
repete 50 vezes:
read()
(calcula novo valor)
write()
unlock()
Devem existir no mínimo 5 processos executando, competindo pelo acesso ao arquivo. O conteúdo final do arquivo deve representar a sequência de operações realizadas.