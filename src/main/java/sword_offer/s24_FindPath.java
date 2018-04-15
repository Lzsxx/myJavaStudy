package sword_offer;

import java.util.ArrayList;

//输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
// 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。

// 思路：优先采用递归思想；深度优先遍历；用一个list记录走过的路径，当这个节点处理完以后（已经从左右支递归归来）
// 就将该节点从list中删除
// 当遍历到叶子节点，就判断target
public class s24_FindPath {
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null || target < 0){
            return arrayList;
        }
        traverse(root, target);
        return arrayList;
    }
    public void traverse(TreeNode root, int target){
        if (root == null){
            return ;
        }
        list.add(root.val);
        if (root.left == null && root.right == null){
            int sum = 0;
            ArrayList<Integer> tempList = new ArrayList<>();
            for(int a : list){
                sum += a;
                tempList.add(a);
            }
            if (sum == target){
                arrayList.add(tempList);
            }
        }
        traverse(root.left, target);
        traverse(root.right, target);
        list.remove(list.size() - 1);
    }

     class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
