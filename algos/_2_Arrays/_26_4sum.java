import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class _26_4sum {
    /*
     * Given an array nums of n integers, return an array of all the unique
     * quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
     * 
     * 0 <= a, b, c, d < n
     * a, b, c, and d are distinct.
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * You may return the answer in any order.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,0,-1,0,-2,2], target = 0
     * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     * Example 2:
     * 
     * Input: nums = [2,2,2,2,2], target = 8
     * Output: [[2,2,2,2]]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 200
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     */



     //brute method would be using Subseq with sum k or using 4 for loops

     //better way would be to minmize this 4 loops into 3 ,idea is to make use 3 ponters i j k,initially we keep i at 0, j at 1 and k at 2, we start moving this k and also store sum=nums[i]+nums[j]+nums[k] and check if the fouth number ie target-sum is there in the hashset we  have cretaed called temp,if not move k furhter and add that k into hashset
     public static List<List<Integer>> optimal(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;

        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates for the first number

            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // Skip duplicates for the second number

                int left = j + 1;
                int right = len - 1;

                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++; // Skip duplicates for the third number
                        while (left < right && nums[right] == nums[right - 1]) right--; // Skip duplicates for the fourth number
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
    public static void main(String[] args) {
        System.out.println(optimal(new int[]{1,0,-1,0,-2,2}, 0));
    }
}
