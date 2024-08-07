package _2_Arrays;

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
    public static void main(String[] args) {//N ranges from 1 to infinity
        int numRows = 5; // You can change this to test other values
        List<List<Integer>> pascalTriangle = generatePascalTriangleBrute(numRows);
        System.out.println(pascalTriangle);
        System.out.println(generatePascalOptimal(numRows));
        //to find nth pascal list:
        System.out.println(bruteNth(pascalTriangle, 4));
        System.out.println(optimalNth(4));
        //to find number in ith row& jth column of that row in pascalList
        System.out.println(bruteAt_i_j(pascalTriangle, 5, 3));
        System.out.println(optimal_i_j(5, 3));

       
        
    }
    
    public static List<List<Integer>> generatePascalTriangleBrute(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (numRows <= 0) {
            return result;
        }
        
        result.add(new ArrayList<>(Arrays.asList(1))); // First row
        
        for (int i = 1; i < numRows; i++) {
            List<Integer> previousRow = result.get(result.size() - 1);
            List<Integer> currentRow = new ArrayList<>();
            
            currentRow.add(1); // First element of each row is always 1
            
            for (int j = 1; j < previousRow.size(); j++) {
                int value = previousRow.get(j - 1) + previousRow.get(j);
                currentRow.add(value);
            }
            
            currentRow.add(1); // Last element of each row is always 1
            
            result.add(currentRow);
        }
        
        return result;
    }
    public static List<List<Integer>> generatePascalOptimal(int numRows){//O(n2)
        List<List<Integer>>ans=new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            ans.add(optimalNth(i));
        }
        return ans;
    }
    //IMP:
    // Given an integer 'n', return the nth list in pascal's triangle list
    public static List<Integer> bruteNth(List<List<Integer>>fullPascal,int nth){
        return fullPascal.get(nth-1);
    }
    public static List<Integer> optimalNth(int row){//VVVIMP  o(n)---o(1)
        //MUST WATCH TO UNDESRATND:(from 17:00)
        //https://www.youtube.com/watch?v=bR7mQgwQ_o8&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=34
        
        List<Integer>li=new ArrayList<>();
        li.add(1);//first will always be 1 for every row
        //observe that the 1th elemnt is always = rowNumber (0 based indexing)
        //user provides in 1 based and we assume in 0 based,then 1th elemnt ==(rowNumber-1),so
        //the number of elents in ith row===i
        int ans=1;
        for (int col = 1; col < row; col++) {//row -3 in which we have added 1 on first ad last
            ans=(ans*(row-col));    
            ans=ans/col;    
            li.add(ans);        
        }
        return li;


    }
    //given row & col nomber of pascal triangle,return number at that pos
    public static int bruteAt_i_j(List<List<Integer>>fullPascal,int row,int col){
        return fullPascal.get(row-1).get(col-1);
    }
    public static long optimal_i_j(int row,int col){//O(n)----o(1)
        //the solution is to compute iCj (nCr) will always give the correct ans,but calculationg i!,j! then (i-j)! will take a lot of time complexity
        //so we observe that for ex: 10C3, its 10*9*8/(3*2*1) after cancelling 7! from Nr&Dr,observe that in every case, in such type of simplifies exp, the numbr of terms of Nr==Dr==r,so we can use loop there:
        //for 10C3 => ((10-0)/((3-0)))+((10-1)/(3-1))+((10-2)/(3-2))
        //for iCj => ((i-0)/((j-0)))+((i-1)/(j-1))+((i-2)/(j-2))...where j runs from 0 to r-1 ie <r
        //rowCcol
        //but thers one problem in this method,supoose u get (9/2),computer will take 4 ,,which's wrong
        //so better to take a product/multiplication based approach rather than sum based ie:
        // 10C3=>((10-0)/(0+1)) ie 10--->(10*(10-1)/(1+1)) ie 45--->45*(10-2)/(2+1) ie 120 the ans,here the above mentioned problem is solved complteley
        long ans=1;
        row--;col--;//coz user will pass using 1 based indexing
        for (int i = 0; i < col; i++) {
            ans=ans*(row-i);
            ans=ans/(i+1);
        }
        return ans;
    }

}
