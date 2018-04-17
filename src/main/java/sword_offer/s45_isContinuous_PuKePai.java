package sword_offer;

//随机从中抽出了5张牌,看看能不能抽到顺子,大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
// 为了方便起见,你可以认为大小王是0。

// 思路：就是先将数组排序，然后计算两两之间的gap，如果遇到相同的数，直接false，如果遇到0，count+1,
// 最后来比较0和gap的个数，如果0的个数大于等于gap数，就能组成顺子


import java.lang.reflect.Array;
import java.util.Arrays;

public class s45_isContinuous_PuKePai {
    public static void main(String[] args) {
        s45_isContinuous_PuKePai test = new s45_isContinuous_PuKePai();
        int[] num = {0, 3, 2, 6, 4};
        boolean result = test.isContinuous(num);
        System.out.println(result);
    }
    public boolean isContinuous(int [] numbers) {
        if (numbers.length < 1){
            return false;
        }
        // 排序
        Arrays.sort(numbers);
        int gap = 0;
        int zeroCount = 0;
        if ( numbers[0] == 0){
            zeroCount ++;
        }
        // 计算Gap
        for (int i = 1; i < numbers.length; i++) {

            if (numbers[i] == 0){
                zeroCount ++;
                continue;
            }
            if (numbers[i - 1] == 0){
                continue;
            }
            int temp = numbers[i] - numbers[i - 1];
            // 相等直接false
            if ( temp == 0){
                return false;
            }
            gap += temp - 1;
        }
        if (zeroCount >= gap){
            return true;
        }
        return false;
    }
}
