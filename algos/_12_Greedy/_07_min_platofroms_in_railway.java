package _12_Greedy;

import java.util.Arrays;

/*
Minimum Platforms
Difficulty: MediumAccuracy: 26.84%Submissions: 514K+Points: 4
You are given the arrival times arr[] and departure times dep[] of all trains that arrive at a railway station on the same day. Your task is to determine the minimum number of platforms required at the station to ensure that no train is kept waiting.

At any given time, the same platform cannot be used for both the arrival of one train and the departure of another. Therefore, when two trains arrive at the same time, or when one arrives before another departs, additional platforms are required to accommodate both trains.

Examples:

Input: arr[] = [900, 940, 950, 1100, 1500, 1800], dep[] = [910, 1200, 1120, 1130, 1900, 2000]
Output: 3
Explanation: There are three trains during the time 9:40 to 12:00. So we need a minimum of 3 platforms.
Input: arr[] = [900, 1235, 1100], dep[] = [1000, 1240, 1200]
Output: 1
Explanation: All train times are mutually exclusive. So we need only one platform
Input: arr[] = [1000, 935, 1100], dep[] = [1200, 1240, 1130]
Output: 3
Explanation: All 3 trains have to be there from 11:00 to 11:30
Constraints:
1≤ number of trains ≤ 50000
0000 ≤ arr[i] ≤ dep[i] ≤ 2359
Note: Time intervals are in the 24-hour format(HHMM) , where the first two characters represent hour (between 00 to 23 ) and the last two characters represent minutes (this will be <= 59 and >= 0).


 */
public class _07_min_platofroms_in_railway {
    /*
We should use a sorted event-based approach (also known as a two-pointer method):

Sort the arrival times and departure times separately.
Use two pointers:
One pointer (i) iterating over arr[] (arrivals).
Another pointer (j) iterating over dep[] (departures).
Maintain a counter (platforms_needed) to track active trains.
Whenever a train arrives, increase the counter.
Whenever a train departs, decrease the counter.
Track the maximum platforms needed at any time.
     */
    static int findPlatform(int arr[], int dep[]) {
        // add your code here
        int ans=0;
        int nPlatformsNeeded=0;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int i=0,j=0;
        int len=arr.length;
        while(i<len && j<len){
            if(arr[i]<=dep[j]){
                //koi train aa chuki he aur kisi aur train platform par hai since the dept time is more
                nPlatformsNeeded++;
                ans=Math.max(ans,nPlatformsNeeded);
                i++;
            }else{
                nPlatformsNeeded--;
                j++;
            }
        }
        return ans;
    }
}
