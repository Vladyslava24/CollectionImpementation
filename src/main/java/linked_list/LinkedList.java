package linked_list;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}. In order to keep track on nodes, {@link LinkedList} keeps a reference to a head node.
 *
 * @param <T> generic type parameter
 */
public class LinkedList<T> implements List<T> {
    transient int size = 0;
    transient Node<T> first;
    transient Node<T> last;

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        public Node(T item) {
            this.item = item;
        }

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> List<T> of(T... elements) {
        List<T> list = new LinkedList<>();
        for (T element : elements) {
            list.add(element);
        }
        return list;
    }

    /**
     * Adds an element to the end of the list
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node newNode = new Node(element);
        if (first == null) {
            newNode.next = null;
            newNode.prev = null;
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index >= size+1) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        if (index == 0) {
            addFirst(element);
        }
        else if (index == size) {
            addLast(element);
        } else {
            Node<T> current = first;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<T> temp = current.next;
            current.next = new Node<T>(element);
            (current.next).next = temp;
            size++;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(T element) {
        Node<T> newNode = new Node<T>(element); // Create a new node
        newNode.next = first; // link the new node with the head
        first = newNode; // head points to the new node
        size++; // Increase list size
        if (last == null) // the new node is the only node in list
            last = first;
    }

    /** Add an element to the end of the list */
    public void addLast(T element) {
        Node<T> newNode = new Node<T>(element); // Create a new for element e

        if (last == null) {
            first = last = newNode; // The new node is the only node in list
        }
        else {
            last.next = newNode; // Link the new with the last node
            last = last.next; // tail now points to the last node
        }

        size++;
    }


    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        //Node newNode = new Node(element);
        if (index == 0) {
            first.item = element;
        }
        else if (index == size) {
            last.item = element;
        } else {
            Node<T> current = first;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            (current.next).item = element;

        }

    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }

        return result.item;
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        else if (index == 0) {
             removeFirst();
        }
        else if (index == size - 1) {
             removeLast();
        }
        else {
            Node<T> prev = first;

            for (int i = 1; i < index; i++) {
                prev = prev.next;
            }

            Node<T> current = prev.next;
            prev.next = current.next;
            size--;
        }
    }

    /** Remove the head node and
     *  return the object that is contained in the removed node. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        else {
            Node<T> temp = first;
            first = first.next;
            size--;
            if (first == null) {
                last = null;
            }
            return temp.item;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            Node<T> temp = first;
            first = last = null;
            size = 0;
            return temp.item;
        }
        else {
            Node<T> current = first;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            Node<T> temp = last;
            last = current;
            last.next = null;
            size--;
            return temp.item;
        }
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (get(i) == element) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        if(first == null)
            return 0;
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }
}
