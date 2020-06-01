import java.util.*;
import java.io.*;
public class LL{
    Node head;
    static class Node{
        int val;
        Node link;
        Node(int d){
            this.val=d;
            this.link=null;
        }

    }
    public static LL insert(LL list,int val){
        Node n_node= new Node(val);
        n_node.link=null;
        if(list.head==null){
            list.head=n_node;
        }
        else{
            Node ptr=list.head;
            while(ptr.link!=null){
                ptr=ptr.link;
            }
            ptr.link=n_node;
        }
        return list;
    }
    public static void printlist(LL list){
        Node p=list.head;
        while(p.link!=null){
            System.out.printf("%d ",p.data);
            p=p.link;
        }
    }
    public static void main(String[] args){
            LL list= new LL();
                list=insert(list,1);
                list=insert(list,2);
            printlist(list);
    }
}