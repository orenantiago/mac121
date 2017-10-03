/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Variante do Web Exercise 2.5.13 (Sorting with many duplicates)
 * Data: 10/08/2017
 *
 * O programa funciona bem com duplicatas porque, por ele ser 3-Way partitioning,
 * ele evita fazer trocas desnecessárias quando lidando com um elemento igual ao
 * pivô, o colocando ao seu lado, ao mesmo tempo que coloca os elementos
 * diferentes à diante ou antes do pivô, fazendo com que a cada chamada o vetor
 * fique da seguinte forma, dado o pivô p:
 *        [<p..., ==p, >p]
 * chamando recursivamente, chegamos num vetor ordenado.
 *
 * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
 * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
 * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
 * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
 * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
 * DE CÓPIAS DESTA PROGRAMA.
 *
 ******************************************************************************/

public class DuplicateSort {

    public static void sort(Comparable[] a) {
        // Embaralhar o vetor garante que a escolha do pivô seja aleatória
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] array, int low, int hi) {
        if (hi <= low) return;
        int i = low, lt = low, gt = hi;

        // a[low] é o pivô das comparações.
        Comparable pivot = array[low];
        while (i <= gt) {
            int compare = array[i].compareTo(pivot);
            if (compare < 0) {
                swap(array, lt, i);
                lt++; i++;
            }
            else if (compare > 0) {
                swap(array, i, gt);
                gt--;
            }
            // se o valor a ser comparado for igual ao pivô, apenas incremento i.
            else
                i++;
        }

        /* chamadas recursivas para as duas partes que ainda não foram
         * ordenadas */
        sort(array, low, lt-1);
        sort(array, gt+1, hi);
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }
}
