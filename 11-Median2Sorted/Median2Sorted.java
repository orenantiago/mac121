public class Median2Sorted {
  public static Comparable select(Comparable[] a, Comparable[] b, int k) {
    return select(a, b, 0, a.length, 0, b.length, k);
  }
  private static Comparable select(Comparable [] a, Comparable[] b, int ia, int fa, int ib, int fb, int k) {
    if(ia == fa)
      return b[k];
    if(ib == fb)
      return a[k];
    int midA = (fa - ia) / 2;
    int midB = (fb - ib) / 2;
    if(midA + midB < k) {
      // StdOut.println("midA "+ midA);
      // StdOut.println("midB "+ midB);
      if(a[midA].compareTo(b[midB]) > 0)
        return select(a, b, ia, fa, ib + midB + 1, fb, k - midB - 1);

      else
        return select(a, b, ia + midA + 1, fa, ib, fb, k - midA - 1);

    }
    //3
    else {
      if(a[midA].compareTo(b[midB]) > 0)
        return select(a, b, ia, ia + midA, ib, fb, k);

      else
        return select(a, b, ia, fa, ib, ib + midB, k);

    }
  }
  public static void main(String[] args) {
    Integer[] a = {2, 3, 6, 7, 9};//{1,2,3, 4, 5};
    Integer[] b = {1, 4, 8, 10};//{1,2,3, 8, 12};
    //for(int i = 0; i < 9; i++)
      StdOut.println(select(a, b, 6));
  }
}
