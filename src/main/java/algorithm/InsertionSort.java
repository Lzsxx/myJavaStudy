package algorithm;

public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        insertSort(arr);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }
    public static void insertSort(int[] arr){
        /**
        * @Description: 插入排序:
         *
         * 思路：从第二个元素开始，与前面的元素比较，如果前面的元素大于它，就将其与前面的元素交换
         * 实际实现时，不用每次都交换，而是用一个临时变量存储将要比较的元素，如果前面的元素有大于它的，就依次往后挪
         * 直到遇到小于它的，就将临时变量的元素移入目前的空缺，然后处理下一个元素
         *
         * 复杂度：o(N^2)
        */

        /*** 最初版本 ***/
//        for (int i = 1; i < arr.length ; i++) {
//            int temp = arr[i];
//            int index = i;
//            for (int j = i; j > 0; j--){  //可以优化的地方，第二个循环其实不必走完，走到第一个temp大于前值的地方就可以停下，
//                                            所以把判断移入进入循环的条件中，如下：
//                if (temp < arr[j-1]){
//                    arr[j] = arr[j-1];
//                    index = j - 1;
//                }
//            }
//            arr[index] = temp;
//        }

        for (int i = 1; i < arr.length ; i++) {
            int temp = arr[i];
            int j = i;
            while (j-1 >= 0 && temp < arr[j-1]){
                arr[j] = arr[j-1];
                j = j - 1;
            }
            arr[j] = temp;
        }
    }

}
