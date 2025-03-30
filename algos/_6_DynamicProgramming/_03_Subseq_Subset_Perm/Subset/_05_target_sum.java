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

    /*
The recursive solution is very slow, because its runtime is exponential

The original problem statement is equivalent to:
Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target

Let P be the positive subset and N be the negative subset
For example:
Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

Then let's see how this can be converted to a subset sum problem:

                  sum(P) - sum(N) = target
sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)
So the original problem has been converted to a subset sum problem as follows:
Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2

Note that the above formula has proved that target + sum(nums) must be even
We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the suggestion)
For detailed explanation on how to solve subset sum problem, you may refer to Partition Equal Subset Sum

    Recurive:
    public int findTargetSumWays(int[] nums, int target) {
        int tot=0;
        for(int e:nums) tot+=e;
        return ((tot+target)%2==0)?rec(nums,(tot+target)>>1,nums.length-1):0;

        //sum+(-1*)(total-sum)==target=====>2*sum-total=target============>sum=(target+total)/2
    }

    public int rec(int nums[],int target,int i){
        if(i<0) return (target==0)?1:0;
        return rec(nums,target-nums[i],i-1)+rec(nums,target,i-1);
    }

     */
    

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

    //way1:
    public int findTargetSumWays(int[] nums, int target) {//idea://sum+(-1*)(total-sum)==target=====>2*sum-total=target, so if we just need to find subsets with sum = (target+total)/2 and check bfr if (target+total) is even and some ther edge
         // sum(pos) + -1*(remaining_shit) need to be eq to target
        // sum(pos) + -1*(tot-sum(pos)) = target
        // 2*sum(pos)-tot=target
        //if my sum(pos) is (target+tot)/2, then its a valid subset

        int tot=0;
        for(int e:nums) tot+=e;
        if((tot+target)%2!=0 || tot<target || -tot>target) return 0;//for last 2 conditions consider [100] and target=-200

        //now my new target is 
        target=(tot+target)/2;
        int dp[][]=new int[nums.length][target+1];

        dp[0][0]=1;
        if(nums[0]<=target) dp[0][nums[0]]+=1;//'+' imp: consider case [0] ans must be 2, without '+' it would give 1

        for(int i=1;i<nums.length;i++){
            for(int t=0;t<=target;t++){
                int x=(t>=nums[i])?dp[i-1][t-nums[i]]:0;
                int y=dp[i-1][t];
                dp[i][t]=x+y;
            }
        }
        return dp[nums.length-1][target];

        
    }

    //way2
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
