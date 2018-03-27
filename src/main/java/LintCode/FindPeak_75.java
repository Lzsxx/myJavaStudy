package LintCode;

public class FindPeak_75 {
//    你给出一个整数数组(size为n)，其具有以下特点：
//      1.相邻位置的数字是不同的
//      2.A[0] < A[1] 并且 A[n - 2] > A[n - 1]
//      3.假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
//    注意事项
    //    It's guaranteed the array has at least one peak.
    //    The array may contain multiple peeks, find any of them.
    //    The array has at least 3 numbers in it.
    public int findPeak(int[] A) {
        // write your code here
        int low = 0;
        int high = A.length - 1;
        while ((low + 1) < high){
            int mid = low + (high - low) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]){
                return mid;
            }else if (A[mid] < A[mid - 1]){ //一旦出现mid小于后者，而mid又不是峰值，则一定有峰值在mid左侧
                high = mid;
            }else {
                low = mid;
            }
        }
        return -1;
    }
}
