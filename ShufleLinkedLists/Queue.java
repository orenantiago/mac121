/******************************************************************************
 *  Compilation:  javac Queue.java
 *  Execution:    java Queue < input.txt
 *  Data files:   http://introcs.cs.princeton.edu/43stack/tobe.txt
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java Queue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue} class represents a first-in-first-out (FIFO)
 * queue of generic items.
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the top item,
 * testing if the queue is empty, getting the number of items in the
 * queue, and iterating over the items in FIFO order.
 * <p>
 * This implementation uses a singly-linked list with a nested class for
 * linked-list nodes.
 * The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 * operations all take constant time in the worst case.
 * <p>
 * For additional documentation, see <a href="http://introcs.cs.princeton.edu/43stack">Section 4.3</a> of
 * <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @param <Item> the generic type of an item in this queue
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Queue<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int length() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Add the item to the queue.
     */
    public void enqueue(Item item) {
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }


    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /******************************************************************************
     *
     * MAC0121 - Algoritmos e Estruturas de Dados I
     * Aluno: Renan Tiago dos Santos Silva
     * Numero USP: 9793606
     * Tarefa: LParens
     * Data: 27/10/2017
     *
     * O algoritmo, como sugerido pelo monitor, é próximo de um Merge sort, no
     * sentido de seguir a abordagem de divisão e conquista, e ser de complexidade logarítmica,
     * e o que garante que nenhuma permutação é mais privilegiada é que em todo
     * momento, a escolha de qual elemento das duas filas da divisão vai na fila
     * é feita através de um número pseudo aleatório produzido pela classe
     * java.Math.
     * Como eu não crio elementos novos para as filas que crio, mas apenas atribuo,
     * por referência, os elementos das da fila que já existe inicialmente, o
     * programa também se adequa à restrição de espaço utilizado.
     *
     *
     * DECLARO QUE SOU O ÚNICO AUTOR E RESPONSÁVEL POR ESTE PROGRAMA.  TODAS AS
     * PARTES DO PROGRAMA, EXCETO AS QUE SÃO BASEADAS EM MATERIAL FORNECIDO
     * PELO PROFESSOR OU COPIADAS DO LIVRO OU DAS BIBLIOTECAS DE SEDGEWICK & WAYNE,
     * FORAM DESENVOLVIDAS POR MIM.  DECLARO TAMBÉM QUE SOU RESPONSÁVEL POR TODAS
     * AS CÓPIAS DESTE PROGRAMA E QUE NÃO DISTRIBUÍ NEM FACILITEI A DISTRIBUIÇÃO
     * DE CÓPIAS DESTA PROGRAMA.
     *
     ******************************************************************************/

    public void shuffle() {
        if (this.n == 1) {
            return;
        }
        Queue<Item> q1 = new Queue<Item>();
        q1.first = first;

        Node aux = first;

        // caminho até a metade da fila para poder dividi-la em duas filas
        for (int i = 1; i < n / 2; i++)
            aux = aux.next;

        q1.last = aux;
        q1.n = n / 2;

        Queue<Item> q2 = new Queue<Item>();
        q2.n = n % 2 == 0 ? n / 2 : n / 2 + 1;
        q2.first = aux.next;
        q2.last = last;
        aux.next = null;

        // Divisão e conquista
        q1.shuffle();
        q2.shuffle();

        Node nodeQ1 = q1.first;
        Node nodeQ2 = q2.first;

        // Sorteio e defino o primeiro elemento da fila, para a partir dele definir os outros em um loop
        double luck = Math.random();
        if (luck < 0.5) {
            first = nodeQ1;
            nodeQ1 = nodeQ1.next;
        } else {
            first = nodeQ2;
            nodeQ2 = nodeQ2.next;
        }

        // definido o primeiro elemento da fila, sorteio os próximos de maneira aleatória
        aux = first;
        while (nodeQ1 != null && nodeQ2 != null) {
            luck = Math.random();
            if (luck < 0.5) {
                aux.next = nodeQ1;
                nodeQ1 = nodeQ1.next;
            } else {
                aux.next = nodeQ2;
                nodeQ2 = nodeQ2.next;
            }
            aux = aux.next;
        }

        while (nodeQ1 != null) {
            aux.next = nodeQ1;
            nodeQ1 = nodeQ1.next;
            aux = aux.next;
        }

        while (nodeQ2 != null) {
            aux.next = nodeQ2;
            nodeQ2 = nodeQ2.next;
            aux = aux.next;
        }
    }

    /**
     * Unit tests the {@code Queue} data type.
     */
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) queue.enqueue(item);
            else if (!queue.isEmpty()) StdOut.print(queue.dequeue() + " ");
        }
        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
