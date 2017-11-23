/* WARNING: Adapted to introcs */

/******************************************************************************
*  Compilation:  javac DepthFirstSearch.java
*  Execution:    java DepthFirstSearch filename.txt s
*  Dependencies: Graph.java StdOut.java
*  Data files:   http://algs4.cs.princeton.edu/41graph/tinyG.txt
*                http://algs4.cs.princeton.edu/41graph/mediumG.txt
*
*  Run depth first search on an undirected graph.
*  Runs in O(E + V) time.
*
*  % java DepthFirstSearch tinyG.txt 0
*  0 1 2 3 4 5 6
*  NOT connected
*
*  % java DepthFirstSearch tinyG.txt 9
*  9 10 11 12
*  NOT connected
*
******************************************************************************/

/**
*  The {@code DepthFirstSearch} class represents a data type for
*  determining the vertices connected to a given source vertex <em>s</em>
*  in an undirected graph. For versions that find the paths, see
*  {@link DepthFirstPaths} and {@link BreadthFirstPaths}.
*  <p>
*  This implementation uses depth-first search.
*  The constructor takes time proportional to <em>V</em> + <em>E</em>
*  (in the worst case),
*  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
*  It uses extra space (not including the graph) proportional to <em>V</em>.
*  <p>
*  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>
*  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
*
*  @author Robert Sedgewick
*  @author Kevin Wayne
*/
public class DepthFirstSearch {
  private ST<String, Boolean> marked;    // marked[v] = is there an s-v path?
  private int count;           // number of vertices connected to s
  private Stack<String> stack = new Stack<String>();
  private String original;
  private boolean found;
  private int height;
  /**
  * Computes the vertices in graph {@code G} that are
  * connected to the source vertex {@code s}.
  * @param G the graph
  * @param s the source vertex
  * @throws IllegalArgumentException unless {@code 0 <= s < V}
  */
  public DepthFirstSearch(Graph G, String s) {
    stack = new Stack<String>();
    this.original = s;
    found = false;
    marked = new ST<String, Boolean>();
    for (String v : G.vertices()) marked.put(v, false);
    if(G.hasVertex(s)){
      dfs(G, original, 1);
      if(found == true) {
        StdOut.println(s + ": " + s + " " + stack + s);
      }
      else
      StdOut.println(s + ": no word loop");
    }
    else
    StdOut.println(s + ": not in graph");
  }

  // depth first search from v
  void dfs(Graph G, String v, int h) {
    marked.put(v, true);
    String out = v;
    for (String w : G.adjacentTo(v)) {
      if (!marked.get(w)) {
        dfs(G, w, h + 1);
        if(found)
        stack.push(w);

      }
      else
        if(w.equals(original) && h >= 3)
          found = true;
      if(found)
        break;
    }
  }
  /**
  * Is there a path between the source vertex {@code s} and vertex {@code v}?
  * @param v the vertex
  * @return {@code true} if there is a path, {@code false} otherwise
  * @throws IllegalArgumentException unless {@code 0 <= v < V}
  */
  public boolean marked(String v) {
    validateVertex(v);
    return marked.get(v);
  }

  /**
  * Returns the number of vertices connected to the source vertex {@code s}.
  * @return the number of vertices connected to the source vertex {@code s}
  */
  public int count() {
    return count;
  }

  // throw an IllegalArgumentException unless {@code 0 <= v < V}
  private void validateVertex(String v) {
    if (!marked.contains(v))
    throw new IllegalArgumentException("vertex not in graph");
  }

  /**
  * Unit tests the {@code DepthFirstSearch} data type.
  *
  * @param args the command-line arguments
  */

}

/******************************************************************************
*  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
*
*  This file is part of algs4.jar, which accompanies the textbook
*
*      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
*      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
*      http://algs4.cs.princeton.edu
*
*
*  algs4.jar is free software: you can redistribute it and/or modify
*  it under the terms of the GNU General Public License as published by
*  the Free Software Foundation, either version 3 of the License, or
*  (at your option) any later version.
*
*  algs4.jar is distributed in the hope that it will be useful,
*  but WITHOUT ANY WARRANTY; without even the implied warranty of
*  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*  GNU General Public License for more details.
*
*  You should have received a copy of the GNU General Public License
*  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
******************************************************************************/
