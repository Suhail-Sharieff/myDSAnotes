package _3_binarySearch;

public class _11_findSqrt {
    //given an integer x,eturn the square root of x rounded down to the nearest integer  in O(logn) TC
    public static void main(String[] args) {
        System.out.println(brute(26));
        System.out.println(optimal(49));
    }
    public static int brute(int x){
        int ans=1;
        for (int i = 1; i <=x; i++) {
            if (i*i<=x) {
                ans=i;
            }else{
                break;
            }
        }
        return ans;
    }

    public static int optimal(int x){
        //why use BS, coz we know that our ans may lie in some fixed range of numbers from 1 to k, and remaining can be eleiminated
        long low=1,high=x;//COMPULSORY USE LONG< OTHERWISE GET TLE
        int ans=0;
        while (low<=high) {
            long mid=(low+high)/2;
            if ((long)(mid*mid)<=x) {
                low=mid+1;
                ans=(int)mid;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }
}
