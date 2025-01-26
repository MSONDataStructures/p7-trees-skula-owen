package structures;

// Skula's Part
public class MyBinaryTreeUtility implements BinaryTreeUtility {

    @Override
    public <T> int getDepth(BinaryTreeNode<T> root) {
        if (root == null) throw new NullPointerException("Root cannot be null.");

        // An empty node has depth -1, leaf nodes have depth 0
        return computeDepth(root);
    }

    private <T> int computeDepth(BinaryTreeNode<T> node) {
        if (node == null) return -1;

        int leftDepth = computeDepth(node.hasLeftChild() ? node.getLeftChild() : null);
        int rightDepth = computeDepth(node.hasRightChild() ? node.getRightChild() : null);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public <T> boolean isBalanced(BinaryTreeNode<T> root, int tolerance) {
        if (root == null) throw new NullPointerException("Root cannot be null.");
        if (tolerance < 0) throw new IllegalArgumentException("Tolerance cannot be negative.");

        return checkBalance(root, tolerance).isBalanced;
    }

    /**
     * Helper class to hold balance information.
     */
    private static class BalanceStatus {
        boolean isBalanced;
        int depth;

        BalanceStatus(boolean isBalanced, int depth) {
            this.isBalanced = isBalanced;
            this.depth = depth;
        }
    }

    private <T> BalanceStatus checkBalance(BinaryTreeNode<T> node, int tolerance) {
        if (node == null) return new BalanceStatus(true, -1);

        BalanceStatus left = node.hasLeftChild() ? checkBalance(node.getLeftChild(), tolerance) : new BalanceStatus(true, -1);
        if (!left.isBalanced) return new BalanceStatus(false, 0);

        BalanceStatus right = node.hasRightChild() ? checkBalance(node.getRightChild(), tolerance) : new BalanceStatus(true, -1);
        if (!right.isBalanced) return new BalanceStatus(false, 0);

        boolean isCurrentBalanced = Math.abs(left.depth - right.depth) <= tolerance;
        int currentDepth = Math.max(left.depth, right.depth) + 1;

        return new BalanceStatus(isCurrentBalanced, currentDepth);
    }

    @Override
    public <T extends Comparable<? super T>> boolean isBST(BinaryTreeNode<T> root) {
        if (root == null) throw new NullPointerException("Root cannot be null.");

        return isBSTHelper(root, null, null);
    }

    /**
     * Recursively checks if the tree satisfies BST properties.
     *
     * @param node Current node being checked.
     * @param min  The minimum allowable value for the current node.
     * @param max  The maximum allowable value for the current node.
     * @param <T>  Type parameter extending Comparable.
     * @return True if the subtree rooted at node is a BST within the given constraints.
     */
    private <T extends Comparable<? super T>> boolean isBSTHelper(BinaryTreeNode<T> node, T min, T max) {
        if (node == null) return true;

        T value = node.getData();

        // Check if current node violates min constraint :D
        if (min != null && value.compareTo(min) < 0) return false;

        // Check if current node violates max constraint >:D
        // For left subtree, all values must be strictly less than current node's value
        // Therefore, use >= in comparison to invalidate equal values in left
        if (max != null && value.compareTo(max) >= 0) return false;

        // Recursively check subtrees with new constraints
        return isBSTHelper(node.hasLeftChild() ? node.getLeftChild() : null, min, value) &&
                isBSTHelper(node.hasRightChild() ? node.getRightChild() : null, value, max);
    }
}