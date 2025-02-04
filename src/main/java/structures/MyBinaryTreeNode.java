//owen's part (that gave me a headache 😵‍💫)
package structures;

public class MyBinaryTreeNode<T> implements BinaryTreeNode<T> {
    private T data;
    private BinaryTreeNode<T> left;
    private BinaryTreeNode<T> right;
    /**
     * Constructs a new BinaryTreeNode with the given left child, data, and right child.
     * @param left the left child (can be null)
     * @param data the data to store (must not be null)
     * @param right the right child (can be null)
     * @throws NullPointerException if data is null.
     */
    public MyBinaryTreeNode(BinaryTreeNode<T> left, T data, BinaryTreeNode<T> right) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null.");
        }
        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        if (data == null) {
            throw new NullPointerException("Data cannot be null.");
        }
        this.data = data;
    }

    @Override
    public boolean hasLeftChild() {
        return left != null;
    }

    @Override
    public boolean hasRightChild() {
        return right != null;
    }

    /**
     * Returns the left child if present. Otherwise, throws an IllegalStateException.
     * @return the left child
     * @throws IllegalStateException if there is no left child.
     */
    @Override
    public BinaryTreeNode<T> getLeftChild() {
        if (!hasLeftChild()) {
            throw new IllegalStateException("No left child exists.");
        }
        return left;
    }

    /**
     * Returns the right child if present. Otherwise, throws an IllegalStateException.
     * @return the right child
     * @throws IllegalStateException if there is no right child.
     */
    @Override
    public BinaryTreeNode<T> getRightChild() {
        if (!hasRightChild()) {
            throw new IllegalStateException("No right child exists.");
        }
        return right;
    }

    @Override
    public void setLeftChild(BinaryTreeNode<T> left) {
        this.left = left;
    }

    @Override
    public void setRightChild(BinaryTreeNode<T> right) {
        this.right = right;
    }
}