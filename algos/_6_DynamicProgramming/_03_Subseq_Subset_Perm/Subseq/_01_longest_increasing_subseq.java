package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subseq;

import java.util.ArrayList;
import java.util.HashMap;
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
        int nums[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
        test(nums, 0, new ArrayList<>(), Integer.MIN_VALUE);
    }

    static void test(int nums[], int i, List<Integer> empty,  int prev) {
        if (i >= nums.length) {
            System.out.println(empty);
            return;
        }
        //pick logic
        if(prev==Integer.MIN_VALUE || nums[i]>prev){
            empty.add(nums[i]);
            test(nums, i+1, empty, nums[i]);
            empty.remove(empty.size()-1);
        }
        test(nums, i+1, empty, prev);
    }


    //----------------recurive:2^n
    public int rec(int nums[],int i,int prev_idx){
        if(i>=nums.length-1) return 0;
        int pick_curr=(prev_idx==-1 || nums[i]>nums[prev_idx])?1+rec(nums, i+1, i):0;
        int dont_pick_curr=rec(nums, i+1, prev_idx);
        return Math.max(pick_curr, dont_pick_curr);
    }


    //----------------brute memoizaton(coz see that prev_idx we r passing as negative)---TLE 
    public int brute_mem(int nums[],int i,int prev_idx,HashMap<String,Integer>dp){
        if(i>=nums.length) return 0;
        if(dp.containsKey(i+"|"+prev_idx)) return dp.get(i+"|"+prev_idx);
        int pick_curr=(prev_idx==-1 || nums[i]>nums[prev_idx])?1+brute_mem(nums, i+1, i,dp):0;
        int dont_pick_curr=brute_mem(nums, i+1, prev_idx,dp);
        dp.put(i+"|"+prev_idx,Math.max(pick_curr, dont_pick_curr));
        return dp.get(i+"|"+prev_idx);
    }


    //------------------smart mem,teaches how u can hadle -ve idx as well, since u kow that prev_idx can vary from -1 to n ie only 1 negative value,instaed of storing precomputed value at dp[idx][prev_idx], store at dp[idx][prev_idx+1], solves preoblem of prev_idx being -ve
    public int mem(int nums[],int i,int prev_idx,int dp[][]){//)(n^2)
        if(i>=nums.length) return 0;
        if(dp[i][prev_idx+1]!=-1) return dp[i][prev_idx+1];
        int pick_curr=(prev_idx==-1 || nums[i]>nums[prev_idx])?1+mem(nums, i+1, i,dp):0;
        int dont_pick_curr=mem(nums, i+1, prev_idx,dp);
        dp[i][prev_idx+1]=Math.max(pick_curr, dont_pick_curr);
        return dp[i][prev_idx+1];
    }
}
