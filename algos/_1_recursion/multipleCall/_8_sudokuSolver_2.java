package _1_recursion.multipleCall;

import java.util.HashSet;
import java.util.Set;

public class _8_sudokuSolver_2 {

    public static void main(String[] args) {
        char[][] board = {//start filling from the place having less possible valus to be filled
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        solveSudoku(board);

        // Print solved Sudoku board
        printSudoku(board);
    }

    public static boolean solveSudoku(char[][] board) {
        // Find the next empty cell with Minimum Remaining Values (MRV)
        int[] cell = findEmptyCellWithMRV(board);
        int row = cell[0];
        int col = cell[1];

        // If there are no empty cells left, puzzle is solved
        if (row == -1 && col == -1) {
            return true;
        }

        // Try each possible value for the empty cell
        for (char num = '1'; num <= '9'; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solveSudoku(board)) {
                    return true; // Found a solution
                }

                board[row][col] = '.'; // Backtrack
            }
        }

        return false; // No solution found
    }

    public static int[] findEmptyCellWithMRV(char[][] board) {
        // Find the cell with the fewest possible values (MRV)
        int minPossibleValues = 10;
        int[] minCell = {-1, -1};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    int numPossibleValues = getPossibleValues(board, i, j).size();

                    if (numPossibleValues < minPossibleValues) {
                        minPossibleValues = numPossibleValues;
                        minCell[0] = i;
                        minCell[1] = j;
                    }
                }
            }
        }

        return minCell;
    }

    public static Set<Character> getPossibleValues(char[][] board, int row, int col) {
        Set<Character> possibleValues = new HashSet<>();
        for (char num = '1'; num <= '9'; num++) {
            possibleValues.add(num);
        }

        // Remove values in the same row and column
        for (int i = 0; i < 9; i++) {
            possibleValues.remove(board[row][i]);
            possibleValues.remove(board[i][col]);
        }

        // Remove values in the same 3x3 subgrid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                possibleValues.remove(board[i][j]);
            }
        }

        return possibleValues;
    }

    public static boolean isValid(char[][] board, int row, int col, char num) {
        // Check if num can be placed at board[row][col] without violating Sudoku rules
        for (int i = 0; i < 9; i++) {
            // Check row and column
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }

            // Check 3x3 subgrid
            int startRow = (row / 3) * 3;
            int startCol = (col / 3) * 3;
            if (board[startRow + i / 3][startCol + i % 3] == num) {
                return false;
            }
        }

        return true;
    }

    public static void printSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
