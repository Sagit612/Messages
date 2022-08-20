package implementations;
import interfaces.LinkedList;
import java.util.Iterator;

public class CircularLinkedList<E> implements LinkedList<E> {
    private Node<E> last;
    private int size;

    private static class Node<E>{
        Node<E> next;
        E element;

        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }

        public Node(E element) {
            this.element = element;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if(this.last != null){
            newNode.next = this.last;
        }
        this.last.next = this.last;
        this.last = newNode;
        this.size++;
    }

    @Override
    public void addLast(E element) {

    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public String toString() {
        CircularLinkedList.Node<E> current = this.last.next;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(current.element+ " ");
            current = current.next;
        }
        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
