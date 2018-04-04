package LintCode;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Postorder_Traversal_68 {
    /**** 给定的数据结构  **/
    public class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /***** 递归方法 *****/
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        if (root == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root,list);
        return list;
    }

    public void postorderTraversal(TreeNode t, List<Integer> list){
        if (t == null){
            return;
        }
        postorderTraversal(t.left,list);
        postorderTraversal(t.right,list);
        list.add(t.val);
    }
    
    /***** 非递归方法 *****/
    public List<Integer> postorderTraversalWithOut(TreeNode root) {

        List<Integer> list = new LinkedList<>();

        if (root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p =root;
        while (p != null){
            stack.push(p);
            p = p.left;
        }
        TreeNode lastVisit = null;
        while (!stack.isEmpty()){
            p = stack.peek();
            if (p.right != lastVisit && p.right != null){
                p = p.right;
                while (p != null){
                    stack.push(p);
                    p = p.left;
                }
                continue;
            }
            list.add(p.val);
            lastVisit = stack.pop();
        }

        return list;
    }
}
