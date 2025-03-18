package _12_Greedy;


/*
There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

 

Example 1:

Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.
 

Constraints:

n == ratings.length
1 <= n <= 2 * 104
0 <= ratings[i] <= 2 * 104
 */

public class _11_candy {
    //https://www.youtube.com/watch?v=IIqVFvKE6RY&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=296&ab_channel=takeUforward


    //brute force:assign first memeber as 1(for each from left and right side), just traverse checking left and right partner seoaratley, then finally we take max of left and right which proves to satify both condiion
    public int brute(int[] ratings) {
        int len=ratings.length;
        int left[]=new int[len];
        int right[]=new int[len];
        left[0]=right[len-1]=1;
        for(int i=1;i<len;i++){
            if(ratings[i]>ratings[i-1]) left[i]=left[i-1]+1;
            else left[i]=1;
        }
        for(int i=len-2;i>=0;i--){
            if(ratings[i]>ratings[i+1]) right[i]=right[i+1]+1;
            else right[i]=1;
        }
        int ans=0;
        for(int i=0;i<len;i++) ans+=Math.max(left[i],right[i]);
        return ans;
    }



    //--------optimal:  //https://www.youtube.com/watch?v=IIqVFvKE6RY&list=PLgUwDviBIf0oF6QL8m22w1hIDC1vJ_BHz&index=296&ab_channel=takeUforward
    public int candy(int[] ratings) {
        int n=ratings.length;
        int i=1;
        int ans=1;
        while(i<n){
            //handling flat cse
            if(ratings[i]==ratings[i-1]){
                ans++;
                i++;
                continue;
            }
            int up_counter=1;
            while(i<n && ratings[i]>ratings[i-1]){
                up_counter++;
                ans+=up_counter;
                i++;
            }
            //now up_counter must be at peak, now start moving down
            int down_counter=0;
            while(i<n && ratings[i]<ratings[i-1]){
                down_counter++;
                ans+=down_counter;
                i++;
            }
            down_counter++;
            if(up_counter<down_counter){
                ans+=down_counter-up_counter;
            }
        }
        return ans;
    }
}
