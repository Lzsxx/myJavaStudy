package sword_offer;

//输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
// 最长路径的长度为树的深度。

import LintCode.MinStack_12;

import java.util.LinkedList;

// 思路：深度优先遍历，每次进入，count++;然后判断是否是叶子节点，如果是，记录当前最大深度值，
// 进入左子树，右子树，退出本函数前，count--;
public class s38_TreeDepth {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public int max = 0;
    public int TreeDepth(TreeNode root) {
        return TreeDepth_Stack(root);
    }
    /***** 非递归版 *****/
    // 用栈记录经过的每一个节点，到了叶子节点的时候，判断最大值，离开循环的时候，count--
    public int TreeDepth_Stack(TreeNode root){
        if (root == null){
            return 0;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        int count = 0;
        int max = 0;
        TreeNode p = root;
        while (p != null){
            stack.push(p);
            count ++;
            p = p.left;
        }
        TreeNode lastVisit = null;
        while (!stack.isEmpty()){
            p = stack.peek();
            if (p.left == null && p.right == null){
                if (count > max){
                    max = count;
                }
            }
            if (p.right != null && lastVisit != p.right){
                p = p.right;
                while ( p != null){
                    stack.push(p);
                    count ++;
                    p = p.left;
                }
                continue;
            }
            count --;
            lastVisit = stack.pop();
        }
        return max;
    }


    /***** 递归版 *****/
    public int TraverseTreeDepth(TreeNode root, int count){
        if (root == null){
            return count;
        }
        count ++;
        if (root.left == null && root.right == null){
            if (count > max){
                max = count;
            }
        }
        count = TraverseTreeDepth(root.left, count);
        count = TraverseTreeDepth(root.right, count);
        count --;
        return count;
    }
}
