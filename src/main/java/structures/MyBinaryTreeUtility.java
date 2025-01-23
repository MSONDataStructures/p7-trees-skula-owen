package structures;

// Skula's Part
public class MyBinaryTreeUtility implements BinaryTreeUtility {

    @Override
    public <T> int getDepth(BinaryTreeNode<T> root) {
        if (root == null) throw new NullPointerException();
        if (!root.hasLeftChild() && !root.hasRightChild()) return 0;

        int leftDepth = root.hasLeftChild() ? getDepth(root.getLeftChild()) : -1;
        int rightDepth = root.hasRightChild() ? getDepth(root.getRightChild()) : -1;

        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Override
    public <T> boolean isBalanced(BinaryTreeNode<T> root, int tolerance) {
        if (root == null) throw new NullPointerException();
        if (tolerance < 0) throw new IllegalArgumentException();

        return checkBalance(root, tolerance);
    }

    private <T> boolean checkBalance(BinaryTreeNode<T> node, int tolerance) {
        if (node == null) return true;

        int leftDepth = node.hasLeftChild() ? getDepth(node.getLeftChild()) + 1 : 0;
        int rightDepth = node.hasRightChild() ? getDepth(node.getRightChild()) + 1 : 0;

        if (Math.abs(leftDepth - rightDepth) > tolerance) return false;

        if (node.hasLeftChild() && !checkBalance(node.getLeftChild(), tolerance)) return false;
        if (node.hasRightChild() && !checkBalance(node.getRightChild(), tolerance)) return false;

        return true;
    }

    @Override
    public <T extends Comparable<? super T>> boolean isBST(BinaryTreeNode<T> root) {
        if (root == null) throw new NullPointerException();
        return isBSTHelper(root, null, null);
    }

    private <T extends Comparable<? super T>> boolean isBSTHelper(BinaryTreeNode<T> node, T min, T max) {
        if (node == null) return true;

        T value = node.getData();

        // Check bounds
        if (min != null && value.compareTo(min) < 0) return false;
        if (max != null && value.compareTo(max) > 0) return false;

        // Check left subtree (all values must be less than current value)
        // Check right subtree (all values must be greater than or equal to current value)
        return (!node.hasLeftChild() || isBSTHelper(node.getLeftChild(), min, value)) &&
                (!node.hasRightChild() || isBSTHelper(node.getRightChild(), value, max));
    }
}