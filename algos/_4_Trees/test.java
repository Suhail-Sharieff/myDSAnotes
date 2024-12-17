package _4_Trees;

import java.util.List;
import java.util.PriorityQueue;

public class test {

    public static void main(String[] args) {
        int nums[]={100000,2000};
        int multiplier=1000000;
        int k=2;
        PriorityQueue<tt>pq=new PriorityQueue<>((tt o1, tt o2)->{
            if (o1.val==o2.val) {
                return o1.idx-o2.idx;
            }
            return o1.val-o2.val;
        });
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new tt(nums[i],i));
        }

       int mod=1000000007;


        while (k--!=0) {
            tt top=pq.poll();
            long currVal=top.val;
            long bigNum=currVal*multiplier;
            pq.offer(new tt((int)(bigNum%mod), top.idx));
            for (tt tt : pq) {
                System.out.print(tt.val+" ");
            }
            System.out.println();
        }

        var ko=pq.stream().sorted((o1, o2) -> o1.idx-o2.idx).toList();
        
        for(tt e : ko){
            System.out.print(e.val+" ");
        }
        System.out.println();

        
    }
}


class tt{
    @Override
    public String toString() {
        return "tt [val=" + val + ", idx=" + idx + "]";
    }
    int val;
    int idx;
    public tt(int val, int idx) {
        this.val = val;
        this.idx = idx;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + val;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        tt other = (tt) obj;
        if (val != other.val)
            return false;
        return true;
    }
    
}