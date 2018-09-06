package sword_offer;

// 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
// 要求不能创建任何新的结点，只能调整树中结点指针的指向。

import jdk.nashorn.internal.ir.IfNode;

import javax.swing.*;
import java.security.acl.LastOwnerException;
import java.util.LinkedList;

// 思路：
public class s26_Convert_BinaryTree_doubleLink {
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = pRootOfTree;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }

        TreeNode rt = stack.peek();

        TreeNode lastVisit = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            // 中序遍历
            curr.left = lastVisit;
            if (lastVisit != null) {
                lastVisit.right = curr;
            }
            lastVisit = curr;

            // 右子分支进栈
            if (curr.right != null) {
                p = curr.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }
        return rt;
    }
    // 临场发挥版，非递归
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode p = pRootOfTree;
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        TreeNode head = stack.peek();   // 此时栈顶的元素，是最左端的元素，也就是链表的开头
        TreeNode last = null;
        while (!stack.isEmpty()) {
            p = stack.pop();
            p.left = last;  // 指向前驱,如果是第一个元素，前驱会指向null
            if (last != null) {
                last.right = p; //将上一个元素的后继指向本元素
            }
            last = p;
            if (p.right != null) {
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
            }
        }
        // 退出循环时的p，是最后一个元素,补充最后一个元素的后继指向null
        p.right = null;
        return head;
    }




    // 递归版
//    public TreeNode lastVisit = null;
//    public TreeNode Convert(TreeNode pRootOfTree) {
//        if (pRootOfTree == null){
//            return null;
//        }
//        baseconvert(pRootOfTree);
//        // 此时lastVisit指向链表表尾，移动到表头，返回
//        while (lastVisit != null && lastVisit.left != null){
//            lastVisit = lastVisit.left;
//        }
//        return lastVisit;
//    }
//    public void baseconvert(TreeNode root) {
//        if (root == null){
//            return;
//        }
//        TreeNode p = root;
//        // 中序遍历
//        baseconvert(root.left);
//        // 第一次是从null返回回来，左支最小的一个节点
//        p.left = lastVisit; //改变左指向
//        if (lastVisit != null){
//            lastVisit.right = p;    // 如果上一个节点非空，改变右指向
//        }
//        lastVisit = p;  //当前节点处理完以后，转换指向，供下一次使用
//        baseconvert(root.right);
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
