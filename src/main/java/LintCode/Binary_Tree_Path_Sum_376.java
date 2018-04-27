package LintCode;

import java.util.*;

// 给定一个二叉树，找出所有路径中各节点相加总和等于给定 目标值 的路径。
//
//一个有效的路径，指的是从根节点到叶节点的路径。

public class Binary_Tree_Path_Sum_376 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode secLeft = new TreeNode(2);
        TreeNode secRight = new TreeNode(4);
        TreeNode thirdLeft = new TreeNode(2);
        TreeNode thirdRight = new TreeNode(3);

        root.left = secLeft;
        root.right = secRight;

        secLeft.left = thirdLeft;
        secLeft.right = thirdRight;

        List<List<Integer>> listList = binaryTreePathSum_r(root, 5);



    }
    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
    public static List<List<Integer>> listList = new ArrayList<>();
    public static List<Integer> list = new ArrayList<>();

        // 递归实现
    public static List<List<Integer>> binaryTreePathSum_r(TreeNode root, int target) {

        if (root == null){
            return listList;
        }

        list.add(root.val);

        if (root.left != null){
            binaryTreePathSum_r(root.left,target);
        }
        if (root.right != null){
            binaryTreePathSum_r(root.right, target);
        }
        if (root.left == null && root.right == null){
            computeRoute(target);
        }
        list.remove(list.size() - 1);

        return listList;
    }
    public static void computeRoute(int target){
        int sum = 0;
        for (int a : list){
            sum += a;
        }
        if (sum == target){
            listList.add(list);
            for (int a : list){
                System.out.println(a);
            }
        }
    }




        // 非递归实现
        public static List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        /** 
        * @Description: 要求：找出树中所有符合目标节点的路径
         * 思路：采用先序遍历的前半部分，使得找到最左节点，每次遍历一个元素，就将元素值加入到sum中，并将已遍历过的路径加入到list中
         * 如果刚好加完一个叶子节点，就判断这个sum值是否符合，若是符合，就将路径加入listList中
         * 无论是否符合，遍历完以后都要将sum减去这个元素值，并从list中剔除这个值，
         * 然后判断处在list最后面的值是否有右节点，如果有就遍历右支，
         * 如果没有，就将最后一个节点删除，取出list中最后一个节点
        */
        List<List<Integer>> listList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = null;
        int sum = 0;

        if (root == null){
            return listList;
        }
        TreeNode p = root;
        while (p != null){    // 找到最左节点，得到第一条路径
            stack.push(p);
            p = p.left;
        }
        TreeNode lastVisit = null;
        while ( !stack.isEmpty() ){
            p = stack.peek();   //只取节点来判断，因为如果是叶子节点，需要计算路径

            if (p.left == null && p.right == null){
                sum = 0;
                list = new ArrayList<>();
                for ( TreeNode node: stack) {
                    list.add(node.val);
                    sum += node.val;
                }
//                stack.pop();    //计算完不论是否满足都删除
                if (target == sum){ //如果满足就添加
                    listList.add(list);

                    for (int a : list) {
                        System.out.println(a);
                    }
                }
            }else if (p.right != null && lastVisit != p.right){
                p = p.right;
                while (p != null){    // 找到最左节点，得到第一条路径
                    stack.push(p);
                    p = p.left;
                }
                continue;   //跳过stack.pop()
            }
                // 1，没有左孩子，有右孩子，但已经访问过
                // 2, 有左孩子，没有右孩子？这种情况不存在
                // 3, 两个孩子都有？不存在
             lastVisit =  stack.pop();
        }
        return listList;
    }

}
