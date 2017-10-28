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
import java.util.Comparator;


/*
	essa classe é um mapeamento de uma palavra (valor) para sua versão ordenada alfabeticamente(chave)
	exemplo: 
		valor "abacate"
		chave "aaabcet"
*/
public class Mapa {
	public String chave;
	public String valor;
	public static final Comparator<Mapa> ORDEM_CHAVE  = new ComparadorChave();

	public Mapa(String valor) {
		this.valor = valor;

		char [] aux = valor.toCharArray();
		Arrays.sort(aux);
		this.chave = new String(aux).toLowerCase();
	}
	private static class ComparadorChave implements Comparator<Mapa> {
		public int compare(Mapa a, Mapa b) {
			return a.chave.compareTo(b.chave);
		}
	}

	public boolean chaveIgual(Mapa a) {
		return this.chave.compareTo(a.chave) == 0;
	}

	public boolean valorIgual(Mapa a) {
		return this.valor.compareTo(a.valor) == 0;
	}

	public String toString() {
		return this.valor;
	}
}