/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: LParens
 * Data: 27/10/2017
 *
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class LParens {
    public static void main(String[] args) {
        Queue operations = new Queue();
        Queue operands = new Queue();
        Queue auxOperations;
        Queue auxOperands;
        String in = StdIn.readAll();
        String[] expression = in.split(" ");

        for (String item : expression) {
            //se for numero empilha na fila de operandos
            if (item.matches("\\d+"))
                operands.add(item);

            //se for operação coloca na fila de operações
            else if (item.compareTo("*") >= 0 && item.compareTo("/") <= 0)
                operations.add(item);

            else if (item.compareTo(")") == 0) {
                auxOperations = new Queue();
                auxOperands = new Queue();
                while (operands.size() > 2)
                    auxOperands.add(operands.remove());
                while (operations.size() > 1)
                    auxOperations.add(operations.remove());
                auxOperands.add("(" + operands.remove() + operations.remove()
                        + operands.remove() + ")");
                operands = auxOperands;
                operations = auxOperations;
            }
        }

        while (true) {
            if (!operands.isEmpty())
                StdOut.print(operands.remove());
            else {
                StdOut.println();
                break;
            }
            if (!operations.isEmpty())
                StdOut.print(operations.remove());
        }
    }
}
