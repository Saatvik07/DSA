/*package whatever //do not write package name here */

import java.io.*;
import java.lang.*;
import java.util.*;

class BST {
    static Node root;

    static class Node {
        int val;
        Node left;
        Node right;

        public Node(int d) {
            this.val = d;
            this.left = null;
            this.right = null;
        }
    }

    public BST() {
        root = null;
    }

    public static void call_insert(int val) {
        root = insert(root, val);
    }

    public static int height(Node root) {
        int len_left, len_right;
        if (root == null) {
            return 0;
        } else {
            len_left = height(root.left);
            len_right = height(root.right);
            return Math.max(len_left, len_right) + 1;
        }
    }

    public static boolean search(Node root, int val) {
        boolean ans;
        if (root == null) {
            return false;
        } else if (root.val == val) {
            return true;
        } else {
            if (val < root.val) {
                ans = true && search(root.left, val);
            } else {
                ans = true && search(root.right, val);
            }
        }
        return ans;
    }

    public static Node insert(Node root, int val) {
        if (root == null) {
            Node n_node = new Node(val);
            root = n_node;
            return root;
        } else {
            if (val < root.val) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }
        }
        return root;
    }

    public static void in_order(Node root) {
        if (root != null) {
            in_order(root.left);
            System.out.println(root.val);
            in_order(root.right);
        }

    }

    public static void preorder(Node root) {
        if (root != null) {
            System.out.println(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.println(root.val);
        }
    }

    public static int size(Node root) {
        if (root == null) {
            return 0;
        } else {
            return 1 + size(root.left) + size(root.right);
        }
    }

    public static int smallest_val(Node root) {
        if (root.left == null)
            return root.val;
        else
            return smallest_val(root.left);
    }

    public static Node delete(Node root, int val) {
        if (root == null) {
            return null;
        } else if (val > root.val) {
            root.right = delete(root.right, val);
        } else if (val < root.val) {
            root.left = delete(root.left, val);
        } else {
            if (root.left != null && root.right != null) {
                Node temp = root;
                int least_val_right_subtree = smallest_val(temp.right);
                root.val = least_val_right_subtree;
                root.right = delete(root.right, least_val_right_subtree);
            } else if (root.left != null) {
                root = root.left;
            } else if (root.right != null) {
                root = root.right;
            } else {
                root = null;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BST tree = new BST();
        call_insert(7);
        call_insert(6);
        call_insert(4);
        call_insert(5);
        call_insert(3);
        call_insert(2);
        preorder(root);

    }
}