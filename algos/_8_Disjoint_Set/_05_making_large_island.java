package _8_Disjoint_Set;
//https://www.youtube.com/watch?v=lgiz0Oup6gM&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=52&ab_channel=takeUforward
import java.util.HashSet;
import java.util.Set;

/*
 You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.

 

Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
 */
public class _05_making_large_island {


    //-----------------brute force, check what is th max area u can get by changing any one 0 in entire matrix
    public int brute_force(int[][] grid) {//----------TLE
        int ans = size(grid, grid.length, grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    grid[i][j] = 1;
                    ans = Math.max(ans, size(grid, grid.length, grid[0].length));
                    grid[i][j] = 0;
                }
            }
        }
        return ans;
    }

    public int size(int mat[][], int rows, int cols) {
        boolean isVis[][] = new boolean[rows][cols];
        int size = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 1 && !isVis[i][j]) {
                    size = Math.max(bfs(mat, isVis, i, j), size);
                }
            }
        }
        return size;
    }

    public int bfs(int mat[][], boolean isVis[][], int i, int j) {
        int area=1;
        if (!isVis[i][j]) {
            isVis[i][j] = true;
            int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
            for (int dir[] : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && y >= 0 && x < mat.length && y < mat[0].length) {
                    if (!isVis[x][y] && mat[x][y] == 1) {
                        area+= bfs(mat, isVis, x, y);//MISTAKE: wrote return 1+bfs(..), it would return area with only 1 neighbour then, wbut since we need total area wih all neighbours this is mandatory
                    }
                }

            }
        }
        return area;
    }



    //---------------------------------optimal using DSU
     int parent[];
    int size[];

    public int optimal_using_DSU(int[][] grid) {
        int len = grid.length;
        initialize(grid);
        markSizes(grid);
        int ans = get_maxSize();
        int dirs[][] = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int area = 1;
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && x < len && y >= 0 && y < len && grid[x][y] == 1) {
                            int root = find(x * len + y);
                            if (!seen.contains(root)) {
                                seen.add(root);
                                area += size[root];
                            }
                        }
                    }
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;

    }

    public void initialize(int grid[][]) {
        int len = grid.length;
        parent = new int[len * len];
        size = new int[len * len];
        for (int i = 0; i < len * len; i++)
            parent[i] = i;
        for (int i = 0; i < len * len; i++)
            size[i] = 1;
    }

    public void markSizes(int grid[][]){
    int dirs[][] = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[0].length; j++){
            if(grid[i][j] == 1){
                int currCellNum = i * grid[0].length + j;
                for(int dir[] : dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if(x >= 0 && y >= 0 && x < grid.length && y < grid[0].length){
                        if( grid[x][y] == 1){
                            int neighCellNumber = x * grid[0].length + y;
                            join(currCellNum, neighCellNumber);
                        }
                    }
                }
            }
        }
    }
}


    public int get_maxSize() {
        int max = 0;
        for (int i = 0; i < parent.length; i++) {
            if (find(i) == i) { // Only check roots
                max = Math.max(max, size[i]);
            }
        }
        return max;
    }

    public int find(int u) {
        if (parent[u] == u)
            return u;
        parent[u] = find(parent[u]);
        return parent[u];
    }

    public void join(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);
        if (rootU == rootV) return;
        if (size[rootU] >= size[rootV]) {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        } else {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        }
    }

}
