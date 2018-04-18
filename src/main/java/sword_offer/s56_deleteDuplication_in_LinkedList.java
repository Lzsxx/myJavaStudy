package sword_offer;

// 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
// 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

// 思路：遍历，先建一个头节点，然后向后遍历，只有单前一个与后一个不同时，才将前一个加入链表

public class s56_deleteDuplication_in_LinkedList {
    public static void main(String[] args) {
        s56_deleteDuplication_in_LinkedList test = new s56_deleteDuplication_in_LinkedList();
        ListNode t1 = new ListNode(1);
        ListNode t2 = new ListNode(1);
        ListNode t3 = new ListNode(1);
        ListNode t4 = new ListNode(1);
        ListNode t5 = new ListNode(1);
        ListNode t6 = new ListNode(1);
        ListNode t7 = new ListNode(1);
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        t4.next = t5;
        t5.next = t6;
        t6.next = t7;

        ListNode temp = test.deleteDuplication(t1);

        while (temp != null){
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
    public ListNode deleteDuplication(ListNode pHead)
    {
        if (pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode newHead = new ListNode(-1);
        newHead.next = pHead;

        ListNode lastSafe = newHead;
        ListNode p = pHead;
        while ( p != null && p.next != null ){
            if (p.val == p.next.val){
                int value = p.val;
                while ( p != null && p.val == value ){ //需要跳过的
                    p = p.next;
                }
                lastSafe.next = p;  // 跳过重复元素后，指向下一个，但这不稳定，如果下一轮发现它也重复，则还会改变指向
            }else { // 可以加入链表的
                lastSafe = p;   // 确定当前指向的next没有重复，移动向下
                p = p.next;
            }
        }
        return newHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}