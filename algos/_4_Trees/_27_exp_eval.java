//https://www.geeksforgeeks.org/problems/fun-with-expresions2523/1
package _4_Trees;

import java.util.*;
import java.util.function.Function;

public class _27_exp_eval {

    public static void main(String[] args) {
        String exp = "1+4+5+2-3+6+8";//works only for nubers WITHOUT PARENTHESIS
        String postfix[] = getPostFixNotationOf(exp);
        MyTreeNode expTree=getExpressionTree(postfix);
        int ans=f(expTree);
        System.out.println(ans);
    }

    public static String[] getPostFixNotationOf(String exp) {
        Stack<String> st = new Stack<>();
        int len = exp.length();
        Function<String, Integer> priority = (String c) -> {
            if (c.equals("^"))
                return 4;
            if (c.equals("*") || c.equals("/"))
                return 3;
            if (c.equals("+") || c.equals("-"))
                return 2;
            return 1;
        };
        List<String> arr = new ArrayList<>();
        int i = 0;
        while (i < len) {
            char curr = exp.charAt(i);
            if (curr == '(')
                st.push("(");
            else {
                if (Character.isLetterOrDigit(curr)) {
                    StringBuilder num = new StringBuilder();
                    while (i < len && Character.isLetterOrDigit(exp.charAt(i))){
                        num.append(exp.charAt(i++));
                    }
                    arr.add(num.toString());
                    continue;
                }else if (curr == ')') {
                    while(!st.isEmpty() && !st.peek().equals("(")){
                        arr.add(st.pop());
                    }
                    st.pop();
                }else{
                    //is operand
                    while(!st.isEmpty() && priority.apply(Character.toString(curr))<=priority.apply(st.peek())){
                        arr.add(st.pop());
                    }
                    st.push(Character.toString(curr));
                }
                i++;
            }
        }
        while(!st.isEmpty()) arr.add(st.pop());
        // System.out.println(arr);
        return arr.toArray(String[]::new);
    }

    public static MyTreeNode getExpressionTree(String postfix[]) {
        Stack<MyTreeNode> st = new Stack<>();
        for (String curr : postfix) {
            MyTreeNode node = new MyTreeNode(curr);
            if (isNumber(curr)) {
                st.push(node);
            } else {
                node.right = st.pop();
                node.left = st.pop();
                st.push(node);
            }
        }
        if (st.isEmpty())
            return new MyTreeNode("0");
        MyTreeNode.prettyPrintTree(st.peek(), "", true);
        return st.peek();
    }

    static int f(MyTreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null) {
            if(isNumber(root.val.toString())) return Integer.parseInt(root.val.toString());
            return 0;
        }
        int left = f(root.left);
        int right = f(root.right);
        if (root.val.equals("*"))
            return left * right;
        if (root.val.equals("/"))
            return left / right;
        if (root.val.equals("+"))
            return left + right;
        if (root.val.equals("-"))
            return left - right;
        if (root.val.equals("^"))
            return left ^ right;
        if (root.val.equals("&"))
            return left & right;
        return 0;
    }

    static boolean isOperator(String s) {
        return (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("^") || s.equals("&"));
    }

    static boolean isNumber(String s) {
        return !isOperator(s);
    }

}

class MyTreeNode {
    Object val;
    MyTreeNode left;
    MyTreeNode right;

    public MyTreeNode(Object val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return (this.val + "");
    }

    public static void prettyPrintTree(MyTreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }
}


//------------code that handles parenthesis as well:
/*
class Solution {
    public int calculate(String exp) {
        exp = new String(exp.replaceAll("\\s+", ""));//coz given useless spacess also in q
        String postfix[] = getPostFixNotationOf(exp);
        MyTreeNode expTree = getExpressionTree(postfix);
        int ans = f(expTree);
        return ans;
    }

    public static String[] getPostFixNotationOf(String exp) {
        Stack<String> st = new Stack<>();
        int len = exp.length();
        Function<String, Integer> priority = (String c) -> {
            if (c.equals("^"))
                return 4;
            if (c.equals("*") || c.equals("/"))
                return 3;
            if (c.equals("+") || c.equals("-"))
                return 2;
            return 1;
        };
        List<String> arr = new ArrayList<>();
        int i = 0;
        while (i < len) {
            char curr = exp.charAt(i);

            // Handle unary minus before number or parenthesis
            if (curr == '-' && (i == 0 || exp.charAt(i - 1) == '(')) {
                if (i + 1 < len && Character.isDigit(exp.charAt(i + 1))) {
                    // -number case
                    StringBuilder num = new StringBuilder();
                    num.append('-');
                    i++;
                    while (i < len && Character.isDigit(exp.charAt(i))) {
                        num.append(exp.charAt(i++));
                    }
                    arr.add(num.toString());
                    continue;
                } else if (i + 1 < len && exp.charAt(i + 1) == '(') {
                    // -(...) case
                    arr.add("0"); // simulate: 0 - (...)
                    st.push("-");
                    i++;
                    continue;
                }
            }

            // Digits
            if (Character.isDigit(curr)) {
                StringBuilder num = new StringBuilder();
                while (i < len && Character.isDigit(exp.charAt(i))) {
                    num.append(exp.charAt(i++));
                }
                arr.add(num.toString());
                continue;
            }

            // Left parenthesis
            if (curr == '(') {
                st.push("(");
                i++;
            }
            // Right parenthesis
            else if (curr == ')') {
                while (!st.isEmpty() && !st.peek().equals("(")) {
                    arr.add(st.pop());
                }
                st.pop(); // remove '('
                i++;
            }
            // Operators
            else {
                while (!st.isEmpty() && priority.apply(Character.toString(curr)) <= priority.apply(st.peek())) {
                    arr.add(st.pop());
                }
                st.push(Character.toString(curr));
                i++;
            }
        }

        while (!st.isEmpty())
            arr.add(st.pop());
        System.out.println(arr);
        return arr.toArray(String[]::new);
    }

    public static MyTreeNode getExpressionTree(String postfix[]) {
        Stack<MyTreeNode> st = new Stack<>();
        for (String curr : postfix) {
            MyTreeNode node = new MyTreeNode(curr);
            if (isNumber(curr)) {
                st.push(node);
            } else {
                node.right = st.pop();
                node.left = st.pop();
                st.push(node);
            }
        }
        if (st.isEmpty())
            return new MyTreeNode("0");
        // MyTreeNode.prettyPrintTree(st.peek(), "", true);
        return st.peek();
    }

    static int f(MyTreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null) {
            if (isNumber(root.val.toString()))
                return Integer.parseInt(root.val.toString());
            return 0;
        }
        int left = f(root.left);
        int right = f(root.right);
        if (root.val.equals("*"))
            return left * right;
        if (root.val.equals("/"))
            return left / right;
        if (root.val.equals("+"))
            return left + right;
        if (root.val.equals("-"))
            return left - right;
        if (root.val.equals("^"))
            return left ^ right;
        if (root.val.equals("&"))
            return left & right;
        return 0;
    }

    static boolean isOperator(String s) {
        return (s.equals("*") || s.equals("/") || s.equals("+") || s.equals("-") || s.equals("^") || s.equals("&"));
    }

    static boolean isNumber(String s) {
        return !isOperator(s);
    }

}

class MyTreeNode {
    Object val;
    MyTreeNode left;
    MyTreeNode right;

    public MyTreeNode(Object val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return (this.val + "");
    }

    public static void prettyPrintTree(MyTreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }
}
 */