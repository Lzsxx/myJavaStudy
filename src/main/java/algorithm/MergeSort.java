package algorithm;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        mergeSort_split(arr,0,arr.length-1);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }
    public static void mergeSort_split(int[] arr, int low, int high){
        int mid = (low + high) / 2;
        if (low < high){
            mergeSort_split(arr, low, mid);
            mergeSort_split(arr, mid+1, high);
            merge(arr,low,high);
        }
        /***** 隐含的递归出口 *****/
//        else {
//            return;
//        }
    }
    public static void merge(int[] arr, int low, int high){
        // 传入进来的数组arr实际上包含了要被merge的两个数组，以mid为分界线
        // 第一个数组[low, mid]
        // 第二个数组[mid+1, high]
        int mid = (low + high) / 2;
        int sum = high - low + 1;
        int[] temp = new int[sum];

        int i = low;    // 第一个数组的起始指针
        int j = mid + 1;    // 第二个数组的起始指针
        int k = 0;
        while ( i <= mid && j <= high){
            if (arr[i] < arr[j]){
                temp[k] = arr[i];
                i++;
            }else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while ( i <= mid){
            temp[k] = arr[i];
            i++;
            k++;
        }
        while ( j <= high){
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (int m = 0; m < sum; m++){
            arr[low + m] = temp[m];
        }
    }
}
