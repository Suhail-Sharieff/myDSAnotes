package _1_recursion.multipleCall;

import java.util.*;

public class _2_printSubSeq {
    //TC: O(n)
    //SC: O(3)

    //subsequence means a contigous/nonContiguos IN THE GIVEN ORDER
    // ex for{3,1,2}:
    //{3},{1},{2},{3,1},{1,2},{3,2},{3,1,2},{} are subsequences ie 2^3 powersets

    //TRICK:
    //add+print,remove+print
    
    public static void printSubSequence(Vector<Integer>v,Vector<Integer>empty,int start,int size){
      if(start>=size){
        System.out.print(empty+" ");
        return;
      }
       // Include the current element in the subsequence
       empty.add(v.get(start));
       printSubSequence(v, empty, start + 1, size);
       
       // Exclude the current element from the subsequence
       empty.remove(empty.size() - 1); 
       printSubSequence(v, empty, start + 1, size);
    }

    public static void main(String[] args) {
        Vector<Integer>v=new Vector<>(List.of(3,1,2));
        Vector<Integer>empty=new Vector<>();
        printSubSequence(v, empty, 0,v.size());
        
    }
}
