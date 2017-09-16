import java.util.Arrays;
import java.util.Comparator;

public class Mapa {
	public String chave;
	public String valor;
	public static final Comparator<Mapa> ORDEM_CHAVE  = new ComparadorChave();

	public Mapa(String valor) {
		this.valor = valor;

		char [] aux = valor.toCharArray();
		Arrays.sort(aux);
		this.chave = new String(aux);
	}
	private static class ComparadorChave implements Comparator<Mapa> {
		public int compare(Mapa a, Mapa b) {
			return a.chave.compareToIgnoreCase(b.chave);
		}
	}

	public boolean chaveIgual(Mapa a) {
		if (this.chave.compareToIgnoreCase(a.chave) == 0)
			return true;
		return false;
	}

	public boolean valorIgual(Mapa a) {
		if (this.valor.compareToIgnoreCase(a.valor) == 0)
			return true;
		return false;
	}

	public String toString() {
		return this.valor;
	}
}