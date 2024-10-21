package _1_recursion.multipleCall;
import java.util.HashSet;
public class _07_isValidSudoku {
    @SuppressWarnings("unchecked")
    public boolean brute(char[][] board) {//O(9*9)-----(27*9)
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


    
}
