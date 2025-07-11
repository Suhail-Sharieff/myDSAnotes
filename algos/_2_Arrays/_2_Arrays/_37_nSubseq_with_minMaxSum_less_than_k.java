/*1498. Number of Subsequences That Satisfy the Given Sum Condition
Solved
Medium
Topics
premium lock icon
Companies
Hint
You are given an array of integers nums and an integer target.

Return the number of non-empty subsequences of nums such that the sum of the minimum and maximum element on it is less or equal to target. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: nums = [3,5,6,7], target = 9
Output: 4
Explanation: There are 4 subsequences that satisfy the condition.
[3] -> Min value + max value <= target (3 + 3 <= 9)
[3,5] -> (3 + 5 <= 9)
[3,5,6] -> (3 + 6 <= 9)
[3,6] -> (3 + 6 <= 9)
Example 2:

Input: nums = [3,3,6,8], target = 10
Output: 6
Explanation: There are 6 subsequences that satisfy the condition. (nums can have repeated numbers).
[3] , [3] , [3,3], [3,6] , [3,6] , [3,3,6]
Example 3:

Input: nums = [2,3,3,4,6,7], target = 12
Output: 61
Explanation: There are 63 non-empty subsequences, two of them do not satisfy the condition ([6,7], [7]).
Number of valid subsequences (63 - 2 = 61).
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 106
1 <= target <= 106 */


//prereq: the number of subsequneces we get in a sequnce of length n = 2^n-1 (-1 for an empty subseq)

//one more preewq: suppose we have 2 subseq of len x(from idx 0 to x) and y(0 idx  to idx y), suchht some part is overllaping, then nSubseq only from idx x to idx y =(2^x - 2^y) dont worry abt empty subseq, coz since its common in both of them it gets canceeled out


//https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solutions/6896464/beginner-freindly-java-c-python-js


package _2_Arrays;

import java.util.Arrays;

public class _37_nSubseq_with_minMaxSum_less_than_k {

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int mod = (int) (1e9 + 7);
        int nSub[] = new int[nums.length];
        nSub[0] = 1;
        for (int i = 1; i < nums.length; i++)
            nSub[i] = (nSub[i - 1] << 1) % mod;
        int i = 0, j = nums.length - 1;
        int ans = 0;
        while (i <= j) {
            if (nums[i] + nums[j] <= target) {
                ans = (ans + nSub[j - i]) % mod;//MAIN thing, why not (j-i+1), coz we r fixing left elemnt and finding all poss subseq on right o f left(ie min), so (j-i+1)-1 ie j-i
                i++;
            } else {
                j--;
            }
        }
        return ans % mod;
    }
}