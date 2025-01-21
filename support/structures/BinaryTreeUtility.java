package structures;

/**
 * A {@link BinaryTreeUtility} provides utility methods for a {@link BinaryTreeNode}
 * @author jcollard jddevaug
 */
public interface BinaryTreeUtility {
	/**
	 * <p>
	 * Given a {@link BinaryTreeNode} computes the maximum distance that can be
	 * traveled from {@code root} to some child {@link BinaryTreeNode}. For
	 * example:
	 * </p>
	 * <pre>
	 * noChildren ->  A
	 *
	 * getDepth(noChildren); // returns 0
	 * </pre>
	 * <pre>
	 * someChildren ->  A
	 *                /   \
	 *               B     C <- depth 1
	 *                    /
	 *                   D   <- depth 2
	 *                  / \
	 *                 E   F <- depth 3
	 *
	 * getDepth(someChildren); // returns 3
	 * </pre>
	 * @param root
	 *            the root {@link BinaryTreeNode} to examine
	 * @throws NullPointerException
	 *             if root is {@code null}
	 * @return the maximum distance that can be traveled from {@code root}
	 */
	<T> int getDepth(BinaryTreeNode<T> root);

	/**
	 * A binary tree is said to be balanced if for each node in the tree the
	 * depth of each node's children differs by at most some tolerance. Given a
	 * {@link BinaryTreeNode} and a tolerance, returns {@code true} if
	 * {@code root} is balanced and {@code false} otherwise.
	 * @param root
	 *            the root {@link BinaryTreeNode} to examine
	 * @param tolerance the maximum tolerance to decide if the tree is balanced
	 * @return {@code true} if {@code root} is balanced and {@code false}
	 *         otherwise.
	 * @throws NullPointerException
	 *             if {@code root} is {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code tolerance} is less than 0
	 */
	<T> boolean isBalanced(BinaryTreeNode<T> root, int tolerance);

	/**
	 * <p>
	 * A {@link BinaryTreeNode} is said to be a Binary Search Tree if all of the
	 * following properties are true:
	 * <li>The left subtree of a node contains only nodes with values less than
	 * the value stored in {@code root}.</li>
	 * <li>The right subtree of a node contains only nodes with values that are
	 * greater than or equal to the value stored in {@code root}.</li>
	 * <li>The left and right subtree each must also be a binary search tree.</li>
	 * </p>
	 * <p>
	 * Given a {@link BinaryTreeNode}, returns {@code true} if {@code root} is a
	 * Binary Search Tree and {@code false} otherwise.
	 * </p>
	 * @param root
	 *            the {@link BinaryTreeNode} to examine
	 * @return {@code true} if {@code root} is a Binary Search Tree and
	 *         {@code false} otherwise.
	 * @throws NullPointerException
	 *             if {@code root} is {@code null}
	 */
	<T extends Comparable<? super T>> boolean isBST(BinaryTreeNode<T> root);
}
