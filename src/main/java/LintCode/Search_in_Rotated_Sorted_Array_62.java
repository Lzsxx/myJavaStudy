package LintCode;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

class Search_in_Rotated_Sorted_Array_62 {
    public int search(int[] A, int target) {
        // write your code here
        // 要求：一个有序的数组，会在某个点翻转，如0123456,变成4560123,用o(logN)的时间复杂度来求解
        // 思路，二分算法，但在判断时要先确定要啊查找的数据是在正常递增区间内，还是翻转区间内
        // 题目假设没有重复数字
        if (A.length <= 0){
            return -1;
        }
        int low = 0;
        int high = A.length - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (target == A[mid]){
                return mid;
            }
            //正常情况
            if (A[low] <= A[mid] && A[mid] <= A[high]){
                if (target < A[mid]){
                    high = mid - 1;
                }else if (target > A[mid]){
                    low = mid + 1;
                }
            }else if (A[low] > A[mid]){ // 异常情况:翻转点在前半区间
                if (target < A[mid]){   //那么后半段是有序的，如果小于mid，一定在前半区间
                    high = mid - 1;
                }else{
                    if (target <= A[high]){ //否则可能在mid两侧，如果大于mid小于high，则确定在后半段
                        low = mid + 1;
                    }else {
                        high = mid - 1;
                    }
                }
            }else if (A[mid] > A[high]){ //异常情况：翻转点在后半区间
                if (target > A[mid]){   // 那么前半段是有序的，如果target大于mid，则一定在后半段
                    low = mid + 1;
                }else{ // 否则，mid左右都有小于它的点，都有可能存在target，
                    if (target >= A[low]){  //进一步判断是否在前半段有序区，
                        high = mid - 1;
                    }else {
                        low = mid + 1;
                    }
                }
            }
        }
        return  -1;
    }
}


