import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

public class BoundedQueueTest {
    /** Initialize Queue to be used in the tests */
    private BoundedQueue q;
    private static int CAPACITY = 60;

    @Before
    public void makeBoundedQueue() {
        q = new BoundedQueue(CAPACITY);
    }

    /** Test if a queue is empty during construction */
    @Test
    public void testNewQueueIsEmpty() {
        assertTrue(q.isEmpty());
    }


    /** If x is enqueued, then dequeued, the value dequeued is equal to x. */
    @Test
    public void testEnqueueThenDequeue() {
        int queueVal = 100;

        q.enQueue(queueVal);
        assertEquals(q.deQueue(), queueVal);
    }

    /**If one enqueues the values 1 through CAPACITY, in order, into an empty queue, then dequeues CAPACITY items, the values dequeued are 1 through CAPACITY and no Exceptions are thrown.
     */
    @Test
    public void testCapacityInThenCapacityOut() {
        for (int i = 1; i <= CAPACITY; i++) {
            q.enQueue(i);
        }
        for (int i = 1; i <= CAPACITY; i++) {
            assertEquals(((Integer)q.deQueue()).intValue(), i);
        }
    }

    @Test
    public void testRemovingDownToEmpty() {
        int numberOfRemoves = (int)(Math.random() * 20 + 1);

        for (int i = 0; i < numberOfRemoves; i++) {
            q.enQueue(i);
        }
        for (int i = 0; i < numberOfRemoves; i++) {
            q.deQueue();
        }

        assertTrue(q.isEmpty());
    }

    /** Dequeueing from an empty queue throws a NoSuchElementException */
    @Test(expected=IllegalStateException.class)
    public void testDequeueOnEmptyQueue() {
        assertTrue(q.isEmpty());
        q.deQueue();
    }

    /** Peeking into an empty queue does throw a NoSuchElementException */
    @Test(expected=IllegalStateException.class)
    public void testPeekIntoEmptyQueue() {
        assertTrue(q.isEmpty());
        q.deQueue();
    }
    /** After n enqueues to an empty queue where n > 0, the queue is not empty and its size is n */
    @Test
    public void testInsertsToEmptyQueue() {
        int numberOfInserts = 6;

        for (int i = 0; i < numberOfInserts; i++) {
            q.enQueue(i);
        }

        assertTrue(!q.isEmpty());
    }

    /** Enqueueing onto a full queue does throw an IllegalStateException */
    @Test(expected=IllegalStateException.class)
    public void testEnqueueToFullQueue() {
        for (int i = 0; i < CAPACITY; i++) {
            q.enQueue(i);
        }
        q.enQueue(10);
    }
}

