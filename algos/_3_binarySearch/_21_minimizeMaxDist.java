//IMP VERY HARD PROBLEM:
//https://www.youtube.com/watch?v=kMSBvlZ-_HA&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=64
package _3_binarySearch;

import java.util.PriorityQueue;

/**
We have a horizontal number line. On that number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where n = size of the stations array. Now, we add k more gas stations so that d, the maximum distance between adjacent gas stations, is minimized. We have to find the smallest possible value of d. Find the answer exactly to 2 decimal places.

Example 1:

Input:
n = 10
stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
k = 9
Output: 0.50
Explanation: Each of the 9 stations can be added mid way between all the existing adjacent stations ie now we have stations on line : 1, 1.5, 2, 2.5.......9, 9.5, 10  see that in every case the max diff was 1 previously, now is max to max 1.5. This the max (1) is reduced to min possible (0.5)
Example 2:

Input:
n = 10
stations = [3,6,12,19,33,44,67,72,89,95] 
k = 2 
Output: 14.00  (between 19 and 33)
Explanation: Construction of gas stations at 8th(between 72 and 89) and 6th(between 44 and 67) locations.
 

Your Task:
You don't need to read input or print anything. Your task is to complete the function findSmallestMaxDist() which takes a list of stations and integer k as inputs and returns the smallest possible value of d. Find the answer exactly to 2 decimal places.

Expected Time Complexity: O(n*log k)
Expected Auxiliary Space: O(1)

Constraint:
10 <= n <= 5000 
0 <= stations[i] <= 109 
0 <= k <= 105

stations is sorted in a strictly increasing order.
 */
public class _21_minimizeMaxDist {

    public static void main(String[] args) {
        
    }

    //BRUTE:


    public static double brute(int stations[], int k) {
        int n = stations.length;
        // Array to keep track of how many new stations we place between each pair of existing stations
        int newStations[] = new int[n - 1];
        
        for (int newGasPos = 0; newGasPos < k; newGasPos++) {
            // Find the index where the maximum difference between stations exists
            int index = indexWhereMaxDiffExists(stations, newStations);
            // Place a new station there
            newStations[index]++;
        }

        double ans = -1;
        // Calculate the maximum distance between stations after adding all new stations
        for (int i = 0; i < n - 1; i++) {
            double diff = stations[i + 1] - stations[i];
            // Calculate the distance between the stations with the new stations in between
            double hypotheticalDistanceDueToNewStationsInBetween = diff / (newStations[i] + 1);
            ans = Math.max(ans, hypotheticalDistanceDueToNewStationsInBetween);
        }
        return ans; // Return the result rounded to 2 decimal places
    }

    public static int indexWhereMaxDiffExists(int currStationsPos[], int newStations[]) {
        double maxDifference = -1;
        int indexWhereMaxDiffExists = -1;
        
        for (int i = 0; i < currStationsPos.length - 1; i++) {
            double diff = currStationsPos[i + 1] - currStationsPos[i];
            // Calculate the distance after placing the new stations hypothetically
            double hypotheticalDistanceDueToNewStationsInBetween = diff / (newStations[i] + 1);
            if (hypotheticalDistanceDueToNewStationsInBetween > maxDifference) {
                maxDifference = hypotheticalDistanceDueToNewStationsInBetween;
                indexWhereMaxDiffExists = i;
            }
        }
        return indexWhereMaxDiffExists;
    }



    //BETTER USING PRIOTRITY QUEUE---wtch that video striver

    public static double better(int stations[],int k){
        // Priority queue to store pairs of {current max segment length, index}
        // The priority queue orders by the smallest possible distance first (min-heap).
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));//coz we need to arrange them in order of theier differences
        int newStations[] = new int[stations.length - 1];

        // Initialize the priority queue with the distances between existing stations
        for (int i = 0; i < stations.length - 1; i++) {
            double diff = stations[i + 1] - stations[i];
            pq.add(new double[]{diff, i});
        }

        // Place the new gas stations
        for (int newGasPos = 1; newGasPos <= k; newGasPos++) {
            // Get the segment with the maximum distance
            double[] top = pq.poll();
            int idx = (int) top[1];
            newStations[idx]++;
            // Recalculate the distance after adding a new station in this segment
            double initialDiff = stations[idx + 1] - stations[idx];
            double newDiff = initialDiff / (newStations[idx] + 1);
            pq.add(new double[]{newDiff, idx});
        }

        // The answer will be the maximum distance in the priority queue after all stations are added
        return pq.peek()[1];  // Return the smallest max distance rounded to 2 decimal places
    }



    //OPTIMAL USING BS
    public static double optimal(int stations[],int k){
        //lower range =0, higher range =max-min in stations
        double low=0,high=stations[stations.length-1]-stations[0];
        //IMP PART:  since the compiler would check the ans 1e-6 deciamals, we increase by that amout 
        double limit=1e-6;

        while ((high-low)>limit) {
            double mid=(low+high)/2.0;

            // Binary search for the minimum possible d


            // Check if we can place the gas stations with max distance <= mid
            if (canPlaceStations(stations, k, mid)) {
                high = mid;
            } else {
                low = mid;
            }


        }

        return high;
    }

    // Helper function to determine if we can achieve max distance <= d
    public static boolean canPlaceStations(int[] stations, int k, double d) {
        int n = stations.length;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            double distance = stations[i + 1] - stations[i];
            count += (int) (distance / d);  // How many stations needed in this segment
            if (count > k) return false;  // Too many stations needed
        }

        return count <= k;
    }
}