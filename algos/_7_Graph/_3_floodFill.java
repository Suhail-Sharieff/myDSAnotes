package _7_Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*

You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

 

Example 1:

Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2

Output: [[2,2,2],[2,2,0],[2,0,1]]

Explanation:



From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.

Note the bottom corner is not colored 2, because it is not horizontally or vertically connected to the starting pixel.

Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0

Output: [[0,0,0],[0,0,0]]

Explanation:

The starting pixel is already colored with 0, which is the same as the target color. Therefore, no changes are made to the image.

 

Constraints:

m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], color < 216
0 <= sr < m
0 <= sc < n
 */
public class _3_floodFill {

    //prereq: learn how we can spread from (i,j) to all dirs from _2_count_islands

    public static void main(String[] args) {
        int image[][]={
            {1,1,1},
            {1,1,0},
            {1,0,1},
        };
        int sr=1,sc=1;
        int color=2;

        for (int[] e : floodFill(image, sr, sc, color)) {
            System.out.println(Arrays.toString(e));
            
        }
    }


    static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        if(image[sr][sc]==color) return image;//IMP to handle:image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
        Queue<int[]>q=new LinkedList<>();
        q.offer(new int[]{sr,sc});
        int nRows=image.length,nCols=image[0].length;
        //IMP: in count islands we had to trverse in all 8 dirs, but here given hori or verti, so i have made all possible 4 dirs ie (left:x-1,y+0,...like that)
        int dirs[][]={{-1,0},{1,0},{0,1},{0,-1}};
        //color (sr,sc) first
        int origColor=image[sr][sc];
        image[sr][sc]=color;
        while(!q.isEmpty()){
            int top[]=q.poll();
            int top_x=top[0];
            int top_y=top[1];
            
            for(int [] d : dirs){
                int x=top_x+d[0];
                int y=top_y+d[1];
                if(x>=0 && y>=0 && x<nRows && y<nCols && image[x][y]==origColor){
                image[x][y]=color;
                q.offer(new int[]{x,y});
                }
            }

        }

        return image;
    }
}
