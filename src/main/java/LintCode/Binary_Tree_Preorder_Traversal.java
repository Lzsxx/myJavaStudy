package LintCode;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Preorder_Traversal {
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
        preorderTraversal(root,list);
        return list;
    }

    public void preorderTraversal(TreeNode t, List<Integer> list){
//        list.add(t.val);
//        if (t.left != null){
//            preorderTraversal(t.left,list);
//        }
//        if (t.right != null){
//            preorderTraversal(t.right,list);
//        }

        if (t == null){
            return;
        }
        list.add(t.val);
        preorderTraversal(t.left,list);
        preorderTraversal(t.right,list);
    }
    
    /***** 非递归方法 *****/
    public List<Integer> inorderTraversalWithOut(TreeNode root) {
        if (root == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        list.add(root.val);
        if (root.right != null){
            stack.push(root.right);
        }
        if (root.left != null){
            stack.push(root.left);
        }
        // 以上，先将元素添加到栈中的方法，在先序遍历中可以用，因为在遍历到时左右儿子都要加入栈中，
        // 但在中序遍历和后序遍历中就不太好用
        while ( !stack.isEmpty() ){
            TreeNode p = stack.pop();
            list.add(p.val);
            if (p.right != null){
                stack.push(p.right);
            }
            if (p.left != null){
                stack.push(p.left);
            }
        }
        return list;
    }




}
