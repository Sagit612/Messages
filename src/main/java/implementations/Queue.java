package implementations;

import interfaces.AbstractQueue;
import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private int size;

    private static class Node<E>{
        Node<E> next;
        E element;
        public Node(E element) {
            this.element = element;
        }
        public Node(Node<E> next, E element) {
            this.next = next;
            this.element = element;
        }
    }
    public Queue(){

    }

    @Override
    public void offer(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.head == null){
            this.head = newNode;
        }else {
            Node<E> current = this.head;
            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public E poll() {
        ensureNonEmpty();
        E element = this.head.element;
        if(this.size == 1){
            this.head = null;
        }else {
            Node<E> next = this.head.next; // assign the data of the next element of head
            this.head.next = null; // then assign null to the next element of head to delete it
            this.head = next; // after that assign the data of next to the head (head - member 1 (be deleted) - member 2 - member three)
        }
        this.size --;
        return element;
    }
    private void ensureNonEmpty(){
        if (this.size() == 0) throw new IllegalStateException("Queue is Empty!!! Can not poll!");
    }

    public String toString() {
        Queue.Node<E> current = head;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(current.element+ " ");
            current = current.next;
        }
        return result.toString();
    }

    @Override
    public E peek() {
        ensureNonEmpty();
        return head.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue.Node<E> current = head;
            @Override
            public boolean hasNext() {
                return current!=null;
            }
            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }
}
