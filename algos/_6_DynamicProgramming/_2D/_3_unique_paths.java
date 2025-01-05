package _6_DynamicProgramming._2D;
/*
You are present at point ‘A’ which is the top-left cell of an M X N matrix, your destination is point ‘B’, which is the bottom-right cell of the same matrix. Your task is to find the total number of unique paths from point ‘A’ to point ‘B’.In other words, you will be given the dimensions of the matrix as integers ‘M’ and ‘N’, your task is to find the total number of unique paths from the cell MATRIX[0][0] to MATRIX['M' - 1]['N' - 1].

To traverse in the matrix, you can either move Right or Down at each step. For example in a given point MATRIX[i] [j], you can move to either MATRIX[i + 1][j] or MATRIX[i][j + 1].

Detailed explanation ( Input/output format, Notes, Images )
Constraints:
1 ≤ T ≤ 100
1 ≤ M ≤ 15
1 ≤ N ≤ 15

Where ‘M’ is the number of rows and ‘N’ is the number of columns in the matrix. 

Time limit: 1 sec
Sample Input 1:
2
2 2
1 1
Sample Output 1:
2
1
Explanation of Sample Output 1:
In test case 1, we are given a 2 x 2 matrix, to move from matrix[0][0] to matrix[1][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1)
Path 2 = (0, 0) -> (1, 0) -> (1, 1)

Hence a total of 2 paths are available, so the output is 2.

In test case 2, we are given a 1 x 1 matrix, hence we just have a single cell which is both the starting and ending point. Hence the output is 1.
Sample Input 2:
2
3 2
1 6
Sample Output 2:
3
1
Explanation of Sample Output 2:
In test case 1, we are given a 3 x 2 matrix, to move from matrix[0][0] to matrix[2][1] we have the following possible paths.

Path 1 = (0, 0) -> (0, 1) -> (1, 1) -> (2, 1)
Path 2 = (0, 0) -> (1, 0) -> (2, 0) -> (2, 1)
Path 3 =  (0, 0) -> (1, 0) -> (1, 1) -> (2, 1)

Hence a total of 3 paths are available, so the output is 3.

In test case 2, we are given a 1 x 6 matrix, hence we just have a single row to traverse and thus total path will be 1.
 */
public class _3_unique_paths {

    public static void main(String[] args) {
        int mat[][]={
            {0,0},
            {0,0},
            {0,0},
        };
        boolean isVis[][]=new boolean[mat.length][mat[0].length];
        int ans[]={0};
        recursion(mat, 0, 0, new StringBuilder(),isVis,ans);

    }

    //-----------------recursive solution is as same as that of rat in maze problem, but here we just need to move down or tight as mentioned in the question,if u want to know paths its foloowing to reach end point for fun u can use this
    //call like:
    /*
     int mat[][]={
            {0,0},
            {0,0},
            {0,0},
        };
        boolean isVis[][]=new boolean[mat.length][mat[0].length];
        int ans[]={0};
        recursion(mat, 0, 0, new StringBuilder(),isVis,ans);
     */
    public static void recursion(int mat[][], int i, int j, StringBuilder sb, boolean isVis[][], int ans[]) {
        int nRows = mat.length, nCols = mat[0].length;
        if (i == mat.length - 1 && j == mat[0].length - 1) {
            System.out.println("(0,0)->"+sb);
            ans[0]++;
        }
        isVis[i][j] = true;
        int dirs[][] = { { 1, 0 }, { 0, 1 } };
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < nRows && y < nCols && !isVis[x][y]) {
                recursion(mat, x, y, sb.append("("+x+","+y+")->"), isVis, ans);
            }
        }
        isVis[i][j] = false;
    }


    //
}