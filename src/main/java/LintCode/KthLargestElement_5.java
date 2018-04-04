package LintCode;

import algorithm.QuickSort;
import com.sun.org.apache.bcel.internal.generic.SWAP;
import spider.Test;
import sun.awt.image.ShortInterleavedRaster;

import java.text.BreakIterator;

public class KthLargestElement_5 {
    private int kth;
    public static void main(String[] args) {
        KthLargestElement_5 kth = new KthLargestElement_5();
        int[] arr = {1,2,3,4,5,6,8,9,10,7};
        int kth2 = kth.kthLargestElement_quick(10,arr);
        System.out.println(kth2);

    }

    // 堆排序实现
    public int kthLargestElement(int k, int[] nums) {
        // write your code here
        // 先建立大根堆，再用交换的方法，依次排序到第4个（也就是堆排序的一部分）
        // 建堆
        if (nums.length < 1 || nums.length < k){
            return -1;
        }

        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length - 1);
        }
        // 开始交换
//        for (int i = 1; i < nums.length ; i++) {    // 1 ~ nums.length - 1
        for (int i = 1; i <= k ; i++) {    // 1 ~ nums.length - 1
            swap(nums, 0, nums.length - i);
            adjustHeap(nums,0, nums.length - i - 1);
        }
        return nums[nums.length - k];
    }
    public void adjustHeap(int[] nums, int root, int lastLeaf){
        // 思路：比较左右子节点，将大的与根节点交换，然后下移继续看
        int bigger;

        while (root * 2 + 1 <= lastLeaf){
            int leftChild = root * 2 + 1;
            int rightChild = root * 2 + 2;
            if (leftChild < lastLeaf) { //一定有右子节点
                bigger = nums[leftChild] > nums[rightChild] ? leftChild : rightChild;
            }else { //没有右子节点
                bigger = leftChild;
            }
            // 比较根节点和最大子节点
            if (nums[root] < nums[bigger]){
                swap(nums, root, bigger);
                root = bigger;
            }else {
                break;
            }
        }

    }
    public static void swap(int[] arr, int a, int b){
        if (a == b)
            return;
        arr[a] = arr[a] ^ arr[b];
        arr[b] = arr[a] ^ arr[b]; // 等于arr[a]
        arr[a] = arr[a] ^ arr[b]; // 等于arr[b]
    }

    // 快排算法实现
    public int kthLargestElement_quick(int k, int[] nums) {
        if (nums.length < 1 || nums.length < k){
            return -1;
        }
        quickSort(nums, 0, nums.length-1, k-1); //其实是找下标为k-1的数
        return kth;
    }
    public  void quickSort(int[] arr, int low, int high, int k){
        int middle = getMiddle(arr, low, high); //每一趟排序完成后，就可以确定一个元素的位置，middle已确定
        // 后面不用两边都再分治，只需要根据K值确定一边即可
        if (middle == k){
            kth = arr[middle];
        }else if (k > middle ){
            quickSort(arr, middle + 1, high, k); //k在右边
        }else {
            quickSort(arr, low, middle - 1, k);
        }
    }

    public  int getMiddle(int[] arr, int low, int high){
        int temp = arr[low];
        while (low < high){
            while (low < high && arr[high] <= temp){    //与从小到大排列时的情况相反，符号变反，用从大到小的方式排序
                high --;
            }
            arr[low] = arr[high];     // low一开始指向阈值，而阈值已经存储在temp中，所以可以写覆盖，后续循环中此处的low都已被写到它处，依旧可以写覆盖，下方的high同理
            while (low < high && arr[low] >= temp){
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;    // low==high,也可以arr[high] = temp;
        return low;
    }
}
