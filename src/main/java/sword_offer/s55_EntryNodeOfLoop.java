package sword_offer;

// 一个链表中包含环，请找出该链表的环的入口结点。

// 思路：维护一快一慢两个指针，当两者相遇时，一定在环里，此时将一者移动到起点，重新一步步走，相遇处一定是入口

public class s55_EntryNodeOfLoop {
    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null){
            return null;
        }
        ListNode slow = pHead.next;
        ListNode fast = pHead.next.next;
        while (slow != fast){
            slow =slow.next;
            fast = fast.next.next;
        }
        fast = pHead;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

}
