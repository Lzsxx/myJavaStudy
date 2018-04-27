package LintCode;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 给出一棵二叉树，返回其节点值的层次遍历（逐层从左往右访问）

public class Binary_Tree_Level_Order_Traversal_69 {

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
            listList.add(list);
        }
        return listList;
    }


}
