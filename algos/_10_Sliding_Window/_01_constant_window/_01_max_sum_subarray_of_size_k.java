package _10_Sliding_Window._01_constant_window;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
Given an array of integers arr[]  and a number k. Return the maximum sum of a subarray of size k.

Note: A subarray is a contiguous part of any given array.

Examples:

Input: arr[] = [100, 200, 300, 400] , k = 2
Output: 700
Explanation: arr3  + arr4 = 700, which is maximum.
Input: arr[] = [100, 200, 300, 400] , k = 4
Output: 1000
Explanation: arr1 + arr2 + arr3 + arr4 = 1000, which is maximum.
Input: arr[] = [100, 200, 300, 400] , k = 1
Output: 400
Explanation: arr4 = 400, which is maximum.
Constraints:
1 <= arr.size() <= 106
1 <= arr[i]<= 106
1 <= k<= arr.size()
 */
public class _01_max_sum_subarray_of_size_k {
    public int maximumSumSubarray(int[] nums, int k) {// O(N)------------sliding window approach
        //https://www.youtube.com/watch?v=pBWCOCS636U&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=274&ab_channel=takeUforward
        // Logic:
        /*
         * Possibilities r:
         * ->sum(0..k)+0
         * ->sum(0...k-1)+sum(n..k)
         * ->sum(0....k+1)+sum(n...k-1)
         * ...like that suppose k=3, we can choose [3left+0right] or [2left+1right] or
         * [1left+2right] or [0left+3right] ans is max of all these
         */
        int left_sum = 0;
        for (int i = 0; i < k; i++) {
            left_sum += nums[i];
        }
        int right_sum=0;

        int ans=left_sum;

        int right_ptr=nums.length-1;
        int left_ptr=k-1;

        while (left_ptr!=-1) {
            left_sum-=nums[left_ptr--];
            right_sum+=nums[right_ptr--];
            ans=Math.max(ans, left_sum+right_sum);
        }

        return ans;
        
    }


    //another codeforces variant qustion:
    /*
     * C. Yarik and Array
time limit per test1 second
memory limit per test256 megabytes
A subarray is a continuous part of array.

Yarik recently found an array a
 of n
 elements and became very interested in finding the maximum sum of a non empty subarray. However, Yarik doesn't like consecutive integers with the same parity, so the subarray he chooses must have alternating parities for adjacent elements.

For example, [1,2,3]
 is acceptable, but [1,2,4]
 is not, as 2
 and 4
 are both even and adjacent.

You need to help Yarik by finding the maximum sum of such a subarray.

Input
The first line contains an integer t
 (1≤t≤104)
 — number of test cases. Each test case is described as follows.

The first line of each test case contains an integer n
 (1≤n≤2⋅105)
 — length of the array.

The second line of each test case contains n
 integers a1,a2,…,an
 (−103≤ai≤103)
 — elements of the array.

It is guaranteed that the sum of n
 for all test cases does not exceed 2⋅105
.

Output
For each test case, output a single integer — the answer to the problem.

Example
InputCopy
7
5
1 2 3 4 5
4
9 9 8 8
6
-1 4 -1 0 5 -4
4
-1 2 4 -3
1
-1000
3
101 -99 101
20
-10 5 -8 10 6 -10 7 9 -2 -6 7 2 -4 6 -1 7 -6 -7 4 1
OutputCopy
15
17
8
4
-1000
101
10



//prerequsite code for just maimum subarry sum:
class Solution {
    public int maxSubArray(int[] nums) {
        int sum=0;
        int ans=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            ans=Math.max(ans,sum);
            sum=Math.max(sum,0);
        }
        return ans;
    }
}

     */
     public static void solve() throws IOException {
        int len = scanInt();
        int arr[] = scanIntArray(len);
        long sum = Math.max(0, arr[0]);
        long max = arr[0];
        for (int i = 1; i < len; i++) {
            boolean diffParity = parityDifferent(arr[i - 1], arr[i]);
            if (diffParity) {
                sum += arr[i];
            } else {
                sum = arr[i];
            }
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);

        }
        print(max);
    }

    static boolean parityDifferent(int x, int y) {
        return ((x % 2 == 0) && (y % 2 != 0)) ||
                ((x % 2 != 0) && (y % 2 == 0));
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int scanInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    static int[] scanIntArray(int size) throws IOException {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanInt();
        }
        return array;
    }

    static String nextToken() throws IOException {
        if (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }

    static void print(Object o) throws IOException {
        bw.write(o.toString());
        bw.newLine();
        bw.flush();
    }
}
