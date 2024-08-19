package _3_binarySearch;

/*
You are given an integer array bloomDay, an integer m and an integer k.

You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.

The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make m bouquets from the garden. If it is impossible to make m bouquets return -1.

 

Example 1:

Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
Output: 3
Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
Example 2:

Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
Output: -1
Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
Example 3:

Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
Output: 12
Explanation: We need 2 bouquets each should have 3 flowers.
Here is the garden after the 7 and 12 days:
After day 7: [x, x, x, x, _, x, x]
We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
After day 12: [x, x, x, x, x, x, x]
It is obvious that we can make two bouquets in different ways.
 

Constraints:

bloomDay.length == n
1 <= n <= 105
1 <= bloomDay[i] <= 109
1 <= m <= 106
1 <= k <= n

 */
public class _14_mBouquetes {
    public static void main(String[] args) {
        int bloomday[] = { 71, 10, 3, 10, 2 };
        int m = 3, k = 2;
        System.out.println(brute(bloomday, m, k));
    }

    public static int brute(int bloomDay[], int m, int k) {// O(n^2)---passes 84/93 Tc with TLE
        int len = bloomDay.length;

        int ans = Integer.MAX_VALUE;

        for (int noOfDaysToBloom : bloomDay) {
            int nOfDesiredSets = 0;
            boolean isbloomed[] = new boolean[len];// to mark if that flower will bloom after i days
            int nOfBloomed = 0;
            for (int j = 0; j < len; j++) {
                if (noOfDaysToBloom >= bloomDay[j]) {//we have waited for more than the days it need to bloom ,obviosuly it wuld bloom
                    isbloomed[j] = true;
                }
                if (isbloomed[j]) {
                    nOfBloomed++;
                    if (nOfBloomed == k) {
                        nOfDesiredSets++;
                        nOfBloomed = 0;//coz we need pairs, we have got one, make it again 0
                    }
                } else {//discontinuous in bloomed status ie not adjacent
                    nOfBloomed = 0;
                }
            }
            if (nOfDesiredSets >= m) {
                ans = Math.min(ans, noOfDaysToBloom);

            }

        }

        if (ans >= Integer.MAX_VALUE) {
            return (-1);
        } else {
            return (ans);
        }

    }



    //why BS:
    /*
    * coz we know that the anwer would always lie in range minElemnt in bloomDay[] and max elemnt in bloomDay[] and our range is hypothetically sorted too
    * so some how we can improve TC, we will take mid elemnt in our range and check if at that day if m sets can be obtained or not by making a function, if it returns not possible, we need to check for greter no of days ,so move low to mid+1, but if returns possible, we check  for more smaller value by making high as mid-1, always observe that the final ans would point to low, so we return our low
     */

    public static int optimal(int bloomDay[],int m,int k){
        //base case:
        if (((long)(m * k)) >bloomDay.length) return -1;
        //our answer would always lie in range [min,max] in bloomDay[]
        //finding min
        int min=Integer.MAX_VALUE;
        for (int day : bloomDay) {
            min=Math.min(min, day);
        }
        //finding max:
        int max=Integer.MIN_VALUE;
        for (int day : bloomDay) {
            max=Math.max(max, day);
        }
        
        int low=min,high=max;

        while (low<=high) {
            int mid=(low+high)/2;
            if (isThoseNumberOfDaysEnough(mid, bloomDay, m, k)) {//if those numberOf days we can get m sets,
                //lets check for smaller number of days is possible or not
                high=mid-1;
            }else{//its too small no of days to get m pairs, lets wait for more
                low=mid+1;
            }
        }

        return low;//ans would always point to low
    }


    public static boolean isThoseNumberOfDaysEnough(int nThDay,int bloomDay[],int m,int k){
        //we need k flowers in each of m sets
        int nOfBloomed=0;
        int nOfDesiredSets=0;
        for (int day : bloomDay) {
            if (nThDay>=day) {//it has bloomed
                nOfBloomed++;
                if (nOfBloomed==k) {
                    nOfDesiredSets++;
                    nOfBloomed=0;
                }
            }else{
                nOfBloomed=0;
            }
        }

        return nOfDesiredSets>=m;//DONT PUT == instaed of >= it will not pass some TC
    }
}
