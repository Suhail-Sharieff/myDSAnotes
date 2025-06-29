package _2_Arrays;


/*
A celebrity is a person who is known to all but does not know anyone at a party. A party is being organized by some people. A square matrix mat[][] (n*n) is used to represent people at the party such that if an element of row i and column j is set to 1 it means ith person knows jth person. You need to return the index of the celebrity in the party, if the celebrity does not exist, return -1.

Note: Follow 0-based indexing.

Examples:

Input: mat[][] = [[1, 1, 0], [0, 1, 0], [0, 1, 1]]
Output: 1
Explanation: 0th and 2nd person both know 1st person. Therefore, 1 is the celebrity person. 
Input: mat[][] = [[1, 1], [1, 1]]
Output: -1
Explanation: Since both the people at the party know each other. Hence none of them is a celebrity person.
Input: mat[][] = [[1]]
Output: 0
Constraints:
1 <= mat.size()<= 1000
0 <= mat[i][j]<= 1
mat[i][i] == 1
 */

 public class _35_celebrity_problem {


    //----------brute force O(n^2)
    public int celebrity(int mat[][]) {
        // code here
        for(int i=0;i<mat.length;i++){
            int cnt=0;
            //check if everyone knows ith person
            for(int j=0;j<mat[0].length;j++) if(mat[j][i]==1) cnt++;
            if(cnt==mat.length){//so everyone knows ith person, if he is celebrity, then he should know only himself and no one else, lets check
                cnt=0;
                for(int j=0;j<mat.length;j++) if(mat[i][j]==1) cnt++;
                if(cnt==1) return i;//everyone knows him and he know only himself
            }
        }
        return -1;
        
    }


    //------------optimal:https://www.youtube.com/watch?v=cEadsbTeze4&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=313&ab_channel=takeUforward
    public int optimal(int mat[][]){
            int nPpl=mat.length;
             int x=0,y=nPpl-1;
             while (x<y) {
                 boolean x_knows_y=(mat[x][y]==1);
                 boolean y_knows_x=(mat[y][x]==1);
                 if (x_knows_y) {
                     //y probably a celebrity
                     x++;
                 }else if(y_knows_x){
                     //x doent know y, but y knows x, so x probably a celebrity
                     y--;
                 }else{
                     //x doent know y and y doent know x, both cant be a celebrity
                     x++;
                     y--;
                 }
             }
             //now x=y
             for(int i=0;i<nPpl;i++){
                 if (i == x) continue;
                 if(mat[x][i]==0 && mat[i][x]==1) continue;//x knows none and others know x
                 else return -1;
             }
             return x;
             
    }
}
