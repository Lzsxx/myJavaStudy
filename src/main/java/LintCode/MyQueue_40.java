package LintCode;

import java.util.Stack;

public class MyQueue_40 {
    private Stack<Integer> stack_push = new Stack<>();
    private Stack<Integer> stack_pop = new Stack<>();

    public MyQueue_40() {
        // do intialization if necessary
        stack_push.empty();
        stack_pop.empty();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        if ( stack_push.isEmpty() && !stack_pop.isEmpty()){
            while ( !stack_pop.isEmpty() ){
                stack_push.push(stack_pop.pop());
            }
        }
        stack_push.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (stack_push.isEmpty() && stack_pop.isEmpty() ){
            return -1;
        }
        else if ( !stack_push.isEmpty() && stack_pop.isEmpty()){
            while ( !stack_push.isEmpty() ){
                stack_pop.push(stack_push.pop());
            }
        }
        return stack_pop.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        if (stack_push.isEmpty() && stack_pop.isEmpty() ){
            return -1;
        }
        else if ( !stack_push.isEmpty() && stack_pop.isEmpty()){
            while ( !stack_push.isEmpty() ){
                stack_pop.push(stack_push.pop());
            }
        }
        return stack_pop.peek();
    }
}
