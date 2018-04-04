package bishi_template;

import java.util.Scanner;

public class Scanner_nextLine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // nextLine方式接收字符串
        System.out.println("nextLine方式接收：");
        // 判断是否还有输入
        if (scan.hasNextLine()) {
            String str2 = scan.nextLine();  //可以接受中间有空格
            System.out.println("输入的数据为：" + str2);
        }
        scan.close();
    }
}
