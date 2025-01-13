import java.util.Arrays;

public class test {



    public static void main(String[] args) {
        int nums[]={4, 5, 5, 5, 2, 10, 7, 4, 10, 7, 9, 5, 1, 1, 8, 3, 10, 8, 2, 7, 9, 8, 5, 5, 10, 6, 4, 9, 3, 9, 4, 10, 7, 4, 7, 8, 3, 4, 4, 4, 10, 1, 1, 5, 6, 10, 2, 8, 6, 4, 1, 8, 4, 3, 4, 1, 1, 10, 7, 4, 8, 5, 8, 8, 1, 10, 5, 3, 10, 10, 6, 6, 8, 8, 3, 7};
        int target=50;

        System.out.println(findWays(nums, target));
    }

    public static int findWays(int nums[], int target) {
        // Write your code here.
        int dp[][]=new int[nums.length][target+1];
        for(int e[]:dp) Arrays.fill(e,-1);
        return cnt(nums, target, 0,dp);
    }

    public static int cnt(int nums[],int target,int idx,int dp[][]){
        int mod=1_000_000_007;
        if(target<0) return 0;
        if(idx==nums.length){
            if(target==0) return 1;
            return 0;
        }
        if(dp[idx][target]!=-1) return dp[idx][target]%mod;
        int x=cnt(nums,target-nums[idx],idx+1,dp)%mod;
        int y=cnt(nums,target,idx+1,dp)%mod;
        dp[idx][target]=(x+y)%mod;
        return dp[idx][target];
    }
}