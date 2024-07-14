package _1_recursion.sorting;

import java.util.Vector;

/**
 * _1_mergeSort
 */
//divide and merge
//pseudo-code:
/*
 * void mergeSort(int arr[],int low,int high){
 *        int mid=(low+high)/2;
 *        mergeSort(arr,low,mid);//sort first half  after dividing continuously
 *        mergeSort(arr,mid+1,high);//sorrt next half after dividing continuously
 *        merge(arr,low,high);//now merge 2 previously 2 array(after sorting) 
 * 
 * }
 */



public class _1_mergeSort {

    //O(logn**base 2)-->for func
    //O(n)--->for merge
    //Overall:O(nlong)
    //space:O(n)--->coz no extra space is  used and we have cleared Vector at each step as well

    public static void func(int arr[],int lowIdx,int highIdx,Vector<Integer>temp){
        if (lowIdx==highIdx) {
            return;
        }
        int midIdx=(lowIdx+highIdx)/2;
        func(arr, lowIdx, midIdx,temp);
        func(arr, midIdx+1, highIdx,temp);
        merge(arr, lowIdx, midIdx, highIdx, temp);
    }

   public static void merge(int arr[],int lowIdx,int midIdx,int highIdx,Vector<Integer>temp){
        int leftIdx=lowIdx;
        int rightIdx=midIdx+1;
        while (leftIdx<=midIdx&&rightIdx<=highIdx) {
            if (arr[leftIdx]<=arr[rightIdx]) {
                temp.add(arr[leftIdx++]);
            }else{
                temp.add(arr[rightIdx++]);
            }
        }
        //if anyone is remaining
        while (leftIdx<=midIdx) {
            temp.add(arr[leftIdx++]);
        }
        while (rightIdx<=highIdx) {
            temp.add(arr[rightIdx++]);
        }
        //now temp has sorted elemnts,copy it to array----IMP
        for(int i=lowIdx;i<=highIdx;i++){
            arr[i]=temp.get(i-lowIdx);
        }
        temp.clear();//clear to use next
   }
    public static void main(String[] args) {
        int a[]={1,3,2,5,4,6,-1};
        Vector<Integer>ans=new Vector<>();
        func(a, 0, a.length-1, ans);
        for (Integer e : a) {
            System.out.print(e+" ");
        }
    }
}