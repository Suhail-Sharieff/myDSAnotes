package _3_binarySearch;

import java.util.Arrays;


/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

 

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23


EXPLAINATION OF TC 1:
piles = [3,6,7,11], h = 8
k = 1 to 11 bananas per hours

k = 1,
3/1 + 6/1 + 7/1 + 11/1 = 3 + 6 + 7 + 11 = 27hrs > 8 hrs = Not enough time

k = 2,
3/2 + 6/2 + 7/2 + 11/2 = 2 + 3 + 4 + 6 = 15 hrs > 8 hrs = Not enough time

k = 3,
3/3 + 6/3 + 7/3 + 11/3 = 1+ 2+ 3 + 4 = 10hrs > 8 hrs = Not enough time

k = 4,
3/4 + 6/4 + 7/4 + 11/4 = 1 + 2 + 2 + 3 = 8hrs = GOOD

k = 5,
3/5 + 6/5 + 7/5 + 11/5 = 1 + 2 + 2 + 3 = 8hrs = GOOD, but not the minimum k

k = 6,
3/6 + 6/6 + 7/6 + 11/6 = 1 + 1 + 2 + 2 = 4hrs = GOOD, but not the minimum k
.
.
.
So the answer is k=4 .
 */
public class _13_kokoBananas {
    public static void main(String[] args) {
        
    }

    public static long timeTakenForThatRate(int rate,int piles[]){
        long timeTaken=0;
            for(int e:piles){
                //if e>rate then time=e/rate, if(e%rate!=0), time++
                if(e>=rate){
                    timeTaken+=e/rate;
                    if(e%rate!=0){
                        timeTaken++;
                    }
                }else{
                    timeTaken++;
                }
            }
            return timeTaken;
    }
    public int brute(int[] piles, int h) {//gives TLE ,passes 5/126 TC
        
        Arrays.sort(piles);//coz ans may be till max number in piles[]
        int len=piles.length;
        int max=piles[len-1];
        int ans=Integer.MAX_VALUE;
        for(int rate=1;rate<=max;rate++){
            long timeTaken=timeTakenForThatRate(rate, piles);
            // for(int e:piles){
            //     if(timeTaken>h){//then y need to uneccary calculate when we know that this cant be answer
            //         break;
            //     }
            //     //if e>rate then time=e/rate, if(e%rate!=0), time++
            //     if(e>=rate){
            //         timeTaken+=e/rate;
            //         if(e%rate!=0){
            //             timeTaken++;
            //         }
            //     }else{
            //         timeTaken++;
            //     }
            // }
            // System.out.println(timeTaken);
            if(timeTaken>h){
                //that i cant be correct rate
            }else{
                ans=Math.min(ans,rate);
            }
        }

        return ans;
    }



    //now how to solve it is, Binary Search: WHyBinary search?
    /*
     * coz we know exacly the range in which our ans will lie, that ie [1,maxNumberInpiles[]]
     * also we can some how figure this piles array into the portion we know exacly where the ans will not lie and where the ans would lie
     */
    public static int optimal(int piles[],int h){//O(n*log(max))
        //find out maxElemnt in piles..can do by sorting but nlogn<n,so
        int max=Integer.MIN_VALUE;
        for (int e : piles) {
            max=Math.max(max, e);
        }
        int low=1,high=max;//our ans always lies between 1 and max
        while (low<=high) {//until high crosses low in the hypothetical array [1,2,3,4,5..............max], see that this array is sorted, thus our BS works
            int mid=(low+high)/2;
            long timeTaken=timeTakenForThatRate(mid, piles);
            if (timeTaken<=h) {//we got a possible answer, all the values after this are also possible, but we need min value, so we check if another value less than this exists in our hypothetical array by moving high to mid-1;
                high=mid-1;
            }else{//the time taken for this mid is more than limit 'h', so we check for higher rates, ie move low to mid+1
                low=mid+1;
            }
        }
        //IMP PART: observe that our answer is always stopping at low  and also observe that initially low was pointing at not possible value and at last it became viceversa...also high was pointing at possible answer, and at last it would point to not possible ans...ie polarity has reversed, so we return low
        return low;
    }
}
