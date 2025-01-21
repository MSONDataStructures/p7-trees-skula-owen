package structures;

/**
 * <p>
 * A {@link BinarySearchTree} is a container that maintains an internal state
 * consistent with a Binary Search Tree.
 * </p>
 *
 * @param <T> the type of data stored in this {@link BinarySearchTree}
 * @author jcollard jddevaug
 */
public interface BinarySearchTree<T extends Comparable<? super T>> {
    /**
     * Adds {@code toAdd} to this {@link BinarySearchTree}. For convenience,
     * this method returns the modified {@link BinarySearchTree}. This method
     * runs in O(size) time. However, if this {@link BinarySearchTree} is
     * balanced, this method runs in O(log(size)) time.
     * A successful call to this method will always result in size increasing by one.
     * For simplicity, you can suppose that a duplicate value being added to the
     * tree should head to the right, that is, the left child will have a strictly
     * lesser value, and the right child will have a greater-than-or-equal-to value
     * in any node of the BST (consistent with the <code>isBST</code> method in the
     * <code>BinaryTreeUtility</code> class).
     *
     * @param toAdd the element to add to this {@link BinarySearchTree}
     * @return For convenience, this method returns the modified
     * {@link BinarySearchTree}.
     * @throws NullPointerException if {@code toAdd} is {@code null}
     */
    BinarySearchTree<T> add(T toAdd);

    /**
     * Searches this {@link BinarySearchTree} for {@code toFind} and returns
     * {@code true} if there is at least one instance of {@code toFind} in this
     * {@link BinarySearchTree} and {@code false} otherwise. This method
     * runs in O(size) time. However, if this {@link BinarySearchTree} is balanced,
     * this method runs in O(log(size)) time.
     *
     * @param toFind the element to search for
     * @return {@code true} if there is an instance of {@code toFind} in this
     * {@link BinarySearchTree} and {@code false otherwise.
     * @throws NullPointerException if {@code toFind} is {@code null}
     */
    boolean contains(T toFind);

    /**
     * Removes {@code toRemove} from this {@link BinarySearchTree} if at least
     * one element is considered comparably equivalent (compareTo(toRemove) ==
     * 0). Returns {@code true} if this {@link BinarySearchTree} was modified
     * and {@code false} otherwise. This method runs in O(size) time. However,
     * if this {@link BinarySearchTree} is balanced, this method runs in
     * O(log(size)) time. If there is more than one copy of {@code toRemove}
     * in the tree, only one copy of the node should be removed.
     *
     * @param toRemove the element to be removed
     * @return {@code true} if this {@link BinarySearchTree} was modified and
     * {@code false} otherwise
     */
    boolean remove(T toRemove);

    /**
     * Returns the number of elements in this {@link BinarySearchTree}
     *
     * @return the number of elements in this {@link BinarySearchTree}
     */
    int size();

    /**
     * Returns {@code true} if this {@link BinarySearchTree} contains no
     * elements and {@code false} otherwise.
     *
     * @return {@code true} if this {@link BinarySearchTree} contains no
     * elements and {@code false} otherwise.
     */
    boolean isEmpty();

    /**
     * Returns the minimum value in this {@link BinarySearchTree}.
     *
     * @return the minimum value in this {@link BinarySearchTree}.
     * @throws IllegalStateException if this {@link BinarySearchTree} is empty.
     */
    T getMinimum();

    /**
     * Returns the maximum value in this {@link BinarySearchTree}.
     *
     * @return the maximum value in this {@link BinarySearchTree}.
     * @throws IllegalStateException if this {@link BinarySearchTree} is empty.
     */
    T getMaximum();

    /**
     * Returns the root {@link BinaryTreeNode} in this {@link BinarySearchTree}.
     *
     * @return the root {@link BinaryTreeNode} in this {@link BinarySearchTree}.
     * @throws IllegalStateException if this {@link BinarySearchTree} is empty.
     */
    BinaryTreeNode<T> getRoot();
}
