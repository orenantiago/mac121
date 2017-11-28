import java.util.ArrayList;
public class BST {
  private Node root;
  private class Node {
    private String key;
    private ArrayList<Integer> indexes;
    private Node left, right;
    private int size;

    public Node(String key, Integer index, int size) {
        this.key = key;
        this.indexes = new ArrayList();
        this.indexes.add(index);
        this.size = size;
    }
    public void add(Integer index) {
      this.indexes.add(index);
    }
    public Integer get() {
      if(!indexes.isEmpty()) {
        Integer index = indexes.get(0);
        indexes.remove(0);
        return index;
      }
      else
        return null;
    }
  }
  public BST() {}

  public void put(String key, int index) {
    root = put(root, key, index);
  }

  private Node put(Node root, String key, int index) {
    // cria a raiz
    if(root == null) return new Node(key, index, 1);
    int compare = key.compareTo(root.key);
    if(compare < 0)
      root.left = put(root.left, key, index);
    if(compare > 0)
      root.right = put(root.right, key, index);
    else
      root.add(index);
    return root;
  }

  public Integer get(String key) {
    return get(root, key);
  }

  private Integer get(Node root, String key) {
    int compare = key.compareTo(root.key);
    if (compare < 0)
      return get(root.left, key);
    else if (compare > 0)
      return get(root.right, key);
    else
      return root.get();
  }

  public static void main(String[] args) {
    BST bst = new BST();
    String a = "abc";
    String b = "cu";
    bst.put(a, 1);
    bst.put(a, 2);
    bst.put(a, 3);
    bst.put(b, 2);
    bst.put(b, 2);
    bst.put(b, 3);
    StdOut.println(bst.get(a));
    StdOut.println(bst.get(a));
    StdOut.println(bst.get(a));
    StdOut.println(bst.get(b));
    StdOut.println(bst.get(b));
    StdOut.println(bst.get(b));
  }
}
