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
        return index;
      }
      else
        return null;
    }
    public Integer removeIndex() {
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

  public boolean contains(String key) {
    return get(key) != null;
  }

  public Integer get(String key) {
    return get(root, key);
  }

  private Integer get(Node root, String key) {
    if (root == null) return null;
    int compare = key.compareTo(root.key);
    if (compare < 0)
      return get(root.left, key);
    else if (compare > 0)
      return get(root.right, key);
    else
      return root.get();
  }

  public Integer removeIndex(String key) {
    return removeIndex(root, key);
  }

  private Integer removeIndex(Node root, String key) {
    if (root == null) return null;
    int compare = key.compareTo(root.key);
    if (compare < 0)
      return get(root.left, key);
    else if (compare > 0)
      return get(root.right, key);
    else
      return root.removeIndex();
  }

  public void delete(String key) {
      root = delete(root, key);
  }

  private Node delete(Node x, String key) {
      if (x == null) return null;

      int cmp = key.compareTo(x.key);
      if      (cmp < 0) x.left  = delete(x.left,  key);
      else if (cmp > 0) x.right = delete(x.right, key);
      else {
          if (x.right == null) return x.left;
          if (x.left  == null) return x.right;
          Node t = x;
          x = min(t.right);
          x.right = deleteMin(t.right);
          x.left = t.left;
      }
      x.size = size(x.left) + size(x.right) + 1;
      return x;
  }

  private int size(Node x) {
      if (x == null) return 0;
      else return x.size;
  }

  private Node min(Node x) {
      if (x.left == null) return x;
      else                return min(x.left);
  }

  public void deleteMin() {
      root = deleteMin(root);
  }

  private Node deleteMin(Node x) {
      if (x.left == null) return x.right;
      x.left = deleteMin(x.left);
      x.size = size(x.left) + size(x.right) + 1;
      return x;
  }

  public static void main(String[] args) {
    BST bst = new BST();
    String a = "abc";
    String b = "cu";
    bst.put(a, 1);
    bst.put("abc", 2);
    bst.put(a, 3);
    bst.put(b, 12);
      StdOut.println(bst.removeIndex(a));
StdOut.println(bst.removeIndex(b));
StdOut.println(bst.removeIndex(a));
  }
}
