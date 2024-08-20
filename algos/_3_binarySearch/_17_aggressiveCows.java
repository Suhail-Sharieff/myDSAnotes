package _3_binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*

You are given an array consisting of n integers which denote the position of a stall. You are also given an integer k which denotes the number of aggressive cows. You are given the task of assigning stalls to k cows such that the minimum distance between any two of them is the maximum possible.
The first line of input contains two space-separated integers n and k.
The second line contains n space-separated integers denoting the position of the stalls.

Example 1:

Input:
n=5 
k=3
stalls = [1 2 4 8 9]
Output:
3
Explanation:
The first cow can be placed at stalls[0], 
the second cow can be placed at stalls[2] and 
the third cow can be placed at stalls[3]. 
The minimum distance between cows, in this case, is 3, 
which also is the largest among all possible ways.
Example 2:

Input:
n=5 
k=3
stalls = [10 1 2 7 5]
Output:
4
Explanation:
The first cow can be placed at stalls[0],
the second cow can be placed at stalls[1] and
the third cow can be placed at stalls[4].
The minimum distance between cows, in this case, is 4,
which also is the largest among all possible ways.
Your Task:
Complete the function int solve(), which takes integer n, k, and a vector stalls with n integers as input and returns the largest possible minimum distance between cows.

Expected Time Complexity: O(n*log(10^9)).
Expected Auxiliary Space: O(1).
 */

public class _17_aggressiveCows {

    public static void main(String[] args) {
        int stalls[] = { 1, 2, 4, 8, 9 };
        int k = 3;
        System.out.println(brute(stalls, k));
        System.out.println(better(stalls, k));
        System.out.println(optimal(stalls, k));
    }

    public static int brute(int stalls[], int k) {
        // generate all possible positions for k cows after sorting stalls, check for
        // the min distance in each subsequence, ans is max of all those min distances
        Arrays.sort(stalls);
        List<List<Integer>> ans = new ArrayList<>();
        generateSubseq(ans, new ArrayList<>(), 0, stalls, k);
        // System.out.println(ans);
        int max = Integer.MIN_VALUE;
        for (List<Integer> list : ans) {
            int temp = Integer.MAX_VALUE;
            for (int i = 1; i < list.size(); i++) {
                temp = Math.min(temp, list.get(i) - list.get(i - 1));
            }
            max = Math.max(max, temp);
        }
        return (max);
    }

    public static void generateSubseq(List<List<Integer>> ans, List<Integer> empty, int start, int stalls[], int k) {
        if (start >= stalls.length) {

            if (empty.size() == k) {
                ans.add(new ArrayList<>(empty));
            }
            return;
        }
        empty.add(stalls[start]);
        generateSubseq(ans, empty, start + 1, stalls, k);
        empty.removeLast();
        generateSubseq(ans, empty, start + 1, stalls, k);
    }

    public static int better(int stalls[], int k) {
        // given that nCows>=2, weassume that the first cow is always placed at first
        // index in sorted stalls[]
        Arrays.sort(stalls);
        
        //we know that our ans would lie in range minDistance in sortedStalls and (max-min) in sorted Stalls

        int maxPossibleDistanceBetweenTwoCows = stalls[stalls.length - 1] - stalls[0];
        int ans = Integer.MIN_VALUE;// coz we need max val
        for (int i = 0; i <= maxPossibleDistanceBetweenTwoCows; i++) {
            int nCowsPlaced = 1;
            int prevCowPos = stalls[0];
            for (int j = 1; j < stalls.length; j++) {
                int thatCowPos = stalls[j];
                if (thatCowPos - prevCowPos >= i) {
                    nCowsPlaced++;
                    prevCowPos=thatCowPos;
                    if(nCowsPlaced==k){//all cows r placed
                        break;
                    }
                }
            }
            if (nCowsPlaced==k) {
                //means that distance is possible
                ans=Math.max(ans, i);
            }
        }
        return ans;
    }

    public static int optimal(int stalls[], int k) {
        Arrays.sort(stalls);
        int low=0,high=stalls[stalls.length-1]-stalls[0];
        while (low<=high) {
            int mid=(low+high)/2;

            if (canThatDistancePlaceAllCows(stalls, k, mid)) {
                //check for greater distances since we need max
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;//high would always point at ans at last
    }
    public static boolean canThatDistancePlaceAllCows(int stalls[],int k,int distance){
        int prevCowPos=stalls[0];
        int nCowsPlaced=1;

        for (int j = 1; j < stalls.length; j++) {
            int thatCowPos = stalls[j];
            if (thatCowPos - prevCowPos >= distance) {
                nCowsPlaced++;
                prevCowPos=thatCowPos;
                if(nCowsPlaced==k){//all cows r placed
                    return true;
                }
            }
        }

        return false;
    }
}