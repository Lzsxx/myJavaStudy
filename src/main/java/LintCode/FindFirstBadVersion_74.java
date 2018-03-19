package LintCode;

import java.awt.event.HierarchyBoundsAdapter;

class SVNRepo {
    public static boolean isBadVersion(int k){
        return false;
    }
}
public class FindFirstBadVersion_74 {
    /**
     * public class SVNRepo {
     *     public static boolean isBadVersion(int k);
     * }
     * you can use SVNRepo.isBadVersion(k) to judge whether
     * the kth code version is bad or not.
     */

    public int findFirstBadVersion(int n) {
        // write your code here
        // 版本号1~n, 要求找出错误版本号，调用判断越少越好,出错版本是从某个点x开始，一直到n
        // 分析：调用最多的就是顺序查找，一个个调用，要求越少越好，就是二分查找
        int low = 0;   //向前取一位，不包，如果错误版本号在1，则在下面的循环中，high会走到1处停下
        int high = n;   // 取最后一个值，与模板略有不同，因为如果错误版本号是最后一个，而high取n的话，low会走到n处停下，而返回high就没有指向正确的位置
        while ((low + 1) < high){
            int mid = low + (high - low) / 2;
            if (SVNRepo.isBadVersion(mid)){ // mid之后的版本都有错了
                high = mid;
            }else { // mid以及之前的版本都没错
                low = mid;
            }
        }   // 离开循环的条件，是low与high紧紧相连，那么low一定指向正确的版本，high一定指向错误的版本
        if (!SVNRepo.isBadVersion(high)){
            return -1;
        }
        return high;
    }
}
