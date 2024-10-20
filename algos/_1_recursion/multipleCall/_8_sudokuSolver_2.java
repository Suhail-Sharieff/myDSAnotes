package _1_recursion.multipleCall;

import java.util.*;

public class _8_sudokuSolver_2 {

    public static void main(String[] args) {
        char[][] board = {//start filling from the place having less possible valus to be filled
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'}
        };

        solveSudoku(board,1,0);

        // Print solved Sudoku board
        // printSudoku(board);

    }

    public static void solveSudoku(char[][] board,int val,int row) {
       if (row>=9) {
        printSudoku(board);
        return;
       }
       for (int i = 0; i < 9; i++) {
        if (board[row][i]=='.') {
            if (isSafe(val, row, i, board)) {
                board[row][i]=(char)(val+'0');
                solveSudoku(board,val+1, row+1);
                board[row][i]='.';
            }
        }
        
       }
    }   



    public static boolean isSafe(int val,int r,int c,char[][] passed) {

        char [][]board=new char[9][9];
        for(int i=0;i<0;i++){
            for (int j = 0; j < 9; j++) {
                board[i][j]=passed[i][j];
            }
        }
        //modify
        board[r][c]=(char)(val+'0');

        HashSet<Character>rows[]=new HashSet[9];
        HashSet<Character>cols[]=new HashSet[9];
        HashSet<Character>boxes[]=new HashSet[9];

        for(int i=0;i<9;i++){
            rows[i]=new HashSet<>();
            cols[i]=new HashSet<>();
            boxes[i]=new HashSet<>();
        }

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char curr=board[i][j];
                if(curr=='.'){
                    continue;
                }
                int boxIdx=(i/3)*3+(j/3);
                if(rows[i].contains(curr)
                ||cols[j].contains(curr)
                ||boxes[boxIdx].contains(curr)
                ){
                    return false;
                }
                rows[i].add(curr);
                cols[j].add(curr);
                boxes[boxIdx].add(curr);
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
