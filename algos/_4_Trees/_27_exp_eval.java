
package _4_Trees;

import java.util.Stack;

public class _27_exp_eval {

    public static void main(String[] args) {
        // evaluates postfix exp
        String postfix = "12+";
        eval(postfix);
    }

    public static void eval(String exp) {

        Stack<MyTreeNode> st = new Stack<>();
        // st.push(new MyTreeNode('0'));
        for (char curr : exp.toCharArray()) {
            MyTreeNode node = new MyTreeNode(curr);
            if (Character.isLetterOrDigit(curr)) {
                st.push(node);
            } else {
                node.right = st.pop();
                node.left = st.pop();
                st.push(node);
            }
            MyTreeNode.prettyPrintTree(node, exp, node.left!=null);

        }

        // System.out.println(st);

    }
}

class MyTreeNode {
    char val;
    MyTreeNode left;
    MyTreeNode right;

    public MyTreeNode(char val) {
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