import java.io.*;
import java.util.*;
import java.lang.*;

public class avl {
    Node root;

    static class Node {
        Node left, right;
        int val, ht;

        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.ht = 1;
        }
    }

    public avl() {
        this.root = null;
    }

    public static int update_ht(Node root) {
        if (root.left != null && root.right != null)
            return Math.max(root.left.ht, root.right.ht) + 1;
        else if (root.left != null)
            return root.left.ht + 1;
        else if (root.right != null)
            return root.right.ht + 1;
        else
            return 1;
    }

    public static int bf_finder(Node root) {
        if (root.left != null && root.right != null)
            return root.right.ht - root.left.ht;
        else if (root.left != null)
            return root.left.ht * -1;
        else if (root.right != null)
            return root.right.ht;
        else
            return 0;
    }

    public static Node left_rotation(Node root) {
        Node pivot = root.right;
        Node detached = pivot.left;
        pivot.left = root;
        root.right = detached;
        root.ht = update_ht(root);
        pivot.ht = update_ht(pivot);
        // System.out.println("Pivot: " + pivot.val + "Root: " + root.val);
        return pivot;
    }

    public static Node right_rotation(Node root) {
        Node pivot = root.left;
        Node detached = pivot.right;
        pivot.right = root;
        root.left = detached;
        root.ht = update_ht(root);
        pivot.ht = update_ht(pivot);
        // System.out.println("Pivot: " + pivot.val + "Root: " + root.val);
        return pivot;
    }

    public static Node insert(Node root, int num) {
        if (root == null) {
            Node new_node = new Node(num);
            root = new_node;
            return root;
        } else if (num > root.val) {
            root.right = insert(root.right, num);
        } else if (num < root.val) {
            root.left = insert(root.left, num);
        } else {
            return root;
        }
        root.ht = update_ht(root);
        int balance_factor = bf_finder(root);
        if (balance_factor > 1 && num > root.right.val) { // right-right causes disruption thus left rotate
            return left_rotation(root);
        } else if (balance_factor > 1 && num < root.right.val) {
            root.right = right_rotation(root.right);
            return left_rotation(root);
        } else if (balance_factor < -1 && num < root.left.val) {
            return right_rotation(root);
        } else if (balance_factor < -1 && num > root.left.val) {
            root.left = left_rotation(root.left);
            return right_rotation(root);
        }
        return root;
    }

    public static int smallest_val(Node root) {
        if (root.left == null)
            return root.val;
        else
            return smallest_val(root.left);
    }

    public static Node delete(Node root, int num) {
        if (root == null) {
            return null;
        } else if (num > root.val) {
            root.right = delete(root.right, num);
        } else if (num < root.val) {
            root.left = delete(root.left, num);
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
                return null;
            }
        }
        root.ht = update_ht(root);
        int balance_factor = bf_finder(root);
        if (balance_factor > 1 && (bf_finder(root.right) > 0 || bf_finder(root.right) == 0)) {
            return left_rotation(root);
        } else if (balance_factor > 1 && bf_finder(root.right) < 0) {
            root.right = right_rotation(root.right);
            return left_rotation(root);
        } else if (balance_factor < -1 && (bf_finder(root.left) < 0 || bf_finder(root.left) == 0)) {
            return right_rotation(root);
        } else if (balance_factor < -1 && bf_finder(root.left) > 0) {
            root.left = left_rotation(root.left);
            return right_rotation(root);
        }
        return root;
    }

    public static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.val + " ");
            preorder(root.left);
            preorder(root.right);
        }

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        avl tree = new avl();
        for (int i = 0; i < n; i++) {
            int num = input.nextInt();
            tree.root = insert(tree.root, num);
        }
        preorder(tree.root);
        System.out.println();
        for (int i = 5; i <= 7; i++) {
            tree.root = delete(tree.root, i);
            preorder(tree.root);
            System.out.println();
        }
    }
}