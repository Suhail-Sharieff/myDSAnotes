package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;

import java.util.ArrayList;
import java.util.List;

/*
Given an array arr[], partition it into two subsets(possibly empty) such that each element must belong to only one subset. Let the sum of the elements of these two subsets be sum1 and sum2. Given a difference d, count the number of partitions in which sum1 is greater than or equal to sum2 and the difference between sum1 and sum2 is equal to d. 

Examples :

Input: arr[] =  [5, 2, 6, 4], d = 3
Output: 1
Explanation: There is only one possible partition of this array. Partition : {6, 4}, {5, 2}. The subset difference between subset sum is: (6 + 4) - (5 + 2) = 3.
Input: arr[] = [1, 1, 1, 1], d = 0 
Output: 6 
Explanation: We can choose two 1's from indices {0,1}, {0,2}, {0,3}, {1,2}, {1,3}, {2,3} and put them in sum1 and remaning two 1's in sum2.
Thus there are total 6 ways for partition the array arr. 
Input: arr[] = [1, 2, 1, 0, 1, 3, 3], d = 11
Output: 2
Constraint:
1 <= arr.size() <= 50
0 <= d  <= 50
0 <= arr[i] <= 6
 */
public class _04_partition_with_given_difference {
    public static void main(String[] args) {
        int nums[]={5,2,6,4};
        int reqDiff=3;
        int totalSum=0;
        for(int e:nums)totalSum+=e;
        System.out.println(recursion(nums, reqDiff, totalSum, 0, 0, new ArrayList<>()));;
    }

    //------------------recursion
    public static int recursion(int nums[],int reqDiff,int totalSum,int currSum,int idx,List<Integer>emptyList){
        if (idx==nums.length) {
            if(totalSum-2*currSum==reqDiff){
                return 1;
            }
            return 0;
        }
        emptyList.add(nums[idx]);
        int pickCurr=recursion(nums, reqDiff, totalSum, currSum+nums[idx], idx+1, emptyList);

        emptyList.remove(emptyList.size()-1);
        int dontPickCur=recursion(nums, reqDiff, totalSum, currSum, idx+1, emptyList);

        return pickCurr+dontPickCur;
    }

    //------------memoization
    public static int memoization(int nums[],int reqDiff,int totSum,int currSum,int idx,int dp[][]){
        if (idx==nums.length) {
            if(totSum-2*currSum==reqDiff){
                return 1;
            }
            return 0;
        }
		int mod=1_000_000_007;
            
        if(dp[idx][currSum]!=-1) return dp[idx][currSum]%mod;
        
        int pickCurr=memoization(nums, reqDiff, totSum, currSum+nums[idx], idx+1,dp)%mod;

        int dontPickCurr=memoization(nums, reqDiff, totSum, currSum, idx+1,dp)%mod;
        
        dp[idx][currSum]=(pickCurr+dontPickCurr)%mod;

        return (pickCurr+dontPickCurr)%mod;
    }

    ///----------tabulation----IMP:
    /*
     * observe this
     if(totSum-2*currSum==reqDiff){
                return 1;
            }
        it can be rewritten as totSum-reqDiff=2*currSum ie currSum=(totSum-reqSum)/2 ie we need to find the number of subsets with target value as (totSum-reqDiff)/2
     */
    //https://www.youtube.com/watch?v=zoilQD1kYSg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=19&ab_channel=takeUforward
    public static int tabulate(int nums[],int reqDiff){// now this has been boliled down to problm where we need to find number of ssubsets with target (totSum-reqDiff)/2

        int totalSum=0;
        for(int e:nums)totalSum+=e;

        ///vvvvvimp
        
        if((totalSum-reqDiff)%2!=0 ||(totalSum-reqDiff<0)) return 0;
        
        int target=(totalSum-reqDiff)/2;

        int dp[][]=new int[nums.length][target+1];
        for(int row[]:dp) row[0]=1;
        if(nums[0]==0) dp[0][0]=2;

        if(nums[0]!=0&&nums[0]<=target) dp[0][nums[0]]=1;


        for(int i=1;i<nums.length;i++){
            for(int j=0;j<=target;j++){
                int pickCurr=(j-nums[i]>=0)?dp[i-1][j-nums[i]]:0;
                int dontPickCurr=dp[i-1][j];
                dp[i][j]=pickCurr+dontPickCurr;
            }
        }
        
        return dp[nums.length-1][target];
    }
}
