package sword_offer;

// 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
//思路：用后序遍历，每次返回的时候，同时返回子树的高度，判断是否大于2
public class s39_IsBalanceTree {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    // 临时发挥版
    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftDepth = 0;
        int rightDepth = 0;

        if (root.left != null) {
            leftDepth = countDeepth(root.left);
        }
        if (root.right != null) {
            rightDepth = countDeepth(root.right);
        }
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }else {
            return true;
        }

    }

    public int countDeepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int currDepth = getMax(countDeepth(root.left), countDeepth(root.right)) + 1;
        return currDepth;
    }

    public int getMax(int a, int b) {
        return a > b ? a : b;
    }


//    public boolean IsBalanced_Solution(TreeNode root) {
//        if (root == null){
//            return true;
//        }
//        int leftHeight = computeHeight(root.left);
//        int rightHeight = computeHeight(root.right);
//
//        if (Math.abs(leftHeight - rightHeight) > 1){
//            return false;
//        }
//        return true;
//    }
//    public int computeHeight(TreeNode root){
//        if (root == null){
//            return 0;
//        }
//        if (root.left == null && root.right == null){
//            return 1;   // 叶子节点高度为1
//        }
//        int leftHeight = computeHeight(root.left);
//        int rightHeight = computeHeight(root.right);
//
//        int subHeight = leftHeight > rightHeight ? leftHeight : rightHeight;
//         // 加上本层的高度
//        return ++subHeight;
//    }
}
