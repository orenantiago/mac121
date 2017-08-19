/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Manchetes
 * Data: 10/08/2017
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

import org.apache.commons.lang3.StringEscapeUtils;

public class EstadaoHeadlines {
  public static void main(String args[]) {
    In in = new In("http://www.estadao.com.br");
    String page = in.readAll();

    // treat cases where there are arguments in h3 tag
    int h3 = page.indexOf("<h3");
    int begin = page.indexOf(">", h3);
    int end = page.indexOf("</h3>");

    while(h3 > 0){

      String html = page.substring(begin + 1, end);
      StdOut.println(StringEscapeUtils.unescapeHtml4(html));

      h3 = page.indexOf("<h3", end);
      begin = page.indexOf(">", h3);
      end = page.indexOf("</h3>", begin);
    }
  }
}
