package test;

import java.util.Scanner;

public class aiqiyi1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
//        String input = "test";
        char[] chs = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        char currMax = 'a' - 1;
        for (int i = chs.length - 1; i >= 0 ; i--) {
            if (chs[i] >= currMax){ //要使字符串尽可能地长，同时又要保证放在前面的内容，一定比放在后面的内容的字典序大
                sb.append(chs[i]);  // 这里先使用加在前面，最后做一个reverse
                currMax = chs[i];
            }
        }
        sb.reverse();
        System.out.println(sb.toString());
    }
}
