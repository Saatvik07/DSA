import java.util.*; 

class lab_8_2{
    static class heap{
        int[] heap_arr;
        int size=0;
        heap(int n){
            heap_arr=new int[n];
        }
    }
    public static void heapify(heap binary_heap, int iterator_val){
        int parent=(iterator_val-1)/2;
        if(binary_heap.heap_arr[parent]>0){
            if(binary_heap.heap_arr[parent]<binary_heap.heap_arr[iterator_val]){
                int temp=binary_heap.heap_arr[parent];
                binary_heap.heap_arr[parent]=binary_heap.heap_arr[iterator_val];
                binary_heap.heap_arr[iterator_val]=temp;
                heapify(binary_heap,parent);
            }
        }
    }
    public static void swap(heap binary_heap, int val_1,int val_2){
        int temp=binary_heap.heap_arr[val_1];
        binary_heap.heap_arr[val_1]=binary_heap.heap_arr[val_2];
        binary_heap.heap_arr[val_2]=temp;
    }
    public static void heapify_min(heap binary_heap, int iterator_val){
        int left_child=2*iterator_val+1;
        int right_child=2*iterator_val+2;
        if(left_child<binary_heap.size && right_child<binary_heap.size){
            if(binary_heap.heap_arr[left_child]>binary_heap.heap_arr[iterator_val] && binary_heap.heap_arr[right_child]>binary_heap.heap_arr[iterator_val] ){
                if(binary_heap.heap_arr[left_child]>binary_heap.heap_arr[right_child]){
                    swap(binary_heap,left_child,iterator_val);
                    heapify_min(binary_heap,left_child);
                }
                else{
                    swap(binary_heap,right_child,iterator_val);
                    heapify_min(binary_heap,right_child);
                }
            }
            else if(binary_heap.heap_arr[left_child]>binary_heap.heap_arr[iterator_val]){
                    swap(binary_heap,left_child,iterator_val);
                    heapify_min(binary_heap,left_child);
            }
            else if(binary_heap.heap_arr[right_child]>binary_heap.heap_arr[iterator_val]){
                swap(binary_heap,right_child,iterator_val);
                heapify_min(binary_heap,right_child);
            }
        }
        else if(left_child<binary_heap.size){
            if(binary_heap.heap_arr[left_child]>binary_heap.heap_arr[iterator_val]){
                swap(binary_heap,left_child,iterator_val);
                heapify_min(binary_heap,left_child);
            }
        }
        else if(right_child<binary_heap.size){
            if(binary_heap.heap_arr[right_child]>binary_heap.heap_arr[iterator_val]){
                swap(binary_heap,right_child,iterator_val);
                heapify_min(binary_heap,right_child);
            }
        }
    }
    public static void insert(heap binary_heap, int val){
        binary_heap.size++;
        binary_heap.heap_arr[binary_heap.size-1]=val;
        heapify(binary_heap,binary_heap.size-1);
    }
    public static int extract_max(heap binary_heap){
        int temp=binary_heap.heap_arr[binary_heap.size-1];
        int min=binary_heap.heap_arr[0];
        binary_heap.heap_arr[binary_heap.size-1]=binary_heap.heap_arr[0];
        binary_heap.heap_arr[0]=temp;
        binary_heap.heap_arr[binary_heap.size-1]=0;
        binary_heap.size--;
        heapify_min(binary_heap,0);
        return min;

    } 
    static void kthLargestSum(int arr[], int n, int k,heap binary_heap) 
    { 
        int prefix[] = new int[n + 1]; 
        prefix[0] = 0; 
        prefix[1] = arr[0]; 
        for (int i = 2; i <= n; i++) 
            prefix[i] = prefix[i - 1] + prefix[i - 1]; 
        
        for (int i = 1; i <= n; i++) 
        { 
            for (int j = i; j <= n; j++) 
            { 
                int x = prefix[j] - prefix[i - 1]; 
                if (binary_heap.size< k) 
                    insert(binary_heap,x); 
    
                else
                { 
                    if (binary_heap.heap_arr[0] < x) 
                    { 
                        extract_min(binary_heap); 
                        insert(binary_heap,x); 
                    } 
                } 
            } 
        } 
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
    public static void main(String[] args) 
    { 
        Reader input=new Reader();
        int t=input.nextInt();
        for(int i=0;i<t;i++){
            int n=input.nextInt();
            int k=input.nextInt();
            heap binary_heap=new heap(k);
            int arr[]=new int[n];
            for(int j=0;j<n;j++){
                arr[j]=input.nextInt();
            }
            kthLargestSum(arr,n,k,binary_heap);
            for(int j=0;j<binary_heap.size;j++){
                System.out.printf("%d ",binary_heap.heap_arr[j]);
            }
        }
    } 
} 

/* This code is contributed by Danish Kaleem */
