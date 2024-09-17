package _4_Trees;

import java.util.LinkedList;
import java.util.Queue;

// import java.util.*;


public class _10_verticalTraversal {
     // Function to get the height of the tree
   // Function to get the height of the tree
    public static int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // Function to fill the matrix with tree values
    public static void fillMatrix(TreeNode root, int[][] matrix) {
        if (root == null) return;
        
        int height = matrix.length;
        int width = matrix[0].length;
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(root, 0, (width - 1) / 2));
        
        while (!queue.isEmpty()) {
            Position pos = queue.poll();
            TreeNode node = pos.node;
            int row = pos.row;
            int col = pos.col;
            
            if (node != null) {
                matrix[row][col] = node.val;
                
                // Enqueue left child
                if (node.left != null) {
                    queue.add(new Position(node.left, row + 1, col - (1 << (height - row - 2))));
                }
                
                // Enqueue right child
                if (node.right != null) {
                    queue.add(new Position(node.right, row + 1, col + (1 << (height - row - 2))));
                }
            }
        }
    }

    // Position class to keep track of node and its position in the matrix
    static class Position {
        TreeNode node;
        int row;
        int col;
        
        Position(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }
    
    // Function to convert the tree to 2D matrix
    public static int[][] treeToMatrix(TreeNode root) {
        int height = getHeight(root);
        int width = (1 << height) - 1; // 2^height - 1
        int[][] matrix = new int[height][width];
        
        // Initialize matrix with -1
        for (int[] row : matrix) {
            java.util.Arrays.fill(row, -1);
        }

        fillMatrix(root, matrix);
        return matrix;
    }

    // Function to print the matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        // Example Usage:
        // Construct a sample binary tree
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int[][] matrix = treeToMatrix(root);
        printMatrix(matrix);
    }
}
