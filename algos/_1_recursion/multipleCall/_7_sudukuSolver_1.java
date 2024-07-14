package _1_recursion.multipleCall;
import java.util.HashSet;
import java.util.Set;

public class _7_sudukuSolver_1 {
    

        public static void main(String[] args) {
            // Initialize Sudoku grid (puzzle) as a 1D array
            int[] puzzle = readSudokuGrid();
    
            // Solve Sudoku puzzle
            boolean solved = solveWithSmartAlgorithm(puzzle);
    
            if (solved) {
                System.out.println("Sudoku solved successfully!");
                printSudokuGrid(puzzle);
            } else {
                System.out.println("Could not solve Sudoku puzzle. Please check input.");
            }
        }
    
        public static int[] readSudokuGrid() {
            // Simulating the initial Sudoku grid input (empty cells represented by 0)
            // Example: (0, 0, 3, 0, ...)
            int[] puzzle = {
                0, 0, 3, 0, 2, 0, 6, 0, 0,
                9, 0, 0, 3, 0, 5, 0, 0, 1,
                0, 0, 1, 8, 0, 6, 4, 0, 0,
                0, 0, 8, 1, 0, 2, 9, 0, 0,
                7, 0, 0, 0, 0, 0, 0, 0, 8,
                0, 0, 6, 7, 0, 8, 2, 0, 0,
                0, 0, 2, 6, 0, 9, 5, 0, 0,
                8, 0, 0, 2, 0, 3, 0, 0, 9,
                0, 0, 5, 0, 1, 0, 3, 0, 0
            };
            return puzzle;
        }
    
        public static boolean solveWithSmartAlgorithm(int[] puzzle) {
            int[] emptyCell = findEmptyCellWithMRV(puzzle);//min remaining values
            if (emptyCell == null) {
                return true; // Puzzle solved
            }
    
            int row = emptyCell[0];
            int col = emptyCell[1];
    
            Set<Integer> possibleValues = getPossibleValues(puzzle, row, col);
    
            for (int num : possibleValues) {
                puzzle[row * 9 + col] = num;
                updateSudokuGrid(puzzle);
                try {
                    Thread.sleep(100); // Delay for animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    
                if (solveWithSmartAlgorithm(puzzle)) {
                    return true;
                }
    
                puzzle[row * 9 + col] = 0; // Backtrack
                updateSudokuGrid(puzzle);
                try {
                    Thread.sleep(100); // Delay for animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
            return false; // No valid number found
        }
    
        public static void updateSudokuGrid(int[] puzzle) {
            // Updating the Sudoku grid output after each change
            // In this example, printing the grid to console
            printSudokuGrid(puzzle);
        }
    
        public static int[] findEmptyCellWithMRV(int[] puzzle) {
            int minPossibleValues = 10;
            int[] minCell = null;
    
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (puzzle[row * 9 + col] == 0) {
                        Set<Integer> possibleValues = getPossibleValues(puzzle, row, col);
                        int numPossibleValues = possibleValues.size();
    
                        if (numPossibleValues < minPossibleValues) {
                            minPossibleValues = numPossibleValues;
                            minCell = new int[] {row, col};
                        }
                    }
                }
            }
    
            return minCell;
        }
    
        public static Set<Integer> getPossibleValues(int[] puzzle, int row, int col) {
            Set<Integer> possibleValues = new HashSet<>();
            for (int num = 1; num <= 9; num++) {
                possibleValues.add(num);
            }
    
            // Remove values in the same row and column
            for (int i = 0; i < 9; i++) {
                possibleValues.remove(puzzle[row * 9 + i]);
                possibleValues.remove(puzzle[i * 9 + col]);
            }
    
            // Remove values in the same 3x3 subgrid
            int startRow = (row / 3) * 3;
            int startCol = (col / 3) * 3;
    
            for (int i = startRow; i < startRow + 3; i++) {
                for (int j = startCol; j < startCol + 3; j++) {
                    possibleValues.remove(puzzle[i * 9 + j]);
                }
            }
    
            return possibleValues;
        }
    
        public static void printSudokuGrid(int[] puzzle) {
            // Printing the Sudoku grid (puzzle) to console
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    System.out.print(puzzle[row * 9 + col] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

