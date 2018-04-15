package sword_offer;

import java.util.LinkedList;
import java.util.Queue;

// 题目：给出二叉树的前序和中序遍历，要求重建二叉树
//  思路：分析，递归
class Test{
    public static void main(String[] args) {
        reConstructBinaryTree_04 test = new reConstructBinaryTree_04();
        int[] pre = {1,2,3,4,5,6,7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};
        TreeNode root = test.reConstructBinaryTree(pre, in);
        traverse(root);
    }

    public static void traverse(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode p = queue.poll();
            System.out.println(p.val);
            if (p.left != null){
                queue.offer(p.left);
            }
            if (p.right != null){
                queue.offer(p.right);
            }
        }
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  class reConstructBinaryTree_04 {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length != in.length || pre.length < 1){
            return null;
        }
        return reBuild(pre,0,pre.length - 1, in, 0, in.length - 1);
    }
    public TreeNode reBuild(int [] pre,int preLow, int preHigh,int [] in, int inLow, int inHigh){
        // first
        if (preLow < 0 || preHigh < 0 || preLow >= pre.length || preHigh >= pre.length){
            return null;
        }
        if (inLow < 0 || inHigh < 0 || inLow >= in.length || inHigh >= in.length){
            return null;
        }
        if (preLow > preHigh || inLow > inHigh){
            return null;
        }
        TreeNode root = new TreeNode(pre[preLow]);

        for (int i = inLow; i <= inHigh; i++) {
            if (in[i] == pre[preLow]){  //在中序中找到根节点，然后分别重建左右分支
                int leftNum = -1;
                leftNum = i - inLow;
                // 划分左右子树，分别重建
                root.left = reBuild(pre, preLow + 1, preLow + leftNum, in, inLow,i - 1);
                root.right = reBuild(pre, preLow + leftNum + 1, preHigh, in, i + 1,inHigh);
                break;

//                if (i == inLow){    //只有右支
//                    int leftNum = i - inLow;
//                    root.left = null;
//                    root.right = reBuild(pre, preLow + leftNum + 1, preHigh, in, i + 1,inHigh);
//                }else if (i == inHigh) {    //只有左支
//                    int leftNum = i - inLow;
//                    root.left = reBuild(pre, preLow + 1, preLow + leftNum, in, inLow,i - 1);
//                    root.right = null;
//                }else { //左右都有
//
//                }
            }
        }
        return root;
    }
}
