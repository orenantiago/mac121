public class Clairvoyant {
  public static void main(String[] args) {
    boolean verbose = args.length > 1;
    int cacheSize = Integer.valueOf(args[0]);
    IndexMaxPQ<String> cache = new IndexMaxPQ<String>(cacheSize);
  }
}
