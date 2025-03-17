package _12_Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Fractional Knapsack
Difficulty: MediumAccuracy: 32.46%Submissions: 317K+Points: 4Average Time: 20m
Given two arrays, val[] and wt[], representing the values and weights of items, and an integer capacity representing the maximum weight a knapsack can hold, determine the maximum total value that can be achieved by putting items in the knapsack. You are allowed to break items into fractions if necessary.
Return the maximum value as a double, rounded to 6 decimal places.

Examples :

Input: val[] = [60, 100, 120], wt[] = [10, 20, 30], capacity = 50
Output: 240.000000
Explanation: Take the item with value 60 and weight 10, value 100 and weight 20 and split the third item with value 120 and weight 30, to fit it into weight 20. so it becomes (120/30)*20=80, so the total value becomes 60+100+80.0=240.0 Thus, total maximum value of item we can have is 240.00 from the given capacity of sack. 
Input: val[] = [60, 100], wt[] = [10, 20], capacity = 50
Output: 160.000000
Explanation: Take both the items completely, without breaking. Total maximum value of item we can have is 160.00 from the given capacity of sack.
Input: val[] = [10, 20, 30], wt[] = [5, 10, 15], capacity = 100
Output: 60.000000
Explanation: In this case, the knapsack capacity exceeds the combined weight of all items (5 + 10 + 15 = 30). Therefore, we can take all items completely, yielding a total maximum value of 10 + 20 + 30 = 60.000000.
 */
public class _10_fractional_knapsack {
    public static void main(String[] args) {
        int val[]={8, 2 ,10 ,1 ,9 ,7 ,2 ,6 ,4 ,9};
        int wt[]={10 ,1 ,7 ,7 ,5 ,1 ,8 ,6 ,8 ,7};
        int capacity=21;

        double vPerkg[]=new double[wt.length];
        for(int i=0;i<wt.length;i++){
            vPerkg[i]=(val[i]*1d)/(wt[i]*1d);
        }

        // System.out.println(Arrays.toString(vPerkg));
        PriorityQueue<double[]> pq=new PriorityQueue<>((x,y)->{//stores [vPerkg,wt]
            return Double.compare(y[0],x[0]);
        });
        for(int i=0;i<wt.length;i++) pq.offer(new double[]{vPerkg[i],wt[i]});

        pq.forEach(e->System.out.println(Arrays.toString(e)));
        double ans=0d;

        double rem_capacity=capacity;

        while(rem_capacity>0 && !pq.isEmpty()){
            double[]  top=pq.poll();

            double v_Perkg=top[0];
            double available_wt=top[1];

            double max_wt_we_can_use=Math.min(rem_capacity, available_wt);

            ans+=max_wt_we_can_use*v_Perkg;

            rem_capacity-=max_wt_we_can_use;
        }

        // System.out.println(wt_loaded+"   "+rem_capacity);
        

        System.out.println(ans);

    }
}
