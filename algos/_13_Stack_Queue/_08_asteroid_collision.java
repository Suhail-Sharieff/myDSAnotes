import java.util.Stack;
/*
We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

 

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 

Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */
import java.util.stream.Collectors;

public class _08_asteroid_collision {
    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {
            while(!st.isEmpty() && collision_possible(asteroids[i], st.peek())){
                int right_side_moving_star=st.pop();
                int left_side_moving_star=asteroids[i];
                int res=collide(left_side_moving_star,right_side_moving_star);
                asteroids[i]=res;
            }
            if(asteroids[i]!=0)st.push(asteroids[i]);//case like [8,-8]
        }


        return st.stream().mapToInt(i->i).toArray();

    }

    public boolean collision_possible(int curr, int peek) {//we encountered a left_moving star and stack on top has right_moving star
        return (curr < 0 && peek > 0);
    }

    public int collide(int neg, int pos) {
        if(Math.abs(neg)==pos) return 0;
        int res = Math.max(pos, Math.abs(neg));
        if (res * -1 == neg)
            return neg;
        return pos;
    }
    // public static  String removeStars(String s) {
    //     Stack<Character>st=new Stack<>();
    //     for(char c:s.toCharArray()){
    //         if(c=='*') st.pop();
    //         else st.push(c);
    //     }
    //     return st.stream().collect(Collectors.toList()).stream()
    //     .map(String::valueOf)
    //     .collect(Collectors.joining());
    // }

    // public static void main(String[] args) {
    //     System.out.println(removeStars("leet**cod*e"));
    // }
}
