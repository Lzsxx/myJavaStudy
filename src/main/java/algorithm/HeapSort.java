package algorithm;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        heapSort(arr);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }

    //
    public static void heapSort(int[] arr){
        // 思路，从根节点向上一点点调整，先建起一棵大顶堆，然后再排序
        int lastParent = (arr.length - 1) / 2;
        for (int i = lastParent; i >= 0; i--) {
            heapSort(arr, i, arr.length - 1);
        }
        // 开始交换，调整
        for (int i = 1; i < arr.length; i++) {
            swap(arr, 0, arr.length - i);
            heapSort(arr, 0, arr.length - i - 1);
        }
    }
    public static void heapSort(int[] arr, int root, int lastLeaf){
        int bigger;
        while (root * 2 + 1 <= lastLeaf){   //有左子节点
            int left = root * 2 + 1;
            int right = root * 2 + 2;
            if (right  <= lastLeaf){     // 有右子节点
                bigger = arr[left] > arr[right] ? left : right;
            }else {
                bigger = left;
            }
            // 建立大顶堆
            if (arr[root] < arr[bigger]){
                swap(arr,root,bigger);
                root = bigger;
            }else{
                break;
            }

        }
    }
    public static void swap(int[] arr, int a, int b){
        if (a == b)
            return;
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b]; // 等于arr[a]
        arr[a] = arr[a] - arr[b]; // 等于arr[b]
    }

    public static void print(int[] arr){
        for (int a:arr) {
            System.out.println(a);
        }
    }

}
