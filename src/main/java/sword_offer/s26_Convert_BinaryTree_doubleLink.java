package sword_offer;

// 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
// 要求不能创建任何新的结点，只能调整树中结点指针的指向。

import jdk.nashorn.internal.ir.IfNode;

import javax.swing.*;
import java.security.acl.LastOwnerException;

// 思路：
public class s26_Convert_BinaryTree_doubleLink {
    public TreeNode lastVisit = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null){
            return null;
        }
        baseconvert(pRootOfTree);
        // 此时lastVisit指向链表表尾，移动到表头，返回
        while (lastVisit != null && lastVisit.left != null){
            lastVisit = lastVisit.left;
        }
        return lastVisit;
    }
    public void baseconvert(TreeNode root) {
        if (root == null){
            return;
        }
        TreeNode p = root;
        // 中序遍历
        baseconvert(root.left);
        // 第一次是从null返回回来，左支最小的一个节点
        p.left = lastVisit; //改变左指向
        if (lastVisit != null){
            lastVisit.right = p;    // 如果上一个节点非空，改变右指向
        }
        lastVisit = p;  //当前节点处理完以后，转换指向，供下一次使用
        baseconvert(root.right);

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
