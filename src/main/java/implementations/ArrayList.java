package implementations;

import interfaces.List;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 4;
    private Object[] elements;
    private int size;

    public ArrayList(){
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow(){
        return Arrays.copyOf(this.elements, this.elements.length * 2);
    }

    private E getElement(int index) {
        return (E) this.elements[index];
    }

    private void ensureCapacity(){
        if (this.size < this.elements.length / 3){
            this.elements = shrink();
        }
    }
    private void shift(int index){
        for (int i=index;i<size-1;i++){
            elements[i] = elements[i+1];
        }
    }

    private Object[] shrink(){
        return Arrays.copyOf(this.elements, this.elements.length / 2);
    }

    private void checkIndex(int index){
        if (index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException(String.format("Index out of bounds: %d for size: %d", index, this.size));
        }
    }
    private void insert(int index, E element){
        if (this.size == this.elements.length){
            this.elements = grow();
        }
        E lastElement = this.getElement(this.size - 1);
        for (int i = this.size - 1; i > index; i--){
            this.elements[i] = this.elements[i-1];
        }
        this.elements[this.size] = lastElement;
        this.elements[index] = element;
        this.size++;
    }
    @Override
    public boolean add(E element) {
        if (this.size == this.elements.length){
            this.elements = grow();
        }
        this.elements[this.size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        checkIndex(index);
        insert(index,element);
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return this.getElement(index);
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        E oldElement = this.getElement(index);
        this.elements[index] = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        this.checkIndex(index);
        E element = this.getElement(index);
        this.elements[index] = null;
        shift(index);
        this.size--;
        ensureCapacity();
        return element;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }
}
