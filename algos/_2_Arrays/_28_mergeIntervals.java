package _2_Arrays;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/*
Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

 

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
Example 2:

Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 

Constraints:

1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
 */
public class _28_mergeIntervals {
    public static void main(String[] args) {
        optimal(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        //nlogn is max achievable TC, due to sorting,without soring its not possible to merge the inervals
    }
    public static void optimal(int intervals[][]){//O(nlogn)--O(n)
        Arrays.sort(intervals,(a,b)->(a[0]-b[0]));//sorts as per the first elemnt in each row /set--IMP
        Stack<List<Integer>>stack=new Stack<>();
        stack.add(List.of(intervals[0][0],intervals[0][1]));
        for (int i = 1; i < intervals.length; i++) {
            List<Integer>prev=stack.peek();//most recently added elemnt
            int lowerOfprev=prev.get(0);
            int upperOfprev=prev.get(1);
            int lowerOfcurr=intervals[i][0];
            int upperOfcurr=intervals[i][1];

            if (upperOfprev>=lowerOfcurr) {
                stack.pop();
                stack.add(List.of(lowerOfprev, Math.max(upperOfprev, upperOfcurr)));
                //AND NOT "stack.add(List.of(lowerOfprev,upperOfcurr));"---MISTAKE
                continue;
            }
            stack.add(List.of(intervals[i][0],intervals[i][1]));

        }
        System.out.println(stack);

        

    }
}
