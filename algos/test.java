import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {

    public static void main(String[] args) {
        // HashSet<List<Integer>>hs=new HashSet<>();

            int arr[]={5, 2, 3 ,10, 6, 8};
            int target=10;
            List<List<Integer>>ans=new ArrayList<>();
        func5(arr, target, new ArrayList<>(), ans, 0);
        System.out.println(ans);
    }


    public static void func5(int nums[],int target,List<Integer>empty,List<List<Integer>>ans,int startIdx){//IF ARR IS SORTED
        if (target==0) {
            ans.add(new ArrayList<>(empty));
            System.out.println(empty);
            return;
        }
        for (int i =startIdx ; i < nums.length; i++) {
            if (i>startIdx&&nums[i]==nums[i-1]) {//handle first case where start=0 && avoid dupli also
                continue;
            }
            if (nums[i]>target) {
                break;
            }
            empty.add(nums[i]);
            func5(nums, target-nums[i], empty, ans, i+1);
            // empty.remove(empty.size()-1);
        }
    }
}