package ztest;

import java.beans.IntrospectionException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/9/15 11:03
 */
public class iqiyi_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] strings = line.split("");
        int[] frontArr = new int[3];
        int[] behindArr = new int[3];

        int frontSum = 0;
        int behindSum = 0;

        for (int i = 0; i < strings.length; i++) {
            if (i < 3) {
                int temp = Integer.parseInt(strings[i]);
                frontArr[i] = temp;
                frontSum += temp;
            }else {
                int temp = Integer.parseInt(strings[i]);
                behindArr[i - 3] = temp;
                behindSum += temp;
            }
        }
        int minCount = 0;
        int countA = 0;
        int countB = 0;
        int gap = Math.abs(frontSum - behindSum);
        if (frontSum > behindSum) {
            // 调整前面变小,最大的减少
            Arrays.sort(frontArr);
            int tempGap = gap;
            for (int i = 2; i >= 0 ; i--) {
                if (tempGap > 0) {
                    tempGap = tempGap - frontArr[i];
                    countA++;
                }
            }
//            如果出了循环，还是没有消除gap，则说明减少的操作走不通
            if (tempGap > 0) {
                countA = Integer.MAX_VALUE;
            }

            // 调整后面变大，最小的增大
            Arrays.sort(behindArr);
            tempGap = gap;
            for (int i = 0; i < 3 ; i++) {
                if (tempGap > 0) {
                    tempGap = tempGap - (9 - behindArr[i]);
                    countB++;
                }
            }
            if (tempGap > 0) {
                countB = Integer.MAX_VALUE;
            }
            minCount = countA < countB ? countA : countB;

        } else if (frontSum < behindSum) {
            // 调整前面变大,最小的增加
            Arrays.sort(frontArr);
            int tempGap = gap;
            for (int i = 0; i < 3 ; i++) {
                if (tempGap > 0) {
                    tempGap = tempGap - (9 - frontArr[i]);
                    countA++;
                }
            }
//            如果出了循环，还是没有消除gap，则说明减少的操作走不通
            if (tempGap > 0) {
                countA = Integer.MAX_VALUE;
            }

            // 调整后面变小，最大的减小
            Arrays.sort(behindArr);
            tempGap = gap;
            for (int i = 2; i >= 0 ; i--) {
                if (tempGap > 0) {
                    tempGap = tempGap - behindArr[i];
                    countB++;
                }
            }
            if (tempGap > 0) {
                countB = Integer.MAX_VALUE;
            }
            minCount = countA < countB ? countA : countB;
        }
        System.out.println(minCount);
    }
}
