package _2_Arrays;

import java.util.ArrayList;
import java.util.List;

public class _15_kandaneAlgo {
    // find subarray with max POSITIVE sum(subarray means it should be contigous)

    public static List<Integer> brute(int arr[],List<Integer>ans) {
        long max=Long.MIN_VALUE;
        int from=0,to=0;
        for (int i = 0; i < arr.length; i++) {
           int sum=0;
           for (int j = i; j < arr.length; j++) {
            sum+=arr[j];
            if (sum>max) {
                max=sum;
                from=i;
                to=j;
            }
           }
        }
        for (int i = from; i <= to; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }


    //kadane's algo:--optimal for the given condition where we need only max positive sum 
    //https://www.youtube.com/watch?v=AHZpyENo7k4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=24
    public static List<Integer> kadane(int arr[],List<Integer>ans){
        long sum=0;
        long max=Long.MIN_VALUE;
        int len=arr.length;
        int from=0,to=0;

        for (int i = 0; i < len; i++) {

            if (sum==0) {
                from=i;//to kepp track to print that subarr aswell
            }
           
            sum+=arr[i];
            
            

            if (sum>max) {
                max=sum;
                to=i;
            }




            if (sum<0) {
                sum=0;
            }
        }
       
        if (max<0) {
            System.out.println("No subarray with max sum >0");
            return null;
        }

        for (int i = from; i <= to; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    /*class Solution {
    int maxSubarraySum(int[] arr) {
        // Code here
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int e:arr){
            sum+=e;
            max=Math.max(max,sum);
            sum=Math.max(sum,0);
        }
        return max;
    }
}
 */
   

    public static void main(String[] args) {
        List<Integer>li=new ArrayList<>();
        brute(new int[]{-2,-3,4,-1,-2,1,5,-3},li);
        System.out.println(li);
        li.clear();
        kadane(new int[]{-2,-3,4,-1,-2,1,5,-3},li);
        System.out.println(li);
        li.clear();
        kadane(new int[]{1,2,-4},li);
        System.out.println(li);
    }
}
