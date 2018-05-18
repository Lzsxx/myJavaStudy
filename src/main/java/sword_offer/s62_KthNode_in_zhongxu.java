package sword_offer;
// 给定一颗二叉搜索树，请找出其中的第k大的结点。例如， 5 / \ 3 7 /\ /\ 2 4 6 8 中，
// 按结点数值大小顺序第三个结点的值为4。

import java.util.LinkedList;

// 思路：中序遍历，输出第k个的时候
public class s62_KthNode_in_zhongxu {
    int count = 0;
    TreeNode p = null;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if (pRoot == null){
            return p;
        }
        KthNode(pRoot.left, k);
        count ++;
        if (count == k){
            p = pRoot;
        }
        KthNode(pRoot.right, k);
        return p;
    }

    // 非递归版
//    TreeNode KthNode(TreeNode pRoot, int k)
//    {
//        // 中序遍历
//        TreeNode p = pRoot;
//        LinkedList<TreeNode> stack = new LinkedList<>();
//        while (p != null) {
//            stack.push(p);
//            p = p.left;
//        }
//        int count = 0;
//        while (!stack.isEmpty()) {
//            p = stack.pop();
//            count ++;
//            if (count == k) {
//                return p;
//            }
//            if (p.right != null) {
//                p = p.right;
//                while (p != null) {
//                    stack.push(p);
//                    p = p.left;
//                }
//            }
//        }
//        return null;
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
