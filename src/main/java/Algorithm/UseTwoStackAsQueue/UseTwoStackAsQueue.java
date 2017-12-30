package Algorithm.UseTwoStackAsQueue;

import java.util.Stack;

//题目描述
//用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。

public class UseTwoStackAsQueue {
    public static void main(String[] args) {
        MyQueue.EnQueue_ByTwoStack(1);
        MyQueue.EnQueue_ByTwoStack(2);
        MyQueue.EnQueue_ByTwoStack(3);
        MyQueue.EnQueue_ByTwoStack(4);
        MyQueue.EnQueue_ByTwoStack(5);

        System.out.println(MyQueue.DeQueue_TwoStack());
        System.out.println(MyQueue.DeQueue_TwoStack());
        System.out.println(MyQueue.DeQueue_TwoStack());
        System.out.println(MyQueue.DeQueue_TwoStack());
        System.out.println(MyQueue.DeQueue_TwoStack());
        // 栈空
        System.out.println(MyQueue.DeQueue_TwoStack());
        System.out.println(MyQueue.DeQueue_TwoStack());
    }
}


class MyQueue{
    // A用于入栈
    private static Stack<Integer> A_stack = new Stack<>();
    // B用于出栈
    private static Stack<Integer> B_stack = new Stack<>();

    public static void EnQueue_ByTwoStack(int element){
        A_stack.push(element);
    }
    public static int DeQueue_TwoStack(){
        if (B_stack.isEmpty()){
            while ( !A_stack.empty() ){
                B_stack.push(A_stack.pop());
            }
        }
        if (B_stack.empty()){
            return -1;      // 如果B栈为空，返回-1作为标志
        }

        return B_stack.pop();
    }
}