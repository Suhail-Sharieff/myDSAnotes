package _1_recursion.multipleCall;

import java.util.List;

//STRIVER
//https://www.youtube.com/watch?v=wuVwUK25Rfc&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=18
//ALSO WATCH
//https://www.youtube.com/watch?v=miCYGGrTwFU&t=236s
public class _9_graphColoring {

    // Given an undirected graph and an integer M. The task is to determine if the
    // graph can be colored with at most M colors such that no two adjacent vertices
    // of the graph are colored with the same color. Here coloring of a graph means
    // the assignment of colors to all vertices. Print 1 if it is possible to colour
    // vertices and 0 otherwise.

    // Example 1:

    // Input:
    // N = 4
    // M = 3
    // E = 5
    // Edges[] = {(0,1),(1,2),(2,3),(3,0),(0,2)}
    // Output: 1
    // Explanation: It is possible to colour the
    // given graph using 3 colours.
    public static void main(String[] args) {
        // Example usage:
        int N = 4; // Number of vertices
        int M = 3; // Maximum number of colors
        boolean edges[][] = new boolean[N][N];
        edges[0][1] = true;edges[1][0] = true;
        edges[1][2] = true;edges[2][1] = true;
        edges[2][3] = true; edges[3][2] = true;
        edges[3][0] = true;edges[0][3] = true;
        edges[0][2] = true;edges[2][0] = true;
        

        int[] colors = new int[N]; // Array to store colors assigned to vertices
        if (func(edges, M, N, colors, 0)) {
            System.out.println("Graph can be colored with at most " + M + " colors.");
        } else {
            System.out.println("Graph cannot be colored with at most " + M + " colors.");
        }
    }

    boolean graphColoring(int v, List<int[]> edges, int m) {
        // code here
        boolean hasE[][]= new boolean[v][v];
        
        for(int e[]:edges){
            hasE[e[0]][e[1]]=true;
            hasE[e[1]][e[0]]=true;
        }
        int colors[]=new int[v];
        return func(hasE,m,v,colors,0);
        
    }

    public static void printColorAssignment(int[] colors) {
        for (int color : colors) {
            System.out.print(color + " ");
        }
        System.out.println();
    }

    public static boolean func(boolean[][] edges, int maxNoOfColors, int nOfNodes, int[] colors, int nodeIndex) {
        if (nodeIndex == nOfNodes) {
            printColorAssignment(colors);
            return true; // Assigned color to all vertices
        }

        // Try all colors from 1 to maxNoOfColors
        for (int colorAssigned = 1; colorAssigned <= maxNoOfColors; colorAssigned++) {
            if (isSafe(edges, colors, colorAssigned, nodeIndex, nOfNodes)) {
                // Assign color
                colors[nodeIndex] = colorAssigned;

                // Recur to assign colors to rest of the vertices
                if (func(edges, maxNoOfColors, nOfNodes, colors, nodeIndex + 1)) {
                    return true; // If assignment is successful
                }

                // Backtrack: Remove color assignment
                colors[nodeIndex] = 0;
            }
        }

        return false; // No valid assignment of colors
    }

    public static boolean isSafe(boolean[][] edges, int[] colors, int colorAssigned, int nodeIndex, int nOfNodes) {
        for (int i = 0; i < nOfNodes; i++) {
            // Check if there is an edge and adjacent vertex has same color
            if (edges[nodeIndex][i] && colorAssigned == colors[i]) {
                return false;
            }
        }
        return true; // Safe assignment
    }
}
