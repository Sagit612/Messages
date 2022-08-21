package implementations;
import interfaces.AbstractStack;
import java.util.Iterator;

public class Stack<E> implements AbstractStack<E> {
    private Node<E> top;
    private int size;
    private static class Node<E>{
        E element;
        Node<E> previous;
        public Node(E element) {
            this.element = element;
        }
        public Node(E element, Node<E> previous) {
            this.element = element;
            this.previous = previous;
        }
    }
    public Stack(){

    }
    @Override
    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.previous = top;
        top = newNode;
        this.size++;
    }

    @Override
    public E pop() {
        ensureNonEmpty();
        E element = this.top.element;
        Node<E> temp = this.top.previous;
        this.top.previous = null;
        this.top = temp;
        this.size--;
        return element;
    }

    private void ensureNonEmpty(){
        if (size == 0) throw new IllegalStateException("Stack is Empty!!! Can not pop!");
    }

    @Override
    public String toString() {
        Node<E> current = top;
        StringBuilder result = new StringBuilder();
        while(current!=null){
            result.append(current.element+ " ");
            current = current.previous;
        }
        return result.toString();
    }


    @Override
    public E peek() {
        if(size == 0 ) {
            throw new IllegalStateException("the list is empty");
        }
        Stack.Node<E> current = this.top;
        while (current.previous != null) {
            current = current.previous;
        }
        return current.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = top;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public E next() {
                E element = current.element;
                current = current.previous;
                return element;
            }
        };
    }
}
