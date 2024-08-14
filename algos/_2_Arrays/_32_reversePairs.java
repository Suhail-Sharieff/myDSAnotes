/*
 * 
Given an integer array nums, return the number of reverse pairs in the array.

A reverse pair is a pair (i, j) where:

0 <= i < j < nums.length and
nums[i] > 2 * nums[j].
 

Example 1:

Input: nums = [1,3,2,3,1]
Output: 2
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 3, nums[4] = 1, 3 > 2 * 1
Example 2:

Input: nums = [2,4,3,5,1]
Output: 3
Explanation: The reverse pairs are:
(1, 4) --> nums[1] = 4, nums[4] = 1, 4 > 2 * 1
(2, 4) --> nums[2] = 3, nums[4] = 1, 3 > 2 * 1
(3, 4) --> nums[3] = 5, nums[4] = 1, 5 > 2 * 1
 

Constraints:

1 <= nums.length <= 5 * 104
-231 <= nums[i] <= 231 - 1
 */

 package _2_Arrays;

import java.math.BigInteger;

/**
  * _32_reversePairs
  */
 public class _32_reversePairs {
 
    public static int GLOBAL_CNT=0;
    public static void main(String[] args) {
        int nums[]={5,6,1,2,3,5};
        sort(nums, 0, nums.length-1);
        System.out.println(GLOBAL_CNT);
        
    }

    public static int brute(int nums[]){//used BigInteger coz the nums values r huge ----passes only 32/140 testCases
        int cnt=0;
        int len=nums.length;;
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                BigInteger x=BigInteger.valueOf(nums[i]);
                BigInteger y=BigInteger.valueOf(nums[j]);
                BigInteger v=y.multiply(BigInteger.TWO);
                int l=x.compareTo(v);
                if (l==1) {
                    cnt++;
                }
            }
        }
        return(cnt);
    }

    //OPTIMAL SOLN IS MANUPULATION IN MERGE SORT:
    //check line 77 and counterFunc
    //watch from 22:48
    //https://www.youtube.com/watch?v=0e4bZaP3MDI&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=43
    public static void sort(int nums[],int lowIdx,int highIdx){
        if (lowIdx>=highIdx) {
            return;
        }
        int midIdx=(highIdx+lowIdx)/2;
        sort(nums, lowIdx, midIdx);
        sort(nums, midIdx+1, highIdx);
        counterFunc(nums, lowIdx, midIdx, highIdx);
        merge(nums,lowIdx,midIdx,highIdx);
    }
    public static void merge(int nums[],int lowIdx,int midIdx,int highIdx){
        int left=lowIdx,right=midIdx+1;
        int i=0;
        int temp[]=new int[highIdx-lowIdx+1];
        while (left<=midIdx&&right<=highIdx) {
            if (nums[left]<=nums[right]) {
                temp[i++]=nums[left++];
            }else{
                temp[i++]=nums[right++];
            }
        }
        while (left<=midIdx) {
            temp[i++]=nums[left++];
        }
        while (right<=highIdx) {
            temp[i++]=nums[right++];
        }
        //copy to nums
        for (i = 0; i < temp.length; i++) {
            nums[lowIdx + i] = temp[i];
        }
    }
    public static void counterFunc(int nums[],int lowIdx,int midIdx,int highIdx){
        int right=midIdx+1;
        for (int i = lowIdx; i <=midIdx; i++) {
            while (right<=highIdx&&nums[i]>(2*nums[right])) {
                right++;
            }
            GLOBAL_CNT+=right-(midIdx+1);
        }
    }
 }