package sword_offer;


// 题目：给出一个排序递增的数组，从某处旋转，找到数组中的最小值
public class s06_minNumberInRotateArray {
    public int minNumberInRotateArray(int [] array) {
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
