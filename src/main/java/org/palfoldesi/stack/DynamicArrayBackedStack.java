package org.palfoldesi.stack;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayBackedStack<T> implements Stack<T>, Iterable<T> {

    private T[] items;
    private int n;
    private Class<T> elementType;

    /* No easy way to introduce type safety here...
    See https://www.ibm.com/developerworks/java/library/j-jtp01255/index.html*/

    public DynamicArrayBackedStack(Class<T> elementType) {
        this.elementType = elementType;
        items = (T[]) Array.newInstance(elementType, 20);
    }

    @Override
    public void push(T item) {
        if ((n * 3 / 2) >= items.length) {
            T[] newArray = (T[]) Array.newInstance(elementType, items.length * 2);
            System.arraycopy(items, 0, newArray, 0, n);
            items = newArray;
        }
        items[n] = item;
        n++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty!");
        }
        if (n <= (items.length / 2)) {
            T[] newArray = (T[]) Array.newInstance(elementType, items.length / 2);
            System.arraycopy(items, 0, newArray, 0, n);
            items = newArray;
        }
        n--;
        T item = items[n];
        items[n] = null;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new DynamicArrayBackedStackIterator();
    }

    private class DynamicArrayBackedStackIterator implements Iterator<T> {
        int cursor;

        private DynamicArrayBackedStackIterator() {
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
