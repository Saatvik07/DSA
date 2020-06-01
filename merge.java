import java.util.*;
public class merge{
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);

        int n = input.nextInt();
        int arr[] = new int[n];

        for (int i = 0; i < n;++i) {
            arr[i] = input.nextInt();
        }
        int t = input.nextInt();
        for (int i = 0; i < t; ++i) {
            int x = input.nextInt();
            int low=lower(0,n-1,x,arr);
            int upper=upper(0,n-1,x,arr);
            if(low==-1){
                System.out.println(low);
            }
            else{
                System.out.println(upper-low+1);
            }
        }

    }

    public static int lower(int lo, int hi, int val, int arr[]) {
       int low_val=-1;
       while(lo<hi){
        int mid =(lo+hi)/2;
        if(arr[mid]==val){
            low_val=mid;
            hi=mid;
        }
        else if (arr[mid]<val){
            lo=mid;
        }
        else {
            hi=mid;
        }
    }
    return low_val;
    }

    public static int upper(int lo, int hi, int val, int arr[]) {
        int up_val=0;
        while(lo<=hi){
            int mid =(lo+hi)/2;
            if(arr[mid]>val){
                up_val=mid;
                hi=mid;
            }
            else {
                lo=mid;
            }
        }
        return up_val;
}
}