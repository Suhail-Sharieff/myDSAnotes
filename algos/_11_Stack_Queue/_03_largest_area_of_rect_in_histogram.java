package _11_Stack_Queue;

import java.util.Arrays;
import java.util.Stack;

/*
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104
 */
public class _03_largest_area_of_rect_in_histogram {
    public static void main(String[] args) {
        int arr[] = { 2, 1, 5, 6, 2, 3 };

        optimal(arr);
    }

    // -------------------brute force: //for every heights[i], move untill we
    // encounter a lesser height than heights[i] on left and right, compute area,
    // ans is max of all areas for each index, ie idea is that we assume for each
    // heights[i] that it is minimum, and cimpute area with its largers on left and
    // right, finally take max---O(N^2)
    public int brute_force(int[] heights) {// O(N^2)
        if (heights.length == 1)
            return heights[0];
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int left_ptr = i, right_ptr = i;
            while (left_ptr >= 0 && heights[left_ptr] >= heights[i])
                left_ptr--;
            while (right_ptr < heights.length && heights[right_ptr] >= heights[i])
                right_ptr++;
            ans = Math.max(ans, heights[i] * (right_ptr - left_ptr - 1));
        }
        return ans;
    }

    // ------------------------better: since the largeest area can be occupied by
    // each heights[i] is between its prev and next smallest heights, we can
    // precompute at what indcies they lie and we can return ans after taking
    // max(nse-pse-1)*heights[i]
    public int better(int[] arr) {// O(2n+2n+n)----O(2n)
        int pse[] = get_prev_smallest_of_each(arr);
        int nse[] = get_next_smallest_of_each(arr);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans = Math.max(ans, arr[i] * (nse[i] - pse[i] - 1));
        }
        return ans;

    }

    static int[] get_prev_smallest_of_each(int arr[]) {// 2n
        Stack<Integer> st = new Stack<>();
        int prev_smallest[] = new int[arr.length];
        Arrays.fill(prev_smallest, -1);// fill with -1
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty()) {
                if (arr[i] > arr[st.peek()]) {
                    prev_smallest[i] = st.peek();
                    break;
                }
                st.pop();
            }
            st.push(i);
        }
        return prev_smallest;
    }

    static int[] get_next_smallest_of_each(int arr[]) {// 2n
        Stack<Integer> st = new Stack<>();
        int next_smallest[] = new int[arr.length];
        Arrays.fill(next_smallest, arr.length);// fill with arr.length
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty()) {
                if (arr[i] > arr[st.peek()]) {
                    next_smallest[i] = st.peek();
                    break;
                }
                st.pop();
            }
            st.push(i);
        }
        return next_smallest;
    }

    // ---------------------optimal
    // https://www.youtube.com/watch?v=Bzat9vgD0fs&ab_channel=takeUforward
    // idea used: keep on pushing everything to stack, whenver u find that the
    // elemnt u want to push is less than its previosu,then it must be break point
    // of its ancestors(ie next previous elemnt), so wjile poping out its ancestors,
    // for each poped out ancestor, compute the area between that ancestor and curr
    // element , ans is max of all such
    static int optimal(int arr[]) {//O(N)---O(N)
        Stack<Integer> st = new Stack<>();
        int ans = 0;
        st.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!st.isEmpty() && arr[i] < arr[st.peek()]) {
                int nse_idx = i;
                int prev_element = arr[st.pop()];
                int pse_idx = st.isEmpty() ? -1 : st.peek();
                ans = Math.max(ans, prev_element * (nse_idx - pse_idx - 1));
            }
            st.push(i);
        }
        // System.out.println(ans);
        while (!st.isEmpty()) {// those elemnts having no next smallest r left
            int nse_idx = arr.length;
            int prev_element = arr[st.pop()];
            int pse_idx = st.isEmpty() ? -1 : st.peek();
            ans = Math.max(ans, prev_element * (nse_idx - pse_idx - 1));
        }
        return ans;
    }
}
