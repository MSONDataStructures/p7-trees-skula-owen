# <code>DSp7Tree</code> Binary Search Tree

## Overview
For this assignment you will be implementing a simple Binary Tree along with
some utility functions. This project is based on the content of chapters 9 and
10 in the Java Software Structures book. Start early, and work incrementally!

### Table of Contents
**[Files to Complete](#files-to-complete)**<br>
**[Part One: Import Project](#part-one-import-project)**<br>
**[Part Two: Implement the BinaryTreeNode Interface](#part-two-implement-the-binarytreenode-interface)**<br>
**[Part Three: Implement the BinaryTreeUtility Interface](#part-three-implement-the-binarytreeutility-interface)**<br>
**[Part Four: Implement the BinarySearchTree Interface](#part-three-implement-the-binarysearchtree-interface)**<br>

## Files to Complete
You are expected to write an implementation for each of the interfaces listed
in the `Configuration` class: `BinaryTreeNode`, `BinaryTreeUtility`, and
`BinarySearchTree`. The classes you write should include the command

```package structures```

at the top, and they should be placed in the (currently empty) `main.java.structures`
folder. Update the methods in `Configuration.java` as these implementations
are completed.

### Test files
In the test folder, you are provided with several JUnit test cases that will
help you keep on track while completing this assignment. You should run the
tests often and use them as a checklist of things to do next. Feel free to add
your own JUnit classes to fill out the test suite.

## Part One: Import Project 
When you get your project from GitHub Classroom, you will want to ensure that
your project has no errors and contains the following root items:

**src** - The source folder where all code you are submitting must go. You can
change anything you want in this folder: add new files, etc...<br>
**test** - The test folder where all the public unit tests are available.<br>
**support** - This folder contains support code for you to use. You probably
do not want to change files in this folder.<br>
**JUnit 5** - A library that is used to run the test programs.<br>
**JRE System Library** - This is what allows java to run. I am assuming that for
your SDK you are using some flavor of Java 17.<br>

Please note that for this project, skeleton classes are **not** included. You can
name your implementation classes whatever you like, or even have multiple versions,
but the one that the tester class will use is based on the `Configuration` class.
You should edit those methods to match your class names.

Also, the `support` folder is a **content root**. In IntelliJ, in the Project
Structure, under `Modules | Sources`, I had to right-click on the `support` folder
and mark it as a source. This is also reflected in the `build.gradle.kts` file.
This should work correctly for you without taking any action, but if you are
getting errors from those classes, you may need adjust your setup. If you get any
other mysterious errors, please let me know.

## Part Two: Implement the BinaryTreeNode Interface
A `BinaryTreeNode` represents a node in a binary tree. It stores data of generic
type `T` and may have a right and a left child, each a reference to another
`BinaryTreeNode`.  The `BinaryTreeNode` interface includes standard getters and
setters for the left and right children of a `BinaryTreeNode`, as well as its data.
You should write an implementation class in the `main.java.structures` folder with
whatever instance fields, constructors, and methods you like, as long as you
implement all the required methods of the interface.

## Part Three: Implement the BinaryTreeUtility Interface
The `BinaryTreeUtility` interface provides basic functions for working with a
binary tree.

**Depth** -- The depth of the tree is the maximum level of any leaf node in
the tree.  Recall that the level of a node begins at zero for the root of a
tree with no children.  A child of the root is at level 1.

**Balance** -- The balance of a tree measures how close it is to being a full
or complete tree.  The standard definition of balance for a tree is that, for
every node in the tree, the height of the left subtree can differ from the
height of the right subtree by at most 1. This corresponds to a tolerance of 1
in the version that we will build, but our method will be more general. You will
implement `isBalanced(BinaryTreeNode<T> root, int tolerance)`, which determines
whether the maximum difference in the depth of any two children is no larger
than the given `tolerance` value.

**Testing for a Binary Search Tree** -- Recall that a Binary Search Tree is a
binary tree that also satisfies a special sorting property:
for **each** node in the tree,
- every node in the left subtree has data with value less than the parent,
- and every node in the right subtree has data with value greater than or
  equal to the parent.

Dealing with duplicate values can be a real pain, and our decision to put
duplicates on the right is arbitrary. You might want to ignore duplicates
and assume data values are unique until you get everything else working.
Most of the test cases do not have any duplicate values.

You will write a function `isBST(root)` which returns true if `root` is the
root of a valid binary search tree. Your function need not explicitly test
basic requirements of a binary tree (e.g. that there is a unique path from
the root to every node in the tree). To do these comparisons we need `T` to
be comparable, and while our test cases only include `Integer` and `String`,
your code should work with any comparable type (that is the reason for
`<T extends Comparable<? super T>>`, which takes a bit to decipher).
Testing for a BST has a nice recursive solution, but it is tricky.

Please note that the binary search tree property (Lewis & Chase, p282) is a
necessary condition for a BST, but it is not sufficient. This means that every
BST satisfies the binary search tree property (every node's left child has value
less than the parent, which has value less than or equal to the right child), but
not every binary tree that satisfies the property is a BST (as in the root 6 test).

**Bonus Assignment - Preorder Iterator** -- If you are looking for more challenge,
add a class that  generates a preorder iterator for the binary tree. There are
several approaches, but if you import `java.util.Iterator` and `java.util.Deque`,
you can use a stack for the `hasNext` and `next` methods (use a `Deque` as a stack).
Another approach is to use recursion, which is essentially using the call stack
instead of an instance field stack. Hints in the book.

## Part Four: Implement the BinarySearchTree Interface
The `BinarySearchTree` interface provides basic functions for implementing
a binary search tree.

**Transformers** -- The `add` and `remove` methods are the main transformers
of a BST. These must add or remove elements while maintaining the BST property.
Some details and hints are in the book, but copying-and-pasting that code will
not work quite right. If the value to removed occurs more than once in the tree,
the `remove` method will only delete one copy. Remember that duplicates go to
the right, but again, it can get make your head spin a bit.

**Observers** -- You will implement an `isEmpty()` method and a `size()` method.
In addition, you will implement the `getMinimum()` and `getMaximum()` functions,
which return the smallest and largest values stored in the BST. Easy peasy!
