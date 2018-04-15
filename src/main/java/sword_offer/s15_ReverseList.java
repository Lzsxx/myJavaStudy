package sword_offer;

public class s15_ReverseList {
    public ListNode ReverseList(ListNode head) {
        if (head == null){
            return null;
        }
        ListNode tempHead = head;
        ListNode recordNext = null;
        ListNode recordPre = null;
        while (tempHead != null){
            recordNext = tempHead.next; //先记录起来，后续赋值
            tempHead.next = recordPre;  //上一个访问的值，是我们这次要指向的值
            recordPre = tempHead;   //记录下现在的头节点，下一次指向这个头节点
            tempHead = recordNext;  //移动向下一个值
        }
        return recordPre;
    }

    private class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
