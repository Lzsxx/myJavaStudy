package sword_offer;

import temp.Solution;

import java.util.Stack;

// 输入一个链表，输出该链表中倒数第k个结点。
// 思路：用两个指针i和j,当i走了k-1步之后，j也开始走，当i到达tail时，两者之间的下标在数值上相差k-1，所以j就是倒数第k个
public class s14_FindKthToTail {
    /***** 新版：临场发挥版 *****/
    public ListNode FindKthToTail(ListNode head, int k) {
        // 思路：维护两个指针，相差k
        ListNode low = head;
        ListNode high = head;
        for (int i = 0; i < k; i++) { // 跳k次
            if (high == null) {
                return null;
            }
            high = high.next;
        }
        while (high != null) {
            high = high.next;
            low = low.next;
        }
        return low;
    }
    
    
    /***** 旧版 *****/
//    public ListNode FindKthToTail(ListNode head,int k) {
//        if (head == null || k < 1){
//            return null;
//        }
//        ListNode i = head;
//        ListNode j = head;
//        int count = 1;
//        while (i.next != null){
//            i = i.next;
//            count ++;   //count表示这是第几个
//            if (count > k){ //  当count == k时，j = head,当count == k + 1时，j要等于head.next
//                j = j.next;
//            }
//        }
//        if (k > count){     //遍历结束后，count表示总个数，如果k大于总数，要返回null
//            return null;
//        }
//        return j;
//
//    }

    /***** 用栈，没什么技术含量，舍弃 *****/
//    public ListNode FindKthToTail(ListNode head,int k) {
//        if (head == null || k < 1){
//            return null;
//        }
//        Stack<ListNode> stack = new Stack<>();
//        while (head != null){
//            stack.push(head);
//            head = head.next;
//        }
//
//        if (stack.size() >= k){
//            for (int i = 0; i < k - 1; i++) {
//                stack.pop();
//            }
//            return stack.pop();
//        }else {
//            return null;
//        }
//    }
    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
