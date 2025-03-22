
import java.util.Stack;

/*
155. Min Stack
Solved
Medium
Topics
Companies
Hint
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

 

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:

-231 <= val <= 231 - 1
Methods pop, top and getMin operations will always be called on non-empty stacks.
At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
public class _02_min_stack {
    static class Way1 {

        public Stack<Integer> st;
        public Stack<Integer> st2;

        public Way1() {
            st = new Stack<>();
            st2 = new Stack<>();
        }

        public void push(int val) {
            if (st2.isEmpty() || val <= st2.peek()) {
                st2.push(val);
            } else {
                st2.push(st2.peek());
            }
            st.push(val);
        }

        public void pop() {
            st2.pop();
            st.pop();
        }

        public int top() {
            return st.peek();
        }

        public int getMin() {
            return st2.peek();
        }
    }

    class Way2 {
        private static Stack<int[]> st;

        public Way2() {
            st = new Stack<>();
        }

        public void push(int val) {
            if (st.isEmpty())
                st.push(new int[] { val, val });
            else
                st.push(new int[] { val, Math.min(val, st.peek()[1]) });
        }

        public void pop() {
            st.pop();
        }

        public int top() {
            return st.peek()[0];
        }

        public int getMin() {
            return st.peek()[1];
        }
    }

}
