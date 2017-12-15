public class MarkovDeluxe {

  // esse função faz o pagerank recebendo uma matriz de transição, um vetor com
  // o rank "atual" e o número de execuções a serem feitas. e o pagerank é
  // atribuido ao próprio vetor com o rank atual
  public static SparseVector pageRank(SparseMatrix transitionMatrix, SparseVector rank, int n, int executions) {
    for(int i = 0; i < executions; i++) {
      SparseVector auxRank = new SparseVector(n);
      for(int j = 0; j < n; j++) {
        double p = 0;
        for( int l = 0; l < n; l++) {
          p += rank.get(l) * transitionMatrix.get(l, j);
        }
        auxRank.put(j, p);
      }
      rank = auxRank;
    }
    return rank;
  }
  public static void main(String[] args) {
    In in0 = new In(args[0]);
    String split = args[1];
    double probability = Double.parseDouble(args[2]);
    Integer executions = Integer.parseInt(args[3]);
    boolean verbose = args.length > 4;
    // SymbolDigraph teste = new SymbolDigraph(args[0], split);
    // StdOut.println(teste);


    String [] connections  = in0.readAllLines();
    int n = connections.length;


    // criando a matriz de transição
    SparseMatrix transitionMatrix = new SparseMatrix(n);
    SparseVector outDegree = new SparseVector(n);
    SparseVector inDegree = new SparseVector(n);
    for(String connection : connections) {
      String[] aux = connection.split(split);
      Integer x = Integer.parseInt(aux[0]);
      for(int i = 1; i < aux.length; i++) {
        Integer y = Integer.parseInt(aux[i]);

        double occurrence = transitionMatrix.get(x, y);
        double outOccurrence = outDegree.get(x);
        double inOccurrence = inDegree.get(y);

        occurrence += 1;
        outOccurrence += 1;
        inOccurrence += 1;

        transitionMatrix.put(x, y, occurrence);
        outDegree.put(x, outOccurrence);
        inDegree.put(y, inOccurrence);
      }
    }
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        double p = probability * transitionMatrix.get(i, j)/outDegree.get(i) + (1.0 - probability)/n;
        transitionMatrix.put(i, j, p);
      }
    }
    // Computando o pagerank
    SparseVector rank = new SparseVector(n);
    rank.put(0, 1.0);
    rank = pageRank(transitionMatrix, rank, n, executions);
    if(!verbose)
      for(int i = 0; i < n; i++) StdOut.printf("%5.9f %2d\n", rank.get(i), i);
    else{
      SparseVector newRank = pageRank(transitionMatrix, rank, n, 1);
      for(int i = 0; i < n; i++) {
        StdOut.printf("%5.9f %5.9f %5.0f %2d\n", rank.get(i), newRank.get(i), inDegree.get(i), i);
      }
    }
  }
}
