class DFS {
  private ST<String, Boolean> marked;
  private Queue<String> queue = null;
  private String s;

  DFS(Graph g, String s) {
    this.s = s;
    marked = new ST<String, Boolean>();
    for (String v : g.vertices()) marked.put(v, false);
    search(g, s);
  }
  // funcao que retorna a fila da bagaça
  void search(Graph g, String s) {
    if(!g.hasVertex(s)) {
      StdOut.println(s + ":not in graph");
      return;
    }
    // for(String word : g.adjacentTo(s)) {
    //   if(word == )
    // }
  }
}
