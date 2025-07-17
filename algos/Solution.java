/*
 * 
        ┌── 11
    ┌── -3
┌── 10
│   │       ┌── 1
│   │   ┌── 2
│   └── 5
│       │   ┌── -2
│       └── 3
│           └── 3
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import _4_Trees.TreeNode;

public class Solution {


    public static void main(String[] args) {
        Integer nums[]={111,222,343};


        Arrays.sort(nums,(x, y)->{
            if(x/100!=y/100) return x/100-y/100;
            return ((x/10)%10-(y/10)%10);
        });

        System.out.println(Arrays.toString(nums));
        ArrayList<Integer>arr=new ArrayList<>();
        int i=0;
        int level=1;
        while(i<nums.length){
           int itsPos=(nums[i]/10)%10;
           int pos=1;
           while (itsPos!=pos) {
                pos++;
                arr.add(null);
           }
           while(i<nums.length && (nums[i]/100)==level) arr.add(nums[i++]%10);
           level++;
        }

        System.out.println(arr);
        Queue<TreeNode>q=new LinkedList<>();
        i=0;
        TreeNode root=new TreeNode(arr.get(i));
        q.offer(root);
        i++;
        while(!q.isEmpty()){
            TreeNode top=q.poll();
            if(top==null){}
            if(i<arr.size()) if(arr.get(i)!=null) top.left=new TreeNode(arr.get(i));
            i++;
            if(i<arr.size()) if(arr.get(i)!=null) top.right=new TreeNode(arr.get(i));
            i++;
            q.offer(top.left);
            q.offer(top.right);
        }
        TreeNode.prettyPrintTree(root, "", false);
        TreeNode.prettyPrintTree(TreeNode.constructTree(new Integer[]{1, null, 2, null, null, null, 3}), "", false);
    }   

    

    

    
}