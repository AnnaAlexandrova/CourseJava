package fytyr.idea_projects.course_java.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<T> comparator;

    public Tree() {
        size = 0;
    }

    public Tree(Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("Компаратор равен null");
        }
        this.comparator = comparator;
    }

    public Tree(Collection<T> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекци пуста");
        }
        for (T e : collection) {
            add(e);
        }
    }

    public int getSize() {
        return size;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        } else {
            //noinspection unchecked
            Comparable<T> c = (Comparable<T>) data1;
            return c.compareTo(data2);
        }
    }

    public boolean contains(T data) {
        TreeNode<T> currentRoot = root;
        while (currentRoot != null) {
            int compare = compare(currentRoot.getData(), data);
            if (compare == 0) {
                return true;
            }
            currentRoot = compare > 0
                    ? currentRoot.getLeftChild() : currentRoot.getRightChild();
        }
        return false;
    }

    public void add(T data) {
        TreeNode<T> currentRoot = root;
        TreeNode<T> currentParent = null;
        while (currentRoot != null) {
            currentParent = currentRoot;
            currentRoot = compare(currentRoot.getData(), data) > 0
                    ? currentRoot.getLeftChild() : currentRoot.getRightChild();
        }
        currentRoot = new TreeNode<>(data);
        if (currentParent != null) {
            if (compare(currentParent.getData(), data) > 0) {
                currentParent.setLeftChild(currentRoot);
            } else {
                currentParent.setRightChild(currentRoot);
            }
        } else {
            this.root = currentRoot;
        }
        size++;
    }

    public boolean remove(T data) {
        TreeNode<T> currentRoot = root;
        TreeNode<T> currentParent = null;
        while (currentRoot != null) {
            if (compare(currentRoot.getData(), data) == 0) {
                if (currentRoot.getRightChild() == null && currentRoot.getLeftChild() == null) {
                    removeIfNoChildren(currentRoot, currentParent);

                } else if (currentRoot.getRightChild() == null && currentRoot.getLeftChild() != null) {
                    removeIfOneChild(currentRoot, currentParent, currentRoot.getLeftChild());

                } else if (currentRoot.getRightChild() != null && currentRoot.getLeftChild() == null) {
                    removeIfOneChild(currentRoot, currentParent, currentRoot.getRightChild());

                } else if (currentRoot.getRightChild() != null && currentRoot.getLeftChild() != null) {
                    removeIfTwoChildren(currentRoot, currentParent);
                }
                size--;
                return true;
            }
            currentParent = currentRoot;
            currentRoot = compare(currentRoot.getData(), data) > 0
                    ? currentRoot.getLeftChild() : currentRoot.getRightChild();
        }
        return false;
    }

    private void removeIfNoChildren(TreeNode<T> nodeToRemove, TreeNode<T> parent) {
        if (parent.getLeftChild() == nodeToRemove) {
            parent.setLeftChild(null);
        } else {
            parent.setRightChild(null);
        }
    }

    private void removeIfOneChild(TreeNode<T> nodeToRemove, TreeNode<T> parent, TreeNode<T> child) {
        if (parent != null) {
            if (parent.getLeftChild() == nodeToRemove) {
                parent.setLeftChild(child);
            } else {
                parent.setRightChild(child);
            }
        } else {
            this.root = child;
        }
    }

    private void removeIfTwoChildren(TreeNode<T> nodeToRemove, TreeNode<T> parent) {
        TreeNode<T> minLeft = nodeToRemove.getRightChild().getLeftChild();
        TreeNode<T> minLeftParent = nodeToRemove.getRightChild();
        while (minLeft.getLeftChild() != null) {
            minLeftParent = minLeft;
            minLeft = minLeft.getLeftChild();
        }
        minLeftParent.setLeftChild(minLeft.getRightChild());
        if (parent != null) {
            if (compare(parent.getData(), minLeft.getData()) > 0) {
                parent.setLeftChild(minLeft);
            } else {
                parent.setRightChild(minLeft);
            }
        } else {
            this.root = minLeft;
        }
        minLeft.setLeftChild(nodeToRemove.getLeftChild());
        minLeft.setRightChild(nodeToRemove.getRightChild());
    }

    public void traverseInWidth(Consumer<T> consumer) {
        makeTreeIsNotEmptyExceptionCheck();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.remove();
            consumer.accept(node.getData());
            if (node.getLeftChild() != null) {
                queue.add(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                queue.add(node.getRightChild());
            }
        }
    }

    public void traverseInDepthRecursion(Consumer<T> consumer) {
        makeTreeIsNotEmptyExceptionCheck();
        visit(root, consumer);
    }

    private void visit(TreeNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            visit(node.getLeftChild(), consumer);
            consumer.accept(node.getData());
            visit(node.getRightChild(), consumer);
        }
    }

    public void traverseInDepth(Consumer<T> consumer) {
        makeTreeIsNotEmptyExceptionCheck();
        Deque<TreeNode<T>> deque = new LinkedList<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            TreeNode<T> node = deque.removeLast();
            consumer.accept(node.getData());
            if (node.getRightChild() != null) {
                deque.addLast(node.getRightChild());
            }
            if (node.getLeftChild() != null) {
                deque.addLast(node.getLeftChild());
            }
        }
    }

    private void makeTreeIsNotEmptyExceptionCheck() {
        if (size == 0) {
            throw new NullPointerException("Дерево не содержит ни одного элемента");
        }
    }
}
