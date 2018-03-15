package onlytest;

import sun.reflect.generics.tree.Tree;

import javax.swing.tree.TreeNode;
import java.time.temporal.ValueRange;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] arr = {5,0,9,4,7,2,3,1};

        System.out.println("Before insertSort:");
        for (int a : arr){
            System.out.println(a);
        }

        heapSort(arr);

        System.out.println("After insertSort:");
        for (int a : arr){
            System.out.println(a);
        }
    }
    /***** 堆排序 *****/
    public static void heapSort(int[] arr){
        // 建堆
        int lastLeaf = arr.length - 1;
        for (int i = (lastLeaf - 1) / 2; i >= 0 ; i--) {
            heapSort(arr, i, lastLeaf);
        }

        // [0]与lastLeaf交换
        for (int j = 0; j < arr.length - 1; j++) {  // 循环0 ~ N-1次
            swap(arr, 0 , lastLeaf - j);    // 随着循环，最后一个子节点的下标也会变化
            heapSort(arr, 0, lastLeaf - j - 1);
        }
    }
    public static void heapSort(int[] arr, int root, int lastLeaf){
        int bigger;
        while (root * 2 + 1 <= lastLeaf){   //有左子节点
            int left = root * 2 + 1;
            int right = root * 2 + 2;
            if (right  <= lastLeaf){     // 有右子节点
                bigger = arr[left] > arr[right] ? left : right;
            }else {
                bigger = left;
            }
            // 建立大顶堆
            if (arr[root] < arr[bigger]){
                swap(arr,root,bigger);
                root = bigger;
            }else{
                break;
            }

        }
    }



    /***** 归并排序 *****/
    public static void mergeSort_split(int[] arr, int low, int high){
        if (low >= high){
            return; //递归出口
        }
        int middle = (low + high) / 2;
        mergeSort_split(arr,low, middle);
        mergeSort_split(arr, middle+1, high);
        merge(arr,low,high);
    }
    public static void merge(int[] arr, int low, int high){
        // 第一组 [low, middle]
        // 第二组 [middle+1,high]
        int middle = (low + high) / 2;
        int i = low;
        int j = middle + 1;
        int k = 0;
        int[] temp = new int[high - low + 1];
        while (i <= middle && j <= high){
            if (arr[i] < arr[j]){
                temp[k] = arr[i];
                i++;
            }else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }
        while ( i <= middle){
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j <= high){
            temp[k] = arr[j];
            j++;
            k++;
        }
        for (int l = 0; l < temp.length; l++) {
            arr[low + l] = temp[l];
        }

    }



    /***** 快速排序 *****/
    public static void quickSort(int[] arr,int low, int high){
        // 思路：选取一个基值作为temp，维护两个指针i,j分别从前往后和从后往前，j先开始移动，如果遇到小于temp的就停下
        // i遇到大于temp的就停下，i与j交换，直到两者相遇,一轮完成，再分而治之，递归完成

        if (low >= high){
            return; // 递归出口
        }
        int middle = getMiddle(arr,low,high);
        quickSort(arr,low, middle - 1);
        quickSort(arr,middle + 1, high);
    }
    public static int getMiddle(int[] arr, int low, int high){
        int temp = arr[low];
        while (low < high){
            while (low < high && temp <= arr[high]){
                high --;
            }
            arr[low] = arr[high];
            while (low < high && temp >= arr[low]){
                low ++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }





    /***** 冒泡排序 *****/
    public static void bubbleSort(int[] arr){
        // 思路：从前向后遍历，如果后一个数比前一个数大，就交换
        System.out.println("Bubble Sort");
        int i = 0;
        int flag = arr.length;
        int n = arr.length;

        while (flag > 0 && i < arr.length){
            flag = 0;
            for (int j = 1; j < n ; j++) {
                if (arr[j - 1] > arr[j]){
                    swap(arr,j,j-1);
                    flag = j;
                }
            }
            n = flag;
            i ++;
        }
    }


    /***** 选择排序 *****/
    public static void selectSort(int[] arr){
        // 每次选择最小值放在相应的位置
        System.out.println("S1elect Sort");
        int i,j;
        for ( i = 0; i < arr.length; i++) {
            int min = i;
            for (j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[min]){
                    min = j;
                }
            }
            swap(arr,min,i);
        }
    }
    public static void  swap(int[] arr, int a, int b){
        if (a == b){
            return;
        }
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    /***** 希尔排序 *****/
    public static void shellSort(int[] arr){
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

    /***** 插入排序 *****/
    public static void insertSort(int[] arr){
        // 思路：从前向后遍历，遇到一个值，如果比前面的小，就移动到前面，可以保证前k个值一定是有序的

        int i,j;
        for ( i = 1; i < arr.length; i++) {
            int temp = arr[i];
            for ( j = i; j > 0; j--){
                if (temp < arr[j - 1]){
                    arr[j] = arr[ j - 1];   // remove to next
                }else {
                    System.out.println("When break.the j is:"+j);
                    break;// 到小于等于的地方就停止
                }
            }
            arr[j] = temp;
        }

    }

}