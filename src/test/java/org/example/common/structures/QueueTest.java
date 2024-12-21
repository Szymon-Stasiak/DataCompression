package org.example.common.structures;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueTest {

    @Test
    void shouldProperlyCreateQueue() {
        Queue<Integer> queue = new Queue<>();
        assertNotNull(queue);
    }

    @Test
    void shouldProperlyAddElement() {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertNotNull(queue);
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }

    @Test
    void shouldProperlyPollElement() {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertNotNull(queue);
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertNull(queue.poll());
    }

    @Test
    void shouldProperlyCheckIfQueueIsEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertTrue(queue.isEmpty());
        queue.add(1);
        assertFalse(queue.isEmpty());
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    @Test
    void shouldProperlyPeekElement() {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertNotNull(queue);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.peek());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.peek());
        assertEquals(3, queue.poll());
        assertNull(queue.peek());
    }

    @Test
    void shouldProperlyPeekElement_WhenQueueIsEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertNull(queue.peek());
    }

    @Test
    void shouldProperlyPollElement_WhenQueueIsEmpty() {
        Queue<Integer> queue = new Queue<>();
        assertNull(queue.poll());
    }

    @Test
    void shouldProperlyAddElement_WhenQueueIsEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        assertNotNull(queue);
        assertEquals(1, queue.poll());
        assertNull(queue.poll());
    }

    @Test
    void shouldProperlyAddElement_WhenQueueIsNotEmpty() {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(2);
        assertNotNull(queue);
        assertEquals(1, queue.poll());
        queue.add(3);
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }
}
