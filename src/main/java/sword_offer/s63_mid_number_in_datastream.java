package sword_offer;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.Comparator;
import java.util.PriorityQueue;

//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
// 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

// 思路：当目前总数为偶数时，将新加入的数送入大根堆，然后筛选出最大的元素，放进小根堆
// 当总数为奇数时，新加入的元素经过小根堆筛选，放入大根堆，

// 当要取中位数时，如果此时总数为偶数，就取两堆的堆顶，求平均值，
// 当总数为奇数时，取小根对的堆顶

public class s63_mid_number_in_datastream {

    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(11, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
        //PriorityQueue默认是小顶堆，实现大顶堆，需要反转默认排序器
            return o2.compareTo(o1);
        }
    });
    int count = 0;
    public void Insert(Integer num) {
        if ((count & 1) == 0){  // 偶数
            maxHeap.offer(num);
            int leftMax = maxHeap.poll();   // 筛选出最大元素
            minHeap.offer(leftMax); // 大根堆里的最大，放入小根堆,大根堆相当于所有数据流的左边部分，小根对相当于右半部分
        }else {
            // 此时总数为奇数
            minHeap.offer(num);
            int rightMin = minHeap.poll();
            maxHeap.offer(rightMin);
        }
        count ++;
    }

    public Double GetMedian() {
        if ((count & 1) == 0) {  // 偶数,取平均值
            return (double) ( maxHeap.peek() + minHeap.peek() ) / 2;
        }else { // 奇数，由于为偶数时，最终是加到minHeap里，加完之后变奇数，所以此时应该去minHeap里面取
            return (double) minHeap.peek();
        }
    }
}
