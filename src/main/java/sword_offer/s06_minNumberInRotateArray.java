package sword_offer;


// 题目：给出一个排序递增的数组，从某处旋转，找到数组中的最小值
public class s06_minNumberInRotateArray {
    public int minNumberInRotateArray(int [] array) {
        if (array.length <= 0) {
            return 0;
        }
        int low = 0;
        int high = array.length - 1;
        while (low + 1 <  high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > array[high]) {
                // 在下半段
                low = mid;
            } else{
                // 在上半段
                high = mid;
            }
        }
        return array[low] < array[high] ? array[low] : array[high];
    }
    public int minNumberInRotateArray2(int [] array) {
        if (array.length < 1){
            return 0;
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]){
                return array[i];
            }
        }
        return 0;
    }
}
