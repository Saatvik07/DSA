import java.util.*;
public class inversion_pairs{
    static int helper(int Arr[],int beg,int last,int mid){
        int n_l=mid-beg+1,n_r=last-mid;
        int temp_l[]=new int[n_l];
        int temp_r[]=new int[n_r];
        int x=0,y=0,Arr_index=beg,swaps=0;
        for(int i=0;i<n_l;i++){
            temp_l[i]=Arr[beg+i];
        }
        for(int i=0;i<n_r;i++){
            temp_r[i]=Arr[mid+1+i];
        }
        while(x<n_l && y<n_r){
            if(temp_l[x]<=temp_r[y]){
                Arr[Arr_index]=temp_l[x];
                x++;
            }
            else{
                swaps+=(mid+1)-(beg+x);
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
        return swaps;

    }
    static int recursive(int Arr[],int beg,int last){
        int mid,count=0;
        if(beg<last){
            mid=(beg+last)/2;
            count+=recursive(Arr,beg,mid);
            count+=recursive(Arr,mid+1,last);
            count+=helper(Arr,beg,last,mid);

        }
        return count;
    }
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        int t=input.nextInt();
        int Arr[]=new int[t];
        for(int j=0;j<t;j++){
            Arr[j]=input.nextInt();
        }
        int ans=recursive(Arr,0,t-1);
        System.out.println(ans);
        
    }
}