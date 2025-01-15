package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;

import java.util.HashMap;

/*
You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000
 */
public class _05_target_sum {
    public static void main(String[] args) {
        int nums[]={1,2,3,4,5};
        int target=3;

        int totalSum=0;
        for(int e:nums) totalSum+=e;

        System.out.println(recursion(nums, target, 0, totalSum, nums.length-1, new StringBuilder()));
    }

    //solution: say we have some combination like say +1+1-1+1-1+1 , can we write this as by separating negatives one side and positives one side ie (1+1+1+1)+(-1-1) , ie we have parted this into 2 subsets, say first part we call currSum, the remainign will be rem=totalSum-currSum, then we need to count number of subsets such that  {currSum-((-1)*rem)==target}----IMP observation


    //----------

    static int recursion(int nums[],int target,int currSum,int totalSum,int idx,StringBuilder sb){
        //not written if(target<0) coz given that target can be negative
        if(idx<0){
            int remSum=totalSum-currSum;

            int postivePart=currSum;
            int negativePart=(-1)*remSum;

            if(postivePart+negativePart==target){
                System.out.println(sb +" currSum: "+(currSum)+" rem: "+remSum+" => sum : "+(currSum+"-"+remSum)+" = "+(postivePart+negativePart));
                return 1;
            }
            return 0;
        }
        int pick=recursion(nums,target,currSum+nums[idx],totalSum,idx-1,new StringBuilder(sb).append(nums[idx]+"->"));
        int dontPick=recursion(nums,target,currSum,totalSum,idx-1,new StringBuilder(sb));
        
        return pick+dontPick;
    }

    //------------------------memoization:use hashmap inplace of dp since target canbe negative and dp[idx][target] can give errors
    public int memoize(int nums[],int target,int currSum,int totalSum,int idx,HashMap<String,Integer>dp){
        if(idx<0){
            int remSum=totalSum-currSum;

            int positivePart=currSum;
            int negativePart=(-1)*remSum;

            if(positivePart+negativePart==target){
                return 1;
            }
            return 0;
        }
        if(dp.containsKey(idx+"|"+currSum)) return dp.get(idx+"|"+currSum);

        int pick=memoize(nums,target,currSum+nums[idx],totalSum,idx-1,dp);
        int dontPick=memoize(nums,target,currSum,totalSum,idx-1,dp);

        dp.put(idx+"|"+currSum,pick+dontPick);

        return pick+dontPick;
    }


    //----------------tabulation:

    //we will derive tabulation using the following recursive code that also works:
    public int recursion2(int nums[],int target,int currSum,int idx){
        if(idx<0) return (currSum==target)?1:0;
        int add=recursion2(nums,target,currSum+nums[idx],idx-1);
        int sub=recursion2(nums,target,currSum-nums[idx],idx-1);
        return add+sub;
    }
    public static int tabulation(int nums[], int target) {
        int totalSum = 0;
        for (int e : nums) totalSum += e;
    
        // Offset to handle negative sums
        int offset = totalSum;
        int range = 2 * totalSum + 1;
    
        // DP table: dp[idx][currSum + offset]
        int[][] dp = new int[nums.length + 1][range];
    
        // Base case: At idx = -1, calculate possible ways to achieve target
        dp[0][offset] = 1;  // currSum == 0 is the initial state
    
        // Fill the DP table
        for (int idx = 1; idx <= nums.length; idx++) {
            for (int currSum = -totalSum; currSum <= totalSum; currSum++) {
                int adjustedSum = currSum + offset;
    
                // If out of range, skip
                if (adjustedSum < 0 || adjustedSum >= range) continue;
    
                // Pick the current number
                int pick = (currSum - nums[idx - 1] + offset >= 0 &&
                            currSum - nums[idx - 1] + offset < range) 
                           ? dp[idx - 1][currSum - nums[idx - 1] + offset] 
                           : 0;
    
                // Don't pick the current number
                int dontPick = (currSum + offset >= 0 && currSum + offset < range)
                               ? dp[idx - 1][currSum + offset] 
                               : 0;
    
                dp[idx][adjustedSum] = pick + dontPick;
            }
        }
    
        // Return the result for target
        return (target + offset >= 0 && target + offset < range) 
               ? dp[nums.length][target + offset] 
               : 0;
    }
    
}
