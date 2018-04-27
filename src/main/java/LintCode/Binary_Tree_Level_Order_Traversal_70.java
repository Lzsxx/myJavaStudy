package LintCode;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

// 给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）

public class Binary_Tree_Level_Order_Traversal_70 {

     public class TreeNode {
         public int val;
         public TreeNode left, right;
          public TreeNode(int val) {
             this.val = val;
             this.left = this.right = null;
         }
     }
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
        List<List<Integer>> listList = new LinkedList<List<Integer>>();
        if (root == null){
            return  listList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Stack<List<Integer>> stack = new Stack<>();

        TreeNode p = root;
        queue.add(p);

        while ( !queue.isEmpty() ){
            int count = queue.size();
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                p = queue.remove();
                list.add(p.val);
                if ( p.left != null){
                    queue.add(p.left);
                }
                if (p.right != null){
                    queue.add(p.right);
                }
            }
            stack.push(list);
        }
        while ( !stack.isEmpty() ){
            listList.add(stack.pop());
        }
        return listList;
    }


}
