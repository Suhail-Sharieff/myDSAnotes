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

/*
O(1) Space Approach :
We can consider this problem like valley and peak problem. In each valley there should be 1 candy and for each increasing solpe in either side we need to increse candy by 1. Peaks have highest candy. If any equal rating is found then candy resets to 1 as two equal neighbours may have any number of candies. The peak should contain the higher number of candy between which is calculated from the incresing slope and which is calculated from decreasing slope. Because this will satisfy the condition that peak element is having more candies than its neighbours.

Example :

Let take the Rating as : [1,3,6,8,9,5,3,6,8,5,4,2,2,3,7,7,9,8,6,6,6,4,2]

Each child represented as rating(candy he is given)
Peak = max(peak, valley)

See when peak is encountered we take max of the peak calculated from left and valley calculated from right.
When we get any equal element it gets reset to 1 candy or if it is peak we take max(0, right valley)

           (5)         (4)                         (3)
            9           8                           9
           /|\         /|\                         /|\
          / | \       / | \                (3)    / | \
      (4)8  |  5(2)  6  |  5(3)             7 __ 7  |  8(2)
        /   |   \   (2) |   \              /|   (1) |   \
       /    |    \ /    |    \            / |    |  |    \         (3)
   (3)6     |     3     |     4(2)       3(2)    |  |     6 __ 6 __ 6    -> Total candy = 50
     /      |    (1)    |      \        /   | Reset |    (1)  (1)   |\
    /       |           |       \      /    |  to 1 |          |    | \
(2)3        |           |        2 __ 2     |       |          |    |  4(2)
  /         |           |       (1)  (1)    |       |        Reset  |   \
 /          |           |                   |       |         to 1  |    \
1(1)        |           |                   |       |               |     2(1)
   Peak= max(5,3)  Peak= max(3,4)    Peak= max(3,0) |         Peak= max(0,3)
                                               Peak= max(2,3)   
Implementation :

So we take initially n candy for n children. Then we start traversing the rating array from the second element. If we find equal elements we continue to next element as they already have 1 candy.

If an increasing slop is found (ratings[i] > ratings [i-1]) we increase value of peak and and add the peak value to candy. After each iteration new peak is found and the value is added to candy. In this way we also get the value of minimum height of the peak at the end.

If a decreasing slope is found (ratings[i] < ratings [i-1]) we calculate the depth of the valley which is in turn the minimum height of the previous peak. In each iteration we increse the valley by 1 and add it to the candy. You can visualise it as 1 candy is added to each of the previous members untill peak or the new peak value is interted at the previous peak and other values are shifted to right by 1 place. We also need to check if it is going out of array if so then return the number of candy.

After this we can see we have added the peak value twice in candy once as peak and once as valley. But we need only the max value out of these two so we substract the min(peak, valley) from candy.

After that return the candy.

Note : We have considered the sequence increasing -> decreasing as peak should have the max value between the height of left (increasing) side and height of right (decreasing) side. In case of decresing -> increasing we dont need to give special attention as the vallley will always have 1 candy and in our code in this case peak will have 0 value so nothing will be substracted from the candy in the end of iteration.

See the example for better understanding :

In our code we increase the peak and add peak value untill we get the minimum actual peak.
In case of the decreasing part take this example.

[7,5,3,2], initial candy = 4, In each iteration valley++ and candy += valley

7 starting (valley = 0, candy = 4, candy configuaration  = [1,1,1,1])
 \
  5 (valley = 1, candy = 4+1 = 5, candy configuaration  = [2,1,1,1])
   \
    3 (valley = 2, candy = 5+2 = 7, candy configuaration = [3,2,1,1])
     \
      2 [valley = 3, candy = 7+3 = 10, candy configuartion = [4,3,2,1])
	  
Here see the valley is at last equal to the minimum previous peak value.

As we have given 1 candy to all before so the peak and valley values are actually 1 less than the actual candy they should get.
 */
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
