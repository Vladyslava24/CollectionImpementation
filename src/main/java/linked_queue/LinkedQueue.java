package linked_queue;

/**
 * {@link LinkedQueue} implements FIFO {@link Queue}, using singly linked nodes. Nodes are stores in instances of nested
 * class Node. In order to perform operations {@link LinkedQueue#add(Object)} and {@link LinkedQueue#poll()}
 * in a constant time, it keeps to references to the head and tail of the queue.
 *
 * @param <T> a generic parameter
 */
public class LinkedQueue<T> implements Queue<T> {
    private Node head;
    private Node tail;
    private int size = 0;

    private class Node {
        private T element;
        private Node next;

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to add
     */
    public void add(T element) {
        Node node = new Node();
        node.setElement(element);
        if (head == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    /**
     * Retrieves and removes queue head.
     *
     * @return an element that was retrieved from the head or null if queue is empty
     */
    public T poll() {
        if (size == 0) {
            return null;
        }
        T element = head.getElement();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return element;
    }

    /**
     * Returns a size of the queue.
     *
     * @return an integer value that is a size of queue
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, returns {@code false} if it's not
     */
    public boolean isEmpty() {
        return size == 0;
    }
}

