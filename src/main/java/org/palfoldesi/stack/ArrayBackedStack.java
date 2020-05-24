package org.palfoldesi.stack;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBackedStack<T> implements Stack<T>, Iterable<T> {

    private T[] items;
    private int n;

    /* No easy way to introduce type safety here...
    See https://www.ibm.com/developerworks/java/library/j-jtp01255/index.html*/

    public ArrayBackedStack(Class<T> elementType, int capacity) {
        items = (T[]) Array.newInstance(elementType, capacity);
    }

    @Override
    public void push(T item) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Stack is full!");
        }
        items[n] = item;
        n++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        n--;
        T item = items[n];
        items[n] = null;
        return item;
    }

    private boolean isFull() {
        return n == items.length;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayBackedStackIterator();
    }

    private class ArrayBackedStackIterator implements Iterator<T> {
        int cursor;

        private ArrayBackedStackIterator() {
            cursor = n;
        }

        @Override
        public boolean hasNext() {
            return cursor > 0;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("At end of stack!");
            } else {
                cursor--;
                return items[cursor];
            }
        }
    }
}
