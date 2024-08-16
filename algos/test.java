import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * test
 */
public class test {

    public static void main(String[] args) {
       List<List<Integer>>li=new ArrayList<>();
       HashSet<Integer>hs=new HashSet<>();
       li.add(new ArrayList<>(List.of(-2)));
       li.add(new ArrayList<>(List.of(-3,-2,1)));
    //    li.add(new ArrayList<>(List.of(1,2,3)));

       Collections.sort(li,(a,b)->Math.max(a.getLast()-b.getLast(),a.get(0)-b.get(0)));
       System.out.println(li);

       System.out.println(li.getLast().getLast()-li.getFirst().getFirst());

    }
    
    
}