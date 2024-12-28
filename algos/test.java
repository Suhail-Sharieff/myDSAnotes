import java.util.Arrays;
import java.util.HashMap;

public class test {

    public static void main(String[] args) {
        int coins[]={1,2,5}; 
        int target=11;
        System.out.println(showWhichCoinsweUse(coins, target,new HashMap<>()));
    }
    static  int showWhichCoinsweUse(int coins[],int target,HashMap<Integer,Integer>hp){
        //call like: System.out.println(showWhichCoinsweUse(coins, target,new HashMap<>()));
        if(target==0) return 0;
        int dp[]=new int[target+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int currTar=1;currTar<=target;currTar++){
            int ans=Integer.MAX_VALUE;
            for(int coinChosen : coins){
                if(currTar-coinChosen>=0 && dp[currTar-coinChosen]!=Integer.MAX_VALUE){
                    ans=Math.min(ans,dp[currTar-coinChosen]+1);
                    hp.put(currTar, coinChosen);
                }
            }
            dp[currTar]=ans;
        }
        while (target > 0) {
            System.out.print("For "+target+" we use coin:"+hp.get(target) + ", remaining is "+(target-hp.get(target))+" -> ");
            target -= hp.get(target);
        }
        return dp[target];
    }
}