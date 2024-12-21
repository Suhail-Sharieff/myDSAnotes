
package _4_Trees;

import java.util.Stack;

public class _27_exp_eval {

    public static void main(String[] args) {
        //evaluates postfix exp
        String postfix="12+";
        eval(postfix);;
    }


    public static void eval(String exp){
        
        Stack<MyTreeNode>st=new Stack<>();
        // st.push(new MyTreeNode(' '));
        for(char curr : exp.toCharArray()){
            MyTreeNode node=new MyTreeNode(curr);
            if (Character.isLetterOrDigit(curr)) {
                st.push(node);
            }else{
                node.right=st.pop();
                node.left=st.pop();
                st.push(node);
            }
            System.out.println(st);
        }

        System.out.println(st);

    }
}

class MyTreeNode{
    char val;
    MyTreeNode left;
    MyTreeNode right;
    public MyTreeNode(char val){
        this.val=val;
    }
    @Override
    public String toString() {
        return (this.val+"");
    }
}