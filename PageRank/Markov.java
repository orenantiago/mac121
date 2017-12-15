/******************************************************************************
 *  Compilation:  javac Markov.java
 *  Execution:    java Markov trials
 *  Data files:   http://introcs.cs.princeton.edu/16pagerank/tiny.txt
 *                http://introcs.cs.princeton.edu/16pagerank/medium.txt
 *
 *  This program reads a transition matrix from standard input and
 *  computes the probabilities that a random surfer lands on each page
 *  (page ranks) after the specified number of matrix-vector multiplies.
 *
 *  % java Transition < tiny.txt | java Markov 40
 *   0.27303 0.26573 0.14618 0.24723 0.06783
 *
 ******************************************************************************/

public class Markov {

    public static void main(String[] args) { 
        int trials = Integer.parseInt(args[0]);  // number of iterations
        int n = StdIn.readInt();                 // number of pages
	boolean verbose = args.length > 1;
        StdIn.readInt();                         // ignore integer required by input format


        // Read p[][] from StdIn. 
        double[][] p = new double[n][n];         // p[i][j] = prob. surfer moves from page i to page j
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) 
                p[i][j] = StdIn.readDouble(); 

        // Use the power method to compute page ranks. 
        double[] rank = new double[n]; 
        rank[0] = 1.0; 
        for (int t = 0; t < trials; t++) {

            // Compute effect of next move on page ranks. 
            double[] newRank = new double[n]; 
            for (int j = 0; j < n; j++) {
                //  New rank of page j is dot product of old ranks and column j of p[][]. 
                for (int k = 0; k < n; k++) 
                   newRank[j] += rank[k]*p[k][j]; 
            } 

            // Update page ranks.
            rank = newRank;
        } 

        // print page ranks
	if (!verbose) 
	    for (int i = 0; i < n; i++) 
		StdOut.printf("%5.3f %2d\n", rank[i], i);
	else 
	    printTwoSteps(rank, p);
    }

    private static void printTwoSteps(double[] rank, double[][] p) {
	int n = rank.length;
	double[] newRank = new double[n]; 
	for (int j = 0; j < n; j++)
	    for (int k = 0; k < n; k++) 
		newRank[j] += rank[k]*p[k][j];

        for (int i = 0; i < n; i++) 
            StdOut.printf("%7.5f %7.5f  %2d\n", rank[i], newRank[i], i);
    }
    
} 
