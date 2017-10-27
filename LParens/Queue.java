// Fila de Strings que fiz para utilizar no programa principal
public class Queue {
    private String value;
    private Node first;
    private Node last;
    private int n;

    private static class Node {
        String value;
        Node next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(String item) {
        Node oldLast = last;
        last = new Node();
        last.value = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            oldLast.next = last;
        n++;
    }

    public String remove() {
        if(isEmpty())
            StdOut.println("Pilha vazia");
        String item = first.value;
        first = first.next;
        n--;
        return item;
    }
}
