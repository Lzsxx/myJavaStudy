package sword_offer;

//给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}
// 及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
// 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}，
// {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。

// 思路，用双端队列实现,当遇到一个比较大的值时，如果不是最大值，将队列中小于其的数字都删除，然后将最大值取出，判断是否还在窗口里
// 当遇到一个小于所有的值时，要加入队列，同样也要判断最大值是否在窗口里
// 如何判断，即当前处理的元素的下标，与最大值的下标，相差大于等于size值，当前处理的元素是滑动窗口的最右值

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class s64_maxInSlideWindows {

    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> list = new ArrayList<>();
        if (num.length < 1 || size == 0){
            return list;
        }
        Deque<Integer> deque = new LinkedList<>();
        // 预处理
        for (int i = 0; i < size - 1; i++) {
            while (!deque.isEmpty() && num[i] >= num[deque.getLast()]){
                deque.removeLast();
            }
            deque.addLast(i);   // 加入当前值
        }
        // 正式处理，将最大值加入list
        for (int i = size - 1; i < num.length; i++) {  // 窗口前面的值已经完成加入和判断
            // 每次进入，移比当前除小的元素，然后判断最顶端的元素是否在队列中

            while (!deque.isEmpty() && num[i] >= num[deque.getLast()]){
                deque.removeLast();
            }
            deque.addLast(i);   // 加入当前值
            if (i - deque.getFirst() >= size ){ // 判断最大值是否还在窗口中
                // 不在，移除
                deque.removeFirst();
            }
            list.add(num[deque.getFirst()]);
        }
        return list;
    }
}
