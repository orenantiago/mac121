public class Clairvoyant {
  private class Item {

  }
  public static void main(String[] args) {
    boolean verbose = args.length > 1;
    int cacheSize = Integer.valueOf(args[0]);
    // IndexMaxPQ<Item> cache = new IndexMaxPQ<Item>(cacheSize);
    // Integer i = 0;

    // testes
    // for(Integer i = 0; cache.size() < cacheSize; i++) {
    //   cache.insert(i, i.toString());
    // }
    // while(!cache.isEmpty()) {
    //   StdOut.println(cache.delMax());
    // }

    // implementação
    String[] in = StdIn.readAllStrings();

    BST indexes = new BST();
    for(Integer i = 0; i < in.length; i++) {
      // StdOut.println(in[i] + " " + i);
      indexes.put(in[i], i);
    }
    // BST que guarda o que já ocorreu
    BST occurred = new BST();
    IndexMaxPQ <String>cache = new IndexMaxPQ<String>(cacheSize);
    IndexMaxPQ <Integer>nextOccurrence = new IndexMaxPQ<Integer>(cacheSize);
    int c = 0;
    Integer index;
    for(Integer i = 0; i < in.length; i++) {
      if(cache.size() < cacheSize) {
        occurred.put(in[i], i);
        cache.insert(c, in[i]);
        index = indexes.removeIndex(in[i]);
        StdOut.println(in[i] + " " + index);
        if(index == i) index = indexes.removeIndex(in[i]);
        StdOut.println(in[i] + " " + index);
        nextOccurrence.insert(c, index);
        c++;
        if(verbose) {
          StdOut.println(in[i] + ": +" + in[i]);
        }
      }
      // se já tiver aparecido
      else if(occurred.contains(in[i])) {
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

        occurred.delete(word);
        occurred.put(in[i], i);
        index = indexes.removeIndex(word);
        if(index == i) index = indexes.removeIndex(in[i]);
        // atualiza o cache e as proximas ocorrencias
        // se não tiver mais proxima ocorrencia dessa string, coloca uma prioridade grande
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
