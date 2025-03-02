import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args) {
        List<int[]>li=Arrays.asList(new int[]{0,0},new int[]{1,1});
       int arr[][]=li.toArray(int[][]::new);
       System.out.println(Arrays.toString(arr));
    }   

   
}

class ppppp{
    int v,f;
    public ppppp(int x,int y){
        this.v=x;this.f=y;
    }
}
