package implementations;
import interfaces.LinkedList;
import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head, tail;
    private int size;

    private static class Node<E>{
        Node<E> prev, next;
        E element;

        public Node(E element) {
            this.element = element;
        }

        public Node(Node<E> prev, Node<E> next, E element) {
            this.prev = prev;
            this.next = next;
            this.element = element;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if(size == 0){
            this.head = this.tail = newNode;
        }else {
            newNode.next = this.head;
            this.head = newNode;
            newNode.next.prev = newNode;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0){
            this.head = this.tail = newNode;
        }else {
            Node<E> current = this.head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
            this.tail = current.next;
            current.next.prev = current;
        }
        this.size++;
    }

    @Override
    public E removeFirst() {
        if (this.size == 0){
            throw new IllegalStateException("List is empty");
        }
        E element = this.head.element;
        this.head = this.head.next;
        this.head.prev = null;
        this.size--;
        return element;
    }

    @Override
    public E removeLast() {
        if (this.size == 0){
            throw new IllegalStateException("list is empty");
        }
        E element = this.tail.element;
        this.tail = this.tail.prev;
        this.tail.next = null;
        this.size--;
        return element;
    }
    public void add(int position, E element){
        Node<E> newNode = new Node<>(element);
        if(this.size == 0){
            this.head = this.tail = newNode;
        }else if(position == 1){
            addFirst(element);
        } else if(position < 1){
            throw new IllegalArgumentException("position should be >= 1.");
        }else {
            Node<E> temp = this.head;
            for (int i = 1; i < position - 1; i++){
                if(temp != null){
                    temp = temp.next;
                }
            }
            if(temp != null){
                newNode.next = temp.next;
                newNode.prev = temp;
                temp.next = newNode;
                if (newNode.next != null) newNode.next.prev = newNode;
                this.size++;
            }else {
                throw new IllegalStateException("the previous node is null");
            }
        }
    }

    public void remove(E element){
        if (this.size == 0){
            throw new IllegalStateException("list is empty");
        }else if(element == this.head.element){
            removeFirst();
        }else if(element == this.tail.element){
            removeLast();
        }else {
            Node<E> current = this.head;
            while (current.next != null){
                if (current.element.equals(element)){
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                current = current.next;

            }
            System.out.println(current.element);
//            System.out.println(current.element);
        }
        this.size--;
    }

    @Override
    public E getFirst() {
        return head.element;
    }

    @Override
    public E getLast() {
        return tail.element;
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
        DoublyLinkedList.Node<E> current = this.head;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(current.element+ " ");
            current = current.next;
        }
        return result.toString();
    }
    public String toDesString() {
        DoublyLinkedList.Node<E> current = this.tail;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(current.element+ " ");
            current = current.prev;
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
