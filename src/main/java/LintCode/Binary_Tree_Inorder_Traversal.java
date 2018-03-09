package LintCode;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Inorder_Traversal {
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
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        if (root == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        inorderTraversal(root,list);
        return list;
    }

    public void inorderTraversal(TreeNode t, List<Integer> list){
        if (t == null){
            return;
        }
        inorderTraversal(t.left,list);
        list.add(t.val);
        inorderTraversal(t.right,list);
    }
    
    /***** 非递归方法 *****/
    public List<Integer> inorderTraversalWithOut(TreeNode root) {
        if (root == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()){
            while (current != null){
                stack.push(current);
                current = current.left;
            }
            if ( !stack.isEmpty() ){
                current = stack.pop();
                list.add(current.val);
                current = current.right;    //变换方向，如果非null，在下一次while循环中会入账
            }

        }
        return list;
    }
}
