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

	/*
		recebe um vetor de intervalos ordenados, coloca nele o grupo
		a união dos intervalos, e retorna um vetor de doubles com
		[numero de intervalos, tamanho total]
	*/
	public static double[] joinIntervals(Interval1D [] input) {
		double [] output = new double [2];
		int k, i;
		for (k = 0, i = 0; i < input.length; k++) 
		{
			int j = i + 1;
			double min = input[i].min(), max = input[i].max();

			Interval1D aux = new Interval1D(min, max);

			while(j < input.length && aux.intersects(input[j])) {
				if(input[j].max() > aux.max())
					max = input[j].max();
				else
					max = aux.max();
				aux = new Interval1D(min, max);
				j++;
			}

			i = j;
			input[k] = new Interval1D(min, max);
			output[1] = max - min;
		}
		output[0] = k;
		return output;

	}

    public static void main(String [] args) {

    	boolean verbose = (args.length > 0) ? true : false;
		String [] lines = StdIn.readAllLines();

		Interval1D[] intervals = new Interval1D[lines.length];
		Interval1D[] unionIntervals = new Interval1D[lines.length];

		for (int i = 0; i < lines.length; i++) {
			String [] aux = lines[i].split("[^0-9.]+");
			intervals[i] = new Interval1D(Double.parseDouble(aux[0]),Double.parseDouble(aux[1]));
			unionIntervals[i] = new Interval1D(Double.parseDouble(aux[0]),Double.parseDouble(aux[1]));
		}


		Arrays.sort(unionIntervals, Interval1D.MIN_ENDPOINT_ORDER);
		double [] total = joinIntervals(unionIntervals);
		int components = (int) total[0];

		
		StdDraw.setXscale(0, 60);
        StdDraw.setYscale(0, 60);
        StdDraw.setPenColor(StdDraw.RED);
        //(x,y,x,y)
        StdDraw.line(0, 3, 60, 3);
        StdDraw.show();
			

		StdOut.println("Total length: " + total[1] + " ["+ components + " components]");
		
		if (verbose)
			for(int i = 0; i < intervals.length; i++){

				//imprimindo os intervalos da união
				StdOut.println(unionIntervals[i].toString());
			}

		
	}
}