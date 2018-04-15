package sword_offer;

import java.util.Stack;

// 题目：计算斐波那契数列的第n项
public class s07_Fibonacci {
    public int Fibonacci(int n) {
        if (n == 1){
            return 1;
        }else if (n > 1){
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
        return 0;
    }
    // 非递归版本
    public int Fibonacci2(int n) {
        if (n <= 0){
            return 0;
        }
        if (n == 1 || n == 2){
            return 1;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        for (int i = 3; i <= n; i++) {
            int a = stack.pop();
            int b = stack.pop();
            int result = a + b;
            // 将最近的一个数和新结果PUSH回栈
            stack.push(a);
            stack.push(result);
        }
        return stack.peek();
    }

}
