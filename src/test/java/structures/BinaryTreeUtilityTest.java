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

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import config.Configuration;

public class BinaryTreeUtilityTest {

	private BinaryTreeNode<Integer> root1, root2, root3, root4, root5, root6;
	private BinaryTreeNode<String> rootString;
	private BinaryTreeUtility utility;

	// This tree has over 2 million nodes (n = 20)
	private final BinaryTreeNode<Integer> largeTree = initLargeTree(20);

	private static final <T> BinaryTreeNode<T> node(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
		return Configuration.createBinaryTreeNode(left, elem, right);
	}

	@BeforeEach
	public void setUp() {
		utility = Configuration.createBinaryTreeUtility();
		assertNotNull(utility,
				"It looks like you have not set your implementation of BinaryTreeUtility in the Configuration file.");
		root1 = initRoot1();
		root2 = initRoot2();
		root3 = initRoot3();
		root4 = initRoot4();
		root5 = initRoot5();
		root6 = initRoot6();
		rootString = initRootString();
		assertNotNull(root1, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(rootString, "It looks like your configuration file isn't set for BinaryTreeNode.");
	}

	// Creates a full tree with n levels
	private BinaryTreeNode<Integer> initLargeTree(int n) {
		// for n = 2
		//                  2
		//              /       \
		//             1         1
		//            / \       /  \
		//           0   0     0    0

		if(n < 0) return null;
		return node(initLargeTree(n - 1), n, initLargeTree(n - 1));
	}

	private BinaryTreeNode<Integer> initRoot1() {
		//       5
		return node(null, 5, null);
	}

	private BinaryTreeNode<Integer> initRoot2() {
		//       5
		//        \
		//         7
		return node(null,
				5,
				node(null, 7, null)
		);
	}

	private BinaryTreeNode<Integer> initRoot3() {
		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		return
				node(
						node(
								node(null, 6, null),
								3,
								node(null, 7, null)
						),
						5,
						node(
								node(
										node(null, 4, null),
										1,
										null),
								19,
								null)
				);
	}

	private BinaryTreeNode<Integer> initRoot4() {
		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     11
		//                    /
		//                   14
		return
				node(
						node(
								node(null, 1, null),
								3,
								node(null, 2, null)
						),
						5,
						node(
								node(
										node(null, 14, null),
										11,
										null),
								19,
								null)
				);
	}

	private BinaryTreeNode<Integer> initRoot5() {
		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		return
				node(
						node(
								node(null, 1, null),
								3,
								node(null, 4, null)
						),
						5,
						node(
								node(null, 7, null),
								19,
								node(null, 25, null)
						)
				);
	}

	private BinaryTreeNode<Integer> initRoot6() {
		//                   20
		//               /       \
		//             10        35
		//           /   \     /   \
		//          9    13  25    37
		//        /  \      /  \
		//       5   27   19   29
		//     /                \
		//    3                 32
		//     \                /
		//      4              30
		BinaryTreeNode<Integer> leaf4 = node(null, 4, null);
		BinaryTreeNode<Integer> leaf30 = node(null, 30, null);
		BinaryTreeNode<Integer> node3 = node(null, 3, leaf4);
		BinaryTreeNode<Integer> node32 = node(leaf30, 32, null);
		BinaryTreeNode<Integer> node5 = node(node3, 5, null);
		BinaryTreeNode<Integer> leaf7 = node(null, 7, null);
		BinaryTreeNode<Integer> leaf19 = node(null, 19, null);
		BinaryTreeNode<Integer> node29 = node(null, 29, node32);
		BinaryTreeNode<Integer> node9 = node(node5, 9, leaf7);
		BinaryTreeNode<Integer> leaf13 = node(null, 13, null);
		BinaryTreeNode<Integer> node25 = node(leaf19, 25, node29);
		BinaryTreeNode<Integer> leaf37 = node(null, 37, null);
		BinaryTreeNode<Integer> node10 = node(node9, 10, leaf13);
		BinaryTreeNode<Integer> node35 = node(node25, 35, leaf37);
		BinaryTreeNode<Integer> root20 = node(node10, 20, node35);
		return root20;
	}

	private BinaryTreeNode<String> initRootString() {
		//               "life"
		//              /      \
		//          "is"      "like"
		//           /            \
		//         "a"           "of"
		//          \
		//         "box"
		//            \
		//          "chocolates"
		return
				node(
						node(
								node(null,
										"a",
										node(null,
												"box",
												node(null, "chocolates", null)
										)
								),
								"is",
								null),
						"life",
						node(null,
								"like",
								node(null, "of", null)
						)
				);
	}

	private BinaryTreeNode<Integer> initDupYes() {
		//                  20
		//               /      \
		//             10       20
		//           /   \        \
		//          9    10       25
		//        /  \     \     /  \
		//       5    9    19   23  25
		BinaryTreeNode<Integer> leaf5 = node(null, 5, null);
		BinaryTreeNode<Integer> leaf9 = node(null, 9, null);
		BinaryTreeNode<Integer> leaf19 = node(null, 19, null);
		BinaryTreeNode<Integer> leaf23 = node(null, 23, null);
		BinaryTreeNode<Integer> leaf25 = node(null, 25, null);
		BinaryTreeNode<Integer> node9 = node(leaf5, 9, leaf9);
		BinaryTreeNode<Integer> node10 = node(null, 10, leaf19);
		BinaryTreeNode<Integer> node25 = node(leaf23, 25, leaf25);
		BinaryTreeNode<Integer> parent10 = node(node9, 10, node10);
		BinaryTreeNode<Integer> node20 = node(null, 20, node25);
		BinaryTreeNode<Integer> root20 = node(parent10, 20, node20);
		return root20;
	}

	private BinaryTreeNode<Integer> initDupNo() {
		//                  20
		//               /      \
		//             20       35
		//           /        /   \
		//          9       25    37
		//        /  \     /  \    \
		//       9   17   25  29   37
		BinaryTreeNode<Integer> leaf9 = node(null, 9, null);
		BinaryTreeNode<Integer> leaf17 = node(null, 17, null);
		BinaryTreeNode<Integer> leaf25 = node(null, 25, null);
		BinaryTreeNode<Integer> leaf29 = node(null, 29, null);
		BinaryTreeNode<Integer> leaf37 = node(null, 37, null);
		BinaryTreeNode<Integer> node9 = node(leaf9, 9, leaf17);
		BinaryTreeNode<Integer> node25 = node(leaf25, 25, leaf29);
		BinaryTreeNode<Integer> parent37 = node(null, 37, leaf37);
		BinaryTreeNode<Integer> node20 = node(node9, 20, null);
		BinaryTreeNode<Integer> node35 = node(node25, 35, parent37);
		BinaryTreeNode<Integer> root20 = node(node20, 20, node35);
		return root20;
	}

	@Test //(timeout = 5000ms)
	@Timeout(5)
	public void testDepth() {
		assertEquals(0, utility.getDepth(root1));
		assertEquals(1, utility.getDepth(root2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertEquals(3, utility.getDepth(root3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     11
		//                    /
		//                   14
		assertEquals(3, utility.getDepth(root4));

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		assertEquals(2, utility.getDepth(root5));

		//               "life"
		//              /      \
		//          "is"      "like"
		//           /            \
		//         "a"           "of"
		//          \
		//         "box"
		//            \
		//          "chocolates"
		assertEquals(4, utility.getDepth(rootString));

		//                   20
		//               /       \
		//             10        35
		//           /   \     /   \
		//          9    13  25    37
		//        /  \      /  \
		//       5   27   19   29
		//     /                \
		//    3                 32
		//     \                /
		//      4              30
		assertEquals(5, utility.getDepth(root6));

		assertEquals(20, utility.getDepth(largeTree));
	}

	@Test
	public void testDepthException() {
		assertThrows(NullPointerException.class,
				() -> utility.getDepth(null));
	}

	@Test //(timeout = 5000)
	@Timeout(5)
	public void testIsBalanced() {
		assertTrue(utility.isBalanced(root1, 0));
		assertTrue(utility.isBalanced(root1, 1));
		assertTrue(utility.isBalanced(root1, 2));
		assertTrue(utility.isBalanced(root1, 3));

		assertFalse(utility.isBalanced(root2, 0));
		assertTrue(utility.isBalanced(root2, 1));
		assertTrue(utility.isBalanced(root2, 2));
		assertTrue(utility.isBalanced(root2, 3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertFalse(utility.isBalanced(root3, 0));
		// consider the node with 19
		assertFalse(utility.isBalanced(root3, 1));
		assertTrue(utility.isBalanced(root3, 2));
		assertTrue(utility.isBalanced(root3, 3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     11
		//                    /
		//                   14
		assertFalse(utility.isBalanced(root4, 0));
		assertFalse(utility.isBalanced(root4, 1));
		assertTrue(utility.isBalanced(root4, 2));
		assertTrue(utility.isBalanced(root4, 3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		assertTrue(utility.isBalanced(root5, 0));
		assertTrue(utility.isBalanced(root5, 1));
		assertTrue(utility.isBalanced(root5, 2));
		assertTrue(utility.isBalanced(root5, 3));

		//                   20
		//               /       \
		//             10        35
		//           /   \     /   \
		//          9    13  25    37
		//        /  \      /  \
		//       5   27   19   29
		//     /                \
		//    3                 32
		//     \                /
		//      4              30
		assertFalse(utility.isBalanced(root6, 0));
		assertFalse(utility.isBalanced(root6, 1));
		assertFalse(utility.isBalanced(root6, 2));
		assertTrue(utility.isBalanced(root6, 3));

		//               "life"
		//              /      \
		//          "is"      "like"
		//           /            \
		//         "a"           "of"
		//          \
		//         "box"
		//            \
		//          "chocolates"
		assertFalse(utility.isBalanced(rootString, 0));
		assertFalse(utility.isBalanced(rootString, 1));
		// consider the node with "is"
		assertFalse(utility.isBalanced(rootString, 2));
		assertTrue(utility.isBalanced(rootString, 3));

		assertTrue(utility.isBalanced(largeTree, 0));
		assertTrue(utility.isBalanced(largeTree, 1));
		assertTrue(utility.isBalanced(largeTree, 2));
		assertTrue(utility.isBalanced(largeTree, 3));
	}

	@Test
	public void testIsBalancedNull() {
		assertThrows(NullPointerException.class,
				() -> utility.isBalanced(null, 1));
	}

	@Test
	public void testIsBalancedIllegal() {
		assertThrows(IllegalArgumentException.class,
				() -> utility.isBalanced(root1, -1));
	}

	@Test
	public void testIsBST() {
		assertTrue(utility.isBST(root1));
		assertTrue(utility.isBST(root2));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertFalse(utility.isBST(root3));

		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           1   2     11
		//                    /
		//                   14
		assertFalse(utility.isBST(root4));

		//                  5
		//              /       \
		//             3         19
		//            / \       /  \
		//           1   4     7    25
		assertTrue(utility.isBST(root5));

		//                   20
		//               /       \
		//             10        35
		//           /   \     /   \
		//          9    13  25    37
		//        /  \      /  \
		//       5   27   19   29
		//     /                \
		//    3                 32
		//     \                /
		//      4              30
		assertFalse(utility.isBST(root6));
		// 27 is greater than 20, and 19 is less than 20

		//               "life"
		//              /      \
		//          "is"      "like"
		//           /            \
		//         "a"           "of"
		//          \
		//         "box"
		//            \
		//          "chocolates"
		assertTrue(utility.isBST(rootString));

		assertFalse(utility.isBST(largeTree));
	}

	@Test
	public void testIsBSTException() {
		assertThrows(NullPointerException.class,
				() -> utility.isBST(null));
	}

	@Test
	public void testDuplicateBST() {
		//                  20
		//               /      \
		//             10       20
		//           /   \        \
		//          9    10       25
		//        /  \     \     /  \
		//       5    9    19   23  25
		BinaryTreeNode<Integer> dupYes = initDupYes();
		assertEquals(3, utility.getDepth(dupYes));
		assertFalse(utility.isBalanced(dupYes, 0));
		assertFalse(utility.isBalanced(dupYes, 1));
		assertTrue(utility.isBalanced(dupYes, 2));
		assertTrue(utility.isBST(dupYes));

		//                  20
		//               /      \
		//             20       35
		//           /        /   \
		//          9       25    37
		//        /  \     /  \    \
		//       9   17   25  29   37
		BinaryTreeNode<Integer> dupNo = initDupNo();
		assertEquals(3, utility.getDepth(dupNo));
		assertFalse(utility.isBalanced(dupNo, 0));
		assertFalse(utility.isBalanced(dupNo, 1));
		assertTrue(utility.isBalanced(dupNo, 2));
		assertFalse(utility.isBST(dupNo));
	}

	private void assertIteratorContains(Iterator<Integer> itr, Integer ... elems){
		List<Integer> found = new LinkedList<>();
		for(Integer e : elems){
			assertTrue(itr.hasNext(),
					"Expected iterator to produce " + Arrays.toString(elems) + " but produced " + found);
			Integer test = itr.next();
			found.add(test);
			assertEquals(test, e,
					"Expected iterator to produce " + Arrays.toString(elems) + " but start of iterator produced " + found);
		}

		if(itr.hasNext()){
			while(itr.hasNext())
				found.add(itr.next());
			fail("Expected iterator to produce " + Arrays.toString(elems) + " but produced " + found);
		}
	}
}
