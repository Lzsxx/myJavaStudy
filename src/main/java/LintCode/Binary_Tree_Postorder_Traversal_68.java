package LintCode;


import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
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
        if (root == null){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode lastVisit = null;

        while (current != null){
            stack.push(current);
            current = current.left;
        }
        while ( !stack.isEmpty() ){
            current = stack.peek();
            // 取得此时栈顶元素，如果栈顶元素存在右儿子且没有访问过，要进入到右子树，继续往右子树的最左边走
            if (current.right != null && lastVisit != current.right){
                current = current.right;
                while (current != null){
                    stack.push(current);
                    current = current.left;
                }
            }else { // 否则，就可以访问当前根节点了
                current = stack.pop();
                list.add(current.val);
                lastVisit = current;
            }
        }

        return list;
    }
}
