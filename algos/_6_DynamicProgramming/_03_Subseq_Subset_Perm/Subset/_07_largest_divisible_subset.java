package _6_DynamicProgramming._03_Subseq_Subset_Perm.Subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

answer[i] % answer[j] == 0, or
answer[j] % answer[i] == 0
If there are multiple solutions, return any of them.

 

Example 1:

Input: nums = [1,2,3]
Output: [1,2]
Explanation: [1,3] is also accepted.
Example 2:

Input: nums = [1,2,4,8]
Output: [1,2,4,8]
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= 2 * 109
All the integers in nums are unique.
 */

//SUBSET: order of elements doesnt matter------------------Heart,so we can sort elemnts and apply same logic as that of Subseq/_01_longest_increasing_subsequence(print_LIS function), ie for every j sunch that (j>i), add to ans if (nums[j]%nums[i]==0)

//prereq: Subseq/_01_longest_increasing_subsequence, this problem is almost same as that of it

//https://www.youtube.com/watch?v=gDuZwBW9VvM&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=46&ab_channel=takeUforward

public class _07_largest_divisible_subset {

    public void rec(int nums[], int idx, int prev_idx, List<Integer> ans) {
        if (idx == nums.length) {
            System.out.println(ans);
            return;
        }
        // pick
        if (prev_idx == -1 || nums[idx] % nums[prev_idx] == 0 || nums[prev_idx] % nums[idx] == 0) {
            ans.add(nums[idx]);
            rec(nums, idx + 1, idx, ans);
            ans.remove(ans.size() - 1);
        }
        //dont pick
        rec(nums, idx + 1, prev_idx, ans);
    }


    //-----------------optimal suolution is almost same as Subseq/_01_longest_increasing_subsequence(print_LIS function)
    public List<Integer> optimal(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int len = nums.length;
        Arrays.sort(nums);
        int lengths[] = new int[len];
        int parent[] = new int[len];
        int max_idx = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            parent[i] = i;
            lengths[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {// since u have sorted the array u dont have to check nums[j]%nums[i]==0
                    if (1 + lengths[j] > lengths[i]) {
                        lengths[i] = Math.max(lengths[i], 1 + lengths[j]);
                        parent[i] = j;
                    }
                }
            }
            if (lengths[i] > max) {
                max = lengths[i];
                max_idx = i;
            }
        }

        ans.add(nums[max_idx]);
        while (max_idx != parent[max_idx]) {
            max_idx = parent[max_idx];
            ans.add(nums[max_idx]);
        }
        return ans;
    }

    //NOTE: if u want lexicographically largest among all subsets, sort the array in descending order

}
