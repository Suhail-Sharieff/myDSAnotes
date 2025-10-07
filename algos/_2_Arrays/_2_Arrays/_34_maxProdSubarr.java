package _2_Arrays;
/*
Given an integer array nums, find a 
SUBARRAY
 that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

 

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */
public class _34_maxProdSubarr {
    public static void main(String[] args) {
        int nums[]={2,3,-2,4};
        System.out.println(brute(nums));
        System.out.println(optimal(nums));
    }
    public static int brute(int nums[]){//for each num in nums,store the value of max possible subarr product and fiannaly return MAX of all those
        int len=nums.length;
        int prefix[]=new int[len];
        for(int i=0;i<nums.length;i++){
           int prod=1;
           int max=-11;
           for(int j=i;j<len;j++){
            prod=prod*nums[j];
            max=Math.max(max,prod);
           }
           prefix[i]=max;
        }
        int max=Integer.MIN_VALUE;
        for(int e:prefix){
            max=Math.max(max,e);
        }
        return max;
    }

    //also based on fact that the ans is max[leftProd,rightProd] for each negative number
    public static int optimal(int nums[]){
        //this is based on fact that if we have even odd numbers we get -ve prod and vice versa for even num of neg numbers,also we try to avod zero since will make prod 0, also we keep track of prefix prod from first to last keepin in ind to traet 0 and also twe keep track of suffix array ,the ans is max(suffix,prefix) prod
        int ans=Integer.MIN_VALUE;
        double prefProd=1,suffProd=1;//double to handle large prods
        int len=nums.length;
        for (int i = 0; i < len; i++) {
            //keep in kind existence of 0
            if (prefProd==0) {
                prefProd=1;
            }
            if (suffProd==0) {
                suffProd=1;
            }


            prefProd*=nums[i];
            suffProd*=nums[len-i-1];
            ans=(int)(Math.max(ans, Math.max(prefProd, suffProd)));
        }
        return ans;
    }
}
