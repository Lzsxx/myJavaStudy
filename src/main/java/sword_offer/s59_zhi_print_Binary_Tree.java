package sword_offer;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，
// 第三行按照从左到右的顺序打印，其他行以此类推。

public class s59_zhi_print_Binary_Tree {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listList = new ArrayList<ArrayList<Integer>>();

        if (pRoot == null){
            return  listList;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        boolean fromLeft = true;   //初始时根节点当作是从左往右

        TreeNode p = pRoot;
        queue.add(p);

        while ( !queue.isEmpty() ){
            int count = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
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

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

}
