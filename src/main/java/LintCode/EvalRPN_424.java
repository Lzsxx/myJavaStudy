package LintCode;

import java.util.Stack;

 class EvalRPN {
    public static void main(String[] args) {
        EvalRPN evalRPN = new EvalRPN();
        String[] strings = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN.evalRPN(strings));

    }

    public int evalRPN(String[] tokens) {
        // write your code here
        // 逆波兰式，后缀表达式，思路:遇到数字就入栈，遇到符号就将头两个数字出栈，计算后入栈
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ( tokens[i].equals("+") || tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/")){
                if (stack.size() < 2){
                    return -1;
                }
                int a = Integer.parseInt(stack.pop());
                int b = Integer.parseInt(stack.pop());
                if (tokens[i].equals("+")){
                    stack.push( Integer.toString(a + b));
                }else if (tokens[i].equals("-")){
                    stack.push( Integer.toString(b - a));
                }
                else if (tokens[i].equals("*")){
                    stack.push( Integer.toString(a * b));
                }
                else if (tokens[i].equals("/")){
                    stack.push( Integer.toString(b / a));
                }
            }else {
                stack.push(tokens[i]);
            }
        }
        if (stack.size() == 1){
            return Integer.parseInt(stack.pop());
        }else {
            return  -1;
        }
    }
}
