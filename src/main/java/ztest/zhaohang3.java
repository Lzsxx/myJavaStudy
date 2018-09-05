package ztest;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lzs
 * @date 2018/8/30 20:24
 */
public class zhaohang3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String firstLine = in.nextLine();
        String secondLine = in.nextLine();
        double hours = Double.parseDouble(secondLine);

        String[] foodStr = firstLine.split(" ");
        double[] food = new double[foodStr.length];

        double totalFood = 0;
        for (int i = 0; i < food.length; i++) {
            food[i] = Double.parseDouble(foodStr[i]);
            totalFood += food[i];
        }

        Arrays.sort(food);
        double maxSpeed = food[food.length - 1];
        double speed = Math.ceil(totalFood / hours) ;
        while (speed < maxSpeed) {
            if (judge(hours, food, speed)) {
                break;
            }
            speed++;
        }
        System.out.println((int)speed);
    }

    public static boolean judge(double hours, double[] food, double speed) {
        double tempHour = 0;
        int i = 0;
        while (i < food.length && tempHour < hours) {
            if (food[i] <= speed) {
                tempHour += 1; // 吃完一整堆
                i++;
            }else {
                double hour = Math.ceil(food[i] / speed);
                tempHour += hour;
                i++;
            }
        }
        if (tempHour == hours && i == food.length) {
            return true;
        }
        return false;
    }
}
