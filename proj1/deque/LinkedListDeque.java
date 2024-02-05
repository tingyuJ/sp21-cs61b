package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private Node prev;
        private T value;
        private Node next;
        Node(Node p, T v, Node n) {
            prev = p;
            value = v;
            next = n;
        }
    }
    private Node sentF;
    private Node sentB;
    private int size;

    public LinkedListDeque() {
        sentF = new Node(null, null, null);
        sentB = new Node(sentF, null, null);
        sentF.next = sentB;
        size = 0;
    }

    // Autograder said:
    // The following constructors should be removed:
    //  *  public deque.LinkedListDeque(deque.LinkedListDeque)
    private LinkedListDeque(LinkedListDeque other) {
        sentF = new Node(null, null, null);
        sentB = new Node(sentF, null, null);
        sentF.next = sentB;
        size = other.size;

        Node p = other.sentF.next;
        for (int i = 0; i < size; i++) {
            Node prev = sentB.prev;
            sentB.prev = new Node(prev, p.value, sentB);
            prev.next = sentB.prev;
            p = p.next;

            //the other approach without the p => casting with (T)
            //addLast((T)other.get(i));
        }
    }

    @Override
    public void addFirst(T item) {
        Node next = sentF.next;
        sentF.next = new Node(sentF, item, next);
        next.prev = sentF.next;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node prev = sentB.prev;
        sentB.prev = new Node(prev, item, sentB);
        prev.next = sentB.prev;
        size++;
    }

//    @Override
//    public boolean isEmpty() {
//        return sentF.next == sentB;
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node cur = sentF.next;
        while (cur != sentB) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node item = sentF.next;
        sentF.next = item.next;
        item.next.prev = sentF;
        size--;
        return item.value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node item = sentB.prev;
        sentB.prev = item.prev;
        item.prev.next = sentB;
        size--;
        return item.value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        Node cur = sentF.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.value;
    }

    public T getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        LinkedListDeque copy = new LinkedListDeque(this);
        return (T) copy.getRecursiveHelper(index);
    }

    private T getRecursiveHelper(int index) {
        if (index == 0) {
            return sentF.next.value;
        } else {
            removeFirst();
            return getRecursiveHelper(index - 1);
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node p;

        LinkedListDequeIterator() {
            p = sentF.next;
        }

        public boolean hasNext() {
            return p != sentB;
        }

        public T next() {
            T item = p.value;
            p = p.next;
            return item;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof LinkedListDeque) {
            LinkedListDeque<T> lld = (LinkedListDeque<T>) o;
            if (lld.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!lld.get(i).equals(get(i))) {
//            if (lld.get(i) != get(i)) {
                    return false;
                }
            }
        } else if (o instanceof ArrayDeque) {
            ArrayDeque<T> ad = (ArrayDeque<T>) o;
            if (ad.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {

                if (!ad.get(i).equals(get(i))) {
//            if (ad.get(i) != get(i)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

//    public static void main(String[] args) {
//        LinkedListDeque lld = new LinkedListDeque();
//        ArrayDeque lld1 = new ArrayDeque();
//        var nf = lld.removeFirst();
//        var nl = lld.removeLast();
//        System.out.println(lld.isEmpty());
//        System.out.println(lld.size());
//        lld.addFirst(1);
//        lld.printDeque();
//        lld.addLast(3);
//        lld.printDeque();
//        lld.addLast(3);
//        lld.printDeque();
//        lld.addFirst(0);
//        lld.printDeque();
//        var zero = lld.get(0);
//        var two = lld.get(2);
//        var three = lld.get(3);
//
//        System.out.println(lld.isEmpty());
//        System.out.println(lld.size());
//
//        lld1.addFirst(1);
//        lld1.printDeque();
//        lld1.addLast(3);
//        lld1.printDeque();
//        System.out.println(lld.equals(lld1));
//    }
}
