package MyJavaCollection.util.tree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTree {

    private static class AvlNode{
        private int element;
        private int height;
        AvlNode left;
        AvlNode right;

        public AvlNode(int element, AvlNode left, AvlNode right) {
            this.element = element;
            this.right = right;
            this.left = left;
            this.height = 0;
        }
        public AvlNode(int element) {
            this(element,null, null);
        }

        private int compareTo(int x){
            return x - this.element;
        }
    }
    private AvlNode root;

    public AvlTree() {
        this.root = null;
    }
    // 二叉树清空
    public void makeEmpty(){
        root = null;
    }
    // 二叉树判空
    public boolean isEmpty(){
        return root == null;
    }


    /***  封装外部调用 **/
    public void insert(int x){
        root = insert(x, root);
    }


    /***  具体内部调用 **/
    private AvlNode insert(int x, AvlNode t){
        if (t == null){
            t = new AvlNode(x); // 改变t的指向为新元素
        } else {
            // else中不会改变指向，只会沿着链一直往下走，直到走到Null，进入上面的循环
            int compareResult = t.compareTo(x);
            if (compareResult < 0){
                t.left = insert(x, t.left);
            }else if (compareResult > 0){
                t.right = insert(x, t.right);
            }else{
                ;
            }
        }
        t.height = Max(getHeight(t.left), getHeight(t.right)) + 1;
        return balance(t);
    }

    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode balance(AvlNode t){
        if (t == null){
            return t;
        }
        int leftSubHeight = getHeight(t.left);
        int rightSubHeight = getHeight(t.right);

        if (leftSubHeight - rightSubHeight > ALLOWED_IMBALANCE){
            // 新插入在左儿子
            if (getHeight(t.left.left) >= getHeight(t.left.right)){ //【这里用>=,是为了考虑删除时，两边树深相等，要采用单旋转】
                // 新添加的在左儿子的左子树上，LL，要求左儿子向右旋转
                t = LL_Rotate(t);
            }else {
                // 新加在左儿子的右子树上，LR，要求左儿子的右儿子先向左旋转，左儿子再向右旋转
                //【注意，最后旋转左儿子时，此时的左儿子节点可能已经改变，但依然是用t.left来表示，也就是说，左儿子指代的是那个位置，而不是固定的某个值】
                t = LR_Rotate(t);
            }
        }else if (rightSubHeight - leftSubHeight > ALLOWED_IMBALANCE){
            // 新插入在右儿子
            if (getHeight(t.right.left) >= getHeight(t.right.right)){
                // 新添加的在右儿子的左子树上，RL，要求右儿子的左儿子先向右旋转，右儿子再向左旋转
                t = RL_Rotate(t);
            }else {
                // 新加在右儿子的右子树上，RR，要求右儿子向左旋转
                t = RR_Rotate(t);
            }
        }
        return t;
    }
    // LL情况，向右旋
    private AvlNode LL_Rotate(AvlNode t){
        // 左儿子向右上旋转为中心节点
        // 中心节点变为左儿子的右孩子，新left指向原左儿子的右子树
        System.out.println("LL_Rotate");
        AvlNode leftChild = t.left;
        t.left = leftChild.right;
        leftChild.right = t;

        t.height = Max(getHeight(t.left), getHeight(t.right)) + 1;
        leftChild.height = Max(getHeight(leftChild.left), getHeight(leftChild.right)) + 1;

//        System.out.println("t.height="+t.height +"\nleftChild.height="+leftChild.height);

        return leftChild;
    }
    private AvlNode RR_Rotate(AvlNode t){
        // 右儿子向左上旋转为中心节点
        // 中心节点变为右儿子的左孩子，新right指向原右儿子的左子树
        System.out.println("RR_Rotate");
        AvlNode rightChild = t.right;
        t.right = rightChild.left;
        rightChild.left = t;

        t.height = Max(getHeight(t.left), getHeight(t.right)) + 1;
        rightChild.height = Max(getHeight(rightChild.left), getHeight(rightChild.right)) + 1;

//        System.out.println("t.height="+t.height +"\nrightChild.height="+rightChild.height);

        return rightChild;
    }
    private AvlNode LR_Rotate(AvlNode t){
        // 先左旋，后右旋
        // 左儿子的右孩子左旋，左儿子本身右旋
        System.out.println("LR_Rotate");
        t.left = RR_Rotate(t.left);
        return LL_Rotate(t);
    }
    private AvlNode RL_Rotate(AvlNode t){
        // 先右旋，再左旋
        // 右儿子的左孩子右旋，右儿子本身左旋
        System.out.println("RL_Rotate");
        t.right = LL_Rotate(t.right);
        return RR_Rotate(t);
    }


    //// 未测试
    public AvlNode remove(int x, AvlNode t ){
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
            AvlNode rightMinNode = findMin(t.right);
            t.element = rightMinNode.element;
            t.right = remove(rightMinNode.element, t.right); //需要等号，因为insert和remove会改变结构，需要靠此连接

        }else {
            /** 子树个数为0或1，**/
            // 如果是叶子节点则直接删除，即：将父节点指向null
            // 如果有一个子节点，不管在左边还是在右边，都是将其顶上父节点的位置即可
            // 所以这里也不用判断是从左路下来的还是从右路下来的
            t = (t.left != null ) ? t.left : t.right;
        }
        t.height = Max(getHeight(t.left), getHeight(t.right)) + 1;
        return balance(t);  // 比普通二叉查找树多了这里
    }
    private AvlNode findMin(AvlNode t ){
        if (t == null)  // 空树的情况
            return null;

        // 如果左子树不为null，就一直往左走，直到找到叶子节点
        if (t.left == null){    // 会提前判空，走到叶子节点就终止，t不会走到null
            return t;
        }
        return findMin(t.left);
    }
    private int getHeight(AvlNode t){
        return t == null ? -1 : t.height;
    }
    private int Max(int a, int b){
        return a > b ? a : b;
    }
    public AvlNode getRoot(){
        return this.root;
    }
    public void printTree(AvlNode root){
        if (root == null){
            System.out.println("This is an empty tree!");
            return;
        }
        /** 开始遍历 */
        Queue<AvlNode> queue = new LinkedList<AvlNode>();
        AvlNode p = root;

        queue.add(root);
        while ( !queue.isEmpty() ){
            p = queue.remove();
            System.out.println(p.element + "   height=" + p.height);
            if (p.left != null){
                queue.add(p.left);
            }
            if (p.right != null){
                queue.add(p.right);
            }
        }
    }

}
