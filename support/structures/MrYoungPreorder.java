package structures;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Sample implementation of a stack-based preorder iterator.
 * This can also be done using recursion.
 * @param <T> the type parameter for the elements of the tree nodes
 */
public class MrYoungPreorder<T> implements Iterator<T> {
	
	private final Deque<BinaryTreeNode<T>> stack;
	
	public MrYoungPreorder(BinaryTreeNode<T> root){
		if (root == null) {
			throw new NullPointerException();
		}
		stack = new LinkedList<>();
		stack.push(root);
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public T next() {
		BinaryTreeNode<T> toVisit = stack.pop();
		if(toVisit.hasRightChild()) stack.push(toVisit.getRightChild());
		if(toVisit.hasLeftChild()) stack.push(toVisit.getLeftChild());
		return toVisit.getData();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
