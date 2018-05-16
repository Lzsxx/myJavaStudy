package sword_offer;

//输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
// 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）

// 思路：由于当一个节点的random指出去的时候，该节点可能还没创建，所以没法直接顺序复制
// 巧妙：第一轮先在原链表上复制，新节点加在被复制节点的后面，random指针先不处理
// 第二轮，再遍历依次，处理random指针
// 第3轮：将被复制节点和新节点间的指针断开，形成两个链表

public class s25_ComplexLinkList_Clone {
    // 临场发挥版
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null) {
            return null;
        }
        RandomListNode p = pHead;

        // 先复制,后断开链表
        while (p != null) {
            RandomListNode temp = p.next;
            RandomListNode copyNode = new RandomListNode(p.label);
            copyNode.next = p.next;
            p.next = copyNode;
            p = temp;
        }
        // 处理random
        p = pHead;
        while (p != null) {
            if (p.random != null) {
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        // 断开，同时处理random
        p = pHead;
        RandomListNode newHead = pHead.next;
        while (p != null) {
            RandomListNode copyNode = p.next;
            if (copyNode.next != null) {
                // 还原原来的指针，random没有动过，不用修正
                p.next = copyNode.next;

                // 修正复制节点的两个指针
                copyNode.next = copyNode.next.next;
            }else {
                p.next = null;
            }
            // 指向下一个节点
            p = p.next;
        }
        return newHead;
    }

//    public RandomListNode Clone(RandomListNode pHead)
//    {
//        if (pHead == null){
//            return null;
//        }
//        RandomListNode p = pHead;
//        // 第一轮，复制节点
//        while (p != null){
//            RandomListNode cloneP = new RandomListNode(p.label);
//            cloneP.random = null;
//            cloneP.next = p.next;
//            p.next = cloneP;
//            p = cloneP.next;
//        }
//        // 第二轮；处理random
//        p = pHead;
//        while (p != null){
//            if (p.random != null){
//                p.next.random = p.random.next;
//            }
//            p = p.next.next;
//        }
//        // 第三轮：断开新旧链接
//        p = pHead;
//        RandomListNode newHead = p.next;
//        while ( p != null ){
//            RandomListNode nextP = p.next;
//            if (p.next != null){    //要注意处理p.next为null的情况，因为while循环里面并没有处理
//                p.next = p.next.next;
//            }else {
//                p.next = null;
//            }
//            p = nextP;
//        }
//        return newHead;
//    }
    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
