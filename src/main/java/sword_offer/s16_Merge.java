package sword_offer;
//输入两个单调递增的链表，输出两个链表合成后的链表，
// 当然我们需要合成后的链表满足单调不减规则。
public class s16_Merge {
    public ListNode Merge(ListNode list1,ListNode list2) {
        if (list1 == null || list2 == null){
            return list1 == null ? list2 : list1;
        }
        ListNode curr = null;
        if (list1.val >= list2.val){ //取较小的值
            curr = list2;
            list2 = list2.next;
        }else {
            curr = list1;
            list1 = list1.next;
        }
        ListNode head = curr;
        while (list1 != null && list2 != null){
            if (list1.val >= list2.val){ //取较小的值
                curr.next = list2;
                curr = list2;
                list2 = list2.next;
            }else {
                curr.next = list1;
                curr = list1;
                list1 = list1.next;
            }
        }
        while (list1 != null){
            curr.next = list1;
            curr = list1;
            list1 = list1.next;
        }
        while (list2 != null){
            curr.next = list2;
            curr = list2;
            list2 = list2.next;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
