package config;

import structures.BinarySearchTree;
import structures.BinaryTreeNode;
import structures.BinaryTreeUtility;
import structures.MyBinaryTreeNode;

/**
 * This class acts as a configuration file which tells the testing framework
 * which implementation you want us to use when your assignment is graded.
 * @author jcollard jddevaug
 */
public class Configuration {
	public static <T> BinaryTreeNode<T> createBinaryTreeNode(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
		return new MyBinaryTreeNode<>(left, elem, right);
	}

//merge other stuffz to master brancH?
	public static BinaryTreeUtility createBinaryTreeUtility(){
		return null;
	}

	public static <T extends Comparable<? super T>> BinarySearchTree<T> createBinarySearchTree(){
		return null;
	}
}
