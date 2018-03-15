package algorithm;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        shellSort(arr);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }

    public static void shellSort(int[] arr){
        /**
        * @Description: 希尔排序：
         * 思路：又称缩小增量排序，初始时增量为arr.length / 2，下一次为arr.length / 4,...直到最后为1
         * 每一次根据不同的增量取值，将这些值当作一个小组进行插入排序
         * 特性：不稳定
        */

        /***** 对应插入排序中优化版本1 *****/
//        for (int r = arr.length / 2; r >= 1; r/=2){
//            for (int i = r; i < arr.length ; i++) { // 遍历从r~arr.length的数
//                int temp = arr[i];  //0是分组里的第一个数，不用比较，需要比较的是0后面的第一个数：下标为r的数
//                                    // 第二轮时1是第一个数，从r+1开始比较
//                int j = i;
//                while ( j-r >= 0 && temp < arr[j-r]){
//                    arr[j] = arr[j-r];
//                    j = j - r;
//                }
//                arr[j] = temp;
//            }
//        }
        /***** 对应插入排序中优化版本2：最外层循环内，5个r需要重点注意****/
        // （比插入中多了一个j-r>=0的修改,因为插入中r==1，j每次递减1，最后肯定能恰好停在0上停止，
        // 而希尔排序中用j>0, 当j-r为负数时依然会进入循环，会导致越界

        int i,j,r;
        for (r = arr.length / 2; r>=1;  r = r/2){
            for (i = r; i < arr.length; i++){
                int temp = arr[i];
                for (j = i; j-r >= 0; j = j - r){
                    if (temp < arr[j - r]){
                        arr[j] = arr[j - r];
                    }else {
                        break;
                    }
                }
                arr[j] = temp;
            }
        }
    }

}
