package _2_Stack;

import java.util.*;
//rules:
/*
->if digit append to ans
->if +,-,/,*,(,) push to stack:
    rules:the top pushed char must have stricly greater precedence than its prev,ie the top will have gretaer precedence than all its bottom.
        rule:
            -if '(' or ')' found append to stack
            -if the gretaer precedence is found than peek, safe to push 
            -if same precendence found,append the pop and then push
            -if lower precendece needs to push to stack than peek, pop out all elemnts and append each
    
 */
public class infixToPostfix {
    //to compare priority
    public static int precedence(char c){
        switch (c) {
            case '+':
            case '-':
               return 1;
        
            case '*':
            case '/':
               return 2;
        
            default:
                return 0;
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter the valid string to convert into postfix:");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        sc.close();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            } else if (ch == '(') {
                st.push(ch);
            } else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    char toAppend = st.pop();
                    sb.append(toAppend);
                }
                st.pop();
            }
            // push * / etc
            else {
                //also take care of priority
                while (!st.isEmpty()&&precedence(ch)<=precedence(st.peek())) {
                    sb.append(st.pop());
                }
                st.push(ch);
            }
        }
        // to push remaining stuff
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        System.out.println("PostFixed String is: " + sb);
    }
}