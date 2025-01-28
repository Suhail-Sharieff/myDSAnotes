package _7_Graph._04_Dir_weigh;
//teaches importance of mod operator in CP
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Given start, end and an array arr of n numbers. At each step, start is multiplied with any number in the array and then mod operation with 100000 is done to get the new start.

Your task is to find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

Example 1:

Input:
arr[] = {2, 5, 7}
start = 3, end = 30
Output:
2
Explanation:
Step 1: 3*2 = 6 % 100000 = 6 
Step 2: 6*5 = 30 % 100000 = 30
Example 2:

Input:
arr[] = {3, 4, 65}
start = 7, end = 66175
Output:
4
Explanation:
Step 1: 7*3 = 21 % 100000 = 21 
Step 2: 21*3 = 63 % 100000 = 63 
Step 3: 63*65 = 4095 % 100000 = 4095 
Step 4: 4095*65 = 266175 % 100000 = 66175
Your Task:
You don't need to print or input anything. Complete the function minimumMultiplications() which takes an integer array arr, an integer start and an integer end as the input parameters and returns an integer, denoting the minumum steps to reach in which end can be achieved starting from start.

Expected Time Complexity: O(105)
Expected Space Complexity: O(105)

Constraints:

1 <= n <= 104
1 <= arr[i] <= 104
1 <= start, end < 105
 */
public class _02_min_multi_to_reach_end {
    public static void main(String[] args) {
        int arr[]={20 ,14, 1, 4};
        int start=8,end=4288;
        minimumMultiplications(arr, start, end);
    }
    static int minimumMultiplications(int[] arr, int start, int end) {

        Queue<int[]>q=new LinkedList<>();
        int mod=100000 ;
        
        int dist[]=new int [mod];
        Arrays.fill(dist,Integer.MAX_VALUE);
        Arrays.sort(arr);
       
        dist[start]=0;
        q.offer(new int[]{start,0});
        
        while(!q.isEmpty()){
            int front[]=q.poll();
            int currStart=front[0];
            int nSteps=front[1];
            for(int e:arr){
                int x=(currStart*e)%mod;
                if(dist[x]>nSteps+1){//MISTAKE I DID:(heart of problem): i also added (x<=end) in this if stmt, coz its clearly given that we r concerned only with suppose u get currStart as 100002 and say u end=2, after mod u get 2, but if u add the if consition x<=end means 100002<=2 ie true, see that we may get ans later, so just push to q without chekin
                    dist[x]=nSteps+1;
                    q.offer(new int[]{x,dist[x]});
                }
            }
        }
        return dist[end]==Integer.MAX_VALUE?-1:dist[end];
    }
}
