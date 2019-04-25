package fytyr.idea_projects.course_java.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;

    public Tree() {
        size = 0;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public TreeNode<T> findNode(T data) {
        TreeNode<T> currentRoot = root;
        while (currentRoot != null) {
            if (Objects.equals(data, currentRoot.getData())) {
                return currentRoot;
            }
            currentRoot = currentRoot.getData().hashCode() > data.hashCode()
                    ? currentRoot.getLeftChild() : currentRoot.getRightChild();
        }
        return null;
    }

    public void add(T data) {
        TreeNode<T> currentRoot = root;
        TreeNode<T> currentParent = null;
        while (currentRoot != null) {
            currentParent = currentRoot;
            currentRoot = currentRoot.getData().hashCode() > data.hashCode()
                    ? currentRoot.getLeftChild() : currentRoot.getRightChild();
        }
        currentRoot = new TreeNode<>(data);
        if (currentParent != null) {
            if (currentParent.getData().hashCode() > data.hashCode()) {
                currentParent.setLeftChild(currentRoot);
            } else {
                currentParent.setRightChild(currentRoot);
            }
        } else {
            setRoot(currentRoot);
        }
        size++;
    }

    public boolean remove(T data) {
        TreeNode<T> currentRoot = root;
        TreeNode<T> currentParent = null;
        while (currentRoot != null) {
            if (Objects.equals(currentRoot.getData(), data)) {
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
            currentRoot = currentRoot.getData().hashCode() > data.hashCode()
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
            setRoot(child);
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
            if (parent.getData().hashCode() > minLeft.getData().hashCode()) {
                parent.setLeftChild(minLeft);
            } else {
                parent.setRightChild(minLeft);
            }
        } else {
            setRoot(minLeft);
        }
        minLeft.setLeftChild(nodeToRemove.getLeftChild());
        minLeft.setRightChild(nodeToRemove.getRightChild());
    }

    public void traverseInWidth(Consumer<T> consumer) {
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

    public void traverseInDepthRecursion(TreeNode<T> node, Consumer<T> consumer) {
        if (node != null) {
            traverseInDepthRecursion(node.getLeftChild(), consumer);
            consumer.accept(node.getData());
            traverseInDepthRecursion(node.getRightChild(), consumer);
        }
    }

    public void traverseInDepth(Consumer<T> consumer) {
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();
            consumer.accept(node.getData());
            if (node.getRightChild() != null) {
                stack.add(node.getRightChild());
            }
            if (node.getLeftChild() != null) {
                stack.add(node.getLeftChild());
            }
        }
    }
}
