import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {


    static List<Integer>list;
    public static void main(String[] args) {
        list=Collections.synchronizedList(new ArrayList<>());
        
    }

    synchronized static void add(){
        list.add(23);
    }
    synchronized static void remove(){
        list.removeLast();
    }
}