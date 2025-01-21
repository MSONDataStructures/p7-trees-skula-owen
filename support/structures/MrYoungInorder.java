package structures;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sample implementation of a stack-based inorder iterator.
 * This can also be done using recursion.
 * @param <T> the type parameter for the elements of the tree nodes
 */
public class MrYoungInorder<T> implements Iterator<T> {

    private final Deque<BinaryTreeNode<T>> stack;

    public MrYoungInorder(BinaryTreeNode<T> root){
        if (root == null) {
            throw new NullPointerException();
        }
        stack = new LinkedList<>();
        stack.push(root);
        BinaryTreeNode<T> current = root;
        while (current.hasLeftChild()) {
            current = current.getLeftChild();
            stack.push(current);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        BinaryTreeNode<T> toVisit = stack.pop();
        if(toVisit.hasRightChild()) {
            BinaryTreeNode<T> current = toVisit.getRightChild();
            stack.push(current);
            while (current.hasLeftChild()) {
                current = current.getLeftChild();
                stack.push(current);
            }
        }
        return toVisit.getData();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
