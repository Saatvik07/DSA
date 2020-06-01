import java.util.*;
import java.io.*;
import java.lang.*;
public class prob_2{
    static int size=0;
    Node head;
    Node last;
    static class Node{
        int val;
        Node link_next;
        Node(int d){
            this.val=d;
            this.link_next=null;
        }

    }
    public static prob_2 insert(prob_2 list,int val,Node ptr,int a){
        Node n_node= new Node(val);
        if(a==0){
        if(list.head==null){
            list.head=n_node;
            list.last=n_node;
        }
        else{
            list.last.link_next=n_node;
            list.last=n_node;
        }
    }
    else{
        n_node.link_next=ptr.link_next;
        ptr.link_next=n_node;
    }
        return list;
    }
    
    public static void main(String[] args) 
    { 
        Scanner input = new Scanner(System.in);
        prob_2 list = new prob_2();
        int num_c=input.nextInt();
        int arr_c[]=new int[num_c];
        int q=input.nextInt();
        Node n=new Node(0);
        for(int i=0;i<num_c;i++){
            arr_c[i]=input.nextInt();
            list=insert(list,arr_c[i],n,0);
        }
        Node ptr =list.head;
        for(int i=0;i<q;i++){
            int num=input.nextInt();
            if(num==1){
                System.out.println(ptr.val);
            }
            else if(num==2){
                if(ptr.link_next!=null){
                ptr=ptr.link_next;
                }
            }
            else{
                int c=input.nextInt();
                insert(list,c,ptr,1);
            }
        }
    
    }

}