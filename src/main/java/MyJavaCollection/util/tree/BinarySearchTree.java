package MyJavaCollection.util.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree  {

    private class BinaryNode {
        private int element;
        private BinaryNode left;
        private BinaryNode right;

        public BinaryNode(int element, BinaryNode left, BinaryNode right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public BinaryNode(int element) {
            this(element, null, null);
        }

        public int getElement() {
            return element;
        }

        public int compareTo(int o) {
            return o - this.element;
        }
    }

    private BinaryNode root;

    public BinarySearchTree() {
        root = null;    // 初建二叉树，根为null
    }
    // 二叉树清空
    public void makeEmpty(){
        root = null;
    }
    // 二叉树判空
    public boolean isEmpty(){
        return root == null;
    }

    /** 具体方法的简单封装*/
    public void insert(int x){
        root = insert(x, root);
    }
    public void remove(int x ){
        root = remove(x, root);
    }
    public int findMin( BinaryNode t,boolean isPublic ){    // isPublic为了实现重载，
        BinaryNode node = findMin(t);
        return node.element;
    }
    public int findMax( BinaryNode t,boolean isPublic ) {    // isPublic为了实现重载，
        BinaryNode node = findMax(t);
        return node.element;
    }
    public boolean contains(int x ){
        return contains(x, root);
    }




        /** 以下是方法的具体实现* */

    //// 下面是主要方法，主要有 insert , remove , contains, findMin, findMax,
    // 其中，insert和remove会改变结构，返回都是BinaryNode, findXXX不改变结构，但本身要求返回BinaryNode
    // contains不改变结构，本身要求返回boolean
    private BinaryNode insert(int x, BinaryNode t ){

        if (t == null){ // 先确定初始时传入空树的情况
            return new BinaryNode(x);
        }
        // 不为空时，要与中间节点依次比较大小，找到一个叶子节点，
        // 根据与叶子节点的大小关系，确定是在左端还是在右端
        int compareResult = t.compareTo(x); // 若 x > t.element ,则返回大于0

        if (compareResult < 0){
            t.left = insert(x, t.left);
        }else if (compareResult > 0){
            t.right = insert(x, t.right);
        }else { // 等于0,默认不添加，有些情况可能需要添加计数，这里不要求
            ;
        }
        return t;   // 如果是等于0，或者是从>0或者<0走下来的，则t都是之前传入的值，所以以前的指向不会改变，
    }
    public BinaryNode remove(int x, BinaryNode t ){
        // 如果初始传入空树，返回null
        if (t == null)
            return null;

        // 先遍历找到目标节点
        int compareResult = t.compareTo(x);
        if (compareResult < 0 ){
             t.left = remove(x, t.left);
        }else if (compareResult > 0){
             t.right = remove(x, t.right);
        }else if(t.left != null && t.right != null) {
            /** 找到了目标，但要判断子树个数是否为2 **/
            //有两个子树时，用右子树的最小值，或左子树的最大值来替代应该删除的值，然后再递归删除那个被替代的值

            // 用右子树最小值的情况
            BinaryNode rightMinNode = findMin(t.right);
            t.element = rightMinNode.element;
            t.right = remove(rightMinNode.element, t.right); //需要等号，因为insert和remove会改变结构，需要靠此连接

        }else {
            /** 子树个数为0或1，**/
            // 如果是叶子节点则直接删除，即：将父节点指向null
            // 如果有一个子节点，不管在左边还是在右边，都是将其顶上父节点的位置即可
            // 所以这里也不用判断是从左路下来的还是从右路下来的
            t = (t.left != null ) ? t.left : t.right;
        }
        return t;
    }
    private BinaryNode findMin( BinaryNode t ){
        if (t == null)  // 空树的情况
            return null;

        // 如果左子树不为null，就一直往左走，直到找到叶子节点
        if (t.left == null){    // 会提前判空，走到叶子节点就终止，t不会走到null
            return t;
        }
        return findMin(t.left);
    }
    private BinaryNode findMax( BinaryNode t ){
        if (t == null)  // 空树的情况
            return null;
        while (t.right != null){ // 会提前判空，走到叶子节点就终止，t不会走到null
            t = t.right;
        }
        return t;
    }
    private boolean contains(int x, BinaryNode t ){
        if (t == null ){
            return false;
        }
        int compareResult = t.compareTo(x);
        if (compareResult < 0 ){
            return contains(x, t.left);
        }else if (compareResult > 0){
            return contains(x, t.right);
        }else {
            return true;
        }


    }
    public BinaryNode getRoot(){
        return this.root;
    }
    public void printTree(BinaryNode root){
        if (root == null){
            System.out.println("This is an empty tree!");
            return;
        }
        /** 开始遍历 */
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        BinaryNode p = root;

        queue.add(root);
        while ( !queue.isEmpty() ){
            p = queue.remove();
            System.out.println(p.element);
            if (p.left != null){
                queue.add(p.left);
            }
            if (p.right != null){
                queue.add(p.right);
            }
        }
    }

}
