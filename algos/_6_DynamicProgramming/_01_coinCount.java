package _6_DynamicProgramming;

import java.util.List;
/*
Consider a money system consisting of n coins. Each coin has a positive integer value. Your task is to produce a sum of money x using the available coins in such a way that the number of coins is minimal.
For example, if the coins are \{1,5,7\} and the desired sum is 11, an optimal solution is 5+5+1 which requires 3 coins.
Input
The first input line has two integers n and x: the number of coins and the desired sum of money.
The second line has n distinct integers c_1,c_2,....,c_n: the value of each coin.
Output
Print one integer: the minimum number of coins. If it is not possible to produce the desired sum, print -1.
Constraints

1 <=n <= 100
1 <= x <= 10^6
1 <= c_i <= 10^6

Example
Input:
3 11
1 5 7

Output:
3
 */

public class _01_coinCount {
    
    public static void main(String[] args) {
        // int nOfCoins=3;
        int target=11;
        int arr[]={1,5,7};
        int ans=recursive(arr, target, new boolean[target+1], new int[target+1]);
        System.out.println((ans==Integer.MAX_VALUE)?-1:ans);

    }

   
   
   
   
    

    public static void brute(int nums[],int target,int start,List<Integer>empty,int ans[]){//but still it may not pass 1 or 2 TC
        if (start>=nums.length) {
            if (target==0) {
                ans[0]=Math.min(ans[0], empty.size());
            }
            return;
        }
        if (nums[start]<=target) {
            empty.add(nums[start]);
            brute(nums, target-nums[start], start, empty,ans);
            empty.remove(empty.size()-1);
        }
        brute(nums, target, start+1,  empty,ans);
    }


    public static int recursive(int arr[],int target,boolean ready[],int values[]){//just memoize the recursion O(nk),where n is the target sum and k is the number of coins.
        
        if (target<0) {
            return Integer.MAX_VALUE;
        }
        if (target==0) {
            return 0;
        }
        if (ready[target]) {
            return values[target];
        }
        int ans=Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int subResult = recursive(arr, target - arr[i], ready, values);
            if (subResult != Integer.MAX_VALUE) {
                ans = Math.min(ans, subResult + 1); // Take one coin and add the result for the remaining target
            }
        }
        ready[target]=true;
        values[target]=ans;
        return ans;
    }
}
