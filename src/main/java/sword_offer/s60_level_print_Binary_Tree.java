package sword_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// 从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。

public class s60_level_print_Binary_Tree {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> listList = new ArrayList<ArrayList<Integer>>();

        if (pRoot == null){
            return  listList;
        }

        Queue<TreeNode> queue = new LinkedList<>();

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
