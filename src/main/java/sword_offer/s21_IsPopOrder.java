package sword_offer;

import java.util.LinkedList;
//输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
// 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
// 序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的
// 弹出序列。（注意：这两个序列的长度是相等的）

// 思路：依次比较A和B的元素，如果B中待比较的数与A中目前待比较的数相同，则不入栈，
// 如果不同，再比较B[i]和栈顶的数，如果相同，则出栈，如果还是不同，则将A当前数入栈
// A移动向下一个数，B不动
public class s21_IsPopOrder {
    public static void main(String[] args) {
        s21_IsPopOrder test = new s21_IsPopOrder();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        System.out.println(test.IsPopOrder(a, b));
    }
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length != popA.length){
            return false;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (i < pushA.length){
            if (stack.size() == 0){
                if (pushA[i] == popA[j]){
                    j ++;
                    i ++;
                }else {
                    stack.push(pushA[i]);
                    i ++;
                }
            }else {
                // 相等的情况，先入栈再出栈，干脆就不做操作
                if (pushA[i] == popA[j]){
                    i ++;
                    j ++;
                }else if (stack.peek() == popA[j]){ //栈顶相同，出栈
                    stack.pop();
                    j ++;
                }else { //都不相同，入栈
                    stack.push(pushA[i]);
                    i ++;
                }
            }
        }
        // 全部元素入栈后，到这里可能已经都出栈了（两个数组完全相等的情况，也可能还有元素留在栈中）
        while ( !stack.isEmpty() && j < popA.length){
            if (stack.pop() != popA[j]){
                return false;
            }
            j ++;
        }
        // 意外退出
        if (!stack.isEmpty() || j < popA.length){
            return false;
        }
        // 经过重重考验，
        return true;
    }
}
