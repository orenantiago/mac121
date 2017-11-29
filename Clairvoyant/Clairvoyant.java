/******************************************************************************
 *
 * MAC0121 - Algoritmos e Estruturas de Dados I
 * Aluno: Renan Tiago dos Santos Silva
 * Numero USP: 9793606
 * Tarefa: Clairvoyant
 * Data: 28/11/2017
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
public class Clairvoyant {
  public static void putST(ST<String, Queue<Integer>> st, String in, Integer index) {
    Queue<Integer> aux;
    if(st.contains(in)) {
      aux = st.get(in);
      aux.enqueue(index);
      st.put(in, aux);
    }
    else {
      aux = new Queue<Integer>();
      aux.enqueue(index);
      st.put(in, aux);
    }
  }
  public static Integer get(ST<String, Queue<Integer>> st, String in) {
    Queue<Integer> q;
    if(st.contains(in)) {
      q = st.get(in);
      if(!q.isEmpty()) {
        Integer i = q.dequeue();
        st.put(in, q);
        return i;
      }
    }
    return null;
  }
  public static void main(String[] args) {
    boolean verbose = args.length > 1;
    int cacheSize = Integer.valueOf(args[0]);

    String[] in = StdIn.readAllStrings();
    ST<String, Queue<Integer>> indexes = new ST<String, Queue<Integer>>();
    for(Integer i = 0; i < in.length; i++) {
      putST(indexes, in[i], i);
    }
    // ST que guarda o que já ocorreu
    ST<String, Queue<Integer>> occurred = new ST<String, Queue<Integer>>();
    IndexMaxPQ <String>cache = new IndexMaxPQ<String>(cacheSize);
    IndexMaxPQ <Integer>nextOccurrence = new IndexMaxPQ<Integer>(cacheSize);
    int c = 0;
    Integer index;
    for(Integer i = 0; i < in.length; i++) {
      if(cache.size() < cacheSize && !occurred.contains(in[i])) {
        putST(occurred, in[i], i);
        cache.insert(c, in[i]);
        index = get(indexes, in[i]);
        if(index == i) index = get(indexes, in[i]);
        if(index != null)
          nextOccurrence.insert(c, index);
        else
          nextOccurrence.insert(c, in.length);
        c++;
        if(verbose) {
          StdOut.println(in[i] + ": +" + in[i]);
        }
      }
      // se já tiver aparecido
      else if(occurred.contains(in[i])) {
        boolean ok = true;
        for(Integer j = 0; j < cache.size() && ok; j++) {
          if(cache.keyOf(j).compareTo(in[i]) == 0) {
            index = get(indexes, in[i]);
            if(index == i) index = get(indexes, in[i]);
            if(index == null)
              nextOccurrence.changeKey(j, in.length + 1);
            else
              nextOccurrence.changeKey(j, index);
            break;
          }
        }
        if(verbose) {
          StdOut.println(in[i] + ": in cache");
        }
      }
      // se não tiver aparecido, e o cache não estiver vazio, aí fudeu
      else {
        Integer j = nextOccurrence.delMax();
        String word = cache.keyOf(j);
        if(verbose) {
          StdOut.println(in[i] + ": -"+ word + "/+" + in[i]);
        }

        occurred.remove(word);
        putST(occurred, in[i], i);
        putST(occurred, in[i], i);
        index = get(indexes, word);
        if(index == i) index = get(indexes, in[i]);
        if(index == null)
          nextOccurrence.insert(j, in.length);
        else
          nextOccurrence.insert(j, index);
        cache.changeKey(j, in[i]);
        c++;
      }

    }

    StdOut.println("Number of cache misses: " + c);


  }
}
