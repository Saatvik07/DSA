import java.lang.*;
import java.io.*;
import java.util.*;

class Huffman_coding{
    static int bits_a=0,bits_b=0;
    static class Node{
        Node left;
        Node right;
        int frequency;
        int gray_scale_value;
        Node(int f,int g){
            this.frequency=f;
            this.gray_scale_value=g;
            this.left=null;
            this.right=null;
        }
    }
    static class heap{
        Node[] heap_arr;
        int size;
        heap(int n){
            heap_arr=new Node[n];
            this.size=0;
        }
    }
    public static void swap(heap binary_heap, int val_1,int val_2){
        Node temp=binary_heap.heap_arr[val_1];
        binary_heap.heap_arr[val_1]=binary_heap.heap_arr[val_2];
        binary_heap.heap_arr[val_2]=temp;
    }
    public static void heapify(heap binary_heap, int iterator_val){
        int parent=(iterator_val-1)/2;
        if(binary_heap.heap_arr[parent].frequency>0){
            if(binary_heap.heap_arr[parent].frequency>binary_heap.heap_arr[iterator_val].frequency){
                swap(binary_heap,parent,iterator_val);
                heapify(binary_heap,parent);
            }
        }
    }
    
    public static void heapify_min(heap binary_heap, int iterator_val){
        int left_child=2*iterator_val+1;
        int right_child=2*iterator_val+2;
        if(left_child<binary_heap.size && right_child<binary_heap.size){
            if(binary_heap.heap_arr[left_child].frequency<binary_heap.heap_arr[iterator_val].frequency && binary_heap.heap_arr[right_child].frequency<binary_heap.heap_arr[iterator_val].frequency){
                if(binary_heap.heap_arr[left_child].frequency<binary_heap.heap_arr[right_child].frequency){
                    swap(binary_heap,left_child,iterator_val);
                    heapify_min(binary_heap,left_child);
                }
                else{
                    swap(binary_heap,right_child,iterator_val);
                    heapify_min(binary_heap,right_child);
                }
            }
            else if(binary_heap.heap_arr[left_child].frequency<binary_heap.heap_arr[iterator_val].frequency){
                    swap(binary_heap,left_child,iterator_val);
                    heapify_min(binary_heap,left_child);
            }
            else if(binary_heap.heap_arr[right_child].frequency<binary_heap.heap_arr[iterator_val].frequency){
                swap(binary_heap,right_child,iterator_val);
                heapify_min(binary_heap,right_child);
            }
        }
        else if(left_child<binary_heap.size){
            if(binary_heap.heap_arr[left_child].frequency<binary_heap.heap_arr[iterator_val].frequency){
                swap(binary_heap,left_child,iterator_val);
                heapify_min(binary_heap,left_child);
            }
        }
        else if(right_child<binary_heap.size){
            if(binary_heap.heap_arr[right_child].frequency<binary_heap.heap_arr[iterator_val].frequency){
                swap(binary_heap,right_child,iterator_val);
                heapify_min(binary_heap,right_child);
            }
        }
    }
    
    public static void insert(heap binary_heap, Node n_node){
        binary_heap.size++;
        binary_heap.heap_arr[binary_heap.size-1]=n_node;
        heapify(binary_heap,binary_heap.size-1);
    }
    public static Node extract_min(heap binary_heap){
        Node temp=binary_heap.heap_arr[binary_heap.size-1];
        Node min=binary_heap.heap_arr[0];
        binary_heap.heap_arr[binary_heap.size-1]=binary_heap.heap_arr[0];
        binary_heap.heap_arr[0]=temp;
        binary_heap.heap_arr[binary_heap.size-1]=null;
        binary_heap.size--;
        heapify_min(binary_heap,0);
        return min;

    } 
    public static void print(Node root, String s){
        if(root.left==null && root.right==null && root.gray_scale_value>=0){
            bits_a+=s.length()*root.frequency;
            return;
        }
        print(root.left,s+'0');
        print(root.right,s+'1');
    }
    static class Reader { 
        final private int BUFFER_SIZE = 1 << 16; 
        private DataInputStream din; 
        private byte[] buffer; 
        private int bufferPointer, bytesRead; 
  
        public Reader() 
        { 
            din = new DataInputStream(System.in); 
            buffer = new byte[BUFFER_SIZE]; 
            bufferPointer = bytesRead = 0; 
        } 
  
        public String readLine() throws IOException 
        { 
            byte[] buf = new byte[64]; // line length 
            int cnt = 0, c; 
            while ((c = read()) != -1) 
            { 
                if (c == '\n') 
                    break; 
                buf[cnt++] = (byte) c; 
            } 
            return new String(buf, 0, cnt); 
        } 
  
        public int nextInt() throws IOException 
        { 
            int ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do
            { 
                ret = ret * 10 + c - '0'; 
            }  while ((c = read()) >= '0' && c <= '9'); 
  
            if (neg) 
                return -ret; 
            return ret; 
        } 
         public long nextLong() throws IOException 
        { 
            long ret = 0; 
            byte c = read(); 
            while (c <= ' ') 
                c = read(); 
            boolean neg = (c == '-'); 
            if (neg) 
                c = read(); 
            do { 
                ret = ret * 10 + c - '0'; 
            } 
            while ((c = read()) >= '0' && c <= '9'); 
            if (neg) 
                return -ret; 
            return ret; 
        } 
        private void fillBuffer() throws IOException 
        { 
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
            if (bytesRead == -1) 
                buffer[0] = -1; 
        } 

        private byte read() throws IOException 
        { 
            if (bufferPointer == bytesRead) 
                fillBuffer(); 
            return buffer[bufferPointer++]; 
        } 

        public void close() throws IOException 
        { 
            if (din == null) 
                return; 
            din.close(); 
        } 
    

    }
    public static void main(String[] args) throws IOException 
    { 
        Reader input=new Reader();
        int r=input.nextInt();
        int c=input.nextInt();
        int t_bits=r*c*8;
        int arr[] = new int[256];
        int ctr=0;
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                int val=input.nextInt();
                if(arr[val]==0) ctr++;
                arr[val]++;
            }
        }
        heap binary_heap=new heap(ctr);
        for(int i=0;i<256;i++){ 
            if(arr[i]>0){
                Node n_node= new Node(arr[i],i);
                insert(binary_heap,n_node);
            }
        }
        Node root=null;
        while(binary_heap.size>1){
            Node x=extract_min(binary_heap);
            Node y=extract_min(binary_heap);
            Node n_node=new Node(x.frequency+y.frequency,-1);
            n_node.left=x;
            n_node.right=y;
            root=n_node;
            insert(binary_heap,n_node);
        }
        print(root,"");
        double a=(double)t_bits/(double)bits_a;
        double ans=(double) Math.round(a*10)/10;
        System.out.println(ans);
        int ctr_1=0;
        for(int i=0;i<=24;i++){
            if(i<24){
                int highest=arr[i*10],iter=i*10,sum=0;
            for(int j=i*10;j<=(i*10)+9;j++){
                    if(arr[j]>highest){
                        highest=arr[j];
                        iter=j;
                    }
                    sum+=arr[j];
                    arr[j]=0;
                }
                arr[iter]=sum;
            }
            else{
                int highest=arr[i*10],iter=i*10,sum=0;
                for(int j=240;j<256;j++){
                    if(arr[j]>highest){
                        highest=arr[j];
                        iter=j;
                    }
                    sum+=arr[j];
                    arr[j]=0;
                }
                arr[iter]=sum;
            }
        }
        heap binary_heap_1=new heap(25);
        for(int i=0;i<256;i++){
            if(arr[i]>0){
                Node n_node=new Node(arr[i],i);
                insert(binary_heap_1,n_node);
            }
        }
        Node root_1=null;
        while(binary_heap_1.size>1){
            Node x=extract_min(binary_heap_1);
            Node y=extract_min(binary_heap_1);
            Node n_node=new Node(x.frequency+y.frequency,-1);
            n_node.left=x;
            n_node.right=y;
            root_1=n_node;
            insert(binary_heap_1,n_node);
        }
        bits_a=0;
        print(root_1,"");
        double b=(double)t_bits/(double)bits_a;
        double ans_1=(double) Math.round(b*10)/10;
        System.out.println(ans_1);
    }
} 
