import java.util.*;
public class merge_sort{
    static void helper(int Arr[],int beg,int last,int mid){
        int n_l=mid-beg+1,n_r=last-mid;
        int temp_l[]=new int[n_l];
        int temp_r[]=new int[n_r];
        int x=0,y=0,Arr_index=beg;
        for(int i=0;i<n_l;i++){
            temp_l[i]=Arr[beg+i];
        }
        for(int i=0;i<n_r;i++){
            temp_r[i]=Arr[mid+1+i];
        }
        while(x<n_l && y<n_r){
            if(temp_l[x]>temp_r[y]){
                Arr[Arr_index]=temp_l[x];
                x++;
            }
            else{
                Arr[Arr_index]=temp_r[y];
                y++;    
            }
            Arr_index++;
        }
        while(x<n_l){
            Arr[Arr_index]=temp_l[x];
            x++;
            Arr_index++;
        }
        while(y<n_r){
            Arr[Arr_index]=temp_r[y];
            y++;
            Arr_index++;
        }


    }
    static void recursive(int Arr[],int beg,int last){
        int mid;
        if(beg<last){
            mid=(beg+last)/2;
            recursive(Arr,beg,mid);
            recursive(Arr,mid+1,last);
            helper(Arr,beg,last,mid);
        }
    }
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        int t=input.nextInt();
        int q=input.nextInt();
        for(int j=0;j<t;j++){
            Arr[j]=input.nextInt();
        }
        recursive(Arr,0,t-1);
        for(int i=0;i<q;i++){
            int num=input.nextInt();
            System.out.println(Arr[num-1]);
        }
        System.out.println(sum);
    }
}