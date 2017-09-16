/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Anagrams
 * Data: 15/09/2017
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

public class Anagrams {
	public static void main(String[] args) {
		String [] palavras = StdIn.readAllLines(); 
		Mapa [] mapa = new Mapa [palavras.length];
		
		for(int i = 0; i < palavras.length; i++) {
			mapa[i] = new Mapa(palavras[i]);
		}

		Arrays.sort(mapa, Mapa.ORDEM_CHAVE);

		imprimeAnagramas(mapa);
	}

	public static void imprimeAnagramas(Mapa [] mapa) {
		
		for(int i = 0; i < mapa.length - 1; i++) {

			boolean linhaNova = true;
			
			for(;mapa[i].chaveIgual(mapa[i + 1]) && i < mapa.length - 1; i++) {	
				if(!mapa[i].valorIgual(mapa[i + 1])) {
					if(linhaNova) {
						StdOut.print("\n* " + mapa[i] + " ");
						linhaNova = false;
					}
					StdOut.print(mapa[i + 1] + " ");
				}
			}
		}

		StdOut.println();
	}

	
}
