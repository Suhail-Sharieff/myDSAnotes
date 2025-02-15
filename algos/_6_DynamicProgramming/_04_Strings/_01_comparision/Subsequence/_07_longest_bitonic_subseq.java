package _6_DynamicProgramming._04_Strings._01_comparision.Subsequence;


//first we will solve easy version of this question: longest bitonic SUBARRAY(longest mountain array), then we will move to longest bitonic SUBSEQUENCE.
/*
You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.

 

Example 1:

Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: arr = [2,2,2]
Output: 0
Explanation: There is no mountain.
 

Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 104
 

Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?
 */
class longest_mountain_array{
    public int longestMountain(int[] arr) {
        if(arr.length<3) return 0;
        int ans=0;
        for(int i=0;i<arr.length;i++){
           int l=left_idx(i,arr);
           int r=right_idx(i,arr);
           if(l!=i && r!=i) ans=Math.max(ans,r-l+1);
        }
        return ans;
    }

    
    public int left_idx(int idx,int arr[]){
        while(idx>0 && arr[idx-1]<arr[idx]) idx--;
        return idx;
    }
    public int right_idx(int idx,int arr[]){
        while(idx<arr.length-1 && arr[idx+1]<arr[idx]) idx++;
        return idx;
    }
}


//------------------------------------------------------FOLLOW UP, previosus q was longest bitonic SUBARRAY, now find logest bitonic SUBSEQUENCE


/*
Problem statement
A Bitonic Sequence is a sequence of numbers that is first strictly increasing and then strictly decreasing.



A strictly ascending order sequence is also considered bitonic, with the decreasing part as empty, and same for a strictly descending order sequence.



For example, the sequences [1, 3, 5, 3, 2], [1, 2, 3, 4] are bitonic, whereas the sequences [5, 4, 1, 4, 5] and [1, 2, 2, 3] are not.



You are given an array 'arr' consisting of 'n' positive integers.



Find the length of the longest bitonic subsequence of 'arr'.



Example :
Input: 'arr' = [1, 2, 1, 2, 1]

Output: 3

Explanation: The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1] is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
5 
1 2 1 2 1


Sample Output 1:
3


Explanation For Sample Input 1:
The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1] is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.


Sample Input 2 :
5
1 2 1 3 4


Sample Output 2 :
4


Explanation For Sample Input 2:
The longest bitonic sequence for this array will be [1, 2, 3, 4].


Expected time complexity :
The expected time complexity is O(n ^ 2).


Constraints:
1 <= 'n' <= 10^3
1 <= 'arr[i]' <= 10^5

Time Limit: 1sec
 */
public class _07_longest_bitonic_subseq {
    //https://www.youtube.com/watch?v=y4vN0WNdrlg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=49&ab_channel=takeUforward



    //Heart: if u observe for each index i, from [0...i](ie from left till i) and also from [n-1...i](ie from right till i), both subsequence must be increasing, so if i calculate LIS , one from extreme left and other from extreme right, ie get 2 dp arrays, in the irst dp array u will have LIS from [0..i] and in second u will have LIS from [n-1...i], so the answer will be max(dp1[i]+dp2[i]-1), -1 coz we r taking same elemnt twice in both dps, do skip 1 to avoid repetition
    public static int longestBitonicSequence(int[] nums, int n) {
        int dp1[] = new int[nums.length];
        int dp2[] = new int[nums.length];
        int ans = 0;
        
        // Compute dp1: longest increasing subsequence ending at each index.
        for (int i = 0; i < nums.length; i++) {
            dp1[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp1[i] = Math.max(dp1[i], 1 + dp1[j]);
                }
            }
        }
        
        // Compute dp2: longest decreasing subsequence starting at each index.
        for (int i = nums.length - 1; i >= 0; i--) {
            dp2[i] = 1;
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    dp2[i] = Math.max(dp2[i], 1 + dp2[j]);
                }
            }
        }
        
        // Combine dp1 and dp2 to get the longest bitonic subsequence.
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, dp1[i] + dp2[i] - 1);
        }
        return ans;
    }
    
}
