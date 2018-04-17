package LintCode;


import java.util.*;

public class Binary_Tree_Zigzag_Level_Order_Traversal_71 {

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
        boolean fromLeft = true;   //初始时根节点当作是从左往右

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
            if ( !fromLeft ){
                Collections.reverse(list);
            }
            fromLeft = !fromLeft;
            listList.add(list);
        }
        return listList;
    }


}
