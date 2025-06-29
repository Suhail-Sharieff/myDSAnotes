package _2_Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

 

Example 1:

Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:

Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:

Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]
 

Constraints:

1 <= nums.length <= 100
-100 <= nums[i] <= 100 */
public class _16_sortArrayByFreq {

    public int[] frequencySort(int[] nums) {
        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Step 2: Convert nums to Integer[] for sorting
        Integer[] boxedNums = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            boxedNums[i] = nums[i];
        }
        
        // Step 3: Sort based on frequency and value...coz Arrays.sort works only for Object type and not primitive type
        Arrays.sort(boxedNums, (a, b) -> {
            if (freqMap.get(a).equals(freqMap.get(b))) {
                return Integer.compare(b, a); // Sort by value in descending order if frequencies are the same
            }
            return Integer.compare(freqMap.get(a), freqMap.get(b)); // Sort by frequency in ascending order
        });
        
        // Step 4: Convert Integer[] back to int[]
        for (int i = 0; i < nums.length; i++) {
            nums[i] = boxedNums[i];
        }
        
        return nums;
    }


    
}
