/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: WordLoop
 * Data: 23/11/2017
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
public class WordLoop {
  private static boolean isNeighbor(String a, String b) {
      int cont = 0;
      if(a.length() != b.length())
          return false;

      for(int i = 0; i < a.length(); i++) {
          if(a.charAt(i) != b.charAt(i))
              cont++;
          if(cont > 1)
              return false;
      }
      return cont == 1;
  }
  public static void main(String[] args) {
    In in = new In(args[0]);
    String [] dicionario = in.readAllStrings();
    Graph g = new Graph();
    for (String palavra : dicionario)
        for(String palavra1 : dicionario)
            if(isNeighbor(palavra, palavra1))
                g.addEdge(palavra, palavra1);
    String [] palavras = StdIn.readAllStrings();
    for(String palavra : palavras) {
          DepthFirstSearch search = new DepthFirstSearch(g, palavra);
    }

  }
}
