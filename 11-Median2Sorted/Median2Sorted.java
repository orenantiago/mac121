public class Median2Sorted {
  public static Comparable select(Comparable[] a, Comparable[] b, int k) {
    return select(a, b, 0, a.length, 0, b.length, k);
  }
  private static Comparable select(Comparable [] a, Comparable[] b, int ia, int fa, int ib, int fb, int k) {
    if(ia == fa)
      return a[k];
    if(ib == fb)
      return b[k];
    int midA = (fa - ia) / 2;
    int midB = (fb - ib) / 2;
    if(midA + midB < k + 1) {
      if(a[midA].compareTo(b[midB]) > 0)
        return select(a, b, ia, fa, midB + 1, fb, k - midB);
      else
        return select(a, b, ia + midA + 1, fa, ib, fb, k - midA);
    }
    else {
      if(a[midA].compareTo(b[midB]) > 0)
        return select(a, b, ia, ia + midA, ib, fb, k + 1);
      else
        return select(a, b, ia, fa, ib, ia + midB, k + 1);
    }
  }
  public static void main(String[] args) {
    Integer[] a = {1,2,3, 4, 5};
    Integer[] b = {1,2,3, 8, 12};
    //for(int i = 0; i < 8; i++)
      StdOut.println(select(a, b, 8));
  }
}
