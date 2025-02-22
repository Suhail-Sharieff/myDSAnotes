package _9_Linked_List;

import java.util.Arrays;
import java.util.LinkedList;

public class _00_inbuilt_test {
    //Doubly-LINKED LIST
    protected class DLL_Test{
        public static void main(String[] args) {
            LinkedList<Integer>dll=new LinkedList<>();

            dll.addAll(Arrays.asList(1,2,3,4));
            System.out.println(dll);
            System.out.println(dll.subList(0, 2));
        }
    }
}
