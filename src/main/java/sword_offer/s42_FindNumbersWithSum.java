package sword_offer;

import javax.el.ArrayELResolver;
import java.util.ArrayList;
// 输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，如果有多对数字的和等于S，
// 输出两个数的乘积最小的。

// 分析：乘积最小，那么两者相差最大，所以维护两个指针，分别从前和从后，如果大于target,就将后面的指针前移，如果小于，就前面的指针后移
public class s42_FindNumbersWithSum {
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {

        ArrayList<Integer> list = new ArrayList<>();
        int low = 0;
        int high = array.length - 1;

        while ( low < high){
            int result = array[low] + array[high];
            if (result == sum){
                list.add(array[low]);
                list.add(array[high]);
                return list;
            }
            if (result < sum){
                low ++;
            }else {
                high --;
            }
        }
        return list;
    }
}
