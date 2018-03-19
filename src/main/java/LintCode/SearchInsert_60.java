package LintCode;

 class SearchInsert {
//     public int searchInsert(int[] A, int target) {
//         // write your code here
//         // 要求：给定一个排序数组，无重复，给一个目标值，要求返回目标值应该插入的位置，时间复杂度为o(logN)
//         // 思路：二分查找
//         int low = 0;
//         int high = A.length - 1;
//
//         while (low <= high){
//             int mid = low + (high - low) / 2;
//             if (target > A[mid]){
//                 low = mid + 1;
//             }else if (target < A[mid]){
//                 high = mid - 1;
//             }else {
//                 return mid;
//             }
//         }
//         return low;
//     }


     /***** 编程珠玑版本 *****/
     public int searchInsert(int[] A, int target) {
         // write your code here
         // 要求：给定一个排序数组，无重复，给一个目标值，要求返回目标值应该插入的位置，时间复杂度为o(logN)
         // 思路：二分查找
         int low = -1;
         int high = A.length;

         while ((low + 1) != high){
             int mid = low + (high - low) / 2;
             if ( A[mid] < target){
                 low = mid;
             }else {
                 high = mid;
             }
         }
//         if (high == A.length || A[high] != target){
//             return high;
//         }
         return high;
     }
}
