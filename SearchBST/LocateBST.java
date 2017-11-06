/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: LocateBST
 * Data: 06/11/2017
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

public class LocateBST {
    public static void main(String[] args) {
        In in0 = new In(args[0]);
        BST<Integer, Integer> st = new BST<Integer, Integer>();
        Integer[] left = new Integer[2];
        Integer[] right = new Integer[2];
        while (!in0.isEmpty()) {
            int begin = in0.readInt();
            int end = in0.readInt();
            st.put(begin, end);
        }

        while (!StdIn.isEmpty()) {
            Integer key = StdIn.readInt();

            left[0] = st.floor(key);
            if (left[0] != null)
                left[1] = st.get(left[0]);

            right[0] = st.ceiling(key);
            if (right[0] != null)
                right[1] = st.get(right[0]);

            if (left[0] != null)
                if (left[1] >= key)
                    StdOut.println(key + ": [" + left[0] + ", " + left[1] + "]");
                else if (right[0] != null)
                    StdOut.println(key + ": between [" + left[0] + ", " + left[1] + "]"
                            + " & " + "[" + right[0] + ", " + right[1] + "]");
                else
                    StdOut.println(key + ": right of [" + left[0] + ", " + left[1] + "]");
            else
                StdOut.println(key + ": left of [" + right[0] + ", " + right[1] + "]");
        }
    }
}
