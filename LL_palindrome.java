import java.util.*;
import java.io.*;
import java.lang.*;
public class LL_palindrome{
    Node head;
    static int size=0;
    static class Node{
        int val;
        Node link;
        Node(int d){
            this.val=d;
            this.link=null;
        }

    }
    public static Node recursive_reverse(Node head){
        if(head==null || head.link==null){
            return head;
        }
        Node next=head.link;
        head.link=null;
        Node rest=recursive_reverse(next);
        next.link=head;
        return rest;
    } 
    public static LL_palindrome insert(LL_palindrome list,int val){
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
    public static Node middle(LL_palindrome list){
        Node slow = list.head;
        Node fast = list.head;
        while(fast!= null && fast.link!=null){
            slow=slow.link;
            fast=fast.link.link;
        }
        if(fast==null) size++;
        return slow;
    }
    public static boolean compare(LL_palindrome list, Node ptr_1){
        Node ptr = list.head;
        while(ptr_1!=null){
            if(ptr.val!=ptr_1.val){
                return false;
            }
            ptr=ptr.link;
            ptr_1=ptr_1.link;
        }
        return true;
    }
    public static void main(String[] args) 
	{ 
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int Arr[] = new int[n];
        LL_palindrome list = new LL_palindrome();
		for(int i=0;i<n;i++){
            Arr[i]=input.nextInt();
            list=insert(list,Arr[i]);
		}
        Node mid=middle(list);
        LL_palindrome list_1 = new LL_palindrome();
        Node copy_of_mid;
        if(size==1) copy_of_mid = mid;
        else copy_of_mid = mid.link;
        while(copy_of_mid !=null){
            list_1=insert(list_1,copy_of_mid.val);
            copy_of_mid=copy_of_mid.link;
        }
        Node rev_half_list = recursive_reverse(list_1.head);
        boolean ans = compare(list,rev_half_list);
        System.out.println(ans);
	}

}