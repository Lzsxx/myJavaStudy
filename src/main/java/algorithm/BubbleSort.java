package algorithm;

import sun.util.locale.provider.FallbackLocaleProviderAdapter;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        bubbleSort(arr);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }
    public static void bubbleSort(int[] arr){
        /**
        * @Description: 冒泡排序：
         * 思路：每次将一个元素与后一个元素比较，如果前者大，就将前者与后者交换，交换后再将后者与后者的后者交换
         * 最后会将最大值冒泡到最后，然后开始下一次遍历，最外层一共要遍历n次，内层第一轮比较n-1次，第二轮比较n-2次
         *
         * 优化版本：如果第二轮内每次比较都是前者大于后者，则说明已经是有序状态，此时就可以设置flag然后停止外层循环，结束算法
        */

        /***** 最初版本 *****/
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 1; j < arr.length - i; j++){
//                if (arr[j] < arr[j - 1]){
//                    swap(arr, j,j-1);
//                }
//            }
//        }

        /***** 优化版本 1 *****/
//        boolean flag = true;
//        int n = arr.length;
//        while (flag){   // 第一层循环
//            flag = false;
//            for (int j = 1; j < n; j++) {   //第二层循环
//                if (arr[j] < arr[j - 1]){
//                    swap(arr, j, j-1);
//                    flag = true;
//                }
//            }
//            n--;
//        }

        /***** 优化版本 2 *****/
        /**
        * @Description: 版本1用boolean来标注是否进行了交换，然后提前结束外层循环，而内层循环每次边界值-1
         * 但实际上可以记录上一次最后交换的位置来进一步缩小内层循环的边界值，
         * 版本2中flag既是是否交换的flag，又是内层循环的边界值
        */
        int n = arr.length;
        int flag = n;
        while (flag > 0){
            flag = 0;
            for (int j = 1; j < n; j++){
                if (arr[j] < arr[j - 1]){
                    swap(arr, j , j-1);
                    flag = j;
                }
            }
            n = flag;
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
