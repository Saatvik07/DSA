import java.util.*;
import java.io.*;
import java.lang.*;
public class CLL_lab_sec_b{
    Node head;
    Node tail;
    Node curr;
    static class Node{
        int val;
        Node link_next;
        Node(int d){
            this.val=d;
            this.link_next=null;
        }

    }
    public static CLL_lab_sec_b insert(CLL_lab_sec_b list,int val){
        Node n_node= new Node(val);
        if(list.head==null){
            list.head=n_node;
            list.curr=n_node;
            list.tail=n_node;
            list.tail.link_next=list.head;
        }
        else if(list.curr==list.tail){
            list.tail.link_next=n_node;
            list.tail=n_node;
            list.tail.link_next=list.head;
        }
        else{
            n_node.link_next=list.curr.link_next;
            list.curr.link_next=n_node;
        }
        return list;
    }
    
    
    public static void delete(CLL_lab_sec_b list){
        list.curr=list.curr.link_next;
    }
    public static void main(String[] args) 
    { 
        Scanner input = new Scanner(System.in);
        int test=input.nextInt();
        CLL_lab_sec_b list = new CLL_lab_sec_b();
        list = insert(list, 1);
        for(int i=0;i<test;i++){
            int q=input.nextInt();
            if(q==1){
                int d=input.nextInt();
                list=insert(list,d);
            }
            else if(q==2){
                delete(list);
            }
            else{
                System.out.printf("%d\n",list.curr.val);
            }
        }
        
    
    }

}