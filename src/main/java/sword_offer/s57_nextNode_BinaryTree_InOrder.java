package sword_offer;

// 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
// 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。


public class s57_nextNode_BinaryTree_InOrder {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // 如果为空树，直接返回
        if (pNode == null){
            return null;
        }
        // 如果节点右孩子存在，则后继节点是右孩子的最左节点，所以要在右支走到最左
        if (pNode.right != null){
            TreeLinkNode p = pNode.right;
            while (p.left != null){
                p = p.left;
            }
            return p;
        }
        // 如果没有右孩子，又没有父节点，则应该返回Null，这在最后返回实现
        // 如果没有右孩子，有父节点，则判断它是不是父节点的左孩子，如果是，直接返回父节点
        while (pNode.next != null){ // 有父节点
            TreeLinkNode parent = pNode.next;
            if (parent.left == pNode){
                return parent;
            }
            // 如果pNode是父节点的右孩子，则要回溯上去，直到该节点所在的分支是某个节点的左支
            pNode = parent;
        }
        return null;    // 根节点，无右支的情况
    }
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}
