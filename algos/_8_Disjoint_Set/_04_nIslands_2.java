package _8_Disjoint_Set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given a n,m which means the row and column of the 2D matrix and an array of  size k denoting the number of operations. Matrix elements is 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) and each operator has two integer A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. Return how many island are there in the matrix after each operation.You need to return an array of size k.
Note : An island means group of 1s such that they share a common side.

 

Example 1:

Input: n = 4
m = 5
k = 4
A = {{1,1},{0,1},{3,3},{3,4}}

Output: 1 1 2 2
Explanation:
0.  00000
    00000
    00000
    00000
1.  00000
    01000
    00000
    00000
2.  01000
    01000
    00000
    00000
3.  01000
    01000
    00000
    00010
4.  01000
    01000
    00000
    00011
 

 

Example 2:

Input: n = 4
m = 5
k = 4
A = {{0,0},{1,1},{2,2},{3,3}}

Output: 1 2 3 4
Explanation:
0.  00000
    00000
    00000
    00000
1.  10000
    00000
    00000
    00000
2.  10000
    01000
    00000
    00000
3.  10000
    01000
    00100
    00000
4.  10000
    01000
    00100
    00010
 

Your Task:
You don't need to read or print anything. Your task is to complete the function numOfIslands() which takes an integer n denoting no. of nRows in the matrix, an integer m denoting the number of columns in the matrix and a 2D array of size k denoting  the number of operators.

Expected Time Complexity: O(m * n)
Expected Auxiliary Space: O(m * n)

Constraints:

1 <= n,m <= 100
1 <= k <= 1000
 */
public class _04_nIslands_2 {

    public static void main(String[] args) {
        int queries[][]={
            {1,1},
            {0,1},
            {3,3},
            {3,4}
        };
        int nRows=4,nCols=5;
        System.out.println(brute_force(nRows, nCols, queries));
        System.out.println(optimal_using_DSU(nRows, nCols, queries));
    }

    // -----------------brute force
    static List<Integer> brute_force(int nRows, int nCols, int[][] queries) {
        // Your code here
        int mat[][] = new int[nRows][nCols];
        List<Integer> ans = new ArrayList<>();
        for (int q[] : queries) {
            mat[q[0]][q[1]] = 1;
            ans.add(nIslands(mat, nRows, nCols));
        }
        return ans;
    }

    static int nIslands(int mat[][], int nRows, int nCols) {
        boolean isVis[][] = new boolean[nRows][nCols];
        int cnt = 0;
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                if (mat[i][j] == 1 && !isVis[i][j]) {
                    cnt++;
                    bfs(mat, isVis, i, j);
                }
            }
        }
        return cnt;
    }

    static void bfs(int mat[][], boolean isVis[][], int i, int j) {
        if (!isVis[i][j]) {
            isVis[i][j] = true;
            int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
            for (int dir[] : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length) {
                    if (mat[x][y] == 1) {
                        bfs(mat, isVis, x, y);
                    }
                }

            }
        }
    }



    //--------------------------optimal appraoch:https://www.youtube.com/watch?v=Rn6B-Q4SNyA&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=51&t=3s&ab_channel=takeUforward
    //idea is that we treat each cell as the index 0,1,2,3....m*n. Whenever a query is cllled, first verify if already land is ther or not, if land is not there cnt++ for every query(we assume that it will be separate component, later we will adjust it ), form a land there, go in 4 dirs of that query cell, check if any neighbour is land and has dismillar parent of itslef, if there it doent have same ultimate parent, then they r to be connected, connect them using join function, decreemnt the cnt, same then for that query curr cnt is ans.
    static List<Integer> optimal_using_DSU(int nRows,int nCols,int [][]queries){
        int mat[][]=new int[nRows][nCols];
        int cnt=0;
        int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
        DSU dsu=new DSU(nRows*nCols);
        int ans[]=new int[queries.length];
        int i=0;
        for(int q[]:queries){
            if(mat[q[0]][q[1]]==1){
                ans[i++]=cnt;
                continue;
            }
            //make land there
            mat[q[0]][q[1]]=1;
            int currCellNumber=q[0]*nCols+q[1];//MISTAKE: wrote nRows in place of nCols
            cnt++;
            for(int dir[]:dirs){
                int x=q[0]+dir[0];
                int y=q[1]+dir[1];
                if(x>=0 && y>=0 && x<nRows && y<nCols){

                    int neighBourCellNumber=x*nCols+y;////MISTAKE: wrote nRows in place of nCols

                    if(mat[x][y]==1){
                        if(dsu.get_parent_of_with_path_compression(currCellNumber)!=dsu.get_parent_of_with_path_compression(neighBourCellNumber)){
                            dsu.join(currCellNumber, neighBourCellNumber);
                            cnt--;
                        }
                    }
                }
            }
            ans[i++]=cnt;
        }
        // System.out.println(Arrays.toString(ans));
        return Arrays.stream(ans).boxed().toList();
    }
}
