package algorithm;

import java.time.temporal.Temporal;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {5,0,9,4,7,2,3,1};
        int[] arr = {1,2,3,4,5,6,8,9,10,7};
        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        quickSort(arr,0,arr.length-1);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }

    public static void quickSort(int[] arr, int low, int high){
        if (low >= high){
            return;     // 递归退出的出口
        }
        int middle = getMiddle(arr, low, high); //每一趟排序完成后，就可以确定一个元素的位置，middle已确定
        quickSort(arr, low, middle - 1);    // 所以下面分别从middle-1和middle+1开始
        quickSort(arr, middle + 1, high);
    }

    /***** 最初版--通过硬交换实现，由于多次swap读写内存，速度慢 *****/
//    public static int getMiddle(int[] arr, int low, int high){
//        int temp = arr[low];    // 取第一个数为阈值
//        int index = low;
//
//        while (low < high){
//            while (low < high && arr[high] >= temp){
//                high --;
//            }
//            swap(arr, high, index); // 如果是前面的while循环由于high = low了，也是由于high靠向了low,此时的index=low,high=low,所以index=low
//            index = high;
//            while (low < high && arr[low] <= temp){ //等于的就略过，比如第一个作为阈值的值
//                low++;
//            }
//            swap(arr, low, index);
//            index = low;
//        }
//        // low和high每次只会+1或者-1，一旦出现相等，就会推出所有while循环，所以退出时一定有low=high
//        return low;
//    }

    /***** 小改进版：由于swap中会改写两个元素的值，改进版采用写覆盖，先用temp记录哨兵的值，最后才将哨兵写入 *****/
    public static int getMiddle(int[] arr, int low, int high){
        int temp = arr[low];
        while (low < high){
            while (low < high && arr[high] >= temp){
                high --;
            }
            arr[low] = arr[high];     // low一开始指向阈值，而阈值已经存储在temp中，所以可以写覆盖，后续循环中此处的low都已被写到它处，依旧可以写覆盖，下方的high同理
            while (low < high && arr[low] <= temp){
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;    // low==high,也可以arr[high] = temp;
        return low;
    }




    public static void swap(int[] arr, int a, int b){
        if (a == b)
            return;
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b]; // 等于arr[a]
        arr[a] = arr[a] - arr[b]; // 等于arr[b]
    }
}
