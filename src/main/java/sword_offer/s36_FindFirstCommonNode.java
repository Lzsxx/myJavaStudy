package sword_offer;

// 输入两个链表，找出它们的第一个公共结点。

// 思路：第一个公共节点以后的，应该都是相同的公共节点，所以相当于一条拉链，
// 那么先计算出两个链表的长度，让长的先走length1 - length2步，然后再同时next比较
public class s36_FindFirstCommonNode {
    public ListNode FindFirstCommonNode(ListNode pHeadA, ListNode pHeadB) {
        if (pHeadA == null || pHeadB == null){
            return null;
        }
        if (pHeadA == pHeadB){
            return pHeadA;
        }
        int lengthA = 0;
        int lengthB = 0;
        ListNode tempA = pHeadA;
        ListNode tempB = pHeadB;
        while (pHeadA != null){
            lengthA ++;
            pHeadA = pHeadA.next;
        }
        while (pHeadB != null){
            lengthB ++;
            pHeadB = pHeadB.next;
        }
        ListNode LongList;
        ListNode ShortList;
        int steps = 0;
        if (lengthA > lengthB) {
            LongList = tempA;
            ShortList = tempB;
            steps = lengthA - lengthB;
        } else {
            LongList = tempA;
            ShortList = tempB;
            steps = lengthB - lengthA;
        }
        for (int i = 0; i < steps; i++) {
            LongList = LongList.next;
        }
        while (LongList != null && ShortList != null){
            if (LongList == ShortList){
                return LongList;
            }else {
                LongList = LongList.next;
                ShortList = ShortList.next;
            }
        }
        return null;
    }
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
