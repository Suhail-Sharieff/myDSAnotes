package _10_Sliding_Window._02_dynamic_window;

public class _01_longest_subarray_with_sumK {
    // TAsk: find longest subarray length with sum<=K
    //https://www.youtube.com/watch?v=9kdHxplyl5I&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=272&ab_channel=takeUforward

    public static void main(String[] args) {
        int k = 14;
        int nums[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(brute_force(nums, k));
        System.out.println(optimal(nums, k));
    }

    // --------lopp via all subarrays
    static int brute_force(int nums[], int k) {// O(n^2)
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int currSum = 0;
            for (int j = i; j < nums.length; j++) {
                currSum += nums[j];
                if (currSum > k)
                    break;
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }

    // -----------better solution: 2 pointer technique(optimal if we want to print)
    // initialize l=r=0, take sum, at each point shift right , whenevr sum>k, shrink
    // ie move l, else move right
    static int better(int nums[], int k) {
        int ans = 0;
        int sum = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            sum += nums[r];
            while (sum > k) {
                sum -= nums[l++];
            }
            if (sum <= k) {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }
        return ans;
    }
    //------optimal(cant use if we need to print also)
    static int optimal(int nums[], int k) {//O(2N)
        int ans = 0;
        int sum = 0;
        int l = 0, r = 0;
        while (r < nums.length) {//O(N)
            sum += nums[r];
            if (sum > k) {//overal O(N)//-------while changed to if , since we need only length
                sum -= nums[l++];
            }
            if (sum <= k) {
                ans = Math.max(ans, r - l + 1);
            }
            r++;
        }
        return ans;
    }
}
