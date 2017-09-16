import java.util.Arrays;

public class Anagrams {
	public static void main(String[] args) {
		String [] palavras = StdIn.readAllLines(); 
		Mapa [] tabela = new Mapa [palavras.length];
		
		for(int i = 0; i < palavras.length; i++) {
			tabela[i] = new Mapa(palavras[i]);
		}

		Arrays.sort(tabela, Mapa.ORDEM_CHAVE);

		int i = 0;
		while(i < tabela.length - 1) {
			boolean linhaNova = true;
			while(tabela[i].chaveIgual(tabela[i + 1]) && i < tabela.length - 1) {
				
				if(!tabela[i].valorIgual(tabela[i + 1])) {
					if(linhaNova) {
						StdOut.print("\n* " + tabela[i] + " ");
						linhaNova = false;
					}
					StdOut.print(tabela[i + 1] + " ");
				}

				i++;
			}
			i++;
		}

		StdOut.println();

	}

	
}
