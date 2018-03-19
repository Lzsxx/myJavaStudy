package LintCode;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

class Search_in_Rotated_Sorted_Array_63 {
    public boolean search(int[] A, int target) {
        // write your code here
        // 要求：一个有序的数组，会在某个点翻转，如0123456,变成4560123,用o(logN)的时间复杂度来求解
        // 思路，二分算法，但在判断时要先确定要啊查找的数据是在正常递增区间内，还是翻转区间内
        // 题目假设有重复数字！！！【跟62不同，62没有重复数字，
        // 分析：本题在有重复数字的情况下，
        if (A.length <= 0){
            return false;
        }
        int low = 0;
        int high = A.length - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (target == A[mid]){
                return true;
            }
            //正常情况
            if (A[low] < A[mid] && A[mid] < A[high]){
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
            }else if (A[low] == A[mid] && low < A.length - 1){  //遇到两头是重复的数字，就一步步往前
                low ++;
            }else if (A[high] == A[mid] && high > 0){   //两头重复，一步步走
                high --;
            }else { //用于只有一个数据时，low\high\mid都指向一个数据，
                // 却又因下标不能越界无法进入if使循环结束，则这里强制结束
                return false;
            }
        }
        return  false;
    }
}


