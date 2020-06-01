
import java.util.*;
public class quick_sort{
    static int partition(int Arr[],int beg,int last){
        int pivot=Arr[last];
        int i=beg-1,temp;
        for(int j=beg;j<last+1;j++){
            if(Arr[j]<pivot){
                i++;
                temp=Arr[j];
                Arr[j]=Arr[i];
                Arr[i]=temp;
            }
        }
        temp=Arr[last];
        Arr[last]=Arr[i+1];
        Arr[i+1]=temp;
        return i+1;

    }
    static void recursive(int Arr[],int beg,int last){
        int part;
        if(beg<last){
            part=partition(Arr,beg,last);
            recursive(Arr,beg,part-1);
            recursive(Arr,part+1,last);
        }
    }
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        int t=input.nextInt();
        int Arr[]= new int[t];
        for(int j=0;j<t;j++){
            Arr[j]=input.nextInt();
        }
        recursive(Arr,0,t-1);
        int ans=0;
        for(int i=0;i<t;i+=2){
            ans+=(Arr[i+1]-Arr[i]);
        }
        System.out.println(ans);
    }
}
