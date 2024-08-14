package _3_binarySearch;

public class _1_BSalgo {
    public static void main(String[] args) {
        iterativeBS(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 8);
        recursiveBS(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 8, 0, 8);
        //TC: o(logbase2n) wich gives number of steps to find number,for ex if there r 32 numbers we use unly logbase2(32) ie 5 steps to check approx
    }

    public static int iterativeBS(int sortedNums[], int target) {
        int lowIdx = 0;
        int highIdx = sortedNums.length - 1;
        while (lowIdx <= highIdx) {
            // int midIdx = (lowIdx + highIdx) / 2;//this is also valid BUT BUT BUT supoose u have RR OF SIZE max value,THEN IT MAY NOT FIT INTO INTGER,YES U CAN USE LONG AS MID ,BUT BETTER WAY TO HANDLE THIS IS REWRITING THAT EXPRESSION AS:
            int midIdx = lowIdx+((highIdx-lowIdx)/2);
            if (sortedNums[midIdx] == target) {
                System.out.println("Found at idx : " + midIdx);
                return midIdx;
            } else if (sortedNums[midIdx] > target) {
                highIdx = midIdx - 1;
            } else if (sortedNums[midIdx] < target) {
                lowIdx = midIdx + 1;
            }
        }
        System.out.println("Not found");
        return -1;
    }

    public static int recursiveBS(int nums[], int target, int lowIdx, int highIdx) {
        if (lowIdx > highIdx) {
            System.out.println("Not found");
            return -1;
        }
        // int midIdx = (lowIdx + highIdx) / 2;//this is also valid BUT BUT BUT supoose u have RR OF SIZE max value,THEN IT MAY NOT FIT INTO INTGER,YES U CAN USE LONG AS MID ,BUT BETTER WAY TO HANDLE THIS IS REWRITING THAT EXPRESSION AS:
        int midIdx = lowIdx+((highIdx-lowIdx)/2);
        if (nums[midIdx] == target) {
            System.out.println("Found at idx : " + midIdx);
            return midIdx;
        } else if (nums[midIdx] > target) {
            return recursiveBS(nums, target, lowIdx, midIdx - 1);
        }
        return recursiveBS(nums, target, midIdx + 1, highIdx);
    }
}
