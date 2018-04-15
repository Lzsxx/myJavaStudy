package sword_offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//从上往下打印出二叉树的每个节点，同层节点从左至右打印。
//思路：就是简单的层次遍历
public class s22_PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        if (root == null){
            return list;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        TreeNode p = root;
        queue.add(p);

        while ( !queue.isEmpty() ){
            int count = queue.size();
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
        }
        return list;
    }
}
