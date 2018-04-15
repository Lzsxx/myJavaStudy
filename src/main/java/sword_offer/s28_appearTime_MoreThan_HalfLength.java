package sword_offer;

import com.beust.jcommander.internal.Maps;
import jdk.nashorn.internal.ir.IfNode;

import java.util.HashMap;
import java.util.Map;

// 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
// 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。

// 思路：用map来实现

public class s28_appearTime_MoreThan_HalfLength {
    public static void main(String[] args) {
        int [] arr = {1,2,3,2,2,2,5,4,2};
        s28_appearTime_MoreThan_HalfLength test = new s28_appearTime_MoreThan_HalfLength();
        int result = test.MoreThanHalfNum_Solution(arr);
        System.out.println(result);
    }
    public int MoreThanHalfNum_Solution(int [] array) {
        if (array.length < 1){
            return 0;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int number = array[i];
            if (map.containsKey(number)){
                int count = map.get(number);
                count ++;
                map.put(number, count);
            }else {
                map.put(number,1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            if (entry.getValue() * 2 > array.length){
                return entry.getKey();
            }
        }
        return 0;
    }
}
