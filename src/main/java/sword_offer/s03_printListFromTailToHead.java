package sword_offer;
import java.util.ArrayList;
import java.util.Stack;

// 题目：从尾到头打印链表
// 思路：用栈实现

class ListNode {
       int val;
       ListNode next = null;
       ListNode(int val) {
           this.val = val;
       }
  }
 class printListFromTailToHead {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        // 从尾到头打印链表
        // 思路：用栈存储访问过的元素
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        if (listNode == null){
            return list;
        }

        while ( listNode != null ){
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        while ( !stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }
}
