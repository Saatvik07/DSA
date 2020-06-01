
import java.io.*;
import java.lang.*;
import java.util.*;
class BST_2{
    static Node root;
    static ArrayList<Integer> BST_arr=new ArrayList<Integer>(1000);
    static class Node{
        int val;
        Node left;
        Node right;
        public Node(int d){
            this.val=d;
            this.left=null;
            this.right=null;
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
    public BST_2(){
        root=null;
    }
 
    public  static Node treebuilder(int[] preorder, int[] inorder) {
        
        return recursive_subtree_parser(0, 0, inorder.length - 1,  preorder, inorder);
    }
    
    public static Node recursive_subtree_parser(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) 
            return null;

        Node root = new Node(preorder[preStart]);
        int inIndex = 0;
        
        for (int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == root.val) {
                inIndex = i; 
            }
        }
        root.left = recursive_subtree_parser(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = recursive_subtree_parser(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
    public static boolean BST_check (int arr_1[], ArrayList<Integer> arr,int n) {

        for(int i=0;i<n;i++){
            if(arr_1[i]!=arr.get(i)) return false;
        }
        return true;
    } 
    public static int smallest_val(Node root){
        if(root.left==null) return root.val;
        else return smallest_val(root.left);
    }
     public static int greatest_val(Node root){
        if(root.right==null) return root.val;
        else return greatest_val(root.right);
    }
    public static void postorder(Node root){
        if(root!=null){
            postorder(root.left);
            postorder(root.right);
            System.out.printf("%d ",root.val);
        }
    }
    public static void inorder_arr_creator(Node root){
        if(root!=null){
            inorder_arr_creator(root.left);
            BST_arr.add(root.val);
            inorder_arr_creator(root.right);
        }
        
    }
      public static void main (String[] args) {
        BST_2 tree=new BST_2();
        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        int arr_1[]=new int[n];
        int arr_2[]=new int[n];
        for(int i=0;i<n;i++){
            arr_1[i]=input.nextInt();
        }
        for(int i=0;i<n;i++){
            arr_2[i]=input.nextInt();
        }
        root=treebuilder(arr_1,arr_2);
        postorder(root);
        int min=smallest_val(root);
        int max=greatest_val(root);
        Arrays.sort(arr_1);
        BST_arr.clear();
        inorder_arr_creator(root);
        boolean ans=BST_check(arr_1,BST_arr,n);
        System.out.println();
        if(ans==true){
            System.out.println("YES");
        }
        else System.out.println("NO");
    }
}