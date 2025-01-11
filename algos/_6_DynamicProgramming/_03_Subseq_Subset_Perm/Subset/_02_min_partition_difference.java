package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.

Return the minimum possible absolute difference.

 

Example 1:

example-1
Input: nums = [3,9,7,3]
Output: 2
Explanation: One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.
Example 2:

Input: nums = [-36,36]
Output: 72
Explanation: One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.
Example 3:

example-3
Input: nums = [2,-1,0,4,-2,-9]
Output: 0
Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
 

Constraints:

1 <= n <= 15
nums.length == 2 * n
-107 <= nums[i] <= 107
 */
public class _02_min_partition_difference {

    public static void main(String[] args) {
        int nums[]={3,9,7,3};
        int totSum=0;
        for(int e:nums) totSum+=e;
       System.out.println(recursion(nums, 0, 0, totSum, 0));
    }
    

    //-----------------raw brute force, o through all subsets and check 
    public static void brute(int nums[],int idx,int currSum,int sumOfAllElements,List<Integer>empty,int ans[]){
        if(idx==nums.length){
            if (empty.size()==nums.length/2) {

                int rem=sumOfAllElements-currSum;

                ans[0]=Math.min(ans[0], Math.abs(rem-currSum));

                System.out.println(empty+" currsum: "+currSum+", remaining: "+rem);
            }
            return;
        }
        empty.add(nums[idx]);
        currSum+=nums[idx];
        brute(nums, idx+1, currSum, sumOfAllElements, empty,ans);

        empty.remove(empty.size()-1);
        currSum-=nums[idx];
        brute(nums, idx+1, currSum, sumOfAllElements, empty,ans);
        
    }

    //--------------------recursive, instaed of using Empty lst and checking if empty.size()==nums.length/2 which is very expensive, i used a variable called nNumbersChosen thats all---TLE
    public static int recursion(int nums[],int idx,int currSum,int sumOfAllElements, int nNumbsChosen){
        if (idx==nums.length) {
            if (nNumbsChosen==nums.length/2) {
                int rem=sumOfAllElements-currSum;
                return Math.abs(rem-currSum);
            }
            return Integer.MAX_VALUE;
        }
        currSum+=nums[idx];
        int chooseCurr=recursion(nums, idx+1, currSum, sumOfAllElements, nNumbsChosen+1);

        currSum-=nums[idx];
        int dontChooseCurr=recursion(nums, idx+1, currSum, sumOfAllElements, nNumbsChosen);

        return Math.min(chooseCurr, dontChooseCurr);
    }



    //----------memoization: observe that we have 3 states changing, ie idx,currSum and nNumbersChosen, BUT BUT BUT, if  u use 3D dp ie dp[idx][currrSum][nNumbersChosen], u may encounter error COZ currSum CAN BE NEGATIVE, so instaed of that we will use HashMap<String,Integer> where key will have format "idx|currSum|nNumbersChosen"---TLE
    public static int memoize(int nums[],int idx,int currSum,int sumOfAllElements, int nNumbsChosen,HashMap<String,Integer>hp){
        if (idx==nums.length) {
            if (nNumbsChosen==nums.length/2) {
                int rem=sumOfAllElements-currSum;
                String key=idx + "|" + currSum + "|" + nNumbsChosen;
                hp.put(key,Math.abs(rem-currSum));
                return hp.get(key);
            }
            return Integer.MAX_VALUE;
        }

        String key=idx + "|" + currSum + "|" + nNumbsChosen;
        if (hp.containsKey(key)) {
            return hp.get(key);
        }

        currSum+=nums[idx];
        int chooseCurr=memoize(nums, idx+1, currSum, sumOfAllElements, nNumbsChosen+1,hp);

        currSum-=nums[idx];
        int dontChooseCurr=memoize(nums, idx+1, currSum, sumOfAllElements, nNumbsChosen,hp);


        hp.put(key, Math.min(chooseCurr, dontChooseCurr));
        return hp.get(key);
    }


    //-----------tabulaton:(case 1: target may be given as negative,, so we cannot use dp[idx][target])
    /*
     call like:
     int totSum=0;
        for(int e:nums) totSum+=e;
       return(func(nums, totSum/2,totSum));
     */
    public static int tabulationWithoutNegNums(int nums[],int target,int totalSum){
       
        boolean dp[][]=new boolean[nums.length][target+1];
        for(boolean row[]:dp) row[0]=true;
        if(nums[0]<=target) dp[0][nums[0]]=true;
        for(int i=1;i<nums.length;i++){
            for(int currTar=1;currTar<=target;currTar++){
                boolean chooseCurr=(currTar-nums[i]>=0)?dp[i-1][currTar-nums[i]]:false;
                boolean dontChooseCurr=dp[i-1][currTar];
                dp[i][currTar]=chooseCurr||dontChooseCurr;
            }
        }
        int ans=Integer.MAX_VALUE;
        for(int currTar=0;currTar<=target;currTar++){
            boolean hasSubsetWithCurrTarget=dp[nums.length-1][currTar];
            if (hasSubsetWithCurrTarget) {
                int weHave=currTar;
                int needed=totalSum-weHave;

                ans=Math.min(ans, Math.abs(weHave-needed));
            }
        }
        return ans;
    }


    //-------------tabulation(case 2: handles negatives as well)
    public static int tabulationWithNegNums(int nums[],int target,int totalSum){
        HashMap<String,Boolean>dp=new HashMap<>();
        for(int i=0;i<nums.length;i++) dp.put(i+"|"+0, true);
        if(nums[0]<=target) dp.put(0+"|"+nums[0], true);
        for(int i=1;i<nums.length;i++){
            for(int currTar=1;currTar<=target;currTar++){
                String key=i-1+"";

                if(dp.get(key+"|"+(currTar-nums[i]))==null) dp.put(key+"|"+(currTar-nums[i]), false);
                if(dp.get(key+"|"+currTar)==null) dp.put(key+"|"+currTar, false);

                boolean chooseCurr=dp.get(key+"|"+(currTar-nums[i]));
                boolean dontChooseCurr=dp.get(key+"|"+currTar);
                dp.put(i+"|"+currTar, chooseCurr||dontChooseCurr);
            }
        }
        int ans=Integer.MAX_VALUE;
        for(int currTar=0;currTar<=target;currTar++){
            String key=(nums.length-1)+"";
            boolean hasSubsetWithCurrTarget=dp.get(key+"|"+currTar);
            if (hasSubsetWithCurrTarget) {
                int weHave=currTar;
                int needed=totalSum-weHave;

                ans=Math.min(ans, Math.abs(weHave-needed));
            }
        }

        return ans;
    }


}
