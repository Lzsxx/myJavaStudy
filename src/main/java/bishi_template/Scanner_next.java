package bishi_template;

import java.util.Scanner;

public class Scanner_next {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // next方式接收字符串
        System.out.println("next方式接收：");
        // 判断是否还有输入
        if (scan.hasNext()) {
            String str1 = scan.next();  // 前有空格忽略，后又空格，空格及其之后被忽略
            System.out.println("输入的数据为：" + str1);
        }
        scan.close();
    }
}
