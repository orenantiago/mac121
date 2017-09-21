/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: CountOccurrences
 * Data: 19/09/2017
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

import java.util.Arrays;
public class CountOccurrences {
  public static int numberOfOccurrences(int [] array, int key) {
    int lo = 0, hi = array.length - 1;
    boolean found = false;
    int mid = lo + (hi - lo) / 2;
    while(lo <= hi) {
      if(key > array[mid])
        lo = mid + 1;
      else if(key < array[mid])
        hi = mid - 1;
      else {
        found = true;
        break;
      }
      mid = lo + (hi - lo) / 2;

    }
    int count = 0;
    if(found) {
      int i = mid, j = mid + 1;
      while(i >= 0 && array[i] == key) {
        count++;
        i--;
      }
      while (j < array.length && array[j] == key) {
        count++;
        j++;
      }
    }
    return count;
  }
  public static void main(String[] args) {
    In in0 = new In(args[0]);
    int [] list = in0.readAllInts();
    int [] queries = StdIn.readAllInts();
    for(int query : queries)
      StdOut.println(numberOfOccurrences(list, query));
  }
}
