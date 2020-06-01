/*package whatever //do not write package name here */

import java.io.*;

class Stack{
    Node top;
    static int g_time;
    static class Node{
        int p;
        int time;
        Node link;
        Node(int d,int e){
            this.p=d;
            this.time=e;
            this.link=null;
        }
    }
    public static Stack insert(Stack st,int d){
        Node n_node= new Node(d);
        if(st.top==null){
            st.top=n_node;
        }
        else{
            n_node.link=st.top;
            st.top=n_node;
        }
        return st;
    }
    public static void delete(Stack st){
        if(st.top==null){
            System.out.println("Underflow");
        }
        else{
            st.top=st.top.link;
        }
    }
    public static void search(Stack st, int pro){
        Node ptr=st.top;
        while(ptr.p!=){

        }
    }
    public static void print(Node ptr){
        int ctr=1;
        while(ptr!=null){
            System.out.printf("Element %d : %d\n",ctr,ptr.val);
            ctr++;
            ptr=ptr.link;
        }
        System.out.println();
    }
	public static void main (String[] args) {
		Stack st = new Stack();
		Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int t=input.nextInt();
        for(int i=0;i<t;i++){
            int a=input.nextInt();
            g_time++;
            if(a==1){
                int p=input.nextInt();
                int time=input.nextInt();
                st=insert(st,p,time);
            }
            else{
                int pro=input.nextInt();
                search(st,pro);
            }
        }
	}
}