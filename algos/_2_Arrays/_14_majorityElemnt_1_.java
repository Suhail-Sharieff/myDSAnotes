package _2_Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _14_majorityElemnt_1_ {

    // ---------------------------TYPE 1
    // find the only elemnt in the array whch appears strictly more than N/2 times
    // where n is given

    public static int better1(int arr[], int n) {// O(nlogn+n)---O(n)
        HashMap<Integer, Integer> hs = new HashMap<>();
        int ans = -1;
        for (int e : arr) {
            hs.put(e, hs.getOrDefault(e, 0) + 1);
            if (hs.getOrDefault(e, 0) > n / 2) {
                ans = e;
                break;
            }
        }
        return ans;
    }

    // moore's voting algorithm--interview explain intutuion first then then code
    // https://www.youtube.com/watch?v=nP_ns3uSh80&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=23
    // O(2n)--O(1)
    public static int optimal1(int arr[], int n) {
        /*
         * Why this work?🤔
         * Assume we are searching for the element that appeared more than array_size /
         * 2 times, then we are sure that the voting variable can has value array_size /
         * 2 + 1 and if we decreased it for all other elements in the array it will also
         * stay positive enough to tell us the desired candidate.
         */
        int tempCnt = 0;
        int assumedAns = -1;
        for (int i = 0; i < arr.length; i++) {
            if (tempCnt == 0) {// if thats not majority elemnt why did its count become 0,so assume next,since
                               // till here my assumed elemnt cant be ans
                tempCnt = 1;
                assumedAns = arr[i];
            } else if (arr[i] == assumedAns) {
                tempCnt++;
            } else {// if not equal
                tempCnt--;
            }
        }
        int c = 0;
        for (int e : arr) {
            if (e == assumedAns) {
                c++;
            }
        }
        return (c > n / 2 ? assumedAns : -1);

    }

    // ------------------------------------------------TYPE 2
    /*
     * Given an integer array of size n, find all elements that appear more than ⌊
     * n/3 ⌋ times.(floor of (n/3))
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [3,2,3]
     * Output: [3]
     * Example 2:
     * 
     * Input: nums = [1]
     * Output: [1]
     * Example 3:
     * 
     * Input: nums = [1,2]
     * Output: [1,2]
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 5 * 104
     * -109 <= nums[i] <= 109
     * 
     * 
     * Follow up: Could you solve the problem in linear time and in O(1) space?
     */

    // https://www.youtube.com/watch?v=vwZj1K0e9U8&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=35
    // exp:
    /*
     * Since we are required to return elements that appeared more than ⌊n/3⌋ times
     * then we have atmost two elements that we can return.💪
     * 
     * But how two elements ???🤯🤯🤯🤯
     * Since we want to return elements that appeared more than ⌊n/3⌋ times then
     * atleast it must have appeared ⌊n/3⌋ + 1 times.
     * ⌊n/3⌋ + 1 is greater than the third of the array so
     * 
     * `⌊n/3⌋ + 1` * 3 > array size
     * So, it is impossible to return more than two elements.😔
     * 
     * How can we utilize a great observation like this?🤔
     * Instead of storing the occurencies of all elements, We can only track the two
     * highest elements that have appeared in our array and return them!🤩
     * And there is algorithm for that. It is called Boyer-Moore Majority Voting
     * Algorithm.
     * 
     * This algorithm can be used to return the highest K elements that appeared in
     * the array more than array_size/(K+1) times. In our case, K = 2.
     * 
     * The major Intuition behind this algorithm is that maintaining voting variable
     * for the candidates:
     * Increase the variable if you faced the candidate in your iteration.
     * Decrease the variable if you faced another element.
     * If the variable reaches 0, look for another promising candidate.
     * Why this work?🤔
     * Assume we are searching for the element that appeared more than array_size /
     * 2 times, then we are sure that the voting variable can has value array_size /
     * 2 + 1 and if we decreased it for all other elements in the array it will also
     * stay positive enough to tell us the desired candidate.
     */
    public static List<Integer> optimal2(int[] nums) {
        int count1 = 0, count2 = 0; // Counters for the potential majority elements
        int candidate1 = 0, candidate2 = 0; // Potential majority element candidates

        // First pass to find potential majority elements.
        for (int i = 0; i < nums.length; i++) {
            // If count1 is 0 and the current number is not equal to candidate2, update
            // candidate1.----coz we need unique elemnnts so we add the codition after &&
            if (count1 == 0 && nums[i] != candidate2) {
                count1 = 1;
                candidate1 = nums[i];
            }
            // If count2 is 0 and the current number is not equal to candidate1, update
            // candidate2.----coz we need unique elemnnts so we add the codition after &&
            else if (count2 == 0 && nums[i] != candidate1) {
                count2 = 1;
                candidate2 = nums[i];
            }
            // Update counts for candidate1 and candidate2.
            else if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            }
            // If the current number is different from both candidates, decrement their
            // counts.
            else {
                count1--;
                count2--;
            }
        }

        List<Integer> result = new ArrayList<>();
        int threshold = nums.length / 3; // Threshold for majority element

        // Second pass to count occurrences of the potential majority elements.
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (candidate1 == nums[i]) {
                count1++;
            } else if (candidate2 == nums[i]) {
                count2++;
            }
        }

        // Check if the counts of potential majority elements are greater than n/3 and
        // add them to the result.
        if (count1 > threshold) {
            result.add(candidate1);
        }
        if (count2 > threshold) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(better1(new int[] { 2, 2, 1, 3, 1, 1, 3, 1, 1 }, 9));
        System.out.println(optimal1(new int[] { 2, 2, 1, 3, 1, 1, 3, 1, 1 }, 9));
        System.out.println(optimal2(new int[] { 2, 2, 1, 3, 1, 1, 3, 1, 1 }));
    }
}
