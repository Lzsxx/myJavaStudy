package sword_offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/***** 输入两棵二叉树A，B，判断B是不是A的子结构。
 * （ps：我们约定空树不是任意一个树的子结构）
 *****/
public class s17_HasSubtree {
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 思路；二叉树，首选递归
        // 如何递归？每次进入函数，首先判断，如果root2为null，不管root1是否为null，都返回true，
        // 如果root2不为null，跨过了这个if语句，而root1为null，那么说明root2的结构比root1多，肯定不是子树
        // 如果能进入下一步比较，root2和root1的值相等，则比较左右子树，
        // 不相等，返回false
        if (root2 == null || root1 == null){
            return false;
        }

        // 这里只判断到root1以及左右儿子的情况，并没有判断孙子节点的情况，所以题目中的子结构，应该只算
        // 以root1为根节点，或者root1的儿子为根节点的情况
        return isSubTree(root1, root2) || isSubTree(root1.left,root2) || isSubTree(root1.right,root2);

    }
    public boolean isSubTree(TreeNode root1,TreeNode root2){
        if (root2 == null){
            return true;
        }
        if (root1 == null){
            return false;
        }
        if (root1.val == root2.val){
            return isSubTree(root1.left,root2.left) && isSubTree(root1.right,root2.right);
        }else {
            return false;
        }
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
