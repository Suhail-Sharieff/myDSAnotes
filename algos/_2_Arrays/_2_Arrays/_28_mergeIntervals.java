package _2_Arrays;

import java.util.ArrayList;
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
        optimal(new int[][] { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } });
        // nlogn is max achievable TC, due to sorting,without soring its not possible to
        // merge the inervals
    }

    public static void optimal(int intervals[][]) {// O(nlogn)--O(n)
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));// sorts as per the first elemnt in each row /set--IMP
        Stack<List<Integer>> stack = new Stack<>();
        stack.add(List.of(intervals[0][0], intervals[0][1]));
        for (int i = 1; i < intervals.length; i++) {
            List<Integer> prev = stack.peek();// most recently added elemnt
            int lowerOfprev = prev.get(0);
            int upperOfprev = prev.get(1);
            int lowerOfcurr = intervals[i][0];
            int upperOfcurr = intervals[i][1];

            if (upperOfprev >= lowerOfcurr) {
                stack.pop();
                stack.add(List.of(lowerOfprev, Math.max(upperOfprev, upperOfcurr)));
                // AND NOT "stack.add(List.of(lowerOfprev,upperOfcurr));"---MISTAKE
                continue;
            }
            stack.add(List.of(intervals[i][0], intervals[i][1]));

        }
        System.out.println(stack);

    }

    // ----------another way:
    public int[][] merge(int[][] arr) {
        Arrays.sort(arr, (x, y) -> {
            return x[0] - y[0];
        });
        List<int[]> ans = new ArrayList<>();
        ans.add(arr[0]);
        int prev[] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (prev[1] >= arr[i][0]) {
                ans.set(ans.size() - 1, new int[] { prev[0], Math.max(prev[1], arr[i][1]) });
            } else {
                ans.add(arr[i]);
            }
            prev = ans.getLast();
        }
        // for(var e:ans) System.out.println(Arrays.toString(e));
        return ans.toArray(int[][]::new);
    }

    /*more meaning full:
    class Solution {
    public int[][] merge(int[][] interval) {
        List<int[]>ans=new ArrayList<>();
        Arrays.sort(interval,(x,y)->x[0]-y[0]);

        int ps=interval[0][0];//previousStart
        int pe=interval[0][1];//previousEnd
        
        for(int i=1;i<interval.length;i++){
            int cs=interval[i][0];//currentStart
            int ce=interval[i][1];//currentEnd


            if(cs>pe){
                ans.add(new int[]{ps,pe});
                ps=cs;
                pe=ce;
            }else{
                pe=Math.max(pe,ce);//why max?: consider case like [1,4],[2,3], without this the ans then will be [1,3], but expected [1,4]
            }

        }
        ans.add(new int[]{ps,pe});
        return ans.toArray(int[][]::new);
    }
}
     * 
     */
    // ---------------FOLLOW UP:
    /*
     * 57. Insert Interval
     * Solved
     * Medium
     * Topics
     * Companies
     * Hint
     * You are given an array of non-overlapping intervals intervals where
     * intervals[i] = [starti, endi] represent the start and the end of the ith
     * interval and intervals is sorted in ascending order by starti. You are also
     * given an interval newInterval = [start, end] that represents the start and
     * end of another interval.
     * 
     * Insert newInterval into intervals such that intervals is still sorted in
     * ascending order by starti and intervals still does not have any overlapping
     * intervals (merge overlapping intervals if necessary).
     * 
     * Return intervals after the insertion.
     * 
     * Note that you don't need to modify intervals in-place. You can make a new
     * array and return it.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     * Example 2:
     * 
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     * 
     * 
     * Constraints:
     * 
     * 0 <= intervals.length <= 104
     * intervals[i].length == 2
     * 0 <= starti <= endi <= 105
     * intervals is sorted by starti in ascending order.
     * newInterval.length == 2
     * 0 <= start <= end <= 105
     */
    public int[][] brute(int[][] intervals, int[] newInterval) {//NlogN
        List<int[]> ans = new ArrayList<>();
        for (var e : intervals)ans.add(e);
        ans.add(newInterval);

        ans = mergeAndGiveList(ans.toArray(int[][]::new));
        return ans.toArray(int[][]::new);
    }

    public List<int[]> mergeAndGiveList(int[][] arr) {
        Arrays.sort(arr, (x, y) -> {
            return x[0] - y[0];
        });
        List<int[]> ans = new ArrayList<>();
        ans.add(arr[0]);
        int prev[] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (prev[1] >= arr[i][0]) {
                ans.set(ans.size() - 1, new int[] { prev[0], Math.max(prev[1], arr[i][1]) });
            } else {
                ans.add(arr[i]);
            }
            prev = ans.getLast();
        }
        // for(var e:ans) System.out.println(Arrays.toString(e));
        return ans;
    }

    //-----------optimal:
    /*
Approach 1: Linear Search
Intuition
We can do a linear search by iterating through all the intervals and checking which one of the three conditions the intervals fall under:

No Overlaps before Merging:

This occurs when the current interval ends before the new interval starts.
Overlapping and Merging:

This occurs when the starting point of the current interval is less than or equal to the ending point of the new interval (newInterval[1]), indicating an overlap. We can merge the current interval with the new interval by updating the start and end values of the new interval.
No Overlapping after Merging:

This occurs when the current interval starts after the new interval ends.
1. Identifying Non-Overlapping Intervals Before Merging:
We iterate through all intervals, checking whether the endpoint of the current interval (intervals[i][1]) is less than the starting point of the new interval (newInterval[0]). If this condition holds true, it indicates there is no overlap before merging, and we add the current interval to the result.

2. Identifying and Merging Overlapping Intervals:
During the iteration, we identify overlap by comparing the endpoint of the new interval (newInterval[1]) with the starting point of the current interval (intervals[i][0]). When an overlap is detected, we merge the intervals by updating the start and end values of the new interval. The index (i) is then incremented to move to the next interval. After merging, the new interval is added to the result.

3. Identifying Non-Overlapping Intervals After Merging:
As we have already added the non-overlapping intervals before newInterval and merged overlapping ones, the remaining intervals after are guaranteed not to overlap with the newly merged interval. We simply add these remaining intervals to the result.

The following slideshow illustrates how the linear search algorithm is employed:

Current

Algorithm
Initialize variables n and i to store the size of intervals and the current index, respectively, and an empty array res to store the result.
Case 1: No Overlap Before Insertion:
Loop through intervals while i is less than n and the current interval's endpoint (intervals[i][1]) is less than the new interval's start point (newInterval[0]).
Add the current interval from intervals to the res array.
Increment i to move to the next interval.
Case 2: Overlap and Merge:
Loop through intervals while i is less than n and the new interval's endpoint (newInterval[1]) is greater than or equal to the current interval's start point (intervals[i][0]).
Update the newInterval's start point to the minimum of its current start and the current interval's start.
Update the newInterval's endpoint to the maximum of its current end and the current interval's end.
This essentially merges overlapping intervals into a single larger interval.
Increment i to move to the next interval.
Add the updated newInterval to the res array, representing the merged interval.
Case 3: No overlap after insertion:
Loop through the remaining intervals (from index i) and add them to the res array.
This includes intervals that occur after the new interval and those that don't overlap, as they have already been correctly inserted in the previous iterations (previous two cases).
Return the res array containing all intervals with the new interval inserted correctly.
     */
    public int[][] optimal(int[][] intervals, int[] newInterval) {//NLOgN
        List<int[]>ans=new ArrayList<>();
        int len=intervals.length;
        int i=0;
        //left part
        while(i<len && intervals[i][1]<newInterval[0])  ans.add(intervals[i++]);
        //overallping part
        while(i<len && intervals[i][0]<=newInterval[1]){
            newInterval[0]=Math.min(intervals[i][0],newInterval[0]);
            newInterval[1]=Math.max(intervals[i][1],newInterval[1]);
            i++;
        }
        ans.add(newInterval);
        //right part
        while(i<len) ans.add(intervals[i++]);
        return ans.toArray(int[][]::new);
    }
}
