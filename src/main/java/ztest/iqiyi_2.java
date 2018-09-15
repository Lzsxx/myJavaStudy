package ztest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/9/15 11:27
 */
public class iqiyi_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String lineOne = in.nextLine();
        String[] strings = lineOne.split(" ");
        int foodCategory = Integer.parseInt(strings[0]);
        int days = Integer.parseInt(strings[1]);
        int pth = Integer.parseInt(strings[2]);
        int[] foods = new int[foodCategory];
        // 记录现有食物
        String lineTwo = in.nextLine();
        strings = lineTwo.split(" ");
        for (int i = 0; i < foodCategory; i++) {
            foods[i] = Integer.parseInt(strings[i]);
        }
        // 接下来m天的变化
        for (int i = 0; i < days; i++) {
            String line = in.nextLine();
            strings = line.split(" ");
            int index = Integer.parseInt(strings[1]) - 1;
            if ("A".equals(strings[0])) {
                foods[index]++;
            } else if ("B".equals(strings[0])) {
                foods[index]--;
            }
        }

        // 第p份食物此时的值
        int target = foods[pth - 1];
        Arrays.sort(foods);

        int order = 1;
        for (int i = foods.length - 1; i >= 0; i--) {
            // 假设次序一开始为第1，每发现一个比它大的，就+1
            if (target < foods[i]) {
                order++;
            }
        }
        System.out.println(order);
    }
}
