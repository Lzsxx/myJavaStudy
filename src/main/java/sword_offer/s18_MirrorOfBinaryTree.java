package sword_offer;

import sun.awt.image.ImageWatched;

import java.util.LinkedList;

/***** 操作给定的二叉树，将其变换为源二叉树的镜像。 *****/
//二叉树的镜像定义：源二叉树
//        8
//        /  \
//        6   10
//        / \  / \
//        5  7 9 11
//        镜像二叉树
//        8
//        /  \
//        10   6
//        / \  / \
//        11 9 7  5

    // 思路：其实很简单，不要想复杂，直接移动指针即可，可以用递归，活用辅助栈不用递归
public class s18_MirrorOfBinaryTree {

    // 非递归实现
    public void Mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode p = stack.pop();
            if (p.left != null || p.right != null){
                TreeNode temp = p.left;
                p.left = p.right;
                p.right = temp;
                if (p.left != null){
                    stack.push(p.left);
                }
                if (p.right != null){
                    stack.push(p.right);
                }
            }
        }
    }

    // 递归实现
//    public void Mirror(TreeNode root) {
//        if (root == null){
//            return;
//        }
//        TreeNode left = root.left;
//        TreeNode right = root.right;
//        root.left = right;
//        root.right = left;
//        Mirror(root.left);
//        Mirror(root.right);
//
//    }
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
