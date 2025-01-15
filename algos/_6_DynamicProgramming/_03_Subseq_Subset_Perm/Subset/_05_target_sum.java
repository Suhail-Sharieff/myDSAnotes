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
        int x=recursion(nums,target,currSum+nums[idx],totalSum,idx-1,new StringBuilder(sb).append(nums[idx]+"->"));
        int y=recursion(nums,target,currSum,totalSum,idx-1,new StringBuilder(sb));
        
        return x+y;
    }

    //------------------------memoization:use hashmap inplace of dp since target canbe negative and dp[idx][target] can give errors
    public int memoize(int nums[],int target,int currSum,int totalSum,int idx,HashMap<String,Integer>hp){
        if(idx<0){
            int remSum=totalSum-currSum;

            int positivePart=currSum;
            int negativePart=(-1)*remSum;

            if(positivePart+negativePart==target){
                return 1;
            }
            return 0;
        }
        if(hp.containsKey(idx+"|"+currSum)) return hp.get(idx+"|"+currSum);

        int x=memoize(nums,target,currSum+nums[idx],totalSum,idx-1,hp);
        int y=memoize(nums,target,currSum,totalSum,idx-1,hp);

        hp.put(idx+"|"+currSum,x+y);

        return x+y;
    }
}
