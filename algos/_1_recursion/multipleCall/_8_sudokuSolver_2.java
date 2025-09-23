package _1_recursion.multipleCall;

public class _8_sudokuSolver_2 {

    public static void main(String[] args) {
        char[][] board = { // start filling from the place having less possible valus to be filled
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.', '.', '.', '.', '.' }
        };

        solveSudoku(board);

        // Print solved Sudoku board
        printSudoku(board);

    }

    public static boolean solveSudoku(char[][] board) {// o((9!)^9)
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char j2 = '1'; j2 <= '9'; j2++) {
                        if (isSafe(j2, i, j, board)) {
                            board[i][j] = j2;
                            if (solveSudoku(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean isSafe(char val, int r, int c, char board[][]) {
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == val) {
                return false;
            }
            if (board[r][i] == val) {
                return false;
            }
            if (board[(r / 3) * 3 + (i / 3)][(c / 3) * 3 + (i % 3)] == c) {
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
