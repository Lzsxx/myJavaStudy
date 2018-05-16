package test;


import com.sun.java.swing.plaf.windows.WindowsGraphicsUtils;
import jdk.nashorn.internal.ir.IfNode;
import jdk.nashorn.internal.ir.JoinPredecessor;
import sun.awt.image.ImageWatched;
import sun.util.locale.provider.FallbackLocaleProviderAdapter;

import javax.smartcardio.ATR;
import javax.swing.*;
import java.io.FileOutputStream;
import java.sql.SQLClientInfoException;
import java.util.Stack;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        int[] arr = {2,4,3,6,3,2,5,5};
        int[] a = new int[1];
        int[] b = new int[1];
        System.out.println(2 & 2);
//        System.out.println(test.InversePairs(arr));;
        test.FindContinuousSequence(15);
    }

    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > lists = new ArrayList<>();
        if (sum <= 1) {
            return lists;
        }
        int n = (int) Math.sqrt(2 * sum);
        n = n > 2 ? n : 2;  // 确保n大于等于2
        for (int i = n; i >= 2 ; i--) {
            int avg = sum / i;  // 如果可能，平均数应为avg
            if ((i & 1) == 0) { // 如果 i 是偶数
                if ((avg + 0.5) * i == sum) {   // 这种情况下，以这个平均数类推可以得到正确的总数
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(avg);
                    list.add(avg + 1);
                    for (int j = 1; j < i / 2; j++) {
                        list.add(avg - j);
                        list.add(avg + 1 + j);
                    }
                    Collections.sort(list);
                    lists.add(list);
                }
            }else {     // 如果 i 是奇数
                if (avg * i == sum) {   //此时类推可以得到正确的sum
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(avg);
                    for (int j = 1; j <= i / 2; j++) {
                        list.add(avg - j);
                        list.add(avg + j);
                    }
                    Collections.sort(list);
                    lists.add(list);
                }
            }

        }
        return lists;
    }








    public int TreeDepth(TreeNode root) {
        int max = 0;
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return max;
        }
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode last = null;
        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();
            if (p.left == null && p.right == null) {
                int temp = stack.size();
                if (temp > max) {
                    max = temp;
                }
            }
            if (last != p.right && p.right != null) {
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
                continue;
            }
            last = stack.pop();
        }
        return max;
    }


    public int findMin(int a, int b, int c) {
        int min = a < b ? a : b;
        min = min < c ? min : c;
        return min;
    }

    public static void swap(int[] arr, int a, int b){
        if (a == b) {
            return;
        }
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b];
        arr[a] = arr[a] ^ arr[b];
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        if (root == null) {
            return lists;
        }
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode last = null;
        while (!stack.isEmpty()) {
            TreeNode p = stack.peek();
            if (p.left == null && p.right == null) {
                ArrayList<Integer> path = new ArrayList<>();
                int sum = 0;
                for (TreeNode t : stack) {
                    path.add(t.val);
                    sum += t.val;
                }
                if (sum == target) {
                    Collections.reverse(path);
                    lists.add(path);
                }
            }
            if (last != p.right && p.right != null) {
                p = p.right;
                while (p != null) {
                    stack.push(p);
                    p = p.left;
                }
                continue;
            }
            last = stack.pop();
        }
        return lists;
    }

    public void itr(ArrayList<ArrayList<Integer>> lists,ArrayList<Integer> path, TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left != null) {
            itr(lists, path, root.left, target);
        }
        if (root.right != null) {
            itr(lists, path, root.right, target);
        }
        if (root.left == null && root.right == null) {
            int sum = 0;
            for (int a : path) {
                sum += a;
            }
            if (sum == target) {
                ArrayList<Integer> temp = new ArrayList<>(path);
                lists.add(temp);
            }

        }
        path.remove(path.size() - 1);
    }


    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
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
