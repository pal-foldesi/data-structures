package org.palfoldesi.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class ArrayBackedStackTest {
    @DisplayName("Is initially empty")
    @Test
    public void givenEmptyStack_whenIsEmptyCalled_thenTrueIsReturned() {
        Stack<String> stack = new ArrayBackedStack<>(String.class, 10);
        boolean actual = stack.isEmpty();
        assertThat(actual).isTrue();
    }

    @DisplayName("Is no longer empty once an item has been added")
    @Test
    public void givenStackWithOneItem_whenIsEmptyCalled_thenFalseIsReturned() {
        Stack<String> stack = new ArrayBackedStack<>(String.class, 10);
        stack.push("hi");
        boolean actual = stack.isEmpty();
        assertThat(actual).isFalse();
    }

    @DisplayName("Is empty when all items have been removed")
    @Test
    public void givenEmptyStack_whenAnItemHasBeenAddedAndRemoved_thenTrueIsReturned() {
        Stack<String> stack = new ArrayBackedStack<>(String.class, 10);
        stack.push("hi");
        stack.pop();
        boolean actual = stack.isEmpty();
        assertThat(actual).isTrue();
    }

    @DisplayName("Can be pushed to while not full")
    @Test
    public void givenNotFullStack_whenPushedTo_thenNoExceptionIsThrown() {
        int capacity = 10;
        Stack<String> stack = new ArrayBackedStack<>(String.class, capacity);
        assertThatCode(() -> {
            for (int i = 0; i < capacity; i++) {
                stack.push(String.valueOf(i));
            }
        }).doesNotThrowAnyException();
    }

    @DisplayName("Pushing an item throws an exception when stack is full")
    @Test
    public void givenFullStack_whenPushedTo_thenExceptionIsThrown() {
        Stack<String> stack = new ArrayBackedStack<>(String.class, 1);
        stack.push("hi");
        assertThatExceptionOfType(ArrayIndexOutOfBoundsException.class)
                .isThrownBy(() -> stack.push("oops"));
    }

    @DisplayName("Can be popped out of while not empty")
    @Test
    public void givenNotEmptyStack_whenPopping_thenNoExceptionIsThrown() {
        int capacity = 10;
        Stack<String> stack = new ArrayBackedStack<>(String.class, capacity);
        for (int i = 0; i < capacity; i++) {
            stack.push(String.valueOf(i));
        }

        assertThatCode(() -> {
            for (int i = 0; i < capacity; i++) {
                stack.pop();
            }
        }).doesNotThrowAnyException();
    }

    @DisplayName("Popping out of when empty throws an exception")
    @Test
    public void givenEmptyStack_whenPopping_thenExceptionIsThrown() {
        Stack<String> stack = new ArrayBackedStack<>(String.class, 10);
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(stack::pop);
    }

    @DisplayName("Returns the correct item when popping")
    @Test
    public void givenNotEmptyStack_whenPopping_thenCorrectItemIsReturned() {
        int capacity = 10;
        Stack<Integer> stack = new ArrayBackedStack<>(Integer.class, capacity);
        for (int i = 0; i < capacity; i++) {
            stack.push(i);
        }
        for (int i = capacity - 1; i >= 0; i--) {
            int actual = stack.pop();
            assertThat(actual).isEqualTo(i);
        }
    }

    @DisplayName("Iterator - hasNext() returns false for an empty stack")
    @Test
    public void givenIteratorOfEmptyStack_whenHasNextCalled_thenFalseIsReturned() {
        int capacity = 10;
        Stack<Integer> stack = new ArrayBackedStack<>(Integer.class, capacity);
        Iterator<Integer> iterator = stack.iterator();
        boolean actual = iterator.hasNext();
        assertThat(actual).isFalse();
    }

    @DisplayName("Iterator - hasNext() returns true for a non-empty stack if not at end")
    @Test
    public void givenIteratorOfNotEmptyStack_whenHasNextCalled_thenTrueIsReturned() {
        int capacity = 10;
        Stack<Integer> stack = new ArrayBackedStack<>(Integer.class, capacity);
        stack.push(0);
        Iterator<Integer> iterator = stack.iterator();
        boolean actual = iterator.hasNext();
        assertThat(actual).isTrue();
    }

    @DisplayName("Iterator - hasNext() returns false for a non-empty stack if at end")
    @Test
    public void givenIteratorOfNotEmptyStack_whenAtEndOfStackAndHasNextCalled_thenFalseIsReturned() {
        int capacity = 10;
        Stack<Integer> stack = new ArrayBackedStack<>(Integer.class, capacity);
        for (int i = 0; i < capacity; i++) {
            stack.push(i);
        }
        Iterator<Integer> iterator = stack.iterator();
        for (int i = 0; i < capacity; i++) {
            iterator.next();
        }
        boolean actual = iterator.hasNext();
        assertThat(actual).isFalse();
    }

    @DisplayName("Iterator - next() returns the correct item")
    @Test
    public void givenIteratorOfNotEmptyStack_whenNextCalled_thenCorrectItemIsReturned() {
        int capacity = 10;
        Stack<Integer> stack = new ArrayBackedStack<>(Integer.class, capacity);
        for (int i = 0; i < capacity; i++) {
            stack.push(i);
        }
        Iterator<Integer> iterator = stack.iterator();
        for (int i = capacity - 1; i >= 0; i--) {
            int actual = iterator.next();
            assertThat(actual).isEqualTo(i);
        }
    }

    @DisplayName("Iterator - next() throws exception if at end")
    @Test
    public void givenIteratorOfNotEmptyStack_whenNextCalledAtEnd_thenExceptionIsThrown() {
        int capacity = 10;
        Stack<Integer> stack = new ArrayBackedStack<>(Integer.class, capacity);
        for (int i = 0; i < capacity; i++) {
            stack.push(i);
        }
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(iterator::next);
    }
}
