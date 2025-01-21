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

import config.Configuration;

public class BinaryTreeNodeTest {

	private BinaryTreeNode<Integer> root1, root2, root3;
	private BinaryTreeNode<String> rootString;

	private static final <T> BinaryTreeNode<T> node(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
		return Configuration.createBinaryTreeNode(left, elem, right);
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		root1 = initRoot();
		root2 = initRoot2();
		root3 = initRoot3();
		rootString = initRootString();
		assertNotNull(root1, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(root2, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(root3, "It looks like your configuration file isn't set for BinaryTreeNode.");
		assertNotNull(rootString, "It looks like your configuration file isn't set for BinaryTreeNode.");
	}

	private BinaryTreeNode<Integer> initRoot(){
		return node(null, 5, null);
	}
	
	private BinaryTreeNode<Integer> initRoot2(){
		//       5
		//        \
		//         7
		return node(null,
				5,
				node(null, 7, null)
		);
	}
	
	private BinaryTreeNode<Integer> initRoot3(){
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

	private BinaryTreeNode<String> initRootString(){
		//                "five"
		//              /         \
		//          "three"     "nineteen"
		//          /    \           /
		//       "six" "seven"    "one"
		//                        /
		//                     "four"
		return node(
						node(
								node(null, "six", null),
								"three",
								node(null, "seven", null)
						),
						"five",
						node(
								node(
										node(null, "four", null),
										"one",
										null),
								"nineteen",
								null)
				);
	}

	@Test
	public void testRoot1() {
		assertEquals(Integer.valueOf(5), root1.getData(), "Root 1 should have a single node with 5 stored.");
		assertFalse(root1.hasLeftChild(), "Root 1 should have no children.");
		assertFalse(root1.hasRightChild(), "Root 1 should have no children.");
	}
	
	@Test
	public void testRoot1Exception1() {
		assertThrows(IllegalStateException.class,
				() -> root1.getLeftChild());
	}

	@Test
	public void testRoot1Exception2() {
		assertThrows(IllegalStateException.class,
				() -> root1.getRightChild());
	}
	
	@Test
	public void testRoot2() {
		assertEquals(Integer.valueOf(5), root2.getData(), "The root of root 2 should hold 5.");
		assertFalse(root2.hasLeftChild(), "Root 2 should have only a right child.");
		assertTrue(root2.hasRightChild(), "Root 2 should have a right child.");
		assertEquals(Integer.valueOf(7), root2.getRightChild().getData(), "The right child of root 2 should hold 7.");
		assertFalse(root2.getRightChild().hasRightChild(), "The right child of root 2 should have no children.");
		assertFalse(root2.getRightChild().hasLeftChild(), "The left child of root 2 should have no children.");
	}
	
	@Test
	public void testRoot2Exception1(){
		assertThrows(IllegalStateException.class,
				() -> root2.getLeftChild());
	}
	
	@Test
	public void testRoot2Exception2(){
		assertThrows(IllegalStateException.class,
				() -> root2.getRightChild().getLeftChild());
	}
	
	@Test
	public void testRoot2Exception3(){
		assertThrows(IllegalStateException.class,
				() -> root2.getRightChild().getRightChild());
	}
	
	@Test
	public void testRoot3() {
		//                  5
		//              /       \
		//             3         19
		//            / \       /
		//           6   7     1
		//                    /
		//                   4
		assertEquals(Integer.valueOf(5), root3.getData(), "The root of root 3 should hold 5.");
		assertEquals(Integer.valueOf(3), root3.getLeftChild().getData(), "The left child of root 3 should hold 3.");
		assertEquals(Integer.valueOf(6), root3.getLeftChild().getLeftChild().getData(), "The left-left child of root 3 should hold 6.");
		assertEquals(Integer.valueOf(7), root3.getLeftChild().getRightChild().getData(), "The left-right child of root 3 should hold 7.");
		assertEquals(Integer.valueOf(19), root3.getRightChild().getData(), "The right child of root 3 should hold 19.");
		assertEquals(Integer.valueOf(1), root3.getRightChild().getLeftChild().getData(), "The right-left child of root 3 should hold 1.");
		assertEquals(Integer.valueOf(4), root3.getRightChild().getLeftChild().getLeftChild().getData(), "The right-left-left child of root 3 should hold 4.");
	}
	
	@Test
	public void testRoot3Exception1(){
		assertFalse(root3.getLeftChild().getLeftChild().hasLeftChild());
		assertThrows(IllegalStateException.class,
				() -> root3.getLeftChild().getLeftChild().getLeftChild());
	}
	
	@Test
	public void testRoot3Exception2(){
		assertFalse(root3.getLeftChild().getLeftChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> root3.getLeftChild().getRightChild().getRightChild());
	}
	
	@Test
	public void testRoot3Exception3(){
		assertFalse(root3.getRightChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> root3.getRightChild().getRightChild());
	}
	
	@Test
	public void testRoot3Exception4(){
		assertFalse(root3.getRightChild().getLeftChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> root3.getRightChild().getLeftChild().getRightChild());
	}
	
	@Test
	public void testRoot3Exception5(){
		assertFalse(root3.getRightChild().getLeftChild().getLeftChild().hasLeftChild());
		assertThrows(IllegalStateException.class,
				() -> root3.getRightChild().getLeftChild().getLeftChild().getLeftChild());
	}
	
	@Test
	public void testRoot3Exception6(){
		assertFalse(root3.getRightChild().getLeftChild().getLeftChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> root3.getRightChild().getLeftChild().getLeftChild().getRightChild());
	}

	@Test
	public void testRootString() {
		//                "five"
		//              /         \
		//          "three"     "nineteen"
		//          /    \           /
		//       "six" "seven"    "one"
		//                        /
		//                     "four"
		assertEquals("five", rootString.getData(), "The root of rootString should hold \"five\".");
		assertEquals("three", rootString.getLeftChild().getData(), "The left child of rootString should hold \"three\".");
		assertEquals("six", rootString.getLeftChild().getLeftChild().getData(), "The left-left child of rootString should hold \"six\".");
		assertEquals("seven", rootString.getLeftChild().getRightChild().getData(), "The left-right child of rootString should hold \"seven\".");
		assertEquals("nineteen", rootString.getRightChild().getData(), "The right child of rootString should hold \"nineteen\".");
		assertEquals("one", rootString.getRightChild().getLeftChild().getData(), "The right-left child of rootString should hold \"one\".");
		assertEquals("four", rootString.getRightChild().getLeftChild().getLeftChild().getData(), "The right-left-left child of rootString should hold \"four\".");
	}

	@Test
	public void testRootStringException1(){
		assertFalse(rootString.getLeftChild().getLeftChild().hasLeftChild());
		assertThrows(IllegalStateException.class,
				() -> rootString.getLeftChild().getLeftChild().getLeftChild());
	}

	@Test
	public void testRootStringException2(){
		assertFalse(rootString.getLeftChild().getLeftChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> rootString.getLeftChild().getRightChild().getRightChild());
	}

	@Test
	public void testRootStringException3(){
		assertFalse(rootString.getRightChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> rootString.getRightChild().getRightChild());
	}

	@Test
	public void testRootStringException4(){
		assertFalse(rootString.getRightChild().getLeftChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> rootString.getRightChild().getLeftChild().getRightChild());
	}

	@Test
	public void testRootStringException5(){
		assertFalse(rootString.getRightChild().getLeftChild().getLeftChild().hasLeftChild());
		assertThrows(IllegalStateException.class,
				() -> rootString.getRightChild().getLeftChild().getLeftChild().getLeftChild());
	}

	@Test
	public void testRootStringException6(){
		assertFalse(rootString.getRightChild().getLeftChild().getLeftChild().hasRightChild());
		assertThrows(IllegalStateException.class,
				() -> rootString.getRightChild().getLeftChild().getLeftChild().getRightChild());
	}
}
