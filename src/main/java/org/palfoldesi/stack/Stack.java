package org.palfoldesi.stack;

public interface Stack<T> extends Iterable<T> {
    void push(T item);
    T pop();
    boolean isEmpty();
}