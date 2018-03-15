package algorithm;

public class CountSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        arr = countSort(arr,9);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }

    public static int[] countSort(int[] arr, int border){
        int[] count = new int[border + 1] ;  // border为9, 0~9占位为10
        int[] newArr = new int[arr.length];

        for (int a : arr) {
            count[a] ++;    // 完成计数
        }
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];     //完成计数累加
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = -- count[ arr[i] ] ;   //先取arr中的值，根据值在count中确定在新数组中的下标，不过这个下标要-1才能用，并且-1要写回count数组
            newArr[index] = arr[i];
        }
        return newArr;
    }
}
