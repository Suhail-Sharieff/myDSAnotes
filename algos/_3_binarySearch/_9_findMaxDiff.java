package _3_binarySearch;

import java.util.List;

/*
You are given m arrays, where each array is sorted in ascending order.

You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.

Return the maximum distance.

 

Example 1:

Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Example 2:

Input: arrays = [[1],[1]]
Output: 0
 

Constraints:

m == arrays.length
2 <= m <= 105
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
 */
public class _9_findMaxDiff {
    public int correctMethod(List<List<Integer>> arrays) {
        if (arrays == null || arrays.size() < 2) return 0;

        int globalMin = arrays.get(0).get(0);
        int globalMax = arrays.get(0).get(arrays.get(0).size() - 1);
        int result = 0;

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> curr = arrays.get(i);
            int localMin = curr.get(0);
            int localMax = curr.get(curr.size() - 1);

            result = Math.max(result, Math.max(localMax - globalMin, globalMax - localMin));

            globalMin = Math.min(globalMin, localMin);
            globalMax = Math.max(globalMax, localMax);
        }

        return result;
    }


    ///NOTE: the main intention of posting this Q was see the son below, it is as same as the prev code, but wouldnt pass all the testcases, coz getLast() method is not apt for List---------IMP
        public int wrongMethod(List<List<Integer>> arrays) {
            if (arrays == null || arrays.size() < 2) return 0;
    
            int globalMin = arrays.get(0).get(0);
            int globalMax = arrays.get(0).get(arrays.get(0).size() - 1);
            int result = 0;
    
            for (int i = 1; i < arrays.size(); i++) {
                List<Integer> curr = arrays.get(i);
                int localMin = curr.get(0);
                int localMax = curr.get(curr.size() - 1);
    
                result = Math.max(result, Math.max(localMax - globalMin, globalMax - localMin));
    
                globalMin = Math.min(globalMin, localMin);
                globalMax = Math.max(globalMax, localMax);
            }
    
            return result;
        }
}
