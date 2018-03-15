package algorithm;

import com.sun.rowset.internal.WebRowSetXmlReader;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {587,0,9322,41,7,232,311,10005};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        radisSort_count(arr,10, 5);  // 十进制数，radix = 10, maxExp表示最大数是多少位，这里是十进制的5位数

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }
    /***** 一维数组 计数排序版本 *****/
    public static void radisSort_count(int[] arr, int radix, int maxExp){
        int[] count = new int[radix];
        int[] tempArr = new int[arr.length];
        int exp = 0;
        while (exp < maxExp){
            // 由于有多轮，每次排序前计数器要清空
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }

            // 计数
            for (int a : arr){
                int index = (a / (int) Math.pow(radix,exp) ) % radix;
                count[index] ++;
            }
            // 计数累加
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 排序
            for (int i = arr.length - 1; i >= 0; i--) {
                int index = ( arr[i]/ (int) Math.pow(radix,exp) ) % radix;
                count[index] --;
                int tempIndex = count[index] ;
                tempArr[tempIndex] = arr[i];
            }
            for (int i = 0; i < tempArr.length; i++) {
                arr[i] = tempArr[i];
            }
            exp ++;

            print(arr);

        }



    }




    /***** 二维数组版本 *****/
    public static void radisSort(int[] arr, int radix, int maxExp){
        int[][] bucket = new int[radix][arr.length];
        int[] count = new int[radix];
        int exp = 0;    // 指数，结合radix取得当前判断的是个位数还是十位数的信息，
                        // 以10进制数为例，用LSD，radix(10) ^ exp(0) = 1(个位)， radix(10) ^ exp(1) = 10;

        while (exp < maxExp) {  // maxExp指的是直观的看有多少位，实际的指数位应该-1，而exp<maxExp使得当exp达到maxExp时就停止

            // 遍历原数组，放入桶中
            for (int a : arr){
                int index = (a / (int) Math.pow(radix,exp) ) % radix;
                bucket[index][ count[index] ] = a;
                count[index] ++;
            }

            // 将桶中的数依次放入原数组中
            int arrIndex = 0;
            for (int i = 0; i < radix; i++) {   //有radix个桶
                for (int j = 0; j < count[i]; j++) {
                    arr[arrIndex] = bucket[i][j];
                    arrIndex ++;
                }
                count[i] = 0;
            }
            exp++;  //下一次处理下一数位
            print(arr);
        }
    }

    public static void print(int[] arr){
        System.out.println("==================");
        for (int a : arr){
            System.out.println(a);
        }
    }
}
