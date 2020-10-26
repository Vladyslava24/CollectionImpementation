package stack;

import stack.exception.EmptyStackException;

public class LinkedStack<T> implements Stack<T> {
    private int size = 0;

    private Node top;
    private class Node {
        T element;
        Node next;
    }

    @Override
    public void push(T element) {
        Node oldTop = top;
        top = new Node();
        top.element = element;
        top.next = oldTop;
        size++;
    }

    @Override
    public T pop() {
        if(size == 0){
            throw new EmptyStackException();
        }
        T element = top.element;
        top = top.next;
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
