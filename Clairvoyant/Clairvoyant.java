public class Clairvoyant {
  public static void main(String[] args) {
    boolean verbose = args.length > 1;
    int cacheSize = Integer.valueOf(args[0]);
    IndexMaxPQ<String> cache = new IndexMaxPQ<String>(cacheSize);
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
    for(Integer i = 0; i < in.length; i++)
      indexes.add(in[i], i);

    for(Integer i = 0; i < in.length; i++) {
      // StdOut.println(in[i]);

      Integer priority;
      for(priority = i + 1; priority < in.length && in[priority] != in[i]; priority++);
      if(cache.size() < cacheSize - 2) {
        StdOut.println(cache.size());
        cache.insert(priority, in[i]);
      //   if(verbose) {
      //     StdOut.println(in[i] + ": +" + in[i]);
      //   }
      }

    }


  }
}
