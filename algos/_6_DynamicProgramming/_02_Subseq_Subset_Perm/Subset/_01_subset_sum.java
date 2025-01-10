package _6_DynamicProgramming._02_Subseq_Subset_Perm.Subset;

//pre req: https://www.youtube.com/watch?v=_gPcYovP7wc&ab_channel=AdityaVerma
/*
Given an array of positive integers, arr[] and a value, target, determine if there is a subset of the given set with sum equal to given target. 

Examples:

Input: arr[] = [3, 34, 4, 12, 5, 2], target = 9
Output: true 
Explanation: Here there exists a subset with target sum = 9, 4+3+2 = 9.
Input: arr[] = [3, 34, 4, 12, 5, 2], target = 30
Output: false
Explanation: There is no subset with target sum 30.
Input: arr[] = [1, 2, 3], sum = 6
Output: true
Explanation: The entire array can be taken as a subset, giving 1 + 2 + 3 = 6.
Constraints:
1 <= arr.size() <= 200
1<= arr[i] <= 200
1<= sum <= 4*104
 */
public class _01_subset_sum {
    public static void main(String[] args) {
        int arr[] = { 3, 34, 4, 12, 5, 2 };
        int target = 9;
        System.out.println(tabulate(arr, target));

    }

    // ------recursion:

    public static boolean recursion(int arr[], int target, int idx) {
        if (target == 0)
            return true;
        if (idx == arr.length - 1) {
            // we have reached last while seaching for target, return true if this last
            // elemnt is target, else false
            return arr[idx] == target;
        }

        boolean chooseCurr = (arr[idx] <= target) ? recursion(arr, target - arr[idx], idx + 1) : false;
        boolean dontChooseCurr = recursion(arr, target, idx + 1);

        return chooseCurr || dontChooseCurr;
    }

    // -----------memoize
    public static boolean memoize1(int arr[], int target, int idx, boolean dp[]) {
        if (target == 0)
            return true;
        if (idx == arr.length - 1) {
            return arr[idx] == target;
        }
        if (dp[target] != false)
            return true;
        boolean chooseCurr = (arr[idx] <= target) ? memoize1(arr, target - arr[idx], idx + 1, dp) : false;
        boolean dontChooseCurr = memoize1(arr, target, idx + 1, dp);

        dp[target] = chooseCurr || dontChooseCurr;

        return dp[target];
    }

    // --------using 2 states coz both idx and target are changig
    // call like:
    /*
     * Boolean[][] dp = new Boolean[n][k + 1];
     * return memoize(arr, 0, k, dp);
     */
    public static boolean memoize2(int arr[], int idx, int target, Boolean dp[][]) {
        if (target == 0)
            return true;
        if (idx >= arr.length)
            return false;// --------------VVIMP

        // IMP:dp[i][j] represents Whether it is possible to form a subset(can be empty)
        // of the first i+1 elements of the array that sums up to j
        if (dp[idx][target] != null)
            return dp[idx][target];
        boolean chooseCurr = (arr[idx] <= target) ? memoize2(arr, idx + 1, target - arr[idx], dp) : false;
        boolean dontChooseCurr = memoize2(arr, idx + 1, target, dp);

        dp[idx][target] = chooseCurr || dontChooseCurr;

        return dp[idx][target];
    }

    // _---------------------tab
    // IMP:dp[i][j] represents Whether it is possible to form a subset(can be empty)
    // of the first i+1 elements of the array that sums up to j
    public static boolean tabulate(int arr[], int target) {
        boolean dp[][] = new boolean[arr.length][target + 1];
        for (boolean row[] : dp)
            row[0] = true;
        if (arr[0] <= target) {
            dp[0][arr[0]] = true;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int currTar = 1; currTar <= target; currTar++) {
                boolean chooseCurr = (arr[i] <= currTar) ? dp[i - 1][currTar - arr[i]] : false;
                boolean dontChooseCurr = dp[i - 1][currTar];
                dp[i][currTar] = dontChooseCurr || chooseCurr;
            }
        }

        // The answer is whether we can achieve the target using all elements
        return dp[arr.length - 1][target];
    }


    //------------------------follow up question:
    //Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
    public boolean canPartition(int[] nums) {
        int totalSum=0;
        for(int e:nums) totalSum+=e;
        if(totalSum%2!=0) return false;//its not possible to divide an odd number into two equal halves
        return tabulate(nums,totalSum/2);
    }

}
