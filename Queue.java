/*package whatever //do not write package name here */

import java.io.*;

class Queue{
    Node front;
    Node rear;
    int g_time=-1;
    static class Node{
        int p;
        int time;
        Node n_link;
        Node p_link;
        Node(int d,int e){
            this.p=d;
            this.time=e
            this.p_link=null;
            this.n_link=null;
        }
    }
    public static Queue insert(Queue q,int p,int time){
        Node n_node= new Node(p,time);
        if(q.front==null && q.rear==null ){
            q.front=n_node;
            q.rear=n_node;
        }
        else{
            q.rear.n_link=n_node;
            n_node.p_link=q.rear;
            q.rear=n_node;
        }
        return q;
    }
    public static boolean search(Queue q,int pro){
        Node ptr=q.front;
        while(ptr!=null){
            if(ptr.time-g_time>=0){
                delete(q,ptr);
                return true;
            }
            ptr=ptr.n_link;
        }
        return false;
    }
    public static void delete(Queue q,Node ptr){
        if (q.front==q.rear){
            q.front=null;
            q.rear=null;
        }
        else{
            ptr.p_link.n_link=ptr.n_link;
            ptr.n_link.p_link=ptr.p_link;
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
		Queue q = new Queue();
		Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        int t=input.nextInt();
        int num[]=new int[n];
        for(int i=0;i<t;i++){
            int a=input.nextInt();
            g_time++;
            if(a==1){
                int p=input.nextInt();
                int time=input.nextInt();
                q=insert(q,p,time);

            }
            else{
                int pro=input.nextInt();
                boolean ans=search(q,pro);
                if(ans==true){
                    num[pro]++;

                }

            }
        }
        for(int i=0;i<n;i++){
            System.out.println(num[i]);
        }
	}
}