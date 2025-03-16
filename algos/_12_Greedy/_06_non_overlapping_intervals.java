/*
ntuition
The algorithm sorts intervals based on their end points, then iterates through them, counting overlapping intervals, and returns the count.

Solution Video


Please upvote and subscribe to my channel from here.
http://www.youtube.com/channel/UC9RMNwYTL3SXCP6ShLWVFww?sub_confirmation=1

Approach
This is based on Python code. Other might be different a bit.

Initialize a variable "res" to keep track of the count of overlapping intervals. Set it to 0 initially.

Sort the intervals in ascending order based on their end points using the sort method and a lambda function as the key for sorting.

Initialize a variable "prev_end" to store the end point of the first interval (since the intervals are sorted).

Iterate through the sorted intervals starting from the second one (index 1).

Check if the end point of the previous interval (stored in "prev_end") is greater than the start point of the current interval (intervals[i][0]).

If true, it means there is an overlap between the previous interval and the current one. Increment the "res" variable to count the overlapping interval.
If there is no overlap (i.e., the end point of the previous interval is not greater than the start point of the current interval), update "prev_end" to the end point of the current interval.

After processing all intervals, return the count of overlapping intervals ("res") as the final result.

Complexity
Time complexity: O(n log n)
The main operations in this code are sorting the intervals and iterating through the sorted intervals. Sorting the intervals takes O(n log n) time complexity, where n is the number of intervals. The subsequent loop iterates through the sorted intervals once, which takes O(n) time complexity. Therefore, the overall time complexity of this code is O(n log n).

Space complexity: O(log n) or O(n)
Depends on language you use.


 */


 package _12_Greedy;

import java.util.Arrays;

public class _06_non_overlapping_intervals {
 
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,(x,y)->x[1]-y[1]);
        int ans=0;
        int prev_end=intervals[0][1];
        for(int i=1;i<intervals.length;i++){
            if(intervals[i][0]<prev_end) ans++;
            else prev_end=intervals[i][1];
        }
        return ans;
    }
 }