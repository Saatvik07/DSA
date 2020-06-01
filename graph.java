import java.util.*;
import java.io.*;
import java.lang.*;
 
public class graph_list{
    static class LL{
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
    public static void printlist_1(Node p){
        while(p!=null){
            System.out.printf("%d ",p.val);
            p=p.link;
        }
        System.out.println();
        
    }
    public static void delete(Node ptr,int data){
        Node prev=null;
        while(ptr.val!=data){
            prev=ptr;
            ptr=ptr.link;
        }
        prev.link=ptr.link;
        
    }
}
    static class graph{
        int n_vertices;
        int linked_list_vertices=new LL();
        graph(int n_vertices){
            this.n_vertices=n_vertices;
        }
        public static void graph_initialze(){
            Node temp=linked_list_vertices.head;
            while(temp!=null){
                LL temp.val=new LL();
                temp=temp.link;
            }
        }

    }
    public static void main(String[] args) 
	{ 
        
	}

}