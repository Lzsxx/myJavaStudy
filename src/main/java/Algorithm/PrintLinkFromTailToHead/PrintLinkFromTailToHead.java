package Algorithm.PrintLinkFromTailToHead;


import java.util.Stack;
// 题目描述：
// 输入一个链表，从尾到头打印链表每个节点的值
public class PrintLinkFromTailToHead {
    public static void main(String[] args) {
        // 输入第一个元素，创建链表
        ListNode listHead = createLinkList(9);
        // 头插法，向链表中添加元素
        listHead = createListNode(listHead,8);
        listHead = createListNode(listHead,7);
        listHead = createListNode(listHead,6);
        listHead = createListNode(listHead,5);
        // 从头到尾打印，看创建结果: 56789
        System.out.println("从头到尾打印：");
        printList(listHead);
        // 从尾到头打印，98765
        System.out.println("从尾到头打印：");
        printListFromTailToHead(listHead);
    }

    // 从尾到头打印
    private static void printListFromTailToHead(ListNode listHead){
//         * 思路如下：
//         * 从头到尾遍历链表，放入栈中，然后输出栈
        ListNode printPointer = listHead;
        Stack<Integer> stack = new Stack<>();
        while (printPointer != null){
            stack.push(printPointer.val);
            printPointer = printPointer.next;
        }
        while ( !stack.empty() ){
            int val = stack.pop();
            System.out.println(val);
        }


    }
    // 从头到尾打印
    private static void printList(ListNode listHead){
        ListNode printPointer = listHead;
        while (printPointer != null){
            int val = printPointer.val;
            System.out.println(val);
            printPointer = printPointer.next;
        }
    }
    // 创建链表第一个元素
    private static ListNode createLinkList(int firstElement){
        return new ListNode(firstElement);
    }
    // 创建链表后续元素
    private static ListNode createListNode(ListNode listHead, int newElement){
        ListNode newNode = new ListNode(newElement);
        newNode.next = listHead;
        return newNode;     //采用头插法，所以新生成的是元素作为链表头返回
    }
    // 链表结构类
    static class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

}
