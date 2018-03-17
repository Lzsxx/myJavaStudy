package LintCode;

import sun.awt.windows.WLightweightFramePeer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Paths_480 {
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

        List<String> stringList = binaryTreePaths(root);
        for (String s : stringList) {
            System.out.println(s);
        }


    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static List<String> stringList = new ArrayList<>();
    public static List<TreeNode> list = new ArrayList<>();

    // 递归实现
    public static List<String> binaryTreePaths_r(TreeNode root) {

        // write your code here
        if (root == null) {
            return stringList;
        }
        list.add(root);
        if (root.left != null) {
            binaryTreePaths_r(root.left);
        }
        if (root.right != null) {
            binaryTreePaths_r(root.right);
        }
        if (root.left == null && root.right == null) {
            String string = "";

            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    string += list.get(i).val;
                } else {
                    string += list.get(i).val + "->";
                }
            }
            stringList.add(string);
        }
        list.remove(list.size() - 1);
        return stringList;
    }

    // 非递归实现
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> stringList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if (root == null) {
            return stringList;
        }
        TreeNode p = root;
        while (p != null) {
            stack.push(p);
            p = p.left; // 走到最左边为止
        }

        TreeNode lastVisit = null;
        while (!stack.isEmpty()) {
            p = stack.peek();
            if (p.left == null && p.right == null) {
                generateRoute(stringList, stack);
            } else if (p.right != null && lastVisit != p.right) {
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
                continue;
            }
            lastVisit = stack.pop();
        }
        return stringList;
    }
    public static void generateRoute (List<String> stringList, Stack<TreeNode> stack){
        String string = "";
        for (int i = 0; i < stack.size(); i++) {
            if (i == stack.size() - 1) {
                string += stack.get(i).val;
            } else {
                string += stack.get(i).val + "->";
            }
        }
        stringList.add(string);
    }
}