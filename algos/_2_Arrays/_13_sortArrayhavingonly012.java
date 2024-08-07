package _2_Arrays;

public class _13_sortArrayhavingonly012 {//optimal soln is mind blowing
    //sort arry having only 0,1,2

    //brute force using any sorting algo 

    public static void main(String[] args) {
        int arr[]={0,1,2,0,1,2,1,2,0,0,0,1};

        // better(arr);

        optimal(arr);

        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }



    public static void better(int arr[]){//o(2n)---O(n)
        int nZero=0,nOnes=0,nTwo=0;
        for (int i : arr) {
            if (i==0) {
                nZero++;
            }else if(i==1){
                nOnes++;
            }else{
                nTwo++;
            }
        }
        int idx=0;
        while (nZero--!=0) {
            arr[idx++]=0;
        }
        while (nOnes--!=0) {
            arr[idx++]=1;
        }
        while (nTwo--!=0) {
            arr[idx++]=2;
        }
    }

    //dutch national flag algorithm--watch this--O(n)---O(1)
    //https://www.youtube.com/watch?v=tp8JIuCXBaU&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=22

    /*intution
      it uss 3 pointers low ,mid , high

     ->all elmnts from 0 to low-1 must be 0 
     ->all elmnts from low to mid-1 must be 1
     ->all elmnts from high+1 to len-1 must be 2 


     pseudo code

     untill mid<=high
     if arr[mid]==0{swap(mid,low);mid++;low++}
     if(arr[mid]==1){mid++;}
     if(arr[mid]==2){swap(mid,high);high--}
      
     */

    public static void optimal(int arr[]){
        int low=0,mid=0,high=arr.length-1;

        while (mid<=high) {
            if (arr[mid]==0) {
                swap(low, mid, arr);
                low++;mid++;
            }else if (arr[mid]==1) {
                mid++;
            }else if (arr[mid]==2) {
                swap(mid, high, arr);
                high--;
            }
        }
    }
    public static void swap(int from,int to,int arr[]){
        int temp=arr[from];
        arr[from]=arr[to];
        arr[to]=temp;
    }
}
