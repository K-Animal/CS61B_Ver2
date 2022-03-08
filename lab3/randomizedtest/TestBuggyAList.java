package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        BuggyAList<Integer> input = new BuggyAList();
        AListNoResizing<Integer> expected = new AListNoResizing();

        for (int i = 0; i < 3; i++) {
            input.addLast(i);
            expected.addLast(i);
        }

        assertEquals(expected.size(), input.size());
        assertEquals(expected.removeLast(), input.removeLast());
        assertEquals(expected.removeLast(), input.removeLast());
        assertEquals(expected.removeLast(), input.removeLast());
    }

    @Test
    public void randomizedTest() {
        BuggyAList<Integer> buggy = new BuggyAList();
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggy.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeBuggy = buggy.size();
                assertEquals(size, sizeBuggy);
            } else if (operationNumber == 2 && L.size() > 1) {
                // removeLast
                int size = L.size();
                int expected = L.removeLast();
                int buggyValue = buggy.removeLast();
                assertEquals(expected, buggyValue);
            } else if (operationNumber == 3 && L.size() > 1) {
                // getLast
                int size = L.size();
                int expected = L.getLast();
                int buggyValue = buggy.getLast();
                assertEquals(expected, buggyValue);
            }
        }
    }
}
