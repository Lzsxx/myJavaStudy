package sword_offer;

import java.util.Stack;

// 题目：用两个栈来实现队列
class s05_two_stack_implement_queue {
    // 思路，每次加入队列，就将非空的栈全部pop()，
     Stack<Integer> stack_temp = new Stack<Integer>();
     Stack<Integer> stack = new Stack<Integer>();

     public void push(int node) {
         if(stack.isEmpty()){
             stack.push(node);
         }else {
             while ( !stack.isEmpty()){
                 stack_temp.push(stack.pop());
             }
             stack_temp.push(node);
             while (!stack_temp.isEmpty()){
                 stack.push(stack_temp.pop());
             }
         }
     }
     public int pop() {
         if (stack.isEmpty()){
             return -1;
         }
         return stack.pop();
     }
}
