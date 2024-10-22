package _1_recursion.multipleCall;

import java.util.Arrays;

//the reason to add this problem is that the recursive solution would give TLE

/*
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
1 <= target <= 106
 */



public class _12_subSeq_2pointer {
    /*
Approach
Firstly we need to ensure that the array is sorted for this approach to work.
Now we create a new array of the same size as the input array, this array stores the power of 2 ,
eg- [2 
0
 , 2 
1
 , 2 
2
 , 2 
3
 , 2 
4
  and so on ]

powerValues = [1, 2, 4, 8, 16...]
(powerValues[i - 1] * 2) % mod is done to avoid integer overflow, the dry run for filling the powerValues array is

at i = 0
powerValues[0] = 1

at i = 1
powerValues[1] = (powerValues[0] * 2) % (10^9 + 7)
i.e. powerValues[1] = 2
now we just modify the two sum approach to solve this question.
lets consider -

arr - [3, 5, 6, 7]      target = 9
       |     |
     left   right
since nums[left] + nums[right] <= target then that means that we can find 2^(right- left)different number of Subsequences which satisfy the above condition.

we got 2^(right- left)different number of Subsequences because -
total number of ways to select n elements = 2 ^ n this also includes those cases where we don't select any element and we aren't allowed to pick non-empty subsequences.

so we fix the smallest element, then we only have the choice to select or not select those elements ranging from left + 1 to right

eg - 
    arr - [1, 2, 6, 7]
           |     |
         left   right
if1is fixed, then we only have option for2, and 6 either to select them or not select them. So,

total number of ways to select n elements = 2 ^ (right - (left + 1) + 1) (+ 1 at the end for 0 based indexing.)

so instead of calculating all the number of Subsequences, we use the powerValues array to find number, then we modulo it with 10^9 + 7 to avoid integer overflow

Complexity
Time complexity:
Sorting - O(n∗logn)
Saving the power of 2 - O(n)
Modified Two Sum approach - O(n)
Overall Time Complexity - O(n∗logn) + O(n) + O(n) = O(n∗logn)

Space complexity:
O(n)
     */
    public int numSubseq(int[] nums, int target) {
        // Sorting the array
        Arrays.sort(nums);
        int n = nums.length;
        
        int[] powerValues = new int[n];
        int mod = (int) 1e9 + 7;

        // Filling the array with power of 2's
        for(int i = 0; i < n; i++) {
            powerValues[i] = i == 0 ? 1 : (powerValues[i - 1] * 2) % mod;
        }

        // Modified Two sum approach
        int left = 0;
        int right = n - 1;
        int counter = 0;

        while(left <= right) {
            if(nums[left] + nums[right] > target) {
                right--;
            } else {
                // Using the stored value of power of 2
                // to find the number of Subsequences
                counter = (counter + powerValues[right - left]) % mod;//MISTAKE: counter+=(powerValues[right-left])%mod;-----include counter too
                left++;
            }
        }

        return counter;
    }
}
