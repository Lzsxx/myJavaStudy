package sword_offer;
//定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。

// 修改思路：题目中已经import了一个Stack，那么就借用已有的，不用自己弄数组，那么就只需要维护min就可以了

import java.util.Stack;

// 思路：首先，用一个数组来存储，要保证ensureCapacity
// 每次push时顺序写入，pop时从最后一个下标处取值
// 需要注意的是，每次添加和删除都要维护min的下标
public class s20_MyStack_getMin {
    Stack<Integer> stack = new Stack<>();
    int min = Integer.MAX_VALUE;
    public void push(int node) {
        stack.push(node);
        if (node < min){
            min = node;
        }
    }

    public void pop() {
        int topValue = top();
        stack.pop();
        if (topValue == min){
            min = Integer.MAX_VALUE;
            for(int s : stack){
                if (s < min){
                    min = s;
                }
            }
        }

    }

    public int top() {
        if (stack.isEmpty()){
            return -1;
        }
        return stack.peek();
    }

    public int min() {
        if (stack.isEmpty()){
            return -1;
        }
        return min;
    }
}
