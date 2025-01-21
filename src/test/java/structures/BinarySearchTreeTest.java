package structures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.*;
import java.util.concurrent.TimeUnit;

import config.Configuration;

public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree;
    private Iterator<Integer> inItr;
    private Iterator<Integer> preItr;
    private static final int SPEED_TEST = 1 << 10;

    @BeforeEach
    public void setUp() {
        tree = Configuration.createBinarySearchTree();
        assertNotNull(tree, "It looks like you did not set createBinarySearchTree in your configuration file.");
    }

    @Test
    public void testSimpleAddSizeAndIsEmpty() {
        assertTrue(tree.isEmpty(), "Fresh tree should be empty.");
        assertEquals(0, tree.size(), "Fresh tree should have size 0.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(1, tree.size(), "Size should now be 1.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(2, tree.size(), "Size should now be 2.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(3, tree.size(), "Size should now be 3.");
        assertEquals(tree, tree.add(2), "Add should return tree for convenience.");
        assertFalse(tree.isEmpty(), "Tree should now be non-empty.");
        assertEquals(4, tree.size(), "Size should now be 4.");
    }

    @Test
    public void testSimpleAddAndContains() {
        assertFalse(tree.contains(1), "Tree should not contain anything.");
        assertEquals(tree, tree.add(1), "Add should return tree for convenience.");
        assertTrue(tree.contains(1), "After add, contains should return true.");

        assertFalse(tree.contains(5), "Tree should not contain 5.");
        assertEquals(tree, tree.add(5), "Add should return tree for convenience.");
        assertTrue(tree.contains(5), "After add, contains should return true.");
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testRandomAddContains() {
        Random r = new Random(42);
        Set<Integer> valuesAdded = new HashSet<>();
        for(int i = 0; i < SPEED_TEST; i++){
            assertEquals(i, tree.size(), "Tree should have i elements in it.");
            int next = r.nextInt();

            if(!valuesAdded.contains(next)){
                assertFalse(tree.contains(next), "Tree should not contain this value yet.");
                valuesAdded.add(next);
            }

            assertEquals(tree, tree.add(next), "Add should return tree for convenience.");
            assertTrue(tree.contains(next), "After add, contains should return true.");
        }
    }

    @Test
    public void testAddNullPointer() {
        assertThrows(NullPointerException.class,
                () -> tree.add(null));
    }

    @Test
    public void testContainsNullPointer() {
        assertThrows(NullPointerException.class,
                () -> tree.contains(null));
    }

    @Test
    public void testAddRemoveLevel0() {
        // just the root
        tree.add(5);

        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5);

        assertEquals(1, tree.size());
        assertTrue(tree.remove(5));
        assertEquals(0, tree.size());
    }

    @Test
    public void testAddRemoveLevel1() {
        // add method should return the tree
        tree.add(5).add(2).add(7);
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 2, 5, 7);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5, 2, 7);
        assertEquals(3, tree.size());

        assertTrue(tree.remove(2));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 7);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5, 7);
        assertEquals(2, tree.size());

        assertTrue(tree.remove(7));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5);
        assertEquals(1, tree.size());

        assertTrue(tree.remove(5));
        assertEquals(0, tree.size());
    }

    @Test
    public void testAddRemoveLevel2() {
        // add method should return the tree
        tree.add(5).add(2).add(7).add(6).add(4).add(3);
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 2, 3, 4, 5, 6, 7);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5, 2, 4, 3, 7, 6);
        assertEquals(6, tree.size());

        assertTrue(tree.remove(7));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 2, 3, 4, 5, 6);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5, 2, 4, 3, 6);
        assertEquals(5, tree.size());

        assertTrue(tree.remove(4));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 2, 3, 5, 6);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5, 2, 3, 6);
        assertEquals(4, tree.size());

        assertTrue(tree.remove(2));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 3, 5, 6);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5, 3, 6);
        assertEquals(3, tree.size());
    }

    @Test
    public void testAddRemoveLevel3() {
        // full tree with 15 elements
        tree.add(8).add(4).add(2).add(1).add(3).add(6).add(5).add(7);
        tree.add(12).add(10).add(9).add(11).add(14).add(13).add(15);
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 8, 4, 2, 1, 3, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15);
        assertEquals(15, tree.size());

        assertTrue(tree.remove(2));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 8, 4, 3, 1, 6, 5, 7, 12, 10, 9, 11, 14, 13, 15);
        assertEquals(14, tree.size());

        assertTrue(tree.remove(4));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 8, 5, 3, 1, 6, 7, 12, 10, 9, 11, 14, 13, 15);
        assertEquals(13, tree.size());

        assertTrue(tree.remove(12));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 1, 3, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 8, 5, 3, 1, 6, 7, 13, 10, 9, 11, 14, 15);
        assertEquals(12, tree.size());
    }

    @Test
    public void testAddRemoveRoot() {
        tree.add(43).add(24).add(5).add(28).add(26).add(52).add(47).add(60);
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 24, 26, 28, 43, 47, 52, 60);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 43, 24, 5, 28, 26, 52, 47, 60);
        assertEquals(8, tree.size());

        assertTrue(tree.remove(43));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 24, 26, 28, 47, 52, 60);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 47, 24, 5, 28, 26, 52, 60);
        assertEquals(7, tree.size());

        assertTrue(tree.remove(47));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 24, 26, 28, 52, 60);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 52, 24, 5, 28, 26, 60);
        assertEquals(6, tree.size());

        assertTrue(tree.remove(52));
        assertTrue(tree.remove(60));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 24, 26, 28);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 24, 5, 28, 26);
        assertEquals(4, tree.size());

        assertTrue(tree.remove(24));
        assertTrue(tree.remove(26));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 28);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 28, 5);
        assertEquals(2, tree.size());
    }

    @Test
    public void testAddRemoveDuplicates() {
        tree.add(20).add(20).add(20).add(10).add(10).add(5);
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 10, 10, 20, 20, 20);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 20, 20, 20, 10, 10, 5);

        assertTrue(tree.remove(20));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 10, 10, 20, 20);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 20, 20, 10, 10, 5);

        assertTrue(tree.remove(20));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 10, 10, 20);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 20, 10, 10, 5);

        assertTrue(tree.remove(20));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 10, 10);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 10, 10, 5);

        assertFalse(tree.remove(20));
        assertTrue(tree.remove(10));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5, 10);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 10, 5);

        assertTrue(tree.remove(10));
        assertFalse(tree.remove(10));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 5);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 5);
    }

    @Test
    public void testAddRemoveDuplicates2() {
        tree.add(8).add(20).add(7).add(12).add(7).add(10).add(12);
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 7, 7, 8, 10, 12, 12, 20);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 8, 7, 7, 20, 12, 10, 12);

        assertTrue(tree.remove(8));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 7, 7, 10, 12, 12, 20);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 10, 7, 7, 20, 12, 12);

        assertTrue(tree.remove(7));
        assertTrue(tree.remove(20));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 7, 10, 12, 12);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 10, 7, 12, 12);

        assertTrue(tree.remove(10));
        inItr = new MrYoungInorder<>(tree.getRoot());
        assertIteratorContains(inItr, 7, 12, 12);
        preItr = new MrYoungPreorder<>(tree.getRoot());
        assertIteratorContains(preItr, 12, 7, 12);
    }

    @Test
    public void testRemoveFalse() {
        tree.add(10).add(3).add(7).add(5).add(15).add(13);

        assertEquals(6, tree.size());
        assertFalse(tree.remove(1));
        assertFalse(tree.remove(2));
        assertFalse(tree.remove(4));
        assertFalse(tree.remove(6));
        assertFalse(tree.remove(8));
        assertFalse(tree.remove(9));

        assertEquals(6, tree.size());
        assertFalse(tree.remove(11));
        assertFalse(tree.remove(12));
        assertFalse(tree.remove(14));
        assertFalse(tree.remove(16));
        assertEquals(6, tree.size());
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testRandomAddRemoveAndSize() {
        Random r = new Random();
        //Random r = new Random(48); // if you want a seed
        List<Integer> valuesAdded = new LinkedList<>();

        for(int i = 0; i < SPEED_TEST; i++) {
            assertEquals(i, tree.size(), "Tree should have i elements in it.");
            int next = r.nextInt(3 * SPEED_TEST); // should get some repeats
            valuesAdded.add(next);
            tree.add(next);
            assertTrue(tree.contains(next), "After add, contains should return true.");
        }
        assertEquals(SPEED_TEST, tree.size());

        // remove the values in random order
        for(int i = 0; i < SPEED_TEST; i++) {
            int index = r.nextInt(valuesAdded.size());
            int value = valuesAdded.get(index);
            assertTrue(tree.remove(value), "Could not remove previously added node.");
            valuesAdded.remove(index);
        }
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
    }

    @Test
    public void testSimpleGetMinAndGetMax() {
        tree.add(4).add(2).add(1).add(3).add(5).add(6).add(7);
        assertEquals(Integer.valueOf(1), tree.getMinimum());
        assertEquals(Integer.valueOf(7), tree.getMaximum());
    }

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testRandomGetMinAndGetMax() {
        Random r = new Random(42);
        LinkedList<Integer> values = new LinkedList<>();
        int currentMin = Integer.MAX_VALUE;
        int currentMax = Integer.MIN_VALUE;
        for(int i = 0; i < SPEED_TEST; i++) {
            int next = r.nextInt();
            currentMin = Math.min(currentMin, next);
            currentMax = Math.max(currentMax, next);
            values.add(next);
            tree.add(next);
        }

        assertEquals(Integer.valueOf(currentMin), tree.getMinimum());
        assertEquals(Integer.valueOf(currentMax), tree.getMaximum());
    }

    @Test
    public void testIllegalStateGetMin() {
        assertThrows(IllegalStateException.class,
                () -> tree.getMinimum());
    }

    @Test
    public void testIllegalStateGetMax() {
        assertThrows(IllegalStateException.class,
                () -> tree.getMaximum());
    }

    private void assertIteratorContains(Iterator<Integer> itr, Integer ... elements) {
        List<Integer> found = new LinkedList<>();
        for(Integer e : elements) {
            assertTrue(itr.hasNext(),
                    "Expected iterator to produce " + Arrays.toString(elements) + " but produced " + found);
            Integer test = itr.next();
            found.add(test);
            assertEquals(test, e,
                    "Expected iterator to produce " + Arrays.toString(elements) + " but start of iterator produced " + found);
        }

        if(itr.hasNext()){
            while(itr.hasNext())
                found.add(itr.next());
            fail("Expected iterator to produce " + Arrays.toString(elements) + " but produced " + found);
        }
    }
}
