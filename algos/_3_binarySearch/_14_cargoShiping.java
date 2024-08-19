package _3_binarySearch;

/*
A conveyor belt has packages that must be shipped from one port to another within days days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

 

Example 1:

Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
Example 2:

Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4
Example 3:

Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
 

Constraints:

1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
 */

public class _14_cargoShiping {
    public static void main(String[] args) {
        int weights[]={1,2,3,4,5,6,7,8,9,10};
        int days=5;
        System.out.println(optimal(weights, days));
        
    }
    public static int optimal(int weights[],int days) {//O(nlog(m))
        //the answer would always lie in range [maxWeight,sumOfAllweights]....lower range is maxWeight coz if we take lower value than that we couldnt lift it when its it's turn.....and higher range is sumOfAllweights, ie transport all weights in a single day,more than that its not possible
        //find max
        int maxWeight=Integer.MIN_VALUE;
        for (int i : weights) {
            maxWeight=Math.max(maxWeight, i);
        }
        int totalWeight=0;
        for (int i : weights) {
            totalWeight+=i;
        }
        //MISTAKE: made low=minElemnt in weights, which is wrong, the least possible ans must be such that it can even lift maxWeight, or else how can u transport that weight if its less than that
        int low=maxWeight,high=totalWeight;
        while (low<=high) {
            int mid=(low+high)/2;
            int requiredNoOfDays=noOfDaysForThatMaxWeight(weights, mid);
           if (requiredNoOfDays<=days) {
                high=mid-1;
            }else{
                low=mid+1;
            }
            System.out.println("for "+mid+" kg i need "+requiredNoOfDays+" days");
        }
        return(low);//ans would always stop at low
       

    }
    public static int noOfDaysForThatMaxWeight(int weights[],int maxWeight){
        int noOfSet=0;
        long sum=0;
        for (int i = 0; i < weights.length; i++) {
            sum+=weights[i];
            if (sum>maxWeight) {
                noOfSet++;
                sum=weights[i];///IMP MOST CONFUSING PART, DO DRY RUN
            }

        }
        if (sum!=0) {//if some weight is left at last
            noOfSet++;
        }
        return(noOfSet);
    }
}
