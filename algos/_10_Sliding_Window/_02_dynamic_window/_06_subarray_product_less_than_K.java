package _10_Sliding_Window._02_dynamic_window;
/*
Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

 

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0
 

Constraints:

1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
 */

public class _06_subarray_product_less_than_K {
     public int brute_force(int[] nums, int k) {
        int cnt=0;
        for(int i=0;i<nums.length;i++){
            long prod=1l;
            for(int j=i;j<nums.length;j++){
                prod=prod*1l*nums[j];
                if(prod<k){
                     cnt++;
                }else{//IMP, why do u check if exceeded already and nums[i]>0
                    break;
                }
            }
        }
        return cnt;
    }    

    public int optimal(int[] nums, int k) {
        int left_ptr = 0, right_ptr = 0, prod = 1, ans = 0;
        while (right_ptr < nums.length) {
            prod *= nums[right_ptr];
            while (left_ptr <= right_ptr && prod >= k) {
                prod /= nums[left_ptr];
                left_ptr++;
            }
            ans += right_ptr - left_ptr + 1;
            right_ptr++;
        }
        return ans;
    }


    /*
2302. Count Subarrays With Score Less Than K
Attempted
Hard
Topics
Companies
Hint
The score of an array is defined as the product of its sum and its length.

For example, the score of [1, 2, 3, 4, 5] is (1 + 2 + 3 + 4 + 5) * 5 = 75.
Given a positive integer array nums and an integer k, return the number of non-empty subarrays of nums whose score is strictly less than k.

A subarray is a contiguous sequence of elements within an array.

 

Example 1:

Input: nums = [2,1,4,3,5], k = 10
Output: 6
Explanation:
The 6 subarrays having scores less than 10 are:
- [2] with score 2 * 1 = 2.
- [1] with score 1 * 1 = 1.
- [4] with score 4 * 1 = 4.
- [3] with score 3 * 1 = 3. 
- [5] with score 5 * 1 = 5.
- [2,1] with score (2 + 1) * 2 = 6.
Note that subarrays such as [1,4] and [4,3,5] are not considered because their scores are 10 and 36 respectively, while we need scores strictly less than 10.
Example 2:

Input: nums = [1,1,1], k = 5
Output: 5
Explanation:
Every subarray except [1,1,1] has a score less than 5.
[1,1,1] has a score (1 + 1 + 1) * 3 = 9, which is greater than 5.
Thus, there are 5 subarrays having scores less than 5.
 

Constraints:

1 <= nums.length <= 105
1 <= nums[i] <= 105
1 <= k <= 1015
     */

     public long brute(int[] nums, long k) {//O(N2)
        long ans=0;
        for(int i=0;i<nums.length;i++){
            long sum=0;
            for(int j=i;j<nums.length;j++){
                sum+=nums[j];
                long prod=(sum*1l*(j-i+1));
                if(prod>=k) break;
                ans++;
            }
        }
        return ans;
    }

    public long optimal_(int nums[],long k){
            int left_ptr=0,right_ptr=0;
            long cnt=0l;
            long window_sum=0l;
            while(right_ptr<nums.length){
                window_sum+=nums[right_ptr];
                int window_len=(right_ptr-left_ptr+1);
                long curr_prod=window_sum*1l*window_len ;
                while(left_ptr<=right_ptr && curr_prod >= k){
                    window_sum-=nums[left_ptr];
                    window_len--;
                    curr_prod=window_sum*1l*window_len;
                    left_ptr++;
                }
                cnt+=right_ptr-left_ptr+1;
                right_ptr++;
            }
            return cnt;
    }
}
