package test;


import java.util.*;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        int[] arr = {2,3,4,2,6,2,5,1};
        char[] matrix = {'A', 'B', 'C', 'E', 'S', 'F', 'C', 'S', 'A', 'D', 'E', 'E'};
        char[] str = {'A', 'B', 'C', 'C', 'E', 'D'};
//        System.out.println(test.hasPath(matrix, 3, 4, str));
        ;
    }

//    int count = 0;
    public int InversePairs(int [] array) {
        long count = mergeSort_split(array, 0, array.length - 1);
        return (int) count % 1000000007;
    }
    public static long mergeSort_split(int[] arr, int low, int high){
        long count = 0;
        int mid = (low + high) / 2;
        if (low < high){
            count += mergeSort_split(arr, low, mid);
            count += mergeSort_split(arr, mid+1, high);
            count += merge(arr,low,high);
        }
        return count % 1000000007;
    }
    public static long merge(int[] arr, int low, int high){
        // 传入进来的数组arr实际上包含了要被merge的两个数组，以mid为分界线
        // 第一个数组[low, mid]
        // 第二个数组[mid+1, high]
        long count = 0;
        int mid = (low + high) / 2;
        int sum = high - low + 1;
        int[] temp = new int[sum];

        int i = low;    // 第一个数组的起始指针
        int j = mid + 1;    // 第二个数组的起始指针
        int k = 0;
        while ( i <= mid && j <= high){
            if (arr[i] > arr[j]){
                temp[k] = arr[j];   // 前面大，后面小，每次把小的去掉，如果前面的某一个比当前的大，那么其后面的都比这个数大
                count += mid - i + 1;
                j++;
            }else {
                temp[k] = arr[i];
                i++;
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
        return  count % 1000000007;
    }




    public void swap(char[] chs, int a, int b) {
        if (a == b) {
            return;
        }
        char temp = chs[a];
        chs[a] = chs[b];
        chs[b] = temp;
    }




    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }



}
