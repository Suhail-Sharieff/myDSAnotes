package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subseq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given an integer array nums, return the length of the longest strictly increasing 
subsequence
.

 

Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 

Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104
 

Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class _01_longest_increasing_subseq {
    public static void main(String[] args) {
        int nums[]={10,9,2,5,3,7,101,18};
        rec(nums, 0, new ArrayList<>(),0);
    }

    static void rec(int nums[],int i,List<Integer>em,int cnt){
        if(i>=nums.length) {
            System.out.println(new ArrayList<>(em)+" "+cnt);
            cnt=0;
            return;
        }
        int prev=nums[i];
        em.add(nums[i]);
        for(int j=i+1;j<nums.length;j++){
            if(nums[j]>prev){
                em.add(nums[j]);
                prev=nums[j];
                rec(nums, j+1, em, cnt);
                em.remove(em.size()-1);
                rec(nums, j+1, em, cnt);
            }
        }
        em.remove(em.size()-1);
        rec(nums, i+1, em, cnt);
    }
}
