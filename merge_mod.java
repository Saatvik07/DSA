#include <bits/stdc++.h>
using namespace std;
void merger(long a[],int start,int end,int mid,long m){
    int i=start,j=mid+1;
    int x=start;
    while(i<=mid&&j<=end){
        if(a[i]%m<a[j]%m){
            a[x]=a[x]+(a[i]%m)*m;
            i++;
        }
        else{
           a[x]=a[x]+(a[j]%m)*m; 
           j++;
        }
        x++;
    }
    while(i<=mid){
        a[x]=a[x]+(a[i]%m)*m;
        i++;
        x++;
    }
    while(j<=end){
        a[x]=a[x]+(a[j]%m)*m; 
         j++;
         x++;
    }
    for(int i=start;i<=end;i++){
        a[i]=a[i]/m;
    }
    
}
void recursive(long a[],int start,int end,long m){
    int mid;
    if(start<end){
        mid=(start+end)/2;
        recursive(a,start,mid,m);
        recursive(a,mid+1,end,m);
        merger(a,start,end,mid,m);
    }
}
int main() {
	int n,l;
	cin>>n;
	long a[n];
	for(int i=0;i<n;i++){
	    cin>>a[i];
	}
	long m=*max_element(a,a+n)+1;
	recursive(a,0,n-1,m);
	int sum=0;
	int i=0;
	while(i<n){
	    sum+=a[i+1]-a[i];
	    i+=2;
	}
	cout<<sum<<endl;
	
	return 0;
}