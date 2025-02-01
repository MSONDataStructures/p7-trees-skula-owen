package structures;

public class MyBinarySearchTree<T extends Comparable<? super T>> implements BinarySearchTree<T> {

    private BinaryTreeNode<T> root;
    private int size;

    public MyBinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public BinarySearchTree<T> add(T toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null to the tree.");
        }

        if (root == null) {
            root = new MyBinaryTreeNode<>(null, toAdd, null);
            size++;
        } else {
            boolean added = addRecursive(root, toAdd);
            if (added) size++;
        }
        return this;
    }

    private boolean addRecursive(BinaryTreeNode<T> node, T toAdd) {
        int cmp = toAdd.compareTo(node.getData());

        if (cmp <= 0) {
            // Go left for duplicates and values less than current node
            if (node.hasLeftChild()) {
                return addRecursive(node.getLeftChild(), toAdd);
            } else {
                node.setLeftChild(new MyBinaryTreeNode<>(null, toAdd, null));
                return true;
            }
        } else {
            // Go right for values greater than current node
            if (node.hasRightChild()) {
                return addRecursive(node.getRightChild(), toAdd);
            } else {
                node.setRightChild(new MyBinaryTreeNode<>(null, toAdd, null));
                return true;
            }
        }
    }

    @Override
    public boolean contains(T toFind) {
        if (toFind == null) throw new NullPointerException("Cannot search for null in the tree.");
        return containsRecursive(root, toFind);
    }

    private boolean containsRecursive(BinaryTreeNode<T> node, T toFind) {
        if (node == null) return false;
        int cmp = toFind.compareTo(node.getData());
        if (cmp == 0) return true;
        else if (cmp < 0) {
            return containsRecursive(node.hasLeftChild() ? node.getLeftChild() : null, toFind);
        } else {
            return containsRecursive(node.hasRightChild() ? node.getRightChild() : null, toFind);
        }
    }

    private class RemovalResult {
        BinaryTreeNode<T> node;
        boolean removed;

        RemovalResult(BinaryTreeNode<T> node, boolean removed) {
            this.node = node;
            this.removed = removed;
        }
    }

    @Override
    public boolean remove(T toRemove) {
        if (toRemove == null) throw new NullPointerException("Cannot remove null from the tree.");
        RemovalResult result = removeRecursive(root, toRemove);
        if (result.removed) {
            root = result.node;
            size--;
        }
        return result.removed;
    }

    private RemovalResult removeRecursive(BinaryTreeNode<T> node, T toRemove) {
        if (node == null) {
            return new RemovalResult(null, false);
        }

        int cmp = toRemove.compareTo(node.getData());
        if (cmp < 0) {
            if (node.hasLeftChild()) {
                RemovalResult result = removeRecursive(node.getLeftChild(), toRemove);
                if (result.removed) {
                    node.setLeftChild(result.node);
                    return new RemovalResult(node, true);
                }
            }
            return new RemovalResult(node, false);
        } else if (cmp > 0) {
            if (node.hasRightChild()) {
                RemovalResult result = removeRecursive(node.getRightChild(), toRemove);
                if (result.removed) {
                    node.setRightChild(result.node);
                    return new RemovalResult(node, true);
                }
            }
            return new RemovalResult(node, false);
        } else {
            // Node to be removed found

            // Case 1: Node has an immediate duplicate in the right subtree
            if (node.hasRightChild() && node.getRightChild().getData().compareTo(toRemove) == 0) {
                BinaryTreeNode<T> rightDuplicate = node.getRightChild();
                node.setData(rightDuplicate.getData());
                node.setRightChild(rightDuplicate.hasRightChild() ? rightDuplicate.getRightChild() : null);
                return new RemovalResult(node, true);
            }

            // Case 2: No immediate duplicate, proceed with standard removal
            if (!node.hasLeftChild() && !node.hasRightChild()) {
                // No children
                return new RemovalResult(null, true);
            } else if (!node.hasLeftChild()) {
                // Only right child
                return new RemovalResult(node.getRightChild(), true);
            } else if (!node.hasRightChild()) {
                // Only left child
                return new RemovalResult(node.getLeftChild(), true);
            } else {
                // Both children exist
                // Find the in-order successor (smallest in the right subtree)
                BinaryTreeNode<T> successor = findMin(node.getRightChild());
                // Replace node's data with successor's data
                node.setData(successor.getData());
                // Remove the successor node
                RemovalResult result = removeRecursive(node.getRightChild(), successor.getData());
                node.setRightChild(result.node);
                return new RemovalResult(node, result.removed);
            }
        }
    }

    private BinaryTreeNode<T> findMin(BinaryTreeNode<T> node) {
        while (node.hasLeftChild()) {
            node = node.getLeftChild();
        }
        return node;
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
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        BinaryTreeNode<T> current = root;
        while (current.hasLeftChild()) {
            current = current.getLeftChild();
        }
        return current.getData();
    }

    @Override
    public T getMaximum() {
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        BinaryTreeNode<T> current = root;
        while (current.hasRightChild()) {
            current = current.getRightChild();
        }
        return current.getData();
    }

    @Override
    public BinaryTreeNode<T> getRoot() {
        if (isEmpty()) throw new IllegalStateException("Tree is empty.");
        return root;
    }
}