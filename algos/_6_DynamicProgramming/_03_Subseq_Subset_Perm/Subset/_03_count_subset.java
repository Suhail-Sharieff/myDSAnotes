package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;
/*
You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.



Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.



Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.



Example:
Input: 'arr' = [1, 1, 4, 5]

Output: 3

Explanation: The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4 5
1 4 4 5


Sample Output 1 :
 3


Explanation For Sample Output 1:
The possible ways are:
[1, 4]
[1, 4]
[5]
Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.


Sample Input 2 :
3 2
1 1 1


Sample Output 2 :
3


Explanation For Sample Output 1:
There are three 1 present in the array. Answer is the number of ways to choose any two of them.


Sample Input 3 :
3 40
2 34 5


Sample Output 3 :
0


Expected time complexity :
The expected time complexity is O('n' * 'k').


Constraints:
1 <= 'n' <= 100
0 <= 'arr[i]' <= 1000
1 <= 'k' <= 1000

Time limit: 1 sec
 */
public class _03_count_subset {
    //-------------------memoize
    public static int memoize(int nums[],int target,int idx,int dp[][]){
        int mod=1_000_000_007;
        if(target<0) return 0;
        if(idx<0){
            if(target==0) return 1;
            return 0;
        }
        if(dp[idx][target]!=-1) return dp[idx][target]%mod;
        int x=memoize(nums,target-nums[idx],idx-1,dp)%mod;
        int y=memoize(nums,target,idx-1,dp)%mod;
        dp[idx][target]=(x+y)%mod;
        return dp[idx][target];
    }
    //------------------tabulate
    public static int tabulation(int nums[],int target){
        int dp[][]=new int[nums.length][target+1];
        int mod=1_000_000_007;
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1; // There's always one way to make target 0: by selecting no elements.
        }
        if (nums[0] <= target) {
            dp[0][nums[0]] = 1; // If the first number is within the target, there's one way to achieve it.
        }
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<=target;j++){
                int x=(j-nums[i]>=0)?dp[i-1][j-nums[i]]%mod:0;
                int y=dp[i-1][j]%mod;
                dp[i][j]=(x+y)%mod;
            }
        }
        
        return dp[nums.length-1][target];
    }
}
