package org.example.Structures;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static utils.Mocks.*;
import static utils.TestUtils.checkMinHeap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.example.Structures.Basics.Pair;
import org.example.Structures.Basics.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import utils.AdvancedGetters;

class HeapPriorityQueueTest {

    private HeapPriorityQueue heapPriorityQueue;

    @Mock
    private Alphabet alphabetMock;

    @BeforeEach
    void setUp() {
        alphabetMock = alphabetPartMock();
        heapPriorityQueue = new HeapPriorityQueue(alphabetMock);
    }

    @Test
    void should_properlyCreateHeapPriorityQueue() {
        assert (checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));
    }

    @Test
    void should_properlyPoll() {
        heapPriorityQueue.poll();
        assert (checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26})
    void should_properlyPoll_WhenPolling(int polls) {
        for (int i = 0; i < polls; i++) {
            heapPriorityQueue.poll();
        }
        assert (checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));
    }

    @Test
    void should_properlyPoll_WhenPollingAll() {
        for (int i = 0; i < 26; i++) {
            heapPriorityQueue.poll();
        }
        assertNull(heapPriorityQueue.poll());
        assert (checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26})
    void should_properlyAdd(int adds) {
        int RandomValues[] = {
            34, 7, 12, 56, 78, 90, 43, 21, 67, 89, 45, 32, 15, 60, 73, 28, 92, 10, 49, 31, 83, 25, 66, 88, 39, 54
        };
        Alphabet alphabetMock = mock(Alphabet.class);
        heapPriorityQueue = new HeapPriorityQueue(alphabetMock);
        for (int i = 0; i < adds; i++) {
            Pair pair = pairMock((char) (i + 'a'), RandomValues[i]);
            TreeNode treeNode = treeNodeMock(pair);

            heapPriorityQueue.add(treeNode);
        }
        assert (checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));
    }

    @Test
    void should_properlySwap() {
        try {
            Method method = heapPriorityQueue.getClass().getDeclaredMethod("swap", int.class, int.class);
            method.setAccessible(true);
            method.invoke(heapPriorityQueue, 0, 1);
            assert (!checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            fail("Method invocation failed");
        }
    }

    @Test
    void should_properlyNotSwap() {
        try {
            Method method = heapPriorityQueue.getClass().getDeclaredMethod("swap", int.class, int.class);
            method.setAccessible(true);
            method.invoke(heapPriorityQueue, 1, 1);
            assert (checkMinHeap(AdvancedGetters.getHeap(heapPriorityQueue)));

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            fail("Method invocation failed");
        }
    }
}
