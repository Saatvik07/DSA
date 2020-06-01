import java.util.*;
import java.io.*;
import java.lang.*;

public class LL {
    Node head;

    static class Node {
        int val;
        Node link;
    }

    public static Node recursive_reverse(Node head) {
        if (head == null || head.link == null) {
            return head;
        }
        Node next = head.link;
        head.link = null;
        Node rest = recursive_reverse(next);
        next.link = head;
        return rest;
    }

    public static LL insert(LL list, int val) {
        Node n_node = new Node(val);
        n_node.link = null;
        if (list.head == null) {
            list.head = n_node;
        } else {
            Node ptr = list.head;
            while (ptr.link != null) {
                ptr = ptr.link;
            }
            ptr.link = n_node;
        }
        return list;
    }

    public static void insert_pos(Node ptr, LL list, int pos, int data) {
        Node n_ptr = new Node(data);
        pos -= 1;
        while (pos > 0) {
            ptr = ptr.link;
            pos--;
        }
        n_ptr.link = ptr.link;
        ptr.link = n_ptr;
    }

    public static void printlist_1(Node p) {
        while (p != null) {
            System.out.printf("%d ", p.val);
            p = p.link;
        }
        System.out.println();

    }

    public static void delete(Node ptr, int data) {
        Node prev = null;
        while (ptr.val != data) {
            prev = ptr;
            ptr = ptr.link;
        }
        prev.link = ptr.link;

    }

    public static Node reverse(LL list, int k) {
        Node cur = list.head;
        Node prev = null;
        Node next = null;
        while (k > 0) {
            while (cur.link != null) {
                prev = cur;
                cur = cur.link;
            }
            cur.link = list.head;
            list.head = cur;
            prev.link = null;

            k--;
        }
        return list.head;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        int Arr[] = new int[n];
        LL list = new LL();
        for (int i = 0; i < n; i++) {
            Arr[i] = input.nextInt();
            list = insert(list, Arr[i]);
        }
        Node rev_head = reverse(list, k);
        printlist_1(rev_head);
        /*
         * delete(rev_head,17); printlist_1(rev_head); Node
         * rev_rev_head=recursive_reverse(rev_head); printlist_1(rev_rev_head);
         * insert_pos(rev_head,list,3,100); printlist_1(rev_head);
         */
    }

}