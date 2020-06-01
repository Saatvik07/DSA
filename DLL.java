/*package whatever //do not write package name here */

import java.io.*;

class DLL{
    Node head;
    Node tail;
    static class Node{
        int val;
        Node link_prev;
        Node link_next;
        Node(int d){
            this.val=d;
            this.link_prev=null;
            this.link_prev=null;
        }
    }
    public static DLL insert(DLL list,int d){
        Node n_node= new Node(d);
        if(list.head==null&&list.tail==null){
            list.head=n_node;
            list.tail=n_node;
        }
        else{
            n_node.link_prev=list.tail;
            list.tail.link_next=n_node;
            list.tail=n_node;
        }
        return list;
    }
    public static void delete_back(DLL list){
        if(list.head==null){
            System.out.println("Underflow");
        }
        else{
            list.tail=list.tail.link_prev;
            list.tail.link_next=null;

        }
    }
    public static void print(Node ptr,DLL list){
        int ctr=1;
        while(ptr!=null){
            System.out.printf("Element %d : %d\n",ctr,ptr.val);
            ctr++;
            ptr=ptr.link_next;
        }
        System.out.println();
    }
    public static void print_rev(Node ptr,DLL list){
        int ctr=1;
        while(ptr.link_next!=null){
            ctr++;
            ptr=ptr.link_next;
        }
        Node ptr_1=list.tail;
        while(ptr_1!=null){
            System.out.printf("Element %d : %d\n",ctr,ptr_1.val);
            ctr--;
            ptr_1=ptr_1.link_prev;
        }
        System.out.println();
        
    }
    public static void main (String[] args) {
        DLL list = new DLL();
        list=insert(list,10);
        list=insert(list,8);
        list=insert(list,6);
        list=insert(list,4);
        list=insert(list,2);
        print(list.head,list);
        print_rev(list.head,list);
        delete_back(list);
        print(list.head,list);
        
    }
}