package _1_recursion.multipleCall;
//https://www.youtube.com/watch?v=i05Ju7AftcM&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=16
import java.util.ArrayList;
import java.util.List;

//The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.

// Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.

// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

// Input: n = 4
// Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

// Input: n = 1
// Output: [["Q"]]


public class _6_nQueens {


    public static boolean isSafe(int row,int col,List<String>board,int n){

        //u have to check only upper from that cell above it the column,left upper diagonal,right upper diagonal since the other ways its waste to check(see how we r changing in board)

        //check column above,start from top row and proceed toll last row for that particular column
        for (int i = 0; i < row; i++) {
            if (board.get(i).charAt(col)=='Q') {
                return false;
            }
        }
        //check upper left diagonal,start from that cell and moving upper left,both row and column number decreases
        for (int i = row,j=col; i>=0&&j>=0; i--,j--) {
            if (board.get(i).charAt(j)=='Q') {
                return false;
            }
        }
        //check upper right diagonal
        for (int i = row,j=col; i >=0&&j<n; i--,j++) {
            if (board.get(i).charAt(j)=='Q') {
                return false;
            }
        }

        return true;
    }

    public static void func(int row,int n,List<String>board,List<List<String>>result){
        //traverse row wise
        if (row==n) {
            result.add(new ArrayList<>(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, board, n)) {
                StringBuilder sb=new StringBuilder(board.get(row));
                //if safe, set that col to 'Q'
                sb.setCharAt(col, 'Q'); 
                board.set(row, sb.toString());

                func(row+1, n, board, result);

                //backtrack
                sb.setCharAt(col, '.');
                board.set(row, sb.toString());

            }
        }
    }
    public static void main(String[] args) {
        List<List<String>>result=new ArrayList<>();
        List<String>board=new ArrayList<>();
        //initialize board with empty rows
        StringBuilder sb=new StringBuilder();
        int n=4;//size of nXn matrix give only n
        for (int i = 0; i < n; i++) {
            sb.append('.');
        }
        String emptyRow=sb.toString();
        for (int i = 0; i < n; i++) {
            board.add(emptyRow+"\n");//fill nXn borad with '.' initially
        }

        func(0,n,board,result);
        System.out.println(result);

    }    
}
