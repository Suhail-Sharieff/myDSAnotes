package _2_Arrays;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _25_PascalTriangle {
    /*
     Given an integer numRows, return the first numRows of Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:


 

Example 1:

Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
Example 2:

Input: numRows = 1
Output: [[1]]
 

Constraints:

1 <= numRows <= 30
     */
    public static void main(String[] args) {
        //brute method:
        int n=5;
        List<List<Integer>>li=new ArrayList<>();
        li.add(new ArrayList<>(Arrays.asList(1)));
        for (int i = 0; i < n-1; i++) {
            li.add( brute(li.getLast()));
        }
        System.out.println(li);
    }
    
    public static List<Integer> brute(List<Integer>prev){
        List<Integer>ans=new ArrayList<>();
        if (prev.size()==1) {
            ans.add(1);
            ans.add(1);
            return ans;
        }
        ans.add(1);
        for (int i = 1; i < prev.size(); i++) {
            int curr=prev.get(i);
            int next=prev.get(i-1);
            ans.add(curr+next);
        }
        ans.add(1);
        return ans;
    }
}
