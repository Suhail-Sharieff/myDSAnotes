package _11_Stack_Queue;

import java.util.Arrays;
import java.util.Stack;


/*
Given an array arr[ ] of integers, the task is to find the next greater element for each element of the array in order of their appearance in the array. Next greater element of an element in the array is the nearest element on the right which is greater than the current element.
If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the last element is always -1.

Examples

Input: arr[] = [1, 3, 2, 4]
Output: [3, 4, 4, -1]
Explanation: The next larger element to 1 is 3, 3 is 4, 2 is 4 and for 4, since it doesn't exist, it is -1.
Input: arr[] = [6, 8, 0, 1, 3]
Output: [8, -1, 1, 3, -1]
Explanation: The next larger element to 6 is 8, for 8 there is no larger elements hence it is -1, for 0 it is 1 , for 1 it is 3 and then for 3 there is no larger element on right and hence -1.
Input: arr[] = [10, 20, 30, 50]
Output: [20, 30, 50, -1]
Explanation: For a sorted array, the next element is next greater element also exxept for the last element.
Input: arr[] = [50, 40, 30, 10]
Output: [-1, -1, -1, -1]
Explanation: There is no greater element for any of the elements in the array, so all are -1.
Constraints:
1 ≤ arr.size() ≤ 106
0 ≤ arr[i] ≤ 109
 */

public class _02_prev_greater_next_greater {


    public static void main(String[] args) {
        int arr[]={1, 3, 2, 4};
        get_prev_largest_of_each(arr);
        get_next_largest_of_each(arr);
    }



    //------------------------next greater
    static int[] get_prev_largest_of_each(int arr[]){
        Stack<Integer>st=new Stack<>();
        int next_greater[]=new int[arr.length];
        Arrays.fill(next_greater, -1);
        for(int i=0;i<arr.length;i++){
            while(!st.isEmpty()){
                if(arr[i]<st.peek()){
                    next_greater[i]=st.peek();
                    break;
                }
                st.pop();
            }
            st.push(arr[i]);
        }
        System.out.println(Arrays.toString(next_greater));
        return next_greater;
    }

    //------------------------next greater
     static int[] get_next_largest_of_each(int arr[]){
        Stack<Integer>st=new Stack<>();
        int next_greater[]=new int[arr.length];
        Arrays.fill(next_greater, -1);
        for(int i=arr.length-1;i>=0;i--){
            while(!st.isEmpty()){
                if(arr[i]<st.peek()){
                    next_greater[i]=st.peek();
                    break;
                }
                st.pop();
            }
            st.push(arr[i]);
        }
        System.out.println(Arrays.toString(next_greater));
        return next_greater;
    }
    
}
