package temp;

import java.util.HashMap;

public class Solution{

      public void Mirror(TreeNode root) {
            exchange(root);
      }
      public void exchange(TreeNode root) {
            if (root == null) {
                  return;
            }
            if (root.left != null && root.right != null) {
                  TreeNode temp = root.left;
                  root.left = root.right;
                  root.right = temp;
                  exchange(root.left);
                  exchange(root.right);
            } else if (root.right == null && root.left == null) {
                  return;
            } else if (root.left != null && root.right == null) {
                  root.right = root.left;
                  root.left = null;
                  exchange(root.right);
            } else {
                  root.left = root.right;
                  root.right = null;
                  exchange(root.left);
            }
      }






      public class ListNode {
            int val;
            ListNode next = null;

            ListNode(int val) {
                  this.val = val;
            }
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