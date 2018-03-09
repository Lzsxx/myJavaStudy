package onlytest;

import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.time.temporal.ValueRange;
import java.util.*;

public class Solution {
      public class TreeNode {
          public int val;
          public TreeNode left, right;

          public TreeNode(int val) {
              this.val = val;
              this.left = this.right = null;
          }
    }

    public List<Integer> preorderTraversalWithOut(TreeNode root) {
          if (root == null){
              return null;
          }
          List<Integer> list = new ArrayList<>();
          Stack<TreeNode> stack = new Stack<>();
          list.add(root.val);
          if (root.right != null){
              stack.push(root.right);
          }
          if (root.left != null){
              stack.push(root.left);
          }
          while ( !stack.isEmpty() ){
              TreeNode p = stack.pop();
              list.add(p.val);
              if (p.right != null){
                  stack.push(p.right);
              }
              if (p.left != null){
                  stack.push(p.left);
              }
          }
          return list;
    }


}