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

        /***** 优化版本1 *****/
//        for (int i = 1; i < arr.length ; i++) {
//            int temp = arr[i];
//            int j = i;
//            while (j-1 >= 0 && temp < arr[j-1]){
//                arr[j] = arr[j-1];
//                j = j - 1;
//            }
//            arr[j] = temp;
//        }
        /***** 优化版本2 : 4个1需要重点注意*****/
        int i,j;
        for ( i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for ( j = i; j > 0; j--){   // j = j - 1
                if (temp < arr[j - 1]){
                    arr[j] = arr[ j - 1];   // remove to next
                }else {
                    System.out.println("When break.the j is:"+j);
                    break;// 到小于等于的地方就停止
                }
            }
            arr[j] = temp;
        }
//        public static void insertSort(int[] arr){
//            for (int i = 1; i < arr.length; i++) {
//                int j = i;
//                int temp = arr[j];
//                for (; j - 1 >= 0; j--) {
//                    if (arr[j - 1] > temp) {
//                        arr[j] = arr[j - 1];
//                    }else {
//                        break;
//                    }
//                }
//                arr[j] = temp;
//            }
//        }
    }
/***** 优化版本3：折半插入 *****/
    public static void insertSort_half(int[] arr){
        int i, j;
        for (i = 1; i < arr.length; i++) {
            int temp = arr[i];
            // 查找插入点
            int low = 0;
            int high = i - 1;   //这里要留一个i，不能取到i,因为如果前面的全部都小于arr[i]，二分查找的Low会指向high+1的位置，此时正好是i
            while (low <= high){
                int mid = (low + high) / 2;
                if (temp > arr[mid]){
                    low = mid + 1;  //mid前面的已经可以安全跳过，mid+1是大于或者等于
                }else {
                    high = mid - 1;
                }
            }
            // low是要插入的地方
            for (int k = i; k > low ; k--) {
                arr[k] = arr[k-1];
            }
            arr[low] = temp;
        }

    }

}
