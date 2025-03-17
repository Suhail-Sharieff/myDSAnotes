package _3_binarySearch;

import java.util.Arrays;

/*
2594. Minimum Time to Repair Cars
Solved
Medium
Topics
Companies
Hint
You are given an integer array ranks representing the ranks of some mechanics. ranksi is the rank of the ith mechanic. A mechanic with a rank r can repair n cars in r * n2 minutes.

You are also given an integer cars representing the total number of cars waiting in the garage to be repaired.

Return the minimum time taken to repair all the cars.

Note: All the mechanics can repair the cars simultaneously.

 

Example 1:

Input: ranks = [4,2,3,1], cars = 10
Output: 16
Explanation: 
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
Example 2:

Input: ranks = [5,1,8], cars = 6
Output: 16
Explanation: 
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
 

Constraints:

1 <= ranks.length <= 105
1 <= ranks[i] <= 100
1 <= cars <= 106
 */
public class _29_min_time_to_repair_cars {
    /*
Intuition
In the previous approach, we precomputed the smallest rank and used a frequency array to count how many mechanics had each rank. The idea behind this was to speed up the calculation of how many cars could be repaired in a given time. However, this extra bookkeeping can be removed if we want to space optimize it because we can determine the number of cars repaired directly by iterating over the ranks array.

More specifically, instead of grouping mechanics by rank and iterating over a fixed range of possible ranks (from 1 to 100), we can simply iterate over the given ranks and compute the number of cars each mechanic can repair on the fly. This removes the processing and makes the solution more straightforward while maintaining the same logic.

With this optimization in mind, we keep the core idea of binary search on time. The search space remains the same: the lower bound is 1, representing the smallest unit of time, while the upper bound is ranks[0] * cars^2, representing the worst-case scenario where the slowest mechanic repairs all cars alone.

For a given mid time, we compute how many cars can be repaired by summing up contributions from all mechanics. The number of cars a mechanic with rank r can repair within mid time is given by n ≤ sqrt(mid / r), since repairing n cars requires r * n^2 time. By iterating over the ranks array and applying this formula to each mechanic, we calculate the total number of repaired cars and compare it with the required amount.

If the total number of repaired cars is less than the required amount, it means mid is too small, so we increase low (low = mid + 1). Otherwise, if the total is at least the required amount, we try to minimize the repair time by decreasing high (high = mid).

Algorithm
Set low to 1, the minimum possible repair time.

Set high to ranks[0] * cars * cars, the worst-case maximum repair time.

Perform binary search while low < high:

Compute mid as the middle value between low and high.

Initialize carsRepaired to count total cars repaired in mid time.

Iterate through ranks:

Calculate the number of cars repaired by each mechanic using sqrt(mid / rank).
Accumulate the total carsRepaired.
If carsRepaired is less than cars, update low = mid + 1 since more time is needed.

Else, update high = mid to search for a smaller valid time.

Return low, the minimum time required to repair all cars.

Implementation

Complexity Analysis
Let n be the size of the ranks array, m be the number of cars (cars), and k be the maximum possible rank (100 in this case).

Time Complexity: O(n⋅log(m⋅max_rank))

The algorithm performs a binary search over the possible time range, which takes O(log(m⋅max_rank)) iterations. For each iteration, it calculates the number of cars that can be repaired in O(n) time by iterating over the ranks array. The overall time complexity is O(n⋅log(m⋅max_rank)).

Space Complexity: O(1)

The algorithm uses only a constant amount of extra space for variables, resulting in O(1) space complexity. No additional data structures are used.



     */


    //  /watcH:https://www.bing.com/videos/riverview/relatedvideo?q=Minimum+Time+to+Repair+Cars+mik&mid=CB53706E8A20DBDEB80BCB53706E8A20DBDEB80B&FORM=VIRE
    public long repairCars(int[] ranks, int nCars) {
        long ans=1l;
        long low=1;
        long high=Arrays.stream(ranks).max().getAsInt()*1l*nCars*nCars;
        while(low<=high){
            long mid=(low+high)/2l;
            if(isPossibleToRepairAllCars(mid,ranks,nCars)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
    boolean isPossibleToRepairAllCars(long mid,int ranks[],long nCars){
        double nRepaired=0d;
        for(int e:ranks){
            nRepaired+=(long)Math.sqrt(mid/e);
            if(nRepaired>=nCars) return true;
        }
        return false;
    }

}
