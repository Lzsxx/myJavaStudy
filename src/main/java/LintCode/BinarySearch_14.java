package LintCode;

import java.util.logging.Level;

class BinarySearch {
    public int binarySearch(int[] nums, int target) {
        // write your code here
        /***** 二分查找，要求返回第一个出现的值，所以如果mid命中目标，还要继续往前探测，看是否有重复值 *****/
        int low = 0;
        int high = nums.length - 1;
        int mid ;
        int index;
        while (low <= high){
            mid = low + (high - low) / 2;
            if ( nums[mid] < target){
                low = mid + 1;
            }else if (nums[mid] > target){
                high = mid - 1;
            }else if (nums[mid] == target){
                while (mid > 0 && nums[mid - 1] == target){
                    mid --;
                }
                return mid;
            }
        }
        return -1;
    }
}
