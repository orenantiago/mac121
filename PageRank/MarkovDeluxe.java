public class MarkovDeluxe {
  // public static void putST(ST<Integer, Queue<Integer>> st, Integer key, Integer value) {
  //   Queue<Integer> aux;
  //   if(st.contains(key)) {
  //     aux = st.get(key);
  //     aux.enqueue(value);
  //     st.put(key, aux);
  //   }
  //   else {
  //     aux = new Queue<Integer>();
  //     aux.enqueue(value);
  //     st.put(key, aux);
  //   }
  // }
  // public static Integer get(ST<Integer, Queue<Integer>> st, Integer in) {
  //   Queue<Integer> q;
  //   if(st.contains(in)) {
  //     q = st.get(in);
  //     if(!q.isEmpty()) {
  //       Integer i = q.dequeue();
  //       st.put(in, q);
  //       return i;
  //     }
  //   }
  //   return null;
  // }
  public static void main(String[] args) {
    In in0 = new In(args[0]);
    String [] connections  = in0.readAllLines();
    // mudar isso para colocar a outra parada
    Integer executions = Integer.parseInt(args[1]);
    int n = connections.length;
    // ST<Integer,Queue<Integer>> vertexes = new ST<Integer,Queue<Integer>>();
    // int n;
    // // definir conexões
    // for(String connection : connections) {
    //   String[] aux = connection.split(" ");
    //   Integer x = Integer.parseInt(aux[0]);
    //   for(int i = 1; i < aux.length; i++) {
    //     Integer y = Integer.parseInt(aux[i]);
    //     putST(vertexes, x, y);
    //   }
    // }
    // StdOut.println(vertexes.size());

    SparseMatrix transitionMatrix = new SparseMatrix(n);
    SparseVector outDegree = new SparseVector(n);
    for(String connection : connections) {
      String[] aux = connection.split(" ");
      Integer x = Integer.parseInt(aux[0]);
      for(int i = 1; i < aux.length; i++) {
        Integer y = Integer.parseInt(aux[i]);

        double occurrence = transitionMatrix.get(x, y);
        double outOccurrence = outDegree.get(x);

        occurrence += 1;
        outOccurrence += 1;

        transitionMatrix.put(x, y, occurrence);
        outDegree.put(x, outOccurrence);
      }
    }
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        double p = 0.90 * transitionMatrix.get(i, j)/outDegree.get(i) + 0.10/n;
        transitionMatrix.put(i, j, p);
      }
    }
    StdOut.println(transitionMatrix);
  }
}
