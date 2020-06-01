import java.util.*;
public class sel_sort{
    public static void main(String[] args){
        int Arr[]={26,54,93,17,77,31,44,55,20};
        int temp=0,Ctr=0;;
        for(int i=0;i<Arr.length;i++){
            for(int j=i+1;j<Arr.length;j++){
                if(Arr[j]<Arr[i]){
                    temp=Arr[j];
                    Arr[j]=Arr[i];
                    Arr[i]=temp;
                    Ctr++;
                }
            }
        }
        for(int i=0;i<Arr.length;i++){
            System.out.printf("%d ",Arr[i]);
            System.out.println(Ctr);
            
        }
    }
}