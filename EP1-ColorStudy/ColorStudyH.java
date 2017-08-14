/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Variante do Creative Ex. 3.1.33 (Color study)
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
import java.awt.Color;
import java.lang.Math;

public class ColorStudyH {
    public static void main(String[] args) {
        float h1 = Float.parseFloat(args[0]);
        float h2 = Float.parseFloat(args[1]);
        float distanceh1h2 = (float) Math.sqrt(15 * 15 + 15 * 15);
        StdDraw.setXscale(-1, 16);
        StdDraw.setYscale(-1, 16);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int gray = i * 16 + (15 - j);

                float distance = (float) Math.sqrt(i*i + j*j);
                float hue = h1 + ((h2 - h1) * distance) / distanceh1h2;

                Color c1 = Color.getHSBColor(hue, 0.95f, 0.95f);
                Color c2 = new Color(gray, gray, gray);

                StdDraw.setPenColor(c1);
                StdDraw.filledSquare(i, j, 0.5);
                StdDraw.setPenColor(c2);
                StdDraw.filledSquare(i, j, 0.25);
            }
        }
        StdDraw.save("study-" + String.valueOf(h1) + "-" + String.valueOf(h2)
            + ".png");
    }
}
