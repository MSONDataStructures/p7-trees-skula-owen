package structures;

// Skula/Owen Collaboration over Zoom
public class MyBinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTree<T> {
    private BinaryTreeNode<T> root;
    private int size;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public BinarySearchTree<T> add(T toAdd) {
        if (toAdd == null) throw new NullPointerException();
        root = addHelper(root, toAdd);
        size++;
        return this;
    }

    private BinaryTreeNode<T> addHelper(BinaryTreeNode<T> node, T value) {
        if (node == null) {
            return new MyBinaryTreeNode<>(null, value, null);
        }

        int comparison = value.compareTo(node.getData());
        if (comparison < 0) {
            node.setLeftChild(addHelper(node.hasLeftChild() ? node.getLeftChild() : null, value));
        } else {
            // For equal or greater values, add to right subtree
            node.setRightChild(addHelper(node.hasRightChild() ? node.getRightChild() : null, value));
        }
        return node;
    }

    @Override
    public boolean contains(T toFind) {
        if (toFind == null) throw new NullPointerException();
        return containsHelper(root, toFind);
    }

    private boolean containsHelper(BinaryTreeNode<T> node, T value) {
        if (node == null) return false;

        int comparison = value.compareTo(node.getData());
        if (comparison == 0) return true;
        if (comparison < 0) return node.hasLeftChild() && containsHelper(node.getLeftChild(), value);
        return node.hasRightChild() && containsHelper(node.getRightChild(), value);
    }

    @Override
    public boolean remove(T toRemove) {
        if (toRemove == null) throw new NullPointerException();
        int originalSize = size;
        root = removeHelper(root, toRemove);
        return size != originalSize;
    }

    private BinaryTreeNode<T> removeHelper(BinaryTreeNode<T> node, T value) {
        if (node == null) return null;

        int comparison = value.compareTo(node.getData());
        if (comparison < 0) {
            if (node.hasLeftChild()) {
                node.setLeftChild(removeHelper(node.getLeftChild(), value));
            }
        } else if (comparison > 0) {
            if (node.hasRightChild()) {
                node.setRightChild(removeHelper(node.getRightChild(), value));
            }
        } else {
            size--;
            // Case 1: No children
            if (!node.hasLeftChild() && !node.hasRightChild()) {
                return null;
            }
            // Case 2: One child
            if (!node.hasLeftChild()) return node.getRightChild();
            if (!node.hasRightChild()) return node.getLeftChild();

            // Case 3: Two children
            T successor = findMin(node.getRightChild());
            node.setData(successor);
            node.setRightChild(removeHelper(node.getRightChild(), successor));
            size++; // Compensate for the extra decrease
        }
        return node;
    }

    private T findMin(BinaryTreeNode<T> node) {
        while (node.hasLeftChild()) {
            node = node.getLeftChild();
        }
        return node.getData();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T getMinimum() {
        if (isEmpty()) throw new IllegalStateException();
        BinaryTreeNode<T> current = root;
        while (current.hasLeftChild()) {
            current = current.getLeftChild();
        }
        return current.getData();
    }

    @Override
    public T getMaximum() {
        if (isEmpty()) throw new IllegalStateException();
        BinaryTreeNode<T> current = root;
        while (current.hasRightChild()) {
            current = current.getRightChild();
        }
        return current.getData();
    }

    @Override
    public BinaryTreeNode<T> getRoot() {
        if (isEmpty()) throw new IllegalStateException();
        return root;
    }
}