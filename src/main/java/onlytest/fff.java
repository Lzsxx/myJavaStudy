package onlytest;

import json.JSON_Main;
import org.ietf.jgss.Oid;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.HierarchyBoundsAdapter;
import java.text.BreakIterator;
import java.util.Map;

abstract class fff {

      public static void main(String[] args) {
          int[] arr = {5,0,9,4,7,2,3,1};


          System.out.println("Before insertSort:");
          for (int a : arr){
              System.out.println(a);
          }

          insertSort_half(arr);

          System.out.println("After insertSort:");
          print(arr);
      }
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


    public static void radixSort222(int[] arr, int radix, int maxExp){
          // 基数排序：一维数组版
        // 思路：只用一个一维数组，每次遍历，看对应的个位、十位、百位等应该是什么，然后基数对应桶中是多少个
        // 遍历完一次后，对count数组进行整理，然后从后往前输出
        int exp = 0;
        int count[] = new int[radix];
        int[] auxArr = new int[arr.length];
        while (exp < maxExp){
            // 由于有多轮，每次排序前计数器要清空
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
            // 一轮桶排序
            for (int i = 0; i < arr.length; i++) {
                int index =  arr[i] / (int) Math.pow(radix,exp) % radix;
                count[index] ++;
            }
            // 整理count数组
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }
            for (int i = arr.length - 1; i >=0 ; i--) {
                int index =  arr[i] / (int) Math.pow(radix,exp) % radix;
                count[index] --;
                auxArr[count[index]] = arr[i];
            }
            for (int i = 0; i < auxArr.length; i++) {
                arr[i] = auxArr[i];
            }
            exp ++;
        }

    }

      public static void radixSort(int[] arr, int radix, int maxExp){
          // 思路：1、用二维数组，每次取一个数，放到对应的桶中，全部处理完后，再按顺序搬回数组里
          int[][] bucket = new int[radix][arr.length];
          int exp = 0;  //记录这是基数的第几位
          int[] count = new int[radix];
          while (exp < maxExp){
              // 一轮桶排序
              for (int i = 0; i < arr.length; i++) {
                  int index =  arr[i] / (int) Math.pow(radix,exp) % radix;
                  bucket[index][count[index]] = arr[i];
                  count[index] ++;
              }
              //把桶中的数据倒出来
              int k = 0;
              for (int i = 0; i < radix; i++) {
                  for (int j = 0; j < count[i]; j++) {
                      arr[k++] = bucket[i][j];
                  }
                  count[i] = 0; //记得要重置计数器
              }
              exp++;
          }
      }



      public static  void mergeSort(int[] arr){
          // 首先思路是分治法，将一个arr分解成多个，然后一个个merge
          mergeSort(arr,0,arr.length - 1);
      }
      public static  void mergeSort(int[] arr, int low, int high){
          if (low >= high){
              return;
          }
          int mid = low + (high - low) / 2;
          mergeSort(arr, low, mid);
          mergeSort(arr, mid + 1, high);
          merge(arr,low,mid,high);
      }
      public static void merge(int[] arr, int low, int mid, int high){
          // 以mid为分界线的两个数组Merge
          int[] temp = new int[high - low + 1];
          int i = low;
          int j = mid+1;
          int k = 0;
          while (i <= mid && j <= high){
              if (arr[i] <= arr[j]){
                  temp[k] = arr[i];
                    i++;
              }else {
                  temp[k] = arr[j];
                  j++;
              }
              k++;
          }
          while (i <= mid){
              temp[k] = arr[i];
              i++;
              k++;
          }
          while (j <= high){
              temp[k] = arr[j];
              j++;
              k++;
          }
          for (int m = 0; m < temp.length; m++) {
              arr[low + m] = temp[m];
          }
      }

      public static void quickSort(int[] arr){
          // 快速排序：选定一个值，将大于的值移动到右边，小于的值移动到左边，理论上是交换，实际可以优化一下，采用覆盖
          // 维护两个指针，i和j，一个从左，一个从右，
          // 另一个要点是分治法，每次排完序后，以此为分界点，继续分治，重复上面的排序
          quickSort(arr, 0, arr.length - 1);
      }
      public static void quickSort(int[] arr,int low, int high){
          if (low >= high){
              return;
          }
          int mid = getMiddle(arr,low,high);
          quickSort(arr, low, mid - 1);
          quickSort(arr, mid+1, high);
      }
      public static int getMiddle(int [] arr, int low, int high){
          int temp = arr[low];
          int i = low;
          int j = high;

          while ( i < j){
              while (i < j && arr[j] >= temp){
                  j --;
              }
              arr[i] = arr[j];
              while (i < j && arr[i] <= temp){
                  i ++;
              }
              arr[j] = arr[i];
          }
          // 退出循环时，指向同一个值,i=j
          arr[i] = temp;
          return i;
      }




      public static void bubbleSort(int[] arr){
          // 思路：冒泡排序，原理是从前到后，一个个比较，如果前者比后者大，就将两者交换，最终最大值会被交换到最后一个位置
          // 优化：每次交换记录下下标，那么下一次可以从记录的下标处开始，因为记录下标之前的都已经有序了。
            int pos = arr.length;
            int n = arr.length;
            while (pos > 0){
                pos = 0;
                for (int i = 1; i < n; i++) {
                    if (arr[i] < arr[i - 1]){
                        swap(arr, i, i - 1);
                        pos = i;
                    }
                }
                n = pos;
            }
      }

      public static void heapSort(int [] arr){
            // 思路：首先构建大顶堆，然后将大顶堆与最后一个元素交换位置，依次进行。
          // 构建过程：假设现在已经是一个堆，但是还没有调整好，那么就从最后一个叶子节点的父元素开始调整
          int lastLeaf = arr.length - 1 ;   //最后一个叶子节点的下标
          for (int i = (lastLeaf - 1); i >= 0 ; i--) {
              heapSort(arr, i, lastLeaf);   //建堆过程，每次以一个非叶子节点为根，依次调整，并且需要指定最后一个叶子节点的位置，便于结束
          }

          // 交换排序的过程，总体来看是由交换实现选择，所以叫选择排序
          for (int i = 0; i < arr.length - 1; i++) { //执行length-1次
              swap(arr, 0, lastLeaf - i);
              heapSort(arr, 0, lastLeaf - i - 1);
          }
      }
      public static void heapSort(int[] arr, int root, int lastLeaf){
          // 首先每一个传入的root，肯定有左孩子，不一定有右孩子
          // 先判断如果有右孩子，和左孩子比较大小，如果没有，则取左孩子为bigger
          int bigger;

          while (root * 2 + 1 <= lastLeaf){
              int leftChild = root * 2 + 1;
              if ( leftChild < lastLeaf){ //左孩子不是最后一个叶子节点，则一定有右孩子
                  bigger = arr[leftChild ] > arr[leftChild + 1] ? leftChild : leftChild + 1;
              }else {
                  bigger = leftChild;
              }
              if (arr[root] < arr[bigger]){
                  swap(arr, root, bigger);  //如果有交换，那么就还要继续判断子子孩子为root的情况
                  root = bigger;
              }else {
                    // 由于下方的枝叶都已经调整好，如果本轮没有经过变化，那么可以直接返回
                  return;
              }
          }
      }
      public static void selectSort(int[] arr){
          // 选择排序，每次遍历，标记最小值，与当前元素交换位置
          for (int i = 0; i < arr.length; i++) {
              int temp = i;
              for (int j = i + 1; j < arr.length; j++) {
                  if (arr[temp] > arr[j]){
                      temp = j;
                  }
              }
              swap(arr, temp, i);
          }
      }

      public static void shellSort(int[] arr){
          // 又称缩小增量排序，每次一增量为k的值作为一组进行排序，每次增量大小缩小一倍
          int j;
          for (int k = arr.length / 2; k >=1 ; k /= 2) {
              for (int i = k; i < arr.length; i++) {
                  int temp = arr[i];
                  for ( j = i; j - k >=0 ; j = j-k) {
                      if (temp < arr[j-k]){
                          arr[j] = arr[j - k];
                      }else {
                          break;
                      }
                  }
                  arr[j] = temp;
              }
          }
      }
      public static void insertSort(int[] arr){
          // 思路：没遇到一个数，将它插入到合适的位置，可以保证前面i个数是有序的，
          // o(n^2),稳定

          int i, j;
          for (i = 1; i < arr.length; i++) {
              int temp = arr[i];
              for ( j = i; j-1 >= 0; j = j -1) {
                  if (arr[j - 1] > temp ){
                      arr[j] = arr[j - 1];
                  }else {
                      break;
                  }
              }
              arr[j] = temp;
          }

      }


      public static void swap(int[] arr, int i, int j){
          if (i == j){
              return;
          }
          arr[i] = arr[i] + arr[j];
          arr[j] = arr[i] - arr[j];
          arr[i] = arr[i] - arr[j];
      }

      public static void print(int[] arr){
          for (int a : arr){
              System.out.println(a);
          }
      }
}
