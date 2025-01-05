package _2_Stack;

import java.util.*;

public class expressionEvaluator {
    //an arithmatic expression can be evaluated after converting it into postfix form
    
    //conversion from valid exp to postfix form
    public static String postfixFormOf(String s){
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
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
                //down part IMP
                while (!st.isEmpty() && precedence(ch) <= precedence(st.peek())) {
                    sb.append(st.pop());
                }
                st.push(ch);
            }
        }
        // to push remaining stuff
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        return(sb.toString());
    }

    //to compare proirity---IMP
    public  static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0; // Assuming all other characters have lowest precedence
        }
    }


    //postFix to evaluation 
    public static int evaluate(String postfixedString){
        int len=postfixedString.length();
        Stack<Integer>st=new Stack<>();
        st.push(0);//or else it shows empty stack
        int num2;
        int num1;
        int tempAnswer;
        for (int i = 0; i < len; i++) {
            char ch=postfixedString.charAt(i);
            if (Character.isDigit(ch)) {
                st.push((int)ch-'0');//parse to number digit
            }
            //IMPORTANT
            else{
                //get prev two nums
                num2=st.pop();
                num1=st.pop();
                switch (ch) {
                    case '+':
                        tempAnswer=num1+num2;
                        break;
                
                    case '-':
                        tempAnswer=num1-num2;
                        break;
                
                    case '*':
                        tempAnswer=num1*num2;
                        break;
                
                    case '/':
                        tempAnswer=num1/num2;
                        break;
                
                    default:
                        tempAnswer=0;
                }
                //imp
                st.push(tempAnswer);
            }
        }
        //the last thing to pop is answer
        return st.pop();
    }
    
    public static void main(String[] args) {
        System.out.println("Enter the valid string to evaluate:");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("PostFix: "+postfixFormOf(s));
        int answer=evaluate(postfixFormOf(s));
        System.out.println(s+" = "+answer);
        sc.close();
    }
}
