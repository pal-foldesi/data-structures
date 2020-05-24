package org.palfoldesi.stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class LinkedListBackedStackTest {
    @DisplayName("Is initially empty")
    @Test
    public void givenEmptyStack_whenIsEmptyCalled_thenTrueIsReturned() {
        Stack<String> stack = new LinkedListBackedStack<>();
        boolean actual = stack.isEmpty();
        assertThat(actual).isTrue();
    }

    @DisplayName("Is no longer empty once an item has been added")
    @Test
    public void givenStackWithOneItem_whenIsEmptyCalled_thenFalseIsReturned() {
        Stack<String> stack = new LinkedListBackedStack<>();
        stack.push("hi");
        boolean actual = stack.isEmpty();
        assertThat(actual).isFalse();
    }

    @DisplayName("Is empty when all items have been removed")
    @Test
    public void givenEmptyStack_whenAnItemHasBeenAddedAndRemoved_thenTrueIsReturned() {
        Stack<String> stack = new LinkedListBackedStack<>();
        stack.push("hi");
        stack.pop();
        boolean actual = stack.isEmpty();
        assertThat(actual).isTrue();
    }

    @DisplayName("Can be popped out of while not empty")
    @Test
    public void givenNotEmptyStack_whenPoppingf_thenNoExceptionIsThrown() {
        int itemCount = 10;
        Stack<String> stack = new LinkedListBackedStack<>();
        for (int i = 0; i < itemCount; i++) {
            stack.push(String.valueOf(i));
        }

        assertThatCode(() -> {
            for (int i = 0; i < itemCount; i++) {
                stack.pop();
            }
        }).doesNotThrowAnyException();
    }

    @DisplayName("Popping out of when empty throws an exception")
    @Test
    public void givenEmptyStack_whenPopping_thenExceptionIsThrown() {
        Stack<String> stack = new LinkedListBackedStack<>();
        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(stack::pop);
    }

    @DisplayName("Returns the correct item when popping")
    @Test
    public void givenNotEmptyStack_whenPopping_thenCorrectItemIsReturned() {
        int itemCount = 10;
        Stack<Integer> stack = new LinkedListBackedStack<>();
        for (int i = 0; i < itemCount; i++) {
            stack.push(i);
        }
        for (int i = itemCount - 1; i >= 0; i--) {
            int actual = stack.pop();
            assertThat(actual).isEqualTo(i);
        }
    }

    @DisplayName("Iterator - hasNext() returns false for an empty stack")
    @Test
    public void givenIteratorOfEmptyStack_whenHasNextCalled_thenFalseIsReturned() {
        Stack<Integer> stack = new LinkedListBackedStack<>();
        Iterator<Integer> iterator = stack.iterator();
        boolean actual = iterator.hasNext();
        assertThat(actual).isFalse();
    }

    @DisplayName("Iterator - hasNext() returns true for a non-empty stack if not at end")
    @Test
    public void givenIteratorOfNotEmptyStack_whenHasNextCalled_thenTrueIsReturned() {
        Stack<Integer> stack = new LinkedListBackedStack<>();
        stack.push(0);
        Iterator<Integer> iterator = stack.iterator();
        boolean actual = iterator.hasNext();
        assertThat(actual).isTrue();
    }

    @DisplayName("Iterator - hasNext() returns false for a non-empty stack if at end")
    @Test
    public void givenIteratorOfNotEmptyStack_whenAtEndOfStackAndHasNextCalled_thenFalseIsReturned() {
        int itemCount = 10;
        Stack<Integer> stack = new LinkedListBackedStack<>();
        for (int i = 0; i < itemCount; i++) {
            stack.push(i);
        }
        Iterator<Integer> iterator = stack.iterator();
        for (int i = 0; i < itemCount; i++) {
            iterator.next();
        }
        boolean actual = iterator.hasNext();
        assertThat(actual).isFalse();
    }

    @DisplayName("Iterator - next() returns the correct item")
    @Test
    public void givenIteratorOfNotEmptyStack_whenNextCalled_thenCorrectItemIsReturned() {
        int itemCount = 10;
        Stack<Integer> stack = new LinkedListBackedStack<>();
        for (int i = 0; i < itemCount; i++) {
            stack.push(i);
        }
        Iterator<Integer> iterator = stack.iterator();
        for (int i = itemCount - 1; i >= 0; i--) {
            int actual = iterator.next();
            assertThat(actual).isEqualTo(i);
        }
    }

    @DisplayName("Iterator - next() throws exception if at end")
    @Test
    public void givenIteratorOfNotEmptyStack_whenNextCalledAtEnd_thenExceptionIsThrown() {
        int itemCount = 10;
        Stack<Integer> stack = new LinkedListBackedStack<>();
        for (int i = 0; i < itemCount; i++) {
            stack.push(i);
        }
        Iterator<Integer> iterator = stack.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }

        assertThatExceptionOfType(NoSuchElementException.class).isThrownBy(iterator::next);
    }
}
