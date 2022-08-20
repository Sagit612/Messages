package implementations;
import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head; // head node of the list (or null if empty)
    private int size; // number of nodes in the list

    // Nested Node class
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

    public SinglyLinkedList(){} // constructs an initially empty list
    @Override
    public void addFirst(E element) { // adds element e to the front of the list
        Node<E> newNode = new Node<>(element); // create and link a new node
        if(this.head != null){
            newNode.next = this.head;
        }
        this.head = newNode;
        this.size++;
    }

    @Override
    public void addLast(E element) { // adds element e to the end of the list
        Node<E> newNode = new Node<>(element);
        if(this.head == null){ // special case: previously empty list
            this.head = newNode;
        }
        else {
            Node<E> current = this.head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        this.size++;
    }

    @Override
    public E removeFirst() { // removes and returns the first element
        if(size == 0 ){ // nothing to remove
            throw new IllegalStateException("the list is empty");
        }
        E element = this.head.element;
        this.head = this.head.next;
        size--;
        return element;
    }

    @Override
    public E removeLast() { // removes and returns the last element
        if(size == 0 ){
            throw new IllegalStateException("the list is empty");
        }
        E element = this.head.element;
        if (this.size == 1){
            this.head = null;
        }
        else {
            Node<E> current = this.head;
            Node<E> temp = current;
            while (current.next != null){
                temp = current;
                current = current.next;
            }
            element = current.element;
            temp.next = null;
        }
        this.size--;
        return element;
    }
    public void remove(E element){ // removes the certain element
        if(size == 0 ){
            throw new IllegalStateException("the list is empty");
        }else if (this.size == 1){
            this.head = null;
        }else if (this.head.element.equals(element)){
            removeFirst();
        } else {
            Node<E> current = this.head;
            Node<E> temp = current;
            while (current.next != null){
                if (current.next.next == null){
                    System.out.println(current.next.element);
                    removeLast();
                    return;
                }
                if (current.next.element.equals(element)){
                    System.out.println(current.next.element);
                    current.next = current.next.next;
                }
                current = current.next;
            }
        }
        this.size--;
    }

    @Override
    public E getFirst() { // returns (but does not remove) the first element
        if(size == 0 ) {
            throw new IllegalStateException("the list is empty");
        }
        return head.element;
    }

    @Override
    public E getLast() { // returns (but does not remove) the last element
        if(size == 0 ) {
            throw new IllegalStateException("the list is empty");
        }
        Node<E> current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        SinglyLinkedList.Node<E> current = this.head;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(current.element+ " ");
            current = current.next;
        }
        return result.toString();
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head;
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
