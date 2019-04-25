package fytyr.idea_projects.course_java.tree;

class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    T getData() {
        return data;
    }

    void setLeftChild(TreeNode<T> left) {
        this.left = left;
    }

    void setRightChild(TreeNode<T> right) {
        this.right = right;
    }

    TreeNode<T> getLeftChild() {
        return left;
    }

    TreeNode<T> getRightChild() {
        return right;
    }
}
