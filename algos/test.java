import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * test
 */
public class test {

    public static void main(String[] args) {
        int nums[]=new int[]{1,3,1};
        List<List<Integer>>ans=new ArrayList<>();
        func(nums, 0, new ArrayList<>(), ans);
        System.out.println(ans);

        List<Integer>values=new ArrayList<>();
        for (List<Integer> list : ans) {
            values.add(Math.abs(list.get(0)-list.get(1)));
        }
        System.out.println(values);
        Collections.sort(values);
        System.out.println(values.get(k-1));

    }
    public static void func(int nums[], int start,List<Integer>empty,List<List<Integer>>li){
        if (start>=nums.length) {
            
            if (empty.size()==2) {
                li.add(new ArrayList<>(empty));
            }

            return;
        }
        empty.add(nums[start]);
        func(nums, start+1, empty, li);
        empty.removeLast();
        func(nums, start+1, empty, li);

    }
}