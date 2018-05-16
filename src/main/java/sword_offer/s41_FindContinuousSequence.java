package sword_offer;

import javax.persistence.metamodel.MapAttribute;
import java.util.ArrayList;
import java.util.Collections;
// 输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
// 思路：采用数学方法来做；要求的和是sum ,根据等差数列求和公式，s = (n + 1)* n / 2；
// 可得n的长度必须小于Math.sqrt(s)，然后分析这n个数
// 当n为奇数时，如果这连续n个数能够满足sum，则平均值一定是最中间那个数，所以要满足 n为奇数 && sum % n == 0
// 当n为偶数时，如果连续n个能够满足sum,则需要满足中间两个值的平均数与sum / n（带小数）一样，都是0.5
//      在这种情况下，中间两个数平均值 == sum / n （double型带小数），分别向外延伸一层，次中间的两个数求平均值
//      也应该是0.5，那么就可以将这n个偶数分割成两组，每一组的平均值都是 == sum / n; 这样的组数应该有 n / 2 个
// 如何表示这种关系？
//      满足这种关系的，每一组相加再除以2，组里每个数字都会与总和的平均值相差0.5，那么一组两个数字带来的差值就是1，如果是
// 长度为n的一串数，有 n / 2组，那么带来的差值就是 n/2；所有组的这些差值的总和可以用sum % n 来表示，
// 相当于 sum % n == n / 2

public class s41_FindContinuousSequence {
    public static void main(String[] args) {
        s41_FindContinuousSequence test = new s41_FindContinuousSequence();
        test.FindContinuousSequence(9);
    }

    // 临场发挥版
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer> > lists = new ArrayList<>();
        if (sum <= 1) {
            return lists;
        }
        int n = (int) Math.sqrt(2 * sum);
        n = n > 2 ? n : 2;  // 确保n大于等于2
        for (int i = n; i >= 2 ; i--) {
            int avg = sum / i;  // 如果可能，平均数应为avg
            if ((i & 1) == 0) { // 如果 i 是偶数
                if ((avg + 0.5) * i == sum) {   // 这种情况下，以这个平均数类推可以得到正确的总数
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(avg);
                    list.add(avg + 1);
                    for (int j = 1; j < i / 2; j++) {
                        list.add(avg - j);
                        list.add(avg + 1 + j);
                    }
                    Collections.sort(list);
                    lists.add(list);
                }
            }else {     // 如果 i 是奇数
                if (avg * i == sum) {   //此时类推可以得到正确的sum
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(avg);
                    for (int j = 1; j <= i / 2; j++) {
                        list.add(avg - j);
                        list.add(avg + j);
                    }
                    Collections.sort(list);
                    lists.add(list);
                }
            }

        }
        return lists;
    }


//    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
//        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
//        ArrayList<Integer> list = null;
//        int n = (int) Math.sqrt(2 * sum);   // 长度最多遍历到n
//        for (int length = n; length >= 2; length--) {  //长度最少从2开始
//            // 如果是奇数，直接用 sum % n 来判断
//            if ( (length & 1 ) == 1 && sum % length == 0 ){
//                list = new ArrayList<>();
//                int avg = sum / length;
//                int times = length / 2; // 取数字时向外延伸几圈
//                for (int i = times; i >= 1; i--) {
//                    list.add(avg - i);
//                }
//                list.add(avg);
//                for (int i = 1; i <= times; i++) {
//                    list.add(avg + i);
//                }
//                arrayLists.add(list);
//            }
//            // 如果是偶数，
//            if ( ((length & 1 ) == 0 ) && ((sum % length) * 2 == length)){
//                list = new ArrayList<>();
//                int avg = sum / length; // 这里取值的时候会偏左，所以后面要少加一个
//                int times = length / 2; // 取数字时向外延伸几圈
//                for (int i = times-1; i >= 1; i--) {   // 注意，这里少一圈
//                    list.add(avg - i);
//                }
//                list.add(avg);
//                for (int i = 1; i <= times; i++) {
//                    list.add(avg + i);
//                }
//                arrayLists.add(list);
//            }
//        }
//        return arrayLists;
//    }

}
