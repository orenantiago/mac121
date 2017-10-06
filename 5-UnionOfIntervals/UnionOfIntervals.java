
/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Union of intervals
 * Data: 12/09/2017
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
import java.awt.Color;

public class UnionOfIntervals {

	public static void main(String[] args) {

		boolean verbose = (args.length > 0) ? true : false;
		String[] lines = StdIn.readAllLines();

		Interval1D[] intervals = new Interval1D[lines.length];

		for (int i = 0; i < lines.length; i++) {
			String[] line = lines[i].split("[^0-9.]+");
			double n1 = Double.parseDouble(line[0]);
			double n2 = Double.parseDouble(line[1]);
			intervals[i] = (n1 < n2) ? new Interval1D(n1, n2) : new Interval1D(n2, n1);
		}

		Arrays.sort(intervals, Interval1D.MIN_ENDPOINT_ORDER);
		Interval1D[] unionIntervals = new Interval1D[lines.length];

		int k, i;
		double length = 0;
		for (k = 0, i = 0; i < intervals.length; k++) {
			int j = i + 1;
			double min = intervals[i].min(), max = intervals[i].max();

			Interval1D aux = new Interval1D(min, max);

			while (j < intervals.length && aux.intersects(intervals[j])) {
				max = (intervals[j].max() > aux.max()) ? intervals[j].max() : aux.max();
				aux = new Interval1D(min, max);
				j++;
			}

			i = j;
			unionIntervals[k] = new Interval1D(min, max);
			length = max - min;
		}


		StdOut.println("Total length: " + length + " [" + k + " components]");
		
		// Desenhando os intervalos
		if(verbose && intervals.length <= 60){
			StdDraw.setXscale(0, 16);
			StdDraw.setYscale(0, 16);

			double min = unionIntervals[0].min();
			double max = unionIntervals[k - 1].max();
			double delta = max - min;
			for (i = 0; i < intervals.length; i++) {
				double x0 = (14 * (intervals[i].min() - min)) / delta + 1;
				double x1 = (14 * (intervals[i].max() - min)) / delta + 1;

				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.BLACK);
				StdDraw.line(x0, 0.15 * i + 1, x1, 0.15 * i + 1);

				StdDraw.setPenRadius(0.0025);
				StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
				StdDraw.line(x0, 0, x0, 16);
				StdDraw.line(x1, 0, x1, 16);

				if (i < k) {
					x0 = (14 * (unionIntervals[i].min() - min)) / delta + 1;
					x1 = (14 * (unionIntervals[i].max() - min)) / delta + 1;

					StdDraw.setPenRadius(0.005);
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.line(x0, 0.24 * intervals.length + 1, x1, 0.24 * intervals.length + 1);

					StdOut.println(unionIntervals[i].toString());
				}
			}
		}
	}
}