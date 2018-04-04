package bishi_template;

import java.util.Scanner;

public class Scanner_nextDouble {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double sum = 0;
        int m = 0;

        while (scan.hasNextDouble()) {
            double x = scan.nextDouble();   // 回车结束，空格可以作为分隔符
            System.out.println("get a number:" + x);
            m = m + 1;
            sum = sum + x;
            System.out.println("m is :" + m);
        }

        System.out.println(m + "个数的和为" + sum);
        System.out.println(m + "个数的平均值是" + (sum / m));
        scan.close();
    }
}
