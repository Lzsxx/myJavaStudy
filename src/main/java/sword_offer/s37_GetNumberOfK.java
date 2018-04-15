package sword_offer;

// 统计一个数字在排序数组中出现的次数。

// 思路：二分查找，找到的话，再依次向前和向后统计
public class s37_GetNumberOfK {
    public static void main(String[] args) {
        int[] arr = {2,3,3,3,5};
        s37_GetNumberOfK test = new s37_GetNumberOfK();
        System.out.println(test.GetNumberOfK(arr, 3));
    }
    public int GetNumberOfK(int [] array , int k) {
        if (array.length < 1){
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (k == array[mid]){
                int tempA = mid;
                int tempB = mid;
                int count = 1;
                while (tempA >= 1 && array[tempA - 1] == k){
                    tempA --;
                    count ++;
                }
                while (tempB + 1 <= array.length - 1 && array[tempB + 1] == k){
                    count ++;
                    tempB ++;
                }
                return count;
            }else if ( k > array[mid]){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return 0;
    }
}
