package _3_binarySearch;

import java.util.ArrayList;
import java.util.List;

//this is similar to "Allocate Book " problem
/*
Problem statement
Given an array/list of length ‘n’, where the array/list represents the boards and each element of the given array/list represents the length of each board. Some ‘k’ numbers of painters are available to paint these boards. Consider that each unit of a board takes 1 unit of time to paint.



You are supposed to return the area of the minimum time to get this job done of painting all the ‘n’ boards under a constraint that any painter will only paint the continuous sections of boards.



Example :
Input: arr = [2, 1, 5, 6, 2, 3], k = 2

Output: 11

Explanation:
First painter can paint boards 1 to 3 in 8 units of time and the second painter can paint boards 4-6 in 11 units of time. Thus both painters will paint all the boards in max(8,11) = 11 units of time. It can be shown that all the boards can't be painted in less than 11 units of time.


Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
4 2
10 20 30 40


Sample Output 1 :
60


Explanation For Sample Input 1 :
In this test case, we can divide the first 3 boards for one painter and the last board for the second painter.


Sample Input 2 :
2 2
48 90


Sample Output 2 :
90


Expected Time Complexity:
Try to do this in O(n*log(n)).


Constraints :
1 <= n <= 10^5
1 <= k <= n
1 <= arr[i] <= 10^9

Time Limit: 1 sec.
 */




 //WATCH GOOD EXP:https://www.youtube.com/watch?v=thUd_WJn6wk&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=63
public class _19_painter {
    //indirectly we have to distribute the contigous work to both painters such that our work gets completed in min time
    public static int optimal(ArrayList<Integer> boards, int k)
    {
        int max=0;int sum=0;
        for (int e : boards) {
            max=Math.max(max, e);
            sum+=e;
        }
        int low=max,high=sum;
        while(low<=high){
            int mid=(low+high)/2;
            if(func(boards, mid)<=k){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }


    public static int func(List<Integer>li,int a){
        int sum=0;
        int cnt=1;//VIMP NOT 0
        for(int e:li){
            if(e+sum<=a){
                sum+=e;
            }else{
                cnt++;
                sum=e;
            }
        }
        return cnt;
    }
}
