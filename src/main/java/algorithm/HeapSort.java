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
        // 建堆
        int lastLeaf = arr.length - 1;
        for (int i = (lastLeaf - 1) / 2; i >= 0 ; i--) {
            heapSort(arr, i, lastLeaf);
        }

        // [0]与lastLeaf交换
        for (int j = 0; j < arr.length - 1; j++) {  // 循环0 ~ N-1次,会取到arr.length-2,然后后面swap是交换0和1的位置，调整时调整只有一个根节点的树，此时只是进入函数，不会进入while循环，不做调整，所以不会影响任何
            swap(arr, 0 , lastLeaf - j);    // 随着循环，最后一个子节点的下标也会变化
            heapSort(arr, 0, lastLeaf - j - 1);
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
