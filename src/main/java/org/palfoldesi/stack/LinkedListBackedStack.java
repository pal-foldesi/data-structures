package org.palfoldesi.stack;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListBackedStack<T> implements Stack<T>, Iterable<T> {
    private LinkedList<T> items = new LinkedList<>();

    @Override
    public void push(T item) {
        items.add(item);
    }

    @Override
    public T pop() {
        return items.removeLast();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return items.descendingIterator();
    }
}
