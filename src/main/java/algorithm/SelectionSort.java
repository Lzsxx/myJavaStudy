package algorithm;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        selectionSort(arr);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }

    public static void selectionSort(int[] arr){
        /**
        * @Description:选择排序：
         * 思路：每次选择最小的一个元素往前放
        */
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++){// i前面的为全局有序，所以每次只从i开始往后找其中的最小值
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            swap(arr,i,min);
        }
    }

    public static void swap(int[] arr, int a, int b){
        if (a == b)
            return;
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b]; // 等于arr[a]
        arr[a] = arr[a] - arr[b]; // 等于arr[b]
    }

}
