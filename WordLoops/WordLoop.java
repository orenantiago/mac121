public class WordLoop {
  private static boolean isNeighbor(String a, String b) {
      int cont = 0;
      if(a.length() != b.length())
          return false;

      for(int i = 0; i < a.length(); i++) {
          if(a.charAt(i) != b.charAt(i))
              cont++;
          if(cont > 1)
              return false;
      }
      return cont == 1;
  }
  public static void main(String[] args) {
    In in = new In(args[0]);
    String [] dicionario = in.readAllStrings();
    Graph g = new Graph();
    for (String palavra : dicionario)
        for(String palavra1 : dicionario)
            if(isNeighbor(palavra, palavra1))
                g.addEdge(palavra, palavra1);

    String [] palavras = StdIn.readAllStrings();
    for(String palavra : palavras) {
          DFS dfs = new DFS(g, palavra);
    }

  }
}
