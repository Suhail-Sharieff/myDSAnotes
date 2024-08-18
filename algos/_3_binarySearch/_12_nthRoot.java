package _3_binarySearch;
/*
You are given two positive integers 'n' and 'm'. You have to return the 'nth' root of 'm', i.e. 'm^(1/n)'. If the 'nth root is not an integer, return -1.



Note:
'nth' root of an integer 'm' is a number, which, when raised to the power 'n', gives 'm' as a result.


Example:
Input: ‘n’ = 3, ‘m’ = 27

Output: 3

Explanation: 
3rd Root of 27 is 3, as (3)^3 equals 27.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
3 27


Sample Output 1:
3


Explanation For Sample Input 1:
3rd Root of 27 is 3, as (3)^3 equals 27.


Sample Input 2:
4 69


Sample Output 2:
-1


Explanation For Sample Input 2:
4th Root of 69 is not an integer, hence -1.


Expected Time Complexity:
Try to do this in O(log(n+m)).


Constraints:
1 <= n <= 30
1 <= m <= 10^9

Time Limit: 1 sec.

 */
public class _12_nthRoot {
    public static void main(String[] args) {
        
    }
    public static int brute(int n,int m){//O(sqrt(m)*logn)---runnig till sqrt(m) and Math.pow takes logn time
        int ans=-1;
        for (int i = 1; i*i <=m; i++) {
            if(Math.pow(i, n)==m){
                ans=i;
                break;
            }
        }
        return ans;
    }
    public static int optimal(int n,int m){
        int low=1,high=m;
        int ans=-1;
        while (low<=high) {
            int mid=(low+high)/2;
            if (Math.pow(mid, n)==m) {
                ans=mid;
                break;
            }else if(Math.pow(mid, n)<m){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }
}
