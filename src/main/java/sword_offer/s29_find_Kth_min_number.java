package sword_offer;

// 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。

// 思路：用堆排序

import java.util.ArrayList;

public class s29_find_Kth_min_number {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (k <= 0 || input.length < k){
            return new ArrayList<>();
        }
        return kthMinElement(k, input);
    }

    // 堆排序实现
    public ArrayList<Integer> kthMinElement(int k, int[] nums) {
        // write your code here
        // 先建立小根堆，再用交换的方法，依次排序到第4个（也就是堆排序的一部分）
        // 建堆
        if (nums.length < 1 || nums.length < k){
            return null;
        }

        for (int i = (nums.length - 1) / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length - 1);
        }

        // 开始交换
//        for (int i = 1; i < nums.length ; i++) {    // 1 ~ nums.length - 1
        for (int i = 1; i <= k ; i++) {    // 1 ~ k, 交换k次，不全部交换完
            swap(nums, 0, nums.length - i);
            adjustHeap(nums,0, nums.length - i - 1);
        }

        ArrayList<Integer>  result = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            result.add(nums[nums.length - i]);
        }
        return result;
    }
    public void adjustHeap(int[] nums, int root, int lastLeaf){
        // 思路：比较左右子节点，将大的与根节点交换，然后下移继续看
        int smaller;

        while (root * 2 + 1 <= lastLeaf){
            int leftChild = root * 2 + 1;
            int rightChild = root * 2 + 2;
            if (leftChild < lastLeaf) { //一定有右子节点
                smaller = nums[leftChild] < nums[rightChild] ? leftChild : rightChild;
            }else { //没有右子节点
                smaller = leftChild;
            }
            // 比较根节点和最大子节点
            if (nums[root] > nums[smaller]){
                swap(nums, root, smaller);
                root = smaller;
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
}
