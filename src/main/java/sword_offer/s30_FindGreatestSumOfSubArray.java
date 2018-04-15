package sword_offer;

import javax.el.ArrayELResolver;

// 求连续子数组的最大和，可能有正数和负数同存
// 思路：用一个max记录全局最大值，用一个currentMax记录当前最大值，
// 为什么会有当前最大值呢？因为当遇到负数时，可能会把前面累积的正数一下子带成负数，此时如果后面还有正数
// 再继续加上去的话，反而会使后面的正数变小，此时就要舍弃前面的负数，直接取currentMax为后面的正数
public class s30_FindGreatestSumOfSubArray {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length < 1){
            return -1;
        }
        int max = Integer.MIN_VALUE;
        int currentMax = 0;
        for (int i = 0; i < array.length; i++) {
            if (currentMax < 0){
                currentMax = array[i];
            }else {
                currentMax += array[i];
            }
            if (currentMax > max){
                max = currentMax;
            }
        }
        return max;
    }

}
